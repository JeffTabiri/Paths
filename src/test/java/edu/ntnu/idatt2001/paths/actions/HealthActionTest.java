package edu.ntnu.idatt2001.paths.actions;

import edu.ntnu.idatt2001.paths.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("HealthActionTest")
class HealthActionTest {

    Player testPlayer;

    @BeforeEach
    void setUp() {
        testPlayer = new Player("Test", 100, 100, 100);
    }
    @Test
    void execute() {
        new HealthAction(100).execute(testPlayer);
        int expectedValue = 200;
        int actualValue = testPlayer.getHealth();
        assertEquals(expectedValue, actualValue);
    }
}