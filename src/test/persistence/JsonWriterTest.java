package persistence;

import model.Player;
import model.exception.GameCharacterException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest {
    Player testPlayer1;
    Player testPlayer2;

    @BeforeEach
    void runBefore() {
        testPlayer1 = new Player("Barry");
        testPlayer2 = new Player("Allen");
    }

    @Test
    void testWriterInvalidFile() {
        try {
            ArrayList<Player> testPlayerList = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyPlayerList() {
        try {
            ArrayList<Player> testWritePlayerList = new ArrayList<>();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyPlayerList.json");
            writer.open();
            writer.write(testWritePlayerList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyPlayerList.json");
            ArrayList<Player> testReadPlayerList = reader.read();
            assertEquals(0, testReadPlayerList.size());

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (GameCharacterException e) {
            fail("GameCharacterException shouldn't have been thrown.");
        }
    }

    @Test
    void testWriterGeneralPlayerList() {
        try {
            ArrayList<Player> testWritePlayerList = new ArrayList<>();
            testWritePlayerList.add(testPlayer1);
            testWritePlayerList.add(testPlayer2);

            JsonWriter writer = new JsonWriter("./data/testWriterGeneralPlayerList.json");
            writer.open();
            writer.write(testWritePlayerList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralPlayerList.json");
            ArrayList<Player> testReadPlayerList = reader.read();
            assertEquals(2, testReadPlayerList.size());
            assertEquals("Barry", testReadPlayerList.get(0).getName());
            assertEquals("Allen", testReadPlayerList.get(1).getName());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        } catch (GameCharacterException e) {
            fail("GameCharacterException shouldn't have been thrown.");
        }
    }

}
