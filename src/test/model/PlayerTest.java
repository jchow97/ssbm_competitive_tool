package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {
    private Player testPlayer1;
    private Player testPlayer2;

    @BeforeEach
    void runBefore() {
        testPlayer1 = new Player("Barry", "Fox", "N/A", 1);
        testPlayer2 = new Player("Allen", "Peach", "Marth", 2);
    }

    @Test
    void testConstructor() {
        assertEquals("Barry", testPlayer1.getName());
        ArrayList<String> player1MainChars = testPlayer1.getMainChars();
        assertEquals(0, testPlayer1.getWins());
        assertEquals(0, testPlayer1.getLosses());
        assertEquals(0, testPlayer1.getWinRate());
        assertEquals(1, player1MainChars.size());
        assertEquals("Fox", player1MainChars.get(0));
        assertEquals(-1, testPlayer1.getRank());
        assertEquals(0, testPlayer1.getTournamentWins());


        assertEquals("Allen", testPlayer2.getName());
        ArrayList<String> player2MainChars = testPlayer2.getMainChars();
        assertEquals(0, testPlayer2.getWins());
        assertEquals(0, testPlayer2.getLosses());
        assertEquals(0, testPlayer2.getWinRate());
        assertEquals(2, player2MainChars.size());
        assertEquals("Peach", player2MainChars.get(0));
        assertEquals("Marth", player2MainChars.get(1));
        assertEquals(-1, testPlayer2.getRank());
        assertEquals(0, testPlayer2.getTournamentWins());
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
        assertEquals(12, testPlayer1.getWins());
    }

    @Test
    void testSetLosses() {
        testPlayer1.setLosses(10);
        assertEquals(10, testPlayer1.getLosses());
        testPlayer1.setLosses(2);
        assertEquals(12, testPlayer1.getLosses());
    }

//    @Test
//    void testSetRank() {
//        testPlayer1.setRank(1);
//        assertEquals(1, testPlayer1.getRank());
//
//        testPlayer2.setRank(3);
//        assertEquals(3, testPlayer2.getRank());
//    }

    //testing set wins and set losses changes on win rate.
    //!!! might be future changes to updateWinRate depending on implementation.
    @Test
    void testWinRateChanges() {
        testPlayer1.setWins(6);
        assertEquals(1.00, testPlayer1.getWinRate());
        testPlayer2.setLosses(4);
        assertEquals(0.60, testPlayer1.getWinRate());
    }

}