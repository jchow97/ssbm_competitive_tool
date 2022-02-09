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
        testPlayer1 = new Player("Barry", "Fox", "N/A");
        testPlayer2 = new Player("Allen", "Peach", "Marth");
    }

    @Test
    void testConstructor() {
        assertEquals("Barry", testPlayer1.getName());
        ArrayList<String> player1MainChars = testPlayer1.getMainChars();
        assertEquals(1, player1MainChars.size());
        assertEquals("Fox", player1MainChars.get(0));

        assertEquals("Allen", testPlayer2.getName());
        ArrayList<String> player2MainChars = testPlayer2.getMainChars();
        assertEquals(2, player1MainChars.size());
        assertEquals("Peach", player1MainChars.get(0));
        assertEquals("Marth", player1MainChars.get(1));
    }
}