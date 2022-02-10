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

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: constructs a player with a given initial name, and
    // character(s) they play.
    public Player(String name, String characterOne, String characterTwo, int rank) {
        this.name = name;
        wins = 0;
        losses = 0;
        winRate = 0.00;
        this.mainChars = new ArrayList<>();
        mainChars.add(characterOne);

        // to handle cases where player only plays one character
        if (!characterTwo.equals("N/A")) {
            mainChars.add(characterTwo);
        }

        this.rank = rank;
        tournamentWins = 0;
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: add one win to player object and return number of wins.
    //          updates winRate
    public int addWin() {
        wins = wins + 1;
        updateWinRate();
        return getWins(); //stub

    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: add one loss to player object and return number of losses.
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


    // TODO for future
    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: sets player rank
//    public void setRank(int rank) {
//        //stub
//    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: recalculates winRate and returns new winRate.
    // (will need to account for division by 0)
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

    public String getName() {
        return name;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
    }

    public double getWinRate() {
        return winRate;
    }

    public ArrayList<String> getMainChars() {
        return mainChars;
    }

    public int getRank() {
        return rank;
    }

    public int getTournamentWins() {
        return tournamentWins;
    }

}
