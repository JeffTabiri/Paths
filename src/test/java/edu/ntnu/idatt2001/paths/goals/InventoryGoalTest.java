package edu.ntnu.idatt2001.paths.goals;

import edu.ntnu.idatt2001.paths.playerBuilder.Player;
import edu.ntnu.idatt2001.paths.actions.InventoryAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        testPlayer = new Player("Test", 100, 100, 100);
    }

    @DisplayName("Test if inventory goal is fulfilled")
    @Test
    void isFulfilled() {
        inventoryAction.execute(testPlayer);
        assertTrue(inventoryGoal.isFulfilled(testPlayer));
    }
}