
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.NoSuchElementException;

public class TestHangman {
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
    @Test(expected = NoSuchElementException.class)
    public void testRunMainWithOneArgument() {
        String[] args = {"treehouse"};
        InputStream sysInBackup = System.in; // 'My string' is just to move on test
        ByteArrayInputStream in = new ByteArrayInputStream("no lines, just run the game plz".getBytes());
        System.setIn(in);
        hangman.main(args);
    }
}
