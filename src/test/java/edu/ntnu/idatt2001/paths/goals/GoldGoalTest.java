package edu.ntnu.idatt2001.paths.goals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idatt2001.paths.model.Player;
import edu.ntnu.idatt2001.paths.model.PlayerBuilder;
import edu.ntnu.idatt2001.paths.model.actions.GoldAction;
import edu.ntnu.idatt2001.paths.model.goals.GoldGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("GoldGoalTest")
class GoldGoalTest {

  GoldGoal goldGoal;

  GoldAction goldAction;

  Player testPlayer;


  @BeforeEach
  void setUp() {
    int gold = 100;
    goldAction = new GoldAction(gold);
    goldGoal = new GoldGoal(gold);
    testPlayer = new PlayerBuilder("Test").gold(100).health(100).score(100).build();
  }

  @Test
  void isFulfilled() {
    goldAction.execute(testPlayer);
    assertTrue(goldGoal.isFulfilled(testPlayer));
  }

}