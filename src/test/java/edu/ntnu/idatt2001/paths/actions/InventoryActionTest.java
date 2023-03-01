package edu.ntnu.idatt2001.paths.actions;

import edu.ntnu.idatt2001.paths.Player;
import edu.ntnu.idatt2001.paths.actions.InventoryAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InventoryActionTest {

    Player player1 = new Player("Player", 100,  54, 1234);

    @Test
    void execute() {
        new InventoryAction("Sword").execute(player1);
        assertEquals("Sword", player1.getInventory().get(0));
    }
}