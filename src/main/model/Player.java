package model;


import java.util.ArrayList;

public class Player {

    private String name;
    private int wins;
    private int losses;
    private float winRate;
    private ArrayList<String> mainChars;
    private int rank;
    private int tournamentWins;

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: constructs a player with a given initial name, and
    // character(s) they play.
    public Player(String name, String characterOne, String characterTwo) {
        this.name = name;
        // REDO these with helper functions
        wins = 0;
        losses = 0;
        winRate = 0;
        this.mainChars = new ArrayList<>();
        mainChars.add(characterOne);
        if (!characterTwo.equals("N/A")) {
            mainChars.add(characterTwo);
        }
        rank = -1; // -1 to signal error
        tournamentWins = 0;
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: add one win to player object and return number of wins.
    public int addWin() {
        return 0; //stub
        //TODO
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: add one loss to player object and return number of losses.
    public int addLoss() {
        return 0; //stub
        //TODO
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: changes wins to given integer
    public void setWins(int wins) {
        //stub
        //TODO
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: changes losses to given integer.
    public void setLosses(int losses) {
        //stub
        //TODO
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: recalculates winRate and returns new winRate.
    // (will need to account for division by 0)
    private float updateWinRate() {
        return 0; //stub
        //TODO
        // this.winRate = wins/losses;
        // getWinRate();
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

    public float getWinRate() {
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
