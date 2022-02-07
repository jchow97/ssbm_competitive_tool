package model;


import java.util.ArrayList;

public class Player {

    private String name;
    private int wins;
    private int losses;
    private float winRate;
    private ArrayList<String> charactersPlayed;

    // REQUIRES:
    // MODIFIES:
    // EFFECTS: constructs a player with a given initial name, wins, losses, and
    // characters they play.
    public Player(String name) {
        this.name = name;
        wins = 0;
        losses = 0;
        winRate = 0;
        charactersPlayed = new ArrayList<>();
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: add one win to player object and return number of wins.
    public int addWin() {
        return 0; //stub
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: add one loss to player object and return number of losses.
    public int addLoss() {
        return 0; //stub
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: changes wins to given integer
    public void setWins(int wins) {
        //stub
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: changes losses to given integer.
    public void setLosses(int losses) {
        //stub
    }


    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: recalculates winRate and returns new winRate.
    // (will need to account for division by 0)
    private float updateWinRate() {
        return 0; //stub
        // this.winRate = wins/losses;
        // return winRate;
    }


}
