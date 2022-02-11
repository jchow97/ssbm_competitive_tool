package model;


import java.util.ArrayList;

// may need to rethink how to manipulate rank among players. Potential for data structure implementation here.
// need a DS that is good at search, and perhaps re-arranging (?).

public class Player {

    private String name;
    private int wins;
    private int losses;
    private double winRate;
    private ArrayList<String> mainChars;
    private int rank;
    private int tournamentWins;

    // EFFECTS: constructs a player with a given initial name, and
    // character(s) they play.
    public Player(String name, String characterOne, String characterTwo, int rank) {
        this.name = name;
        wins = 0;
        losses = 0;
        winRate = 0.00;
        this.mainChars = new ArrayList<>();
        mainChars.add(characterOne);

        // handles cases where player only plays one character
        if (!characterTwo.equals("N/A")) {
            mainChars.add(characterTwo);
        }

        this.rank = rank;
        tournamentWins = 0;
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: add one win to player object and return number of wins and
    //          updates winRate
    public int addWin() {
        wins = wins + 1;
        updateWinRate();
        return getWins(); //stub

    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: add one loss to player object and return number of losses and
    //          updates winRate
    public int addLoss() {
        losses = losses + 1;
        updateWinRate();
        return getLosses();
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: changes wins to given integer and update winRate
    public void setWins(int wins) {
        this.wins = wins;
        updateWinRate();
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: changes losses to given integer.
    public void setLosses(int losses) {
        this.losses = losses;
        updateWinRate();
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: recalculates and returns new winRate.
    private double updateWinRate() {
        if (wins == 0) {
            winRate = 0.00;
        } else if (losses == 0) {
            winRate = 1.00;
        } else {
            int total = wins + losses;
            winRate = (double) wins / (double) total;
        }
        return winRate;
    }

    // EFFECTS: returns player's name.
    public String getName() {
        return name;
    }

    // EFFECTS: returns player's wins.
    public int getWins() {
        return wins;
    }

    // EFFECTS: returns player's losses
    public int getLosses() {
        return losses;
    }

    // EFFECTS: returns player's win rate.
    public double getWinRate() {
        return winRate;
    }

    // EFFECTS: returns player's character list.
    public ArrayList<String> getMainChars() {
        return mainChars;
    }

    // EFFECTS: returns player's current rank
    public int getRank() {
        return rank;
    }

    // EFFECTS: returns the player's tournament wins.
    public int getTournamentWins() {
        return tournamentWins;
    }

}
