package model;

public class Match {

    private String playerOne;
    private String playerTwo;
    private String winner;
    private String playerOneCharacter;
    private String playerTwoCharacter;

    public Match(String playerOne, String playerOneCharacter, String playerTwo, String playerTwoCharacter) {
        this.playerOne = playerOne;
        this.playerOneCharacter = playerOneCharacter;

        this.playerTwo = playerTwo;
        this.playerTwoCharacter = playerTwoCharacter;
        this.winner = "";

    }

    // REQUIRES: (doesn't assume winner is p1 or p2, code will handle it).
    // MODIFIES: this
    // EFFECTS: declares who won the match.
    public void winner(String player) {
        //stub
    }

    // REQUIRES:
    // MODIFIES: Player, Tournament(?)
    // EFFECTS: updates the record of players in match
    public void recordMatch() {
        //stub
    }

    // getter methods
    public String getPlayerOne() {
        return playerOne;
    }

    public String getPlayerTwo() {
        return playerTwo;
    }

    public String getWinner() {
        return winner;
    }

    public String getPlayerOneCharacter() {
        return playerOneCharacter;
    }

    public String getPlayerTwoCharacter() {
        return playerTwoCharacter;
    }
}
