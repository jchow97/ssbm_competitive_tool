package model;

import java.util.ArrayList;

public class Match {

    private Player playerOne;
    private Player playerTwo;
    private Player winner;
    private String playerOneCharacter;
    private String playerTwoCharacter;

    public Match(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        ArrayList<String> playerOneChars = playerOne.getMainChars();
        this.playerOneCharacter = playerOneChars.get(0);

        this.playerTwo = playerTwo;
        ArrayList<String> playerTwoChars = playerTwo.getMainChars();
        this.playerTwoCharacter = playerTwoChars.get(0);

        this.winner = null;
    }

    // REQUIRES: assumes no winner has been declared so far
    // (doesn't assume winner is p1 or p2, code will handle it).
    // MODIFIES: this
    // EFFECTS: declares who won the match.
    public boolean declareWinner(String winner) {
        if (winner == playerOne.getName()) {
            this.winner = playerOne;
        } else if (winner == playerTwo.getName()) {
            this.winner = playerTwo;
        } else {
            return false;
        }
        recordMatch();
        return true;
    }

    // REQUIRES: winner has been declared
    // MODIFIES: Player, Tournament(?)
    // EFFECTS: processes the completed match; updates the record of players in match
    public void recordMatch() {
        if (winner == playerOne) {
            playerOne.addWin();
            playerTwo.addLoss();
        } else {
            playerTwo.addWin();
            playerOne.addLoss();
        }
    }

    // getter methods
    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public Player getWinner() {
        return winner;
    }

    public String getPlayerOneCharacter() {
        return playerOneCharacter;
    }

    public String getPlayerTwoCharacter() {
        return playerTwoCharacter;
    }

}
