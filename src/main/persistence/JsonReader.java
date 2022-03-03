package persistence;

import model.Player;
import model.GameCharacter;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
    public Player read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parsePlayer(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it.
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses player from JSON object and returns it
    private Player parsePlayer(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        int wins = jsonObject.getInt("wins");
        int losses = jsonObject.getInt("losses");
        double winRate = jsonObject.getDouble("winRate");

        int rank = jsonObject.getInt("rank");
        int tournamentWins = jsonObject.getInt("tournamentWins");

        //TODO add player constructor here to add all parameters directly
        Player player = new Player(name); // we will need to "create" more things, add more parameters.
        String mainChars = jsonObject.getString("main characters");
        addMainChars(player, jsonObject);
        // perform more things related to other classes if needed.
        return player;
    }

    // MODIFIES: Player
    // EFFECTS: parses main chars from JSON object and adds them to Player
    private void addMainChars(Player player, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("main characters");
        for (Object json : jsonArray) {
            JSONObject nextCharacter = (JSONObject) json;
            addMainChar(player, nextCharacter);
        }
    }

    // MODIFIES: Player
    // EFFECTS: parses main char from JSON object and adds it to Player
    private void addMainChar(Player player, JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        GameCharacter character = new GameCharacter(name);
        player.addMainCharacter(character);
    }



}
