package model;

import model.exception.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class GameCharacterTest {
    private GameCharacter testGameCharacter1;
    private GameCharacter testGameCharacter2;

    @BeforeEach
    void runBefore() {
        try {
            testGameCharacter1 = new GameCharacter("Fox");
        } catch (GameCharacterException e) {
            fail("GameCharacterException should not have been thrown.");
        }

    }

    @Test
    void testConstructorValid() {
        assertEquals("Fox", testGameCharacter1.getName());
    }

    @Test
    void testConstructorInvalid() {
        try {
            testGameCharacter2 = new GameCharacter("Barry");
            fail("GameCharacterException should have been thrown.");
        } catch (GameCharacterException e) {
            // pass
        }
    }
}
