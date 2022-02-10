package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TournamentTest {
    //TODO look at the lab4 import of static variables to try to get rid of this block below

    Tournament testTournament;

//    private Player mango = new Player("Mang0", "Falco", "Fox", 1);
//    private Player zain = new Player("Zain", "Marth", "Roy", 2);
//    private Player plup = new Player("Plup", "Sheik", "Fox", 3);
//    private Player ibdw = new Player("iBDW", "Fox", "N/A", 4);
//    private Player wizzrobe = new Player("Wizzrobe", "Captain Falcon", "N/A", 5);
//    private Player sfat = new Player("SFAT", "Fox", "N/A", 6);
//    private Player hbox = new Player("Hungrybox", "Jigglypuff", "N/A", 7);
//    private Player leffen = new Player("Leffen", "Fox", "Sheik", 8);

    private Match quarterfinal1;
    private Match quarterfinal2;
    private Match quarterfinal3;
    private Match quarterfinal4;
    private Match semiFinal1;
    private Match semiFinal2;
    private Match grandFinal;
    private Player testPlayer;
    private Player testWinner;

    @BeforeEach
    void runBefore() {
        testTournament = new Tournament();
        quarterfinal1 = testTournament.getQuarterfinalMatch1();
        quarterfinal2 = testTournament.getQuarterfinalMatch2();
        quarterfinal3 = testTournament.getQuarterfinalMatch3();
        quarterfinal4 = testTournament.getQuarterfinalMatch4();
        quarterfinal1.declareWinner("Mang0");
        quarterfinal2.declareWinner("Zain");
        quarterfinal3.declareWinner("Plup");
        quarterfinal4.declareWinner("Wizzrobe");
    }

    @Test
    void testConstructor() {
        testPlayer = quarterfinal1.getPlayerOne();
        assertEquals("Mang0", testPlayer.getName());
        assertEquals("Falco", quarterfinal1.getPlayerOneCharacter());
        testPlayer = quarterfinal1.getPlayerTwo();
        assertEquals("Leffen", testPlayer.getName());
        assertEquals("Fox", quarterfinal1.getPlayerTwoCharacter());

        testPlayer = quarterfinal2.getPlayerOne();
        assertEquals("Zain", testPlayer.getName());
        testPlayer = quarterfinal2.getPlayerTwo();
        assertEquals("Hungrybox", testPlayer.getName());

        testPlayer = quarterfinal3.getPlayerOne();
        assertEquals("Plup", testPlayer.getName());

        testPlayer = quarterfinal4.getPlayerOne();
        assertEquals("iBDW", testPlayer.getName());

        assertEquals(null, testTournament.getWinner());
    }

//    @Test
//    void testSetNextRound() {
//    }

    @Test
    void testSetSemifinal() {
        testTournament.setSemifinalRound();
        semiFinal1 = testTournament.getSemifinalMatch1();
        semiFinal2 = testTournament.getSemifinalMatch2();

        testPlayer = semiFinal1.getPlayerOne();
        assertEquals("Mang0", testPlayer.getName());
        assertEquals("Falco", semiFinal1.getPlayerOneCharacter());
        testPlayer = semiFinal1.getPlayerTwo();
        assertEquals("Wizzrobe", testPlayer.getName());
        assertEquals("Captain Falcon", semiFinal1.getPlayerTwoCharacter());

        testPlayer = semiFinal2.getPlayerOne();
        assertEquals("Zain", testPlayer.getName());
        assertEquals("Marth", semiFinal2.getPlayerOneCharacter());
        testPlayer = semiFinal2.getPlayerTwo();
        assertEquals("Plup", testPlayer.getName());
        assertEquals("Sheik", semiFinal2.getPlayerTwoCharacter());
    }

    @Test
    void testSetGrandFinal() {
        testTournament.setSemifinalRound();
        semiFinal1 = testTournament.getSemifinalMatch1();
        semiFinal2 = testTournament.getSemifinalMatch2();

        semiFinal1.declareWinner("Mang0");
        semiFinal2.declareWinner("Plup");

        testTournament.setGrandFinalRound();

        grandFinal = testTournament.getGrandFinalMatch();

        testPlayer = grandFinal.getPlayerOne();
        assertEquals("Mang0", testPlayer.getName());
        assertEquals("Falco", grandFinal.getPlayerOneCharacter());
        testPlayer = grandFinal.getPlayerTwo();
        assertEquals("Plup", testPlayer.getName());
        assertEquals("Sheik", grandFinal.getPlayerTwoCharacter());
    }

    @Test
    void testDeclareWinner() {
        testTournament.setSemifinalRound();
        semiFinal1 = testTournament.getSemifinalMatch1();
        semiFinal2 = testTournament.getSemifinalMatch2();

        semiFinal1.declareWinner("Mang0");
        semiFinal2.declareWinner("Plup");

        testTournament.setGrandFinalRound();

        grandFinal = testTournament.getGrandFinalMatch();
        grandFinal.declareWinner("Plup");

        testWinner = testTournament.declareWinner();

        assertEquals("Plup", testWinner.getName());
    }

}
