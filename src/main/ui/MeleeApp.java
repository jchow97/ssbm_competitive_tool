package ui;

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

        System.out.println("\nThank you for using our application!");
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

    private void displayPlayerRankings() {
        final Object[][] table = new String[][];
        // re-initialize playerList because of local scoping in init()?

        table[0] = new String[] {"Rank", "Player", "Wins", "Losses", "Characters"};
        table[1] = new String[] {"1", playerList.get(0)};
        System.out.println("\nRank  Player   Wins   Losses  Characters      ");
        System.out.println("\n1     player.");
    }

    // MODIFIES: this
    // EFFECTS: process user command
    private void processCommand(String command) {
        if (command.equals("v")) {
            //display player rankings

        } else if (command.equals("t")) {
            //display tournament viewer
            //display Match 1 details
        } else if (command.equals("s")) {
            //ask for player name
        } else if (command.equals("a")) {
            //ask for player details.
        } else {
            //invalid input message.
        }
    }

}
