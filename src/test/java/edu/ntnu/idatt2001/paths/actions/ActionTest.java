package edu.ntnu.idatt2001.paths.actions;

import edu.ntnu.idatt2001.paths.Player;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ActionTest {

    Player player1 = new Player("Player", 100,  54, 1234);
    InventoryAction testAction = new InventoryAction("Shield");

    @Test
    void execute() {
        new InventoryAction("Sword").execute(player1);
        assertEquals("Sword", player1.getInventory().get(0));
    }
}