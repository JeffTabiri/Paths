package edu.ntnu.idatt2001.paths.model.goals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idatt2001.paths.model.Player;
import edu.ntnu.idatt2001.paths.model.PlayerBuilder;
import edu.ntnu.idatt2001.paths.model.actions.HealthAction;
import edu.ntnu.idatt2001.paths.model.goals.HealthGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("HealthGoalTest")
class HealthGoalTest {

  HealthGoal healthGoal;

  HealthAction healthAction;

  Player testPlayer;

  @BeforeEach
  void setUp() {
    healthGoal = new HealthGoal(100);
    healthAction = new HealthAction(100);
    testPlayer = new PlayerBuilder("Test").gold(100).health(100).score(100).build();
  }

  @Test
  void isFulfilled() {
    healthAction.execute(testPlayer);
    assertTrue(healthGoal.isFulfilled(testPlayer));
  }
}