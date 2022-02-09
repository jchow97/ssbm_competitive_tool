package model;


public class Tournament {
    private static final int TOURNAMENT_SIZE = 8;

    private Match quarterfinalMatch1;
    private Match quarterfinalMatch2;
    private Match quarterfinalMatch3;
    private Match quarterfinalMatch4;
    private Match semifinalMatch1;
    private Match semifinalMatch2;
    private Match grandFinalMatch;


    // EFFECTS: construct an 8-player tournament, with 4 matches based on rankings.
    public Tournament() {
        //TODO
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: creates the next round of matches in the tournament if current matches.
    // might be able to set to private (?)
    public void nextRound() {
        //TODO
    }

    // REQUIRES: all quarterfinal matches have winners declared
    // MODIFIES: this
    // EFFECTS: creates the semifinal (top 4) matches in the tournament.
    private void setSemifinalRound() {
        //TODO
    }

    // REQUIRES: all semifinal matches have winners declared
    // MODIFIES: this
    // EFFECTS: creates the final match in the tournament.
    private void setGrandFinalRound() {
        // TODO
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

    public Match getSemifinalMatch1() {
        return semifinalMatch1;
    }

    public Match getSemifinalMatch2() {
        return semifinalMatch2;
    }

    public Match getGrandFinalMatch() {
        return grandFinalMatch;
    }
}
