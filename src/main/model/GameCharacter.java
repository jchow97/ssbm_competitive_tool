package model;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

public class GameCharacter {
    private static final ArrayList<String> characterList = new ArrayList<>(Arrays.asList("Dr. Mario",
            "Mario", "Luigi", "Bowser", "Peach", "Yoshi", "Donkey Kong", "Captain Falcon", "Ganondorf",
            "Falco", "Fox", "Ness", "Ice Climbers", "Kirby", "Samus", "Zelda", "Link", "Young Link",
            "Pichu", "Pikachu", "Jigglypuff", "Mewtwo", "Mr. Game & Watch", "Marth", "Roy", "Sheik"));
    private String name;

    // EFFECTS: constructs a character with a name, checks name against character list.
    public GameCharacter(String name) {
        if (characterList.contains(name)) {
            this.name = name;
        } else {
            //break
        }
    }

    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        return json;
    }

    public String getName() {
        return name;
    }
}
