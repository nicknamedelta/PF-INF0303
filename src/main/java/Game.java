public class Game {
	public static final int MAX_MISSES=6;
	private String mAnswer;
	private String mHits;
	private String mMisses;

	public Game (String answer){
		mAnswer = answer;
		mHits="";
		mMisses="";
	}

	private char validateGuess(char letter){
		if(!Character.isLetter(letter)){
			throw new IllegalArgumentException ("Input A Letter");
		}

		letter=Character.toLowerCase(letter);

		if(mMisses.indexOf(letter) >=0 || mHits.indexOf(letter) >=0){
			throw new IllegalArgumentException (letter +" Already been Guessed!\n");
		}

		return letter;
	}

	public boolean guess (String s){ //Taking a String
		if(s.length() == 0){
			throw new IllegalArgumentException ("No Letter Found");
		}
		return guess(s.charAt(0));
	}

	public boolean guess(char s){ //Taking  a Character
		s= validateGuess(s);
		boolean isHit= mAnswer.indexOf(s) >=0;
		if(isHit){
			mHits+=s;
		} else {
			mMisses+=s;
		}

		return isHit;
	}

	public String getCurrentProgress(){
		String progress="";

		for(char letter : mAnswer.toCharArray()){
			char display='-';
			if(mHits.indexOf(letter)>=0){
				display=letter;
			}
			progress+=display;
		}

		return progress;
	}

	public int getRemainingTries(){
		return (MAX_MISSES - mMisses.length());
	}

	public boolean isSolved(){
		return (getCurrentProgress().indexOf('-') == -1);
	}

	public String getAnswer(){
		return mAnswer;
	}
}