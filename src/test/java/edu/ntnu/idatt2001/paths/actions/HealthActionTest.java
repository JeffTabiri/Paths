package edu.ntnu.idatt2001.paths.actions;

import edu.ntnu.idatt2001.paths.model.Player;
import edu.ntnu.idatt2001.paths.model.PlayerBuilder;
import edu.ntnu.idatt2001.paths.model.actions.HealthAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("HealthActionTest")
class HealthActionTest {

    Player testPlayer;

    @BeforeEach
    void setUp() {
        testPlayer = new PlayerBuilder("Test").gold(100).health(100).score(100).build();
    }
    @Test
    void execute() {
        new HealthAction(100).execute(testPlayer);
        int expectedValue = 200;
        int actualValue = testPlayer.getHealth();
        assertEquals(expectedValue, actualValue);
    }
}