package edu.ntnu.idatt2001.paths.goals;

import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idatt2001.paths.model.Player;
import edu.ntnu.idatt2001.paths.model.PlayerBuilder;
import edu.ntnu.idatt2001.paths.model.actions.InventoryAction;
import edu.ntnu.idatt2001.paths.model.goals.InventoryGoal;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



@DisplayName("InventoryGoalTest")
class InventoryGoalTest {

  InventoryGoal inventoryGoal;

  InventoryAction inventoryAction;

  Player testPlayer;

  @BeforeEach
  void setUp() {
    List<String> inventory = List.of("Sword");
    inventoryGoal = new InventoryGoal(inventory);
    inventoryAction = new InventoryAction("Sword");
    testPlayer = new PlayerBuilder("Test").gold(100).health(100).score(100).build();
  }

  @DisplayName("Test if inventory goal is fulfilled")
  @Test
  void isFulfilled() {
    inventoryAction.execute(testPlayer);
    assertTrue(inventoryGoal.isFulfilled(testPlayer));
  }
}