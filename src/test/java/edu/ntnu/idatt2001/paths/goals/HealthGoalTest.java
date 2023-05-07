package edu.ntnu.idatt2001.paths.goals;

import edu.ntnu.idatt2001.paths.playerBuilder.Player;
import edu.ntnu.idatt2001.paths.actions.HealthAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthGoalTest {

    HealthGoal healthGoal;

    HealthAction healthAction;

    Player testPlayer;

    @BeforeEach
    void setUp() {
        healthGoal = new HealthGoal(100);
        healthAction = new HealthAction(100);
        testPlayer = new Player("Test", 100, 100, 100);
    }

    @Test
    void isFulfilled() {
        healthAction.execute(testPlayer);
        assertTrue(healthGoal.isFulfilled(testPlayer));
    }
}