package model;

public class Match {

    private Player playerOne;
    private Player playerTwo;
    private String winner;
    private String playerOneCharacter;
    private String playerTwoCharacter;

    public Match(Player playerOne, String playerOneCharacter, Player playerTwo, String playerTwoCharacter) {
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
        //TODO
    }

    // REQUIRES:
    // MODIFIES: Player, Tournament(?)
    // EFFECTS: processes the completed match; updates the record of players in match,
    // updates tournament bracket
    public void recordMatch() {
        //stub
        //TODO
    }

    // getter methods
    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
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
