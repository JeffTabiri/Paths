package edu.ntnu.idatt2001.paths.actions;

import edu.ntnu.idatt2001.paths.playerBuilder.Player;
import edu.ntnu.idatt2001.paths.playerBuilder.PlayerBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("ScoreActionTest")
class ScoreActionTest {

    Player testPlayer;

    @BeforeEach
    void setUp() {
        testPlayer = new PlayerBuilder("Test").gold(100).health(100).score(100).build();
    }

    @Test
    void execute() {
        new ScoreAction(100).execute(testPlayer);
        int expectedValue = 200;
        int actualValue = testPlayer.getScore();
        assertEquals(expectedValue, actualValue);
    }
}