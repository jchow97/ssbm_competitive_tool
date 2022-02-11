package model;

import java.util.ArrayList;

// Represents a list of Competitive Players
public class PlayerList {

    private ArrayList<Player> playerList;

    // EFFECTS: constructs an empty player list
    public PlayerList() {
        playerList = new ArrayList<>();
        Player mango = new Player("Mang0", "Falco", "Fox", 1);
        playerList.add(mango);
        Player zain = new Player("Zain", "Marth", "Roy", 2);
        playerList.add(zain);
        Player plup = new Player("Plup", "Sheik", "Fox", 3);
        playerList.add(plup);
        Player ibdw = new Player("iBDW", "Fox", "N/A", 4);
        playerList.add(ibdw);
        Player wizzrobe = new Player("Wizzrobe", "Captain Falcon", "N/A", 5);
        playerList.add(wizzrobe);
        Player sfat = new Player("SFAT", "Fox", "N/A", 6);
        playerList.add(sfat);
        Player hbox = new Player("Hungrybox", "Jigglypuff", "N/A", 7);
        playerList.add(hbox);
        Player leffen = new Player("Leffen", "Fox", "Sheik", 8);
        playerList.add(leffen);
    }

    // REQUIRES:
    // MODIFIES: this
    // EFFECTS: adds a new player to the player list
    public boolean addPlayer(Player player) {
        playerList.add(player);
        return true;
    }

    // EFFECTS returns the entire playerlist as an ArrayList.
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    // EFFECTS: returns the player at the given position in the list
    public Player get(int i) {
        return playerList.get(i);
    }

    // EFFECTS: returns the size of the player list.
    public int size() {
        ArrayList<Player> list = getPlayerList();
        return list.size();
    }
}
