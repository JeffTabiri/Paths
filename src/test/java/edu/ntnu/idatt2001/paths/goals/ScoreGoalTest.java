package edu.ntnu.idatt2001.paths.goals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idatt2001.paths.model.Player;
import edu.ntnu.idatt2001.paths.model.PlayerBuilder;
import edu.ntnu.idatt2001.paths.model.actions.ScoreAction;
import edu.ntnu.idatt2001.paths.model.goals.ScoreGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


@DisplayName("ScoreGoalTest")
class ScoreGoalTest {

  ScoreAction scoreAction;
  ScoreGoal scoreGoal;

  Player testPlayer;


  @BeforeEach
  void setUp() {
    scoreAction = new ScoreAction(100);
    scoreGoal = new ScoreGoal(100);
    testPlayer = new PlayerBuilder("Test").gold(100).health(100).score(100).build();
  }

  @Test
  void isFulfilled() {
    scoreAction.execute(testPlayer);
    assertTrue(scoreGoal.isFulfilled(testPlayer));
  }

}