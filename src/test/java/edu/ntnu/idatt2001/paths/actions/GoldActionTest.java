package edu.ntnu.idatt2001.paths.actions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idatt2001.paths.model.Player;
import edu.ntnu.idatt2001.paths.model.PlayerBuilder;
import edu.ntnu.idatt2001.paths.model.actions.Action;
import edu.ntnu.idatt2001.paths.model.actions.GoldAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


@DisplayName("GoldActionTest")
class GoldActionTest {

  Player testPlayer;
  Action testAction;

  @BeforeEach
  void setUp() {
    testPlayer = new PlayerBuilder("Test").gold(100).health(100).score(100).build();
    testAction = new GoldAction(100);
  }


  @DisplayName("Test execute")
  @Nested
  class ExecuteTest {
    @Test
    void executeWithPositiveValue() {
      testAction.execute(testPlayer);
      int expectedValue = 200;
      int actualValue = testPlayer.getGold();
      assertEquals(expectedValue, actualValue);
    }

    @Test
    void executeWithNegativeValue() {
      new GoldAction(-100).execute(testPlayer);
      int expectedValue = 0;
      int actualValue = testPlayer.getGold();
      assertEquals(expectedValue, actualValue);
    }

    @Test
    void executeWithNoPlayer() {
      assertThrows(IllegalArgumentException.class, () -> testAction.execute(null));
    }
  }

}