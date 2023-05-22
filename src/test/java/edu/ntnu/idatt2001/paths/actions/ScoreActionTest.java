package edu.ntnu.idatt2001.paths.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idatt2001.paths.model.Player;
import edu.ntnu.idatt2001.paths.model.PlayerBuilder;
import edu.ntnu.idatt2001.paths.model.actions.Action;
import edu.ntnu.idatt2001.paths.model.actions.ScoreAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


@DisplayName("ScoreActionTest")
class ScoreActionTest {

  Player testPlayer;
  Action testAction;

  @BeforeEach
  void setUp() {
    testPlayer = new PlayerBuilder("Test").gold(100).health(100).score(100).build();
    testAction = new ScoreAction(100);
  }

  @DisplayName("Test execute")
  @Nested
  class ExecuteTest {
    @Test
    void executeWithPositiveParameters() {
      testAction.execute(testPlayer);
      int expectedValue = 200;
      int actualValue = testPlayer.getScore();
      assertEquals(expectedValue, actualValue);
    }

    @Test
    void executeWithNegativeParameters() {
      new ScoreAction(100).execute(testPlayer);
      int expectedValue = 200;
      int actualValue = testPlayer.getScore();
      assertEquals(expectedValue, actualValue);
    }

    @Test
    void executeWithNoPlayer() {
      assertThrows(IllegalArgumentException.class, () -> testAction.execute(null));
    }
  }

}