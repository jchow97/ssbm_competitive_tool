package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TournamentTest {
    //TODO look at the lab4 import of static variables to try to get rid of this block below

    Tournament testTournament;

    private static final Player mango = new Player("Mang0", "Falco", "Fox", 1);
    private static final Player zain = new Player("Zain", "Marth", "Roy", 2);
    private static final Player plup = new Player("Plup", "Sheik", "Fox", 3);
    private static final Player ibdw = new Player("iBDW", "Fox", "N/A", 4);
    private static final Player wizzrobe = new Player("Wizzrobe", "Captain Falcon", "N/A", 5);
    private static final Player sfat = new Player("SFAT", "Fox", "N/A", 6);
    private static final Player hbox = new Player("Hungrybox", "Jigglypuff", "N/A", 7);
    private static final Player leffen = new Player("Leffen", "Fox", "Sheik", 8);

    Match quarterfinal1;
    Match quarterfinal2;
    Match quarterfinal3;
    Match quarterfinal4;
    Match semiFinal1;
    Match semiFinal2;
    Match grandFinal;


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
        assertEquals(mango, quarterfinal1.getPlayerOne());
        assertEquals("Falco", quarterfinal1.getPlayerOneCharacter());
        assertEquals(leffen, quarterfinal1.getPlayerTwo());
        assertEquals("Fox", quarterfinal1.getPlayerTwoCharacter());

        assertEquals(zain, quarterfinal2.getPlayerOne());
        assertEquals(hbox, quarterfinal2.getPlayerOne());

        assertEquals(plup, quarterfinal3.getPlayerOne());

        assertEquals(ibdw, quarterfinal4.getPlayerOne());

        assertEquals("", testTournament.getWinner());
    }

    @Test
    void testSetNextRound() {
    }

    @Test
    void testSetSemifinal() {
        testTournament.setSemifinalRound();
        semiFinal1 = testTournament.getSemifinalMatch1();
        semiFinal2 = testTournament.getQuarterfinalMatch2();

        assertEquals(mango, semiFinal1.getPlayerOne());
        assertEquals("Falco", semiFinal1.getPlayerOneCharacter());
        assertEquals(wizzrobe, semiFinal1.getPlayerTwo());
        assertEquals("Captain Falcon", semiFinal1.getPlayerTwoCharacter());

        assertEquals(zain, semiFinal2.getPlayerOne());
        assertEquals("Marth", semiFinal2.getPlayerOneCharacter());
        assertEquals(plup, semiFinal2.getPlayerTwo());
        assertEquals("Sheik", semiFinal2.getPlayerTwoCharacter());
    }

    @Test
    void testSetGrandFinal() {
        testTournament.setSemifinalRound();

        semiFinal1.declareWinner("Mang0");
        semiFinal2.declareWinner("Plup");

        testTournament.setGrandFinalRound();

        testTournament.setGrandFinalRound();
        assertEquals(mango, grandFinal.getPlayerOne());
        assertEquals("Falco", grandFinal.getPlayerOneCharacter());
        assertEquals(plup, grandFinal.getPlayerOne());
        assertEquals("Sheik", grandFinal.getPlayerTwoCharacter());
    }

    @Test
    void testDeclareWinner() {
        testTournament.setSemifinalRound();

        semiFinal1.declareWinner("Mang0");
        semiFinal2.declareWinner("Plup");

        testTournament.setGrandFinalRound();

        testTournament.declareWinner();

        assertEquals(plup, testTournament.declareWinner());
    }

}
