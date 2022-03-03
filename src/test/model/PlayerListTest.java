//package model;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//
//import java.util.ArrayList;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class PlayerListTest {
//
//    private ArrayList<Player> testPlayerList;
//
//    private Player testPlayer1;
//    private Player testPlayer2;
//    private Player testPlayer3;
//    private Player testPlayer4;
//    private Player testPlayer5;
//    private Player testPlayer6;
//    private Player testPlayer7;
//    private Player testPlayer8;
//
//    @BeforeEach
//    void runBefore() {
//        testPlayerList = new ArrayList<>;
//        testPlayer1 = testPlayerList.get(0);
//        testPlayer2 = testPlayerList.get(1);
//        testPlayer3 = testPlayerList.get(2);
//        testPlayer4 = testPlayerList.get(3);
//        testPlayer5 = testPlayerList.get(4);
//        testPlayer6 = testPlayerList.get(5);
//        testPlayer7 = testPlayerList.get(6);
//        testPlayer8 = testPlayerList.get(7);
//    }
//
//    @Test
//    void testConstructor() {
//        assertEquals("Mang0", testPlayer1.getName());
//        assertEquals("Zain", testPlayer2.getName());
//        assertEquals("Plup", testPlayer3.getName());
//        assertEquals("iBDW", testPlayer4.getName());
//        assertEquals("Wizzrobe", testPlayer5.getName());
//        assertEquals("SFAT", testPlayer6.getName());
//        assertEquals("Hungrybox", testPlayer7.getName());
//        assertEquals("Leffen", testPlayer8.getName());
//    }
//
//
//    @Test
//    void testAddPlayer() {
//        Player testPlayer9 = new Player("Barry", "Captain Falcon", "N/A", 9);
//        assertTrue(testPlayerList.add(testPlayer9));
//        assertEquals(9, testPlayerList.size());
//
//        Player player = testPlayerList.get(8);
//        assertEquals("Barry", player.getName());
//    }
//
//    @Test
//    void testGetPlayerList() {
//        ArrayList <Player> expectedList = new ArrayList<>();
//        expectedList.add(testPlayer1);
//        expectedList.add(testPlayer2);
//        expectedList.add(testPlayer3);
//        expectedList.add(testPlayer4);
//        expectedList.add(testPlayer5);
//        expectedList.add(testPlayer6);
//        expectedList.add(testPlayer7);
//        expectedList.add(testPlayer8);
//        assertEquals(expectedList, testPlayerList);
//    }
//}
