package model;


import java.util.ArrayList;

public class Tournament {
    private static final int TOURNAMENT_SIZE = 8;

    private PlayerList competitors;

    private Match quarterfinalMatch1;
    private Match quarterfinalMatch2;
    private Match quarterfinalMatch3;
    private Match quarterfinalMatch4;
    ArrayList<Match> quarterfinalMatches;
    private Match semifinalMatch1;
    private Match semifinalMatch2;
    ArrayList<Match> semifinalMatches;
    private Match grandFinalMatch;
    private Player winner;


    // EFFECTS: construct an 8-player tournament, with 4 matches based on rankings.
    public Tournament() {
        competitors = new PlayerList();

        quarterfinalMatch1 = new Match(competitors.get(0), competitors.get(7));
        quarterfinalMatch2 = new Match(competitors.get(1), competitors.get(6));
        quarterfinalMatch3 = new Match(competitors.get(2), competitors.get(5));
        quarterfinalMatch4 = new Match(competitors.get(3), competitors.get(4));

        quarterfinalMatches = new ArrayList<>();
        quarterfinalMatches.add(quarterfinalMatch1);
        quarterfinalMatches.add(quarterfinalMatch2);
        quarterfinalMatches.add(quarterfinalMatch3);
        quarterfinalMatches.add(quarterfinalMatch4);

        winner = null;
    }

    // REQUIRES: grand final match to have a winner declared
    // MODIFIES: this
    // EFFECTS: declares a winner for the tournament and returns player.
    public Player declareWinner() {
        winner = grandFinalMatch.getWinner();
        return winner;
    }

    //TODO for future deliverable.
    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: creates the next round of matches in the tournament if current matches.
    // might be able to set to private (?)
//    public void nextRound() {
//
//    }

    // REQUIRES: all quarterfinal matches have winners declared
    // MODIFIES: this
    // EFFECTS: creates the semifinal (top 4) matches in the tournament.
    public void setSemifinalRound() {
        Player quarterfinal1Winner = quarterfinalMatch1.getWinner();
        Player quarterfinal2Winner = quarterfinalMatch2.getWinner();
        Player quarterfinal3Winner = quarterfinalMatch3.getWinner();
        Player quarterfinal4Winner = quarterfinalMatch4.getWinner();

        this.semifinalMatch1 = new Match(quarterfinal1Winner, quarterfinal4Winner);
        this.semifinalMatch2 = new Match(quarterfinal2Winner, quarterfinal3Winner);

        this.semifinalMatches = new ArrayList<>();
        this.semifinalMatches.add(semifinalMatch1);
        this.semifinalMatches.add(semifinalMatch2);
    }

    // REQUIRES: all semifinal matches have winners declared
    // MODIFIES: this
    // EFFECTS: creates the final match in the tournament.
    public void setGrandFinalRound() {
        Player semifinal1Winner = semifinalMatch1.getWinner();
        Player semifinal2Winner = semifinalMatch2.getWinner();

        grandFinalMatch = new Match(semifinal1Winner, semifinal2Winner);
    }

    public PlayerList getCompetitors() {
        return competitors;
    }

    public Match getQuarterfinalMatch1() {
        return quarterfinalMatch1;
    }

    public Match getQuarterfinalMatch2() {
        return quarterfinalMatch2;
    }

    public Match getQuarterfinalMatch3() {
        return quarterfinalMatch3;
    }

    public Match getQuarterfinalMatch4() {
        return quarterfinalMatch4;
    }

    public ArrayList<Match> getQuarterfinalMatches() {
        return quarterfinalMatches;
    }

    public Match getSemifinalMatch1() {
        return semifinalMatch1;
    }

    public Match getSemifinalMatch2() {
        return semifinalMatch2;
    }

    public ArrayList<Match> getSemifinalMatches() {
        return semifinalMatches;
    }

    public Match getGrandFinalMatch() {
        return grandFinalMatch;
    }

    public Player getWinner() {
        return winner;
    }
}
