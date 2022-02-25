package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class TournamentTest {
    Tournament testTournament;

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
        ArrayList<Match> quarterfinalMatches = testTournament.getQuarterfinalMatches();


        quarterfinal1 = quarterfinalMatches.get(0);
        quarterfinal2 = quarterfinalMatches.get(1);
        quarterfinal3 = quarterfinalMatches.get(2);
        quarterfinal4 = quarterfinalMatches.get(3);
        quarterfinal1.declareWinner("Mang0");
        quarterfinal2.declareWinner("Zain");
        quarterfinal3.declareWinner("Plup");
        quarterfinal4.declareWinner("Wizzrobe");
    }

    @Test
    void testConstructor() {
        testPlayer = quarterfinal1.getPlayerOne();
        assertEquals("Mang0", testPlayer.getName());
        testPlayer = quarterfinal1.getPlayerTwo();
        assertEquals("Leffen", testPlayer.getName());

        testPlayer = quarterfinal2.getPlayerOne();
        assertEquals("Zain", testPlayer.getName());
        testPlayer = quarterfinal2.getPlayerTwo();
        assertEquals("Hungrybox", testPlayer.getName());

        testPlayer = quarterfinal3.getPlayerOne();
        assertEquals("Plup", testPlayer.getName());
        testPlayer = quarterfinal3.getPlayerTwo();
        assertEquals("SFAT", testPlayer.getName());

        testPlayer = quarterfinal4.getPlayerOne();
        assertEquals("iBDW", testPlayer.getName());
        testPlayer = quarterfinal4.getPlayerTwo();
        assertEquals("Wizzrobe", testPlayer.getName());

        assertNull(testTournament.getWinner());
    }

    @Test
    void testSetSemifinal() {
        testTournament.setSemifinalRound();
        ArrayList<Match> semifinalMatches = testTournament.getSemifinalMatches();
        semiFinal1 = semifinalMatches.get(0);
        semiFinal2 = semifinalMatches.get(1);

        testPlayer = semiFinal1.getPlayerOne();
        assertEquals("Mang0", testPlayer.getName());
        //assertEquals("Falco", semiFinal1.getPlayerOneCharacter());
        testPlayer = semiFinal1.getPlayerTwo();
        assertEquals("Wizzrobe", testPlayer.getName());
        //assertEquals("Captain Falcon", semiFinal1.getPlayerTwoCharacter());

        testPlayer = semiFinal2.getPlayerOne();
        assertEquals("Zain", testPlayer.getName());
        //assertEquals("Marth", semiFinal2.getPlayerOneCharacter());
        testPlayer = semiFinal2.getPlayerTwo();
        assertEquals("Plup", testPlayer.getName());
        //assertEquals("Sheik", semiFinal2.getPlayerTwoCharacter());
    }

    @Test
    void testSetGrandFinal() {
        testTournament.setSemifinalRound();
        ArrayList<Match> semifinalMatches = testTournament.getSemifinalMatches();
        semiFinal1 = semifinalMatches.get(0);
        semiFinal2 = semifinalMatches.get(1);

        semiFinal1.declareWinner("Mang0");
        semiFinal2.declareWinner("Plup");

        testTournament.setGrandFinalRound();

        grandFinal = testTournament.getGrandFinalMatch();

        testPlayer = grandFinal.getPlayerOne();
        assertEquals("Mang0", testPlayer.getName());
        testPlayer = grandFinal.getPlayerTwo();
        assertEquals("Plup", testPlayer.getName());
    }

    @Test
    void testDeclareWinner() {
        testTournament.setSemifinalRound();
        ArrayList<Match> semifinalMatches = testTournament.getSemifinalMatches();
        semiFinal1 = semifinalMatches.get(0);
        semiFinal2 = semifinalMatches.get(1);

        semiFinal1.declareWinner("Mang0");
        semiFinal2.declareWinner("Plup");

        testTournament.setGrandFinalRound();

        grandFinal = testTournament.getGrandFinalMatch();
        grandFinal.declareWinner("Plup");

        testWinner = testTournament.declareWinner();

        assertEquals("Plup", testWinner.getName());
    }

    @Test
    void testGetCompetitors() {
        ArrayList<Player> testPlayers = testTournament.getCompetitors();
        Player player = testPlayers.get(0);
        assertEquals("Mang0", player.getName()); // not sure if this will still work
    }

}
