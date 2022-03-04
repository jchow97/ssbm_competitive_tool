package model;

import model.exception.*;

import java.util.ArrayList;
import java.util.Arrays;

// Represents a playable character in the Super Smash Bro. Melee Game.
public class GameCharacter {
    private static final ArrayList<String> characterRoster = new ArrayList<>(Arrays.asList("Dr. Mario",
            "Mario", "Luigi", "Bowser", "Peach", "Yoshi", "Donkey Kong", "Captain Falcon", "Ganondorf",
            "Falco", "Fox", "Ness", "Ice Climbers", "Kirby", "Samus", "Zelda", "Link", "Young Link",
            "Pichu", "Pikachu", "Jigglypuff", "Mewtwo", "Mr. Game & Watch", "Marth", "Roy", "Sheik"));
    private String name;

    // EFFECTS: constructs a character with a name, checks name against character list.
    //          throws an exception if name given is not a valid character.
    public GameCharacter(String name) throws GameCharacterException {
        if (characterRoster.contains(name)) {
            this.name = name;
        } else {
            throw new GameCharacterException("This is not a valid character");
        }
    }

    // EFFECTS: returns the name of the game character.
    public String getName() {
        return name;
    }
}
