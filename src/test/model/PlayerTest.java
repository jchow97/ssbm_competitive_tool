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
        assertEquals(1, player1MainChars.length);
    }
}