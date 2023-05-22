package edu.ntnu.idatt2001.paths.actions;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idatt2001.paths.model.Player;
import edu.ntnu.idatt2001.paths.model.PlayerBuilder;
import edu.ntnu.idatt2001.paths.model.actions.Action;
import edu.ntnu.idatt2001.paths.model.actions.InventoryAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


@DisplayName("InventoryActionTest")
class InventoryActionTest {
  Player testPlayer;
  Action testAction;

  @BeforeEach
  void setUp() {
    testPlayer = new PlayerBuilder("Test").gold(100).health(100).score(100).build();
    testAction = new InventoryAction("Excalibur");
  }



  @DisplayName("Test execute")
  @Nested
  class ExecuteTest {
    @Test
    void executeWithValidParameters() {

      testAction.execute(testPlayer);
      boolean actualValue = testPlayer.getInventory().contains("Excalibur");
      assertTrue(actualValue);
    }

    @Test
    void executeWithInvalidParameters() {
      assertThrows(IllegalArgumentException.class, () -> new InventoryAction(""));
    }

    @Test
    void executeWithNoPlayer() {
      assertThrows(IllegalArgumentException.class, () -> testAction.execute(null));
    }

  }

}