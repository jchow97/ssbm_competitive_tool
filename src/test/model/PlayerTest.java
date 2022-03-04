package model;

import model.exception.GameCharacterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player testPlayer1;
    private Player testPlayer2;
    private Player testPlayer3;
    private ArrayList<GameCharacter> testCharacters;
    private GameCharacter fox;

    @BeforeEach
    void runBefore() {
        testPlayer1 = new Player("Barry");

        testCharacters = new ArrayList<>();
        try {
            fox = new GameCharacter("Fox");
            testCharacters.add(fox);
        } catch (GameCharacterException e) {
            fail("Exception should not have been thrown");
        }


        testPlayer2 = new Player("Player", 1, 2, 1 / 3,
                testCharacters, 15, 3);
        testPlayer3 = new Player("Player", 1, 2, testCharacters, 15, 3);
    }

    @Test
    void testConstructor() {
        assertEquals("Barry", testPlayer1.getName());
        ArrayList<GameCharacter> player1MainChars = testPlayer1.getMainChars();
        assertEquals(0, testPlayer1.getWins());
        assertEquals(0, testPlayer1.getLosses());
        assertEquals(0, testPlayer1.getWinRate());
        assertEquals(0, player1MainChars.size());
        assertEquals(-1, testPlayer1.getRank());
        assertEquals(0, testPlayer1.getTournamentWins());
    }

    @Test
    void testSecondConstructor() {
        assertEquals(1, testPlayer2.getWins());
        assertEquals(2, testPlayer2.getLosses());
        assertEquals(0.33, testPlayer3.getWinRate(), 0.01);
        assertEquals(1, testPlayer2.getMainChars().size());
        assertEquals(15, testPlayer2.getRank());
        assertEquals(3, testPlayer2.getTournamentWins());
    }

    @Test
    void testThirdConstructor() {
        assertEquals(1, testPlayer3.getWins());
        assertEquals(2, testPlayer3.getLosses());
        assertEquals(0.33, testPlayer3.getWinRate(), 0.01);
        assertEquals(1, testPlayer3.getMainChars().size());
        assertEquals(15, testPlayer3.getRank());
        assertEquals(3, testPlayer3.getTournamentWins());
    }

    @Test
    //adding wins incrementally, observing appropriate changes to win rate.
    void testAddWin() {
        testPlayer1.addWin();
        assertEquals(1, testPlayer1.getWins());
        assertEquals(1.00, testPlayer1.getWinRate()); //might have to reconsider float interaction

        testPlayer1.addWin();
        assertEquals(2, testPlayer1.getWins());
        assertEquals(1.00, testPlayer1.getWinRate()); //might have to reconsider float interaction
    }

    @Test
    //adding losses incrementally, observing appropriate changes to win rate.
    void testAddLoss() {
        testPlayer1.addLoss();
        assertEquals(1, testPlayer1.getLosses());
        assertEquals(0.00, testPlayer1.getWinRate()); //might have to reconsider float interaction

        testPlayer1.addLoss();
        assertEquals(2, testPlayer1.getLosses());
        assertEquals(0.00, testPlayer1.getWinRate()); //might have to reconsider float interaction
    }

    //testing set wins and set losses
    @Test
    void testSetWins() {
        testPlayer1.setWins(10);
        assertEquals(10, testPlayer1.getWins());
        testPlayer1.setWins(2);
        assertEquals(2, testPlayer1.getWins());
    }

    @Test
    void testSetLosses() {
        testPlayer1.setLosses(10);
        assertEquals(10, testPlayer1.getLosses());
        testPlayer1.setLosses(2);
        assertEquals(2, testPlayer1.getLosses());
    }

    @Test
    void testSetRank() {
        testPlayer1.setRank(1);
        assertEquals(1, testPlayer1.getRank());

        testPlayer2.setRank(3);
        assertEquals(3, testPlayer2.getRank());
    }

    @Test
    void testSetTournamentWins() {
        testPlayer1.setTournamentWins(1);
        assertEquals(1, testPlayer1.getTournamentWins());

        testPlayer2.setTournamentWins(3);
        assertEquals(3, testPlayer2.getTournamentWins());
    }

    @Test
    void testAddMainCharacter() {
        testPlayer1.addMainCharacter(fox);
        assertEquals("Fox", testPlayer1.getMainChars().get(0).getName());
    }

    //testing set wins and set losses changes on win rate.
    //!!! might be future changes to updateWinRate depending on implementation.
    @Test
    void testWinRateChanges() {
        testPlayer1.setWins(6);
        assertEquals(1.00, testPlayer1.getWinRate());

        testPlayer1.setLosses(4);
        assertEquals(0.60, testPlayer1.getWinRate());
    }

}