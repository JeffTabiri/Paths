package edu.ntnu.idatt2001.paths.actions;

import edu.ntnu.idatt2001.paths.model.Player;
import edu.ntnu.idatt2001.paths.model.PlayerBuilder;
import edu.ntnu.idatt2001.paths.model.actions.InventoryAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("InventoryActionTest")
class InventoryActionTest {
    Player testPlayer;

    @BeforeEach
    void setUp() {
        testPlayer = new PlayerBuilder("Test").gold(100).health(100).score(100).build();
    }

    @Test
    void execute() {
        new InventoryAction("Sword").execute(testPlayer);
        assertTrue(testPlayer.getInventory().contains("Sword"));
    }
}