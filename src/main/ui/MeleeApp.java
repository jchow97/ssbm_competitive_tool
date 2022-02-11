package ui;

import model.Match;
import model.Player;
import model.PlayerList;
import model.Tournament;

import java.util.ArrayList;
import java.util.Scanner;

// Competitive Super Smash Bros. Melee Application
public class MeleeApp {
    private Scanner input;
    private PlayerList playerList;

    // Code based after TellerApp's ui TellerApp code.
    // EFFECTS: runs the Melee application
    public MeleeApp() {
        runMelee();
    }

    // Code based after TellerApp's ui TellerApp code.
    // MODIFIES: this
    // EFFECTS: initializes the UI and displays the main menu.
    // acts as the starting branch for the application.
    private void runMelee() {
        boolean keepGoing = true;
        String command;

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

    // MODIFIES: this
    // EFFECTS: initializes 8-player list and scanner for inputs.
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

    // Code adapted from TellerApp UI.
    // MODIFIES: this
    // EFFECTS: process user command from display menu.
    private void processCommand(String command) {
        if (command.equals("v")) {
            //display player rankings
            displayPlayerRankings();
        } else if (command.equals("t")) {
            //display tournament viewer
            //display Match 1 details
            playerList = runTournament();
        } else if (command.equals("s")) {
            //ask for player name
            displaySearch();
        } else if (command.equals("a")) {
            //ask for player details.
            displayAddPlayer();
        } else {
            System.out.println("Invalid command. Please try again.");
            processCommand(command);
        }
    }

    // EFFECTS: prints player rankings onto console
    private void displayPlayerRankings() {
        System.out.printf("%-4s %-10s %-7s %-7s %-20s %-20s", "Rank", "Player", "Wins", "Losses", "Characters", "");
        System.out.println("\n---------------------------------------------------------------------------------");
        ArrayList<Player> players = playerList.getPlayerList();
        for (Player player: players) {
            ArrayList<String> characters = player.getMainChars();
            if (characters.size() == 2) {
                System.out.printf("%-4d %-10s %-7d %-7d %-20s %-20s\n", player.getRank(), player.getName(),
                        player.getWins(), player.getLosses(), characters.get(0), characters.get(1));
            } else {
                System.out.printf("%-4d %-10s %-7d %-7d %-20s %-20s\n", player.getRank(), player.getName(),
                        player.getWins(), player.getLosses(), characters.get(0), "--");
            }
        }

        System.out.println("Press any key to return to main menu.");
        input.next();
        System.out.println("Returning to Main Menu.");
    }

    // MODIFIES: this
    // EFFECTS: prints tournament bracket with original 8 players onto console
    private PlayerList runTournament() {
        Tournament tournament = new Tournament();
        System.out.println("THE BIGGEST BADDEST TOURNAMENT EVER");

        displayQuarterfinals(tournament);
        tournament = runQuarterfinals(tournament);

        tournament.setSemifinalRound();
        displaySemifinals(tournament);
        tournament = runSemifinals(tournament);

        tournament.setGrandFinalRound();
        Match finals = tournament.getGrandFinalMatch();
        displayFinalRound(finals);
        tournament = runFinals(tournament);

        Player tournamentWinner = tournament.getWinner();
        System.out.printf("%s won the tournament!\n", tournamentWinner.getName());

        System.out.println("t -> Run another tournament.");
        System.out.println("Press any key to return to main menu.");
        String command = input.next();
        command.toLowerCase();
        if (command.equals("t")) {
            runTournament();
        } else {
            System.out.println("Returning to Main Menu.");
        }
        return tournament.getCompetitors();
    }

    // EFFECTS: prints the quarterfinal match ups onto console.
    private void displayQuarterfinals(Tournament tournament) {
        ArrayList<Match> quarterfinalMatches = tournament.getQuarterfinalMatches();
        System.out.println("QUARTERFINALS");
        for (int i = 0; i < 4; i++) {
            int matchNumber = i + 1;
            System.out.printf("\nMatch %d", matchNumber);
            Match match = quarterfinalMatches.get(i);
            Player playerOne = match.getPlayerOne();
            Player playerTwo = match.getPlayerTwo();
            System.out.printf("\n%-10s", playerOne.getName());
            System.out.println("\nvs");
            System.out.printf("%-10s\n", playerTwo.getName());
        }
    }

    // REQUIRES: intended inputs only. For example, must not get matches mixed up.
    // Cannot declare a player from another match as the winner.
    // MODIFIES: this, Tournament, Match, Player
    // EFFECTS: processes the quarterfinal match ups based on player input.
    private Tournament runQuarterfinals(Tournament tournament) {
        ArrayList<Match> matches = tournament.getQuarterfinalMatches();
        for (int i = 0; i < 4; i++) {
            Match match = matches.get(i);
            int matchNumber = i + 1;
            System.out.printf("\nWho won quarterfinal match %d?\n", matchNumber);
            String winner = input.next();
            while (!match.declareWinner(winner)) {
                System.out.println("Invalid name. Please try again. Who won the match?");
                winner = input.next();
                match.declareWinner(winner);
            }
        }
        return tournament;
    }

    // EFFECTS: prints the semifinal match ups onto console.
    private void displaySemifinals(Tournament tournament) {
        ArrayList<Match> semifinalMatches = tournament.getSemifinalMatches();
        System.out.println("SEMIFINALS");
        for (int i = 0; i < 2; i++) {
            int matchNumber = i + 1;
            System.out.printf("\nMatch %d", matchNumber);
            Match match = semifinalMatches.get(i);
            Player playerOne = match.getPlayerOne();
            Player playerTwo = match.getPlayerTwo();
            System.out.printf("\n%-10s", playerOne.getName());
            System.out.println("\nvs");
            System.out.printf("%-10s\n", playerTwo.getName());
        }
    }

    // REQUIRES: intended inputs only. For example, must not get matches mixed up.
    // Cannot declare a player from another match as the winner.
    // MODIFIES: this, Tournament, Match, Player
    // EFFECTS: processes the semifinal match ups based on player input.
    private Tournament runSemifinals(Tournament tournament) {
        ArrayList<Match> matches = tournament.getSemifinalMatches();
        for (int i = 0; i < 2; i++) {
            Match match = matches.get(i);
            int matchNumber = i + 1;
            System.out.printf("Who won semifinal match %d?\n", matchNumber);
            String winner = input.next();
            match.declareWinner(winner); //should incorporate a return value to check for invalid names
        }
        return tournament;
    }

    // EFFECTS: prints the final match ups onto console.
    private void displayFinalRound(Match finalMatch) {
        System.out.println("FINALS");
        Player playerOne = finalMatch.getPlayerOne();
        Player playerTwo = finalMatch.getPlayerTwo();
        System.out.printf("\n%-10s", playerOne.getName());
        System.out.println("\nvs");
        System.out.printf("%-10s\n", playerTwo.getName());
    }

    // REQUIRES: Intended inputs only. Only declare one of the players as winners.
    // MODIFIES: this, Tournament, Match, Player
    // EFFECTS: processes the final match ups based on player input.
    private Tournament runFinals(Tournament tournament) {
        Match match = tournament.getGrandFinalMatch();
        System.out.println("Who won the final match?\n");
        String winner = input.next();
        match.declareWinner(winner);
        tournament.declareWinner();
        return tournament;
    }

    // EFFECTS: prints search prompt onto console, prints player stats if found
    // else, re-prompts user for another player input or return to menu.
    private void displaySearch() {
        boolean search = true;
        while (search) {
            System.out.println("What is the name of the player you are looking for? (Case sensitive)");
            String name = input.next();
            search = searchPlayer(name);

            if (search) {
                System.out.println("Sorry, there is no player with that name.");
            }

            System.out.println("s -> Search for another player.");
            System.out.println("Press any key to return to main menu.");
            String command = input.next();
            command = command.toLowerCase();

            if (command.equals("s")) {
                displaySearch();
            } else {
                System.out.println("Returning to Main Menu.");
            }
        }
    }

    // EFFECTS: searches list of players for given name. Name needs to be exact for a match
    // to be found.
    private boolean searchPlayer(String name) {
        ArrayList<Player> playerListForSearch = playerList.getPlayerList();
        for (Player player: playerListForSearch) {
            if (name.equals(player.getName())) {
                ArrayList<String> characters = player.getMainChars();
                System.out.printf("%s's Stats:", player.getName());
                if (characters.size() == 2) {
                    System.out.printf("\nRank: %-4d %-10s Wins: %-7d Losses: %-7d Characters: %s, %s\n",
                            player.getRank(), player.getName(), player.getWins(),
                            player.getLosses(), characters.get(0), characters.get(1));
                } else {
                    System.out.printf("\nRank: %-4d %-10s Wins: %-7d Losses: %-7d Characters: %s %s\n",
                            player.getRank(), player.getName(), player.getWins(),
                            player.getLosses(), characters.get(0), "--");
                }
                return false;
            }
        }
        return true;
    }

    // MODIFIES: this
    // EFFECTS: prompts user through adding data for a new player.
    private void displayAddPlayer() {
        System.out.println("What is the name of the player you would like to add?");
        String player = input.next();

        System.out.println("How many wins do they have? Please only provide numbers.");
        int wins = input.nextInt();

        System.out.println("How many losses do they have? Please only provide numbers.");
        int losses = input.nextInt();

        ArrayList<String> characters = new ArrayList<>();

        boolean invalidValue = true;
        while (invalidValue) {
            System.out.println("How many characters do they play?");
            int characterCount = input.nextInt();
            if (characterCount == 1 || characterCount == 2) {
                characters = addCharacters(characterCount);
                invalidValue = false;
            } else {
                System.out.println("Invalid. Please pick between 1 or 2.");
            }
        }

        int lowestRank = playerList.size() + 1;
        Player newPlayer = new Player(player, characters.get(0), characters.get(1), lowestRank);
        newPlayer.setWins(wins);
        newPlayer.setLosses(losses);
        playerList.addPlayer(newPlayer);
    }


    // EFFECTS: helper function for handling adding 1 or 2 character(s) to list of characters.
    private ArrayList<String> addCharacters(int characterCount) {
        String characterOne;
        String characterTwo;
        ArrayList<String> characterList = new ArrayList<>();
        if (characterCount == 1) {
            System.out.println("What is their character?");
            characterOne = input.next();
            characterTwo = "N/A";
            characterList.add(characterOne);
            characterList.add(characterTwo);

        } else if (characterCount == 2) {
            System.out.println("What is their first character?");
            characterOne = input.next();
            System.out.println("What is their second character?");
            characterTwo = input.next();
            characterList.add(characterOne);
            characterList.add(characterTwo);
        }
        return characterList;
    }
}