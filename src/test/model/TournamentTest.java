package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;


public class TournamentTest {
    Tournament testTournament;
    private Player testPlayer1;
    private Player testPlayer2;
    private Player testPlayer3;
    private Player testPlayer4;
    private Player testPlayer5;
    private Player testPlayer6;
    private Player testPlayer7;
    private Player testPlayer8;

    ArrayList<Player> competitors;

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
        competitors = new ArrayList<>();
        for (int i = 1; i < 9; i++) {
            String num = Integer.toString(i);
            testPlayer = new Player(num);
            competitors.add(testPlayer);
        }
        testTournament = new Tournament(competitors);
        ArrayList<Match> quarterfinalMatches = testTournament.getQuarterfinalMatches();


        quarterfinal1 = quarterfinalMatches.get(0);
        quarterfinal2 = quarterfinalMatches.get(1);
        quarterfinal3 = quarterfinalMatches.get(2);
        quarterfinal4 = quarterfinalMatches.get(3);
        quarterfinal1.declareWinner("1");
        quarterfinal2.declareWinner("2");
        quarterfinal3.declareWinner("3");
        quarterfinal4.declareWinner("4");
    }

    @Test
    void testConstructor() {
        testPlayer = quarterfinal1.getPlayerOne();
        assertEquals("1", testPlayer.getName());
        testPlayer = quarterfinal1.getPlayerTwo();
        assertEquals("8", testPlayer.getName());

        testPlayer = quarterfinal2.getPlayerOne();
        assertEquals("2", testPlayer.getName());
        testPlayer = quarterfinal2.getPlayerTwo();
        assertEquals("7", testPlayer.getName());

        testPlayer = quarterfinal3.getPlayerOne();
        assertEquals("3", testPlayer.getName());
        testPlayer = quarterfinal3.getPlayerTwo();
        assertEquals("6", testPlayer.getName());

        testPlayer = quarterfinal4.getPlayerOne();
        assertEquals("4", testPlayer.getName());
        testPlayer = quarterfinal4.getPlayerTwo();
        assertEquals("5", testPlayer.getName());

        assertNull(testTournament.getWinner());
    }

    @Test
    void testSetSemifinal() {
        testTournament.setSemifinalRound();
        ArrayList<Match> semifinalMatches = testTournament.getSemifinalMatches();
        semiFinal1 = semifinalMatches.get(0);
        semiFinal2 = semifinalMatches.get(1);

        testPlayer = semiFinal1.getPlayerOne();
        assertEquals("1", testPlayer.getName());
        testPlayer = semiFinal1.getPlayerTwo();
        assertEquals("4", testPlayer.getName());


        testPlayer = semiFinal2.getPlayerOne();
        assertEquals("2", testPlayer.getName());
        testPlayer = semiFinal2.getPlayerTwo();
        assertEquals("3", testPlayer.getName());
    }

    @Test
    void testSetGrandFinal() {
        testTournament.setSemifinalRound();
        ArrayList<Match> semifinalMatches = testTournament.getSemifinalMatches();
        semiFinal1 = semifinalMatches.get(0);
        semiFinal2 = semifinalMatches.get(1);

        semiFinal1.declareWinner("1");
        semiFinal2.declareWinner("3");

        testTournament.setGrandFinalRound();

        grandFinal = testTournament.getGrandFinalMatch();

        testPlayer = grandFinal.getPlayerOne();
        assertEquals("1", testPlayer.getName());
        testPlayer = grandFinal.getPlayerTwo();
        assertEquals("3", testPlayer.getName());
    }

    @Test
    void testDeclareWinner() {
        testTournament.setSemifinalRound();
        ArrayList<Match> semifinalMatches = testTournament.getSemifinalMatches();
        semiFinal1 = semifinalMatches.get(0);
        semiFinal2 = semifinalMatches.get(1);

        semiFinal1.declareWinner("1");
        semiFinal2.declareWinner("3");

        testTournament.setGrandFinalRound();

        grandFinal = testTournament.getGrandFinalMatch();
        grandFinal.declareWinner("3");

        testWinner = testTournament.declareWinner();

        assertEquals("3", testWinner.getName());
    }

    @Test
    void testGetCompetitors() {
        ArrayList<Player> testPlayers = testTournament.getCompetitors();
        Player player = testPlayers.get(0);
        assertEquals("1", player.getName());
    }

}
