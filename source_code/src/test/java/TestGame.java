import org.junit.Test;

import static org.junit.Assert.*;
public class TestGame {

    @Test
    public void testGetAnswer() {
        Game game = new Game("treehouse");
        assertEquals("treehouse", game.getAnswer());
    }

    @Test
    public void testGetRemainingTries() {
        Game game = new Game("treehouse");
        assertEquals(6, game.getRemainingTries());
    }

    @Test
    public void testGetCurrentProgress() {
        Game game = new Game("treehouse");
        assertEquals("---------", game.getCurrentProgress());
    }

    @Test
    public void testIsSolved() {
        Game game = new Game("treehouse");
        assertFalse(game.isSolved());
    }

    @Test
    public void testIsSolvedTrue() {
        Game game = new Game("treehouse");
        game.guess('t');
        game.guess('r');
        game.guess('e');
        game.guess('h');
        game.guess('o');
        game.guess('u');
        game.guess('s');
        assertTrue(game.isSolved());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRepeatableLetter() {
        Game game = new Game("treehouse");
        game.guess('t');
        game.guess('r');
        game.guess('e');
        game.guess('h');
        game.guess('o');
        game.guess('u');
        game.guess('s');
        game.guess('e');
        fail("Expected an IllegalArgumentException to be thrown");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCharacterNotALetter() {
        Game game = new Game("treehouse");
        game.guess('t');
        game.guess('r');
        game.guess('e');
        game.guess('h');
        game.guess('o');
        game.guess('u');
        game.guess('s');
        game.guess('1');
        fail("Expected an IllegalArgumentException to be thrown");
    }

}
