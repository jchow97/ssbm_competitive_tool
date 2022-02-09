package model;

import java.util.ArrayList;

public class PlayerList {

/*  Potentially for future use: Player objects with win/loss results of the past year

    private static final Player mango = new Player("Mang0", 45, 14, ["Falco", "Fox"]);
    private static final Player zain = new Player("Zain", 136, 36, ["Marth"]);
    private static final Player plup = new Player("Plup", 61, 10, ["Sheik", "Fox"]);
    private static final Player ibdw = new Player("iBDW", 105, 26, ["Fox"]);
    private static final Player wizzrobe = new Player("Wizzrobe", 62, 18, ["Captain Falcon"]);
    private static final Player sfat = new Player("SFAT", 130, 39, ["Fox"]);
    private static final Player hbox = new Player("Hungrybox", 323, 69, ["Jigglypuff"]);
    private static final Player leffen = new Player("Leffen", 49, 3, ["Fox", "Sheik"]);*/

    private static final Player mango = new Player("Mang0", "Falco", "Fox");
    private static final Player zain = new Player("Zain", "Marth", "Roy");
    private static final Player plup = new Player("Plup", "Sheik", "Fox");
    private static final Player ibdw = new Player("iBDW", "Fox", "N/A");
    private static final Player wizzrobe = new Player("Wizzrobe", "Captain Falcon", "N/A");
    private static final Player sfat = new Player("SFAT", "Fox", "N/A");
    private static final Player hbox = new Player("Hungrybox", "Jigglypuff", "N/A");
    private static final Player leffen = new Player("Leffen", "Fox", "Sheik");

    private ArrayList<Player> playerList;

    // EFFECTS: constructs an empty player list
    public PlayerList() {
        playerList = new ArrayList<>();
        playerList.add(mango);
        playerList.add(zain);
        playerList.add(plup);
        playerList.add(ibdw);
        playerList.add(wizzrobe);
        playerList.add(sfat);
        playerList.add(hbox);
        playerList.add(leffen);
    }

    // REQUIRES: non-empty list of players
    // MODIFIES: this
    // EFFECTS: updates players to reflect current ranking.
    public ArrayList<Player> updateRanks() {
        return null; //stub
    }

    public boolean addPlayer(Player player) {
        //playerList.add(player);
        return false; //stub
        //TODO
    }

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
}
