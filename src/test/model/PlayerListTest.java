package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerListTest {

    private PlayerList testPlayerList;

    private Player testPlayer1;
    private Player testPlayer2;
    private Player testPlayer3;
    private Player testPlayer4;
    private Player testPlayer5;
    private Player testPlayer6;
    private Player testPlayer7;
    private Player testPlayer8;

    @BeforeEach
    void runBefore() {
        testPlayerList = new PlayerList();
        ArrayList<Player> list = testPlayerList.getPlayerList();
        testPlayer1 = list.get(0);
        testPlayer2 = list.get(1);
        testPlayer3 = list.get(2);
        testPlayer4 = list.get(3);
        testPlayer5 = list.get(4);
        testPlayer6 = list.get(5);
        testPlayer7 = list.get(6);
        testPlayer8 = list.get(7);
    }

    @Test
    void testConstructor() {
        assertEquals("Mang0", testPlayer1.getName());
        assertEquals("Zain", testPlayer2.getName());
        assertEquals("Plup", testPlayer3.getName());
        assertEquals("iBDW", testPlayer4.getName());
        assertEquals("Wizzrobe", testPlayer5.getName());
        assertEquals("SFAT", testPlayer6.getName());
        assertEquals("Hungrybox", testPlayer7.getName());
        assertEquals("Leffen", testPlayer8.getName());

        //        ArrayList<String> expectedList;
//        expectedList.add("Mang0");
//        expectedList.add("Zain");
//        expectedList.add("Plup");
//        expectedList.add("iBDW");
//        expectedList.add("Wizzrobe");
//        expectedList.add("SFAT");
//        expectedList.add("Hungrybox");
//        expectedList.add("Leffen");
//
//        for (Player player : list) {
//
//        }
    }


    @Test
    void testAddPlayer() {
        Player testPlayer9 = new Player("Barry", "Captain Falcon", "N/A", 9);
        assertTrue(testPlayerList.addPlayer(testPlayer9));

        ArrayList<Player> list = testPlayerList.getPlayerList();
        Player player = list.get(8);
        assertEquals("Barry", player.getName());
    }
}
