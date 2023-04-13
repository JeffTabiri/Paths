package edu.ntnu.idatt2001.paths.actions;

import edu.ntnu.idatt2001.paths.Player;
import edu.ntnu.idatt2001.paths.actions.GoldAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("GoldActionTest")
class GoldActionTest {

    Player testPlayer;

    @BeforeEach
    void setUp() {
        testPlayer = new Player("Test", 100, 100, 100);
    }

    @Test
    void execute() {
        new GoldAction(100).execute(testPlayer);
        int expectedValue = 200;
        int actualValue = testPlayer.getGold();

        assertEquals(expectedValue, actualValue);
    }

}