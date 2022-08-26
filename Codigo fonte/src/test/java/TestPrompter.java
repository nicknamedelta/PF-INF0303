import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Field;

@RunWith(MockitoJUnitRunner.class)
public class TestPrompter {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final ByteArrayOutputStream errContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final PrintStream originalErr = System.err;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
        System.setErr(new PrintStream(errContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
        System.setErr(originalErr);
    }

    @Test(timeout = 200)
    public void testDisplayProgress() {
        Game game = new Game("treehouse");
        Prompter prompter = new Prompter(game);
        prompter.displayProgress();
        assertEquals("You have 6 Tries to solve this: ---------\n", outContent.toString());

    }
    @Test(timeout = 200)
    public void testPromptForGuess() {
        InputStream sysInBackup = System.in; // 'My string' is just to move on test
        ByteArrayInputStream in = new ByteArrayInputStream("My string".getBytes());
        System.setIn(in);
        Game game = new Game("treehouse");
        Prompter prompter = new Prompter(game);
        prompter.promptForGuess();
        assertEquals("Enter a character: \n", outContent.toString().replaceAll("\r", ""));
        System.setIn(sysInBackup);
    }
    @Test(timeout = 200)
    public void testPromptForGuessTryAgainWithNoLetters() {
        InputStream sysInBackup = System.in; //
        ByteArrayInputStream in = new ByteArrayInputStream("\na".getBytes());
        System.setIn(in);
        Game game = new Game("treehouse");
        Prompter prompter = new Prompter(game);
        prompter.promptForGuess();
        String response = "Enter a character: \n" +
                "No Letter FoundPls try again\n" +
                "\n" +
                "Enter a character: \n";
        assertEquals(response, outContent.toString().replaceAll("\r", ""));
        System.setIn(sysInBackup);
    }

    @Test(timeout = 200)
    public void testPlay() {
        InputStream sysInBackup = System.in; //
        ByteArrayInputStream in = new ByteArrayInputStream("\ne".getBytes());
        System.setIn(in);
        Game game = new Game("eee");
        Prompter prompter = new Prompter(game);
        prompter.play();
        String response = "You have 6 Tries to solve this: ---\n" +
                "Enter a character: \n" +
                "No Letter FoundPls try again\n" +
                "\n" +
                "Enter a character: \n" +
                "eee\n" +
                "Congralutions you have won with 6 tries left\n";
        assertEquals(response, outContent.toString().replaceAll("\r", ""));
    }

    @Test(timeout = 200)
    public void testPlay0RemaingTries(){
        InputStream sysInBackup = System.in; //
        ByteArrayInputStream in = new ByteArrayInputStream("\ne".getBytes());
        System.setIn(in);
        try {
            Field reader = Game.class.getDeclaredField("mMisses");
            reader.setAccessible(true);
            Game game = new Game("eee");
            reader.set(game, "eeeeeee");
            Prompter prompter = new Prompter(game);
            prompter.play();
            String response = "Bummer the word was eee :(\n";
            assertEquals(response, outContent.toString().replaceAll("\r", ""));

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}

