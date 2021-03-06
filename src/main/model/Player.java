package model;


import java.util.ArrayList;

// Represents a competitive Player.
public class Player {

    private String name;
    private int wins;
    private int losses;
    private double winRate;
    private ArrayList<GameCharacter> mainChars;
    private int rank;
    private int tournamentWins;

    // Constructor used to create generic player used in tests for ease of testing, especially
    // in other test classes.
    // EFFECTS: constructs a player with a given player name.
    public Player(String name) {
        this.name = name;
        wins = 0;
        losses = 0;
        winRate = 0.00;
        this.mainChars = new ArrayList<>();

        // -1 represents unranked.
        rank = -1;
        tournamentWins = 0;
        EventLog.getInstance().logEvent(new Event("Player: " + name + " added."));
    }

    // Constructor reconstructing players when sed for reading JSON.
    // EFFECTS: constructs a player with a given player name, wins, losses, winRate,
    //          mainChars, rank, and tournament wins.
    public Player(String name, int wins, int losses, double winRate,
                  ArrayList<GameCharacter> mainChars, int rank, int tournamentWins) {
        this.name = name;
        this.wins = wins;
        this.losses = losses;
        this.winRate = winRate;
        this.mainChars = mainChars;
        this.rank = rank;
        this.tournamentWins = tournamentWins;

        EventLog.getInstance().logEvent(new Event("Player: " + name + " loaded from file."));
    }

    // Constructor used when adding new player object in UI.
    // EFFECTS: constructs a player with a given player name, wins, losses,
    //          mainChars, rank, and tournament wins.
    public Player(String name, int wins, int losses,
                  ArrayList<GameCharacter> mainChars, int rank, int tournamentWins) {
        this.name = name;
        setWins(wins);
        setLosses(losses);
        this.mainChars = mainChars;
        this.rank = rank;
        this.tournamentWins = tournamentWins;

        EventLog.getInstance().logEvent(new Event("Player: " + name + " added."));
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: add one win to player object and return number of wins and
    //          updates winRate
    public int addWin() {
        wins = wins + 1;
        updateWinRate();
        return getWins();
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
    // EFFECTS: changes wins to given integer and updates winRate
    public void setWins(int wins) {
        this.wins = wins;
        updateWinRate();
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: changes losses to given integer and updates winRate.
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

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: sets rank field to the given integer.
    public void setRank(int rank) {
        this.rank = rank;
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: sets tournamentWins field to the given integer.
    public void setTournamentWins(int tournamentWins) {
        this.tournamentWins = tournamentWins;
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds given character to player.
    public void addMainCharacter(GameCharacter character) {
        mainChars.add(character);
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
    public ArrayList<GameCharacter> getMainChars() {
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

    // EFFECTS: log event when player has been searched for.
    public void logSearchEvent(String keyword) {
        EventLog.getInstance().logEvent(new Event("Search for " + keyword + " occurred."));
    }
}
