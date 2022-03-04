package persistence;

import model.exception.*;
import model.Player;
import model.GameCharacter;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.stream.Stream;

// Class is modeled after JsonSerializationDemo program provided in Phase 2 instructions.
// Represents a reader that reads PlayerData from JSON data stored in file.
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads player data from file and returns it;
    // throws IOException if an error occurs reading data from file.
    public ArrayList<Player> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePlayerList(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it.
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses playerList from JSON object and returns it
    private ArrayList<Player> parsePlayerList(JSONObject jsonObject) {
        ArrayList<Player> playerList = new ArrayList<>();
        JSONArray jsonArray = jsonObject.getJSONArray("playerList");
        for (Object json: jsonArray) {
            JSONObject nextPlayer = (JSONObject) json;
            Player player = parsePlayer(nextPlayer);
            playerList.add(player);
        }
        return playerList;
    }

    // EFFECTS: parses player from JSON object and returns it
    private Player parsePlayer(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int wins = jsonObject.getInt("wins");
        int losses = jsonObject.getInt("losses");
        double winRate = jsonObject.getDouble("winRate");
        ArrayList<GameCharacter> mainChars = new ArrayList<>();
        int rank = jsonObject.getInt("rank");
        int tournamentWins = jsonObject.getInt("tournamentWins");

        parseMainChars(mainChars, jsonObject);

        Player player = new Player(name, wins, losses, winRate, mainChars, rank, tournamentWins);

        return player;
    }

    // MODIFIES: Player
    // EFFECTS: parses main chars from JSON object and adds them to Player
    private void parseMainChars(ArrayList<GameCharacter> mainChars, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("mainChars");
        for (Object json : jsonArray) {
            JSONObject nextCharacter = (JSONObject) json;
            GameCharacter gameCharacter;
            try {
                gameCharacter = new GameCharacter(nextCharacter.getString("name"));
                mainChars.add(gameCharacter);
            } catch (GameCharacterException e) {
                //TODO not complete implementation for exception handling
                break;
            }
        }
    }
}
