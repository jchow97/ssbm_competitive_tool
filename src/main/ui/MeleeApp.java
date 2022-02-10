package ui;

import model.Player;
import model.PlayerList;

import java.util.ArrayList;
import java.util.Scanner;

// Super Smash Bros. Melee Application
public class MeleeApp {
    private Scanner input;
    private PlayerList playerList;

    // EFFECTS: runs the Melee application
    public MeleeApp() {
        runMelee();
    }

    // code based after TellerApp's ui TellerApp code.
    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMelee() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nGoodbye!");
    }

    // EFFECTS: initializes 8-player list.
    private void init() {
        playerList = new PlayerList();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // adapted from TellerApp UI.
    // EFFECTS: prints option menu into console
    private void displayMenu() {
        System.out.println("\nWelcome to the Super Smash Bros. Melee Tournament Tool! Please select an action:");
        System.out.println("\tv -> View Player Rankings");
        System.out.println("\tt -> Run Tournament");
        System.out.println("\ts -> Search for a player");
        System.out.println("\ta -> Add Player");
        System.out.println("\tq -> Quit");
    }

    // MODIFIES: this
    // EFFECTS: process user command
    private void processCommand(String command) {
        if (command.equals("v")) {
            //display player rankings
            displayPlayerRankings();
        } else if (command.equals("t")) {
            //display tournament viewer
            //display Match 1 details
            displayTournament();
        } else if (command.equals("s")) {
            //ask for player name
            displaySearch();
        } else if (command.equals("a")) {
            //ask for player details.
            //TODO
        } else {
            //invalid input message.
            //TODO
        }
    }

    // EFFECTS: prints player rankings onto console
    private void displayPlayerRankings() {
        System.out.printf("%-4s %-10s %-7s %-7s %-20s %-20s", "Rank", "Player", "Wins", "Losses", "Characters", "");
        System.out.println("\n---------------------------------------------------------------------------------");
        for (int i = 0; i < 8; i++) {
            Player player = playerList.get(i);
            ArrayList<String> characters = player.getMainChars();
            if (characters.size() == 2) {
                System.out.printf("%-4d %-10s %-7d %-7d %-20s %-20s\n", player.getRank(), player.getName(),
                        player.getWins(), player.getLosses(), characters.get(0), characters.get(1));
            } else {
                System.out.printf("%-4d %-10s %-7d %-7d %-20s %-20s\n", player.getRank(), player.getName(),
                        player.getWins(), player.getLosses(), characters.get(0), "--");
            }

            //TODO add next input line
        }

    }

    // EFFECTS: prints tournament bracket onto console
    private void displayTournament() {
        System.out.println("TOURNAMENT");
        System.out.println("\n---------------------------------------------------------------------------------");
        //TODO
    }

    // EFFECTS: prints search prompt onto console
    private boolean displaySearch() {
        System.out.println("What is the name of the player you are looking for?");
        String name = input.next();
        ArrayList<Player> playerListForSearch = playerList.getPlayerList();

        for (Player player: playerListForSearch) {
            if (name == player.getName()) {
                ArrayList<String> characters = player.getMainChars();
                System.out.printf("%-4d %-10s %-7d %-7d %-20s %-20s\n", player.getRank(), player.getName(),
                        player.getWins(), player.getLosses(), characters.get(0), characters.get(1));
                return true;
                // TODO haven't gotten it to work yet. While loops could be very helpful.
            }
        }
        return false;
    }
}
