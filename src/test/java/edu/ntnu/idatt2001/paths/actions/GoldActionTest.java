package edu.ntnu.idatt2001.paths.actions;

import edu.ntnu.idatt2001.paths.Player;
import edu.ntnu.idatt2001.paths.actions.GoldAction;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoldActionTest {

    Player player1 = new Player("Player", 100,  54, 1234);

    @Test
    void execute() {
        new GoldAction(100).execute(player1);
        assertEquals(1334, player1.getGold());
    }

}