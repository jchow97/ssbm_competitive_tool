package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

// Represents a competitive Player.
public class Player implements Writable {

    private String name;
    private int wins;
    private int losses;
    private double winRate;
    private ArrayList<GameCharacter> mainChars;
    private int rank;
    private int tournamentWins;

    // EFFECTS: constructs a player with a given player name.
    public Player(String name) {
        this.name = name;

        // to avoid the UI being super long when adding a player, players will
        // just be constructed with constant wins, losses, win rate, rank, and tournament wins.
        wins = 0;
        losses = 0;
        winRate = 0.00;
        this.mainChars = new ArrayList<>();

//        GameCharacter charOne = new GameCharacter(characterOne);
//        mainChars.add(charOne);
//        // handles cases where player only plays one character
//        if (!characterTwo.equals("N/A")) {
//            GameCharacter charTwo = new GameCharacter(characterTwo);
//            mainChars.add(charTwo);
//        }

        rank = -1;
        tournamentWins = 0;
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

    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setTournamentWins(int tournamentWins) {
        this.tournamentWins = tournamentWins;
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds given character to player.
    public void addMainCharacter(GameCharacter character) {
        mainChars.add(character);
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("wins", wins);
        json.put("losses", losses);
        json.put("winrate", winRate);
        json.put("main characters", mainChars);
        json.put("main chars", mainCharsToJson());
        json.put("rank", rank);
        json.put("tournament wins", tournamentWins);
        return json;
    }


    //EFFECTS: returns main characters in this player as a JSON array
    private JSONArray mainCharsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (GameCharacter gc : mainChars) {
            jsonArray.put(gc.toJson());
        }

        return jsonArray;
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

}
