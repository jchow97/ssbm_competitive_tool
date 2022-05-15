package model;


import java.util.ArrayList;

// Represents a Tournament consisting of 8 players.
public class Tournament {
    private ArrayList<Player> competitors;

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
    public Tournament(ArrayList<Player> competitors) {
        this.competitors = competitors;

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

    // EFFECTS: construct an 8-player tournament, with 4 empty matches.
    public Tournament() {
        this.competitors = new ArrayList<>();

        quarterfinalMatch1 = new Match();
        quarterfinalMatch2 = new Match();
        quarterfinalMatch3 = new Match();
        quarterfinalMatch4 = new Match();

        quarterfinalMatches = new ArrayList<>();
        quarterfinalMatches.add(quarterfinalMatch1);
        quarterfinalMatches.add(quarterfinalMatch2);
        quarterfinalMatches.add(quarterfinalMatch3);
        quarterfinalMatches.add(quarterfinalMatch4);

        winner = null;
    }

    // MODIFIES: this
    // EFFECTS: updates competitors list in tournament with given playerList and fills corresponding matches
    public void setCompetitors(ArrayList<Player> competitors) {
        this.competitors = competitors;

        if (competitors.size() > 8) {
            quarterfinalMatch1.setPlayers(competitors.get(0), competitors.get(7));
            quarterfinalMatch2.setPlayers(competitors.get(1), competitors.get(6));
            quarterfinalMatch3.setPlayers(competitors.get(2), competitors.get(5));
            quarterfinalMatch4.setPlayers(competitors.get(3), competitors.get(4));
        }
    }

    // REQUIRES: grand final match to have a winner declared
    // MODIFIES: this
    // EFFECTS: declares a winner for the tournament and returns player.
    public Player declareWinner() {
        winner = grandFinalMatch.getWinner();
        return winner;
    }

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

    // EFFECTS: returns the PlayerList of competitors.
    public ArrayList<Player> getCompetitors() {
        return competitors;
    }

    // EFFECTS: returns the ArrayList of quarterfinal matches.
    public ArrayList<Match> getQuarterfinalMatches() {
        return quarterfinalMatches;
    }

    // EFFECTS: returns the ArrayList of semifinal matches.
    public ArrayList<Match> getSemifinalMatches() {
        return semifinalMatches;
    }

    // EFFECTS: returns the grand final match
    public Match getGrandFinalMatch() {
        return grandFinalMatch;
    }

    // EFFECTS: returns the winner of the tournament
    public Player getWinner() {
        return winner;
    }
}
