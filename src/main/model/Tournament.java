package model;


import java.util.ArrayList;

public class Tournament {
    private static final int TOURNAMENT_SIZE = 8;

    private Match match1;
    private Match match2;
    private Match match3;
    private Match match4;


    // EFFECTS: construct an 8-player tournament, with 4 matches based on rankings.
    public Tournament() {
        //TODO
    }

    // REQUIRES: all current matches have winners declared
    // MODIFIES: this
    // EFFECTS: creates the next round of matches in the tournament if current matches.
    // might be able to set to private (?)
    public void nextRound() {
        //TODO
    }


    public Match getMatch1() {
        return match1;
    }

    public Match getMatch2() {
        return match2;
    }

    public Match getMatch3() {
        return match3;
    }

    public Match getMatch4() {
        return match4;
    }
}
