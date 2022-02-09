package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchTest {
    //TODO
    private Match testMatch1;
    private Player testPlayer1;
    private Player testPlayer2;

    private Match testMatch2;
    private Player testPlayer3;
    private Player testPlayer4;

    @BeforeEach
    void runBefore() {
        testPlayer1 = new Player("Barry", "Fox", "N/A", 1);
        testPlayer2 = new Player("Allen", "Peach", "Marth", 4);
        testMatch1 = new Match(testPlayer1, "Fox", testPlayer2, "Peach");

        testPlayer3 = new Player("Wally", "Falco", "N/A", 2);
        testPlayer4 = new Player("West", "Sheik", "N/A", 3);
        testMatch2 = new Match(testPlayer3, "Falco", testPlayer4, "Sheik");
    }

    @Test
    void testConstructor() {
        assertEquals(testPlayer1, testMatch1.getPlayerOne());
        assertEquals("Fox", testMatch1.getPlayerOneCharacter());
        assertEquals(testPlayer2, testMatch1.getPlayerTwo());
        assertEquals("Peach", testMatch1.getPlayerTwoCharacter());
        assertEquals("", testMatch1.getWinner());

        assertEquals(testPlayer3, testMatch2.getPlayerOne());
        assertEquals("Falco", testMatch2.getPlayerOneCharacter());
        assertEquals(testPlayer4, testMatch2.getPlayerTwo());
        assertEquals("Sheik", testMatch2.getPlayerTwoCharacter());
        assertEquals("", testMatch2.getWinner());
    }

    @Test
    // might have to update this because expected result is a getter that isn't th
    void testDeclareWinner() {
        //adds if given string is one of the players
        testMatch1.declareWinner("Barry");
        assertEquals(testPlayer1.getName(), testMatch1.getWinner());

        //does not add if given string is not one of the players
        testMatch2.declareWinner("Bart");
        assertEquals("", testMatch2.getWinner());
    }

    @Test
    void testRecordMatch() {
        // changed winner to indirectly test player 2 winning.
        testMatch1.declareWinner("Allen");
        testMatch1.recordMatch();
        assertEquals(0, testPlayer1.getWins());
        assertEquals(1, testPlayer1.getLosses());
        assertEquals(1, testPlayer1.getWins());
        assertEquals(0, testPlayer2.getLosses());
    }
}
