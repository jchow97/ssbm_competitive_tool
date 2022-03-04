package persistence;

import model.GameCharacter;
import model.Player;
import model.exception.GameCharacterException;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest {
    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ArrayList<Player> testPlayerList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        } catch (GameCharacterException e) {
            fail("GameCharacterException shouldn't have been thrown.");
        }
    }

    @Test
    void testReaderEmptyPlayerList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyPlayerList.json");
        try {
            ArrayList<Player> testPlayerList = reader.read();
            assertEquals(0, testPlayerList.size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (GameCharacterException e) {
            fail("GameCharacterException shouldn't have been thrown.");
        }
    }

    @Test
    void testReaderGeneralPlayerList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralPlayerList.json");
        try {
            ArrayList<Player> testPlayerList = reader.read();
            //check size and first player details
            assertEquals(8, testPlayerList.size());
            assertEquals("mang0", testPlayerList.get(0).getName());
            assertEquals(0, testPlayerList.get(0).getWins());
            assertEquals(0, testPlayerList.get(0).getLosses());
            assertEquals(0.00, testPlayerList.get(0).getWinRate());
            assertEquals(0, testPlayerList.get(0).getTournamentWins());
            assertEquals(1, testPlayerList.get(0).getRank());

            ArrayList<GameCharacter> testGameCharacterList = testPlayerList.get(0).getMainChars();
            assertEquals("Falco", testGameCharacterList.get(0).getName());
            assertEquals("Fox", testGameCharacterList.get(1).getName());

        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (GameCharacterException e) {
            fail("GameCharacterException shouldn't have been thrown.");
        }
    }

    @Test
    void testReaderPlayerListInvalidChar() {
        JsonReader reader = new JsonReader("./data/testReaderPlayerListInvalidChar.json");
        try {
            ArrayList<Player> testPlayerList = reader.read();
            fail("Supposed to throw GameCharacterException");
        } catch (IOException e) {
            fail("Couldn't read from file");
        } catch (GameCharacterException e) {
            // pass
        }
    }

}
