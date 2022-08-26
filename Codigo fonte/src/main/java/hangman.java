/*
	Created by: Ujjwal Roy
	Date : 07-09-2015
	Hangman Game
*/

public class hangman {

	public static void main(String[] args ){
		//Amazing code goes here:

		if(args.length  ==0) {
			System.out.println("No word found..");
			System.exit(0);
		}
		Game g = new Game(args[0]);
		Prompter prompter = new Prompter(g);
		prompter.play();
	}
}