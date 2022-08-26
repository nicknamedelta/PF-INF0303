import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class TestGameSolve {
    private Game g;

    private char[] try_word;
    private boolean expected;
    private boolean exception;

    public static char[][] try_words = {
            {'b', 'n', 'e', 'i', 'j', 'a'},
            {'e', 'q', 'i', 'r', 't', 'y'},
            {'r', 'e', 'd', 'b', 'a', 'n'},
            {'m', 'e', 'l', 'a', 'n', 'c', 'i'},
            {'r', 'u', 'd', 'b', 'q', '1'},
            {'r', 'u', 'd', 'b', 'q', ' '},
            {'r', 'u', 'd', 'b', 'q', 'q'},
    };

    @Parameterized.Parameters
    public static Collection<Object[]> data() {
        return Arrays.asList(new Object[][]{
                {"banana", try_words[0], true, false},
                {"banana", try_words[1], false, false},
                {"banana", try_words[2], true, false},
                {"melancia", try_words[3], true, false},
                {"melancia", try_words[4], false, true}, //give a number instead of letter
                {"melancia", try_words[5], false, true}, //give empty space instead of letter
                {"melancia", try_words[6], false, true}, //give repeated letter
        });
    }

    public TestGameSolve(String palavra, char[] try_word, boolean expected, boolean exception) {
        this.g = new Game(palavra);
        this.try_word = try_word;
        this.expected = expected;
        this.exception = exception;
    }

    @Test(timeout = 200)
    public void validate() {
        if (exception) {
            try {
                for (char c : try_word) {
                    g.guess(c);
                }
            } catch (IllegalArgumentException e) {
                return;
            }
            throw new IllegalArgumentException("Expected an IllegalArgumentException to be thrown");
        } else {
            for (char c : try_word) {
                g.guess(c);
            }
            assertEquals(expected, g.isSolved());
        }
    }
}
