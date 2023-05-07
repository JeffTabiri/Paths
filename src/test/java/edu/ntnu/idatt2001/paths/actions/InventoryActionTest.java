package edu.ntnu.idatt2001.paths.actions;

import edu.ntnu.idatt2001.paths.playerBuilder.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("InventoryActionTest")
class InventoryActionTest {
    Player testPlayer;

    @BeforeEach
    void setUp() {
        testPlayer = new Player("Test", 100, 100, 100);
    }

    @Test
    void execute() {
        new InventoryAction("Sword").execute(testPlayer);
        assertTrue(testPlayer.getInventory().contains("Sword"));
    }
}