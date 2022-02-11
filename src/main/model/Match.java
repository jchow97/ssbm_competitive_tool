package model;

// Represents a match between players.
public class Match {

    private final Player playerOne;
    private final Player playerTwo;
    private Player winner;

    // EFFECTS: constructs a match with two players and an undeclared winner.
    public Match(Player playerOne, Player playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
        this.winner = null;
    }

    // REQUIRES: assumes no winner has been declared so far
    // MODIFIES: this
    // EFFECTS: declares who won the match.
    public boolean declareWinner(String player) {
        if (player.equals(playerOne.getName())) {
            this.winner = playerOne;
            recordMatch();
            return true;
        } else if (player.equals(playerTwo.getName())) {
            this.winner = playerTwo;
            recordMatch();
            return true;
        } else {
            return false;
        }
    }

    // REQUIRES: winner has been previously declared
    // MODIFIES: Player
    // EFFECTS: updates the wins and losses of players in the match
    public void recordMatch() {
        if (winner == playerOne) {
            playerOne.addWin();
            playerTwo.addLoss();
        } else {
            playerTwo.addWin();
            playerOne.addLoss();
        }
    }

    // EFFECTS: returns player one.
    public Player getPlayerOne() {
        return playerOne;
    }

    // EFFECTS: returns player two.
    public Player getPlayerTwo() {
        return playerTwo;
    }

    // EFFECTS: returns the winner of the match.
    public Player getWinner() {
        return winner;
    }


}
