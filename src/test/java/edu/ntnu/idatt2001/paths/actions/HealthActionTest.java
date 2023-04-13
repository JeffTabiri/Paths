package edu.ntnu.idatt2001.paths.actions;

import edu.ntnu.idatt2001.paths.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HealthActionTest {

    Player player1 = new Player("Player", 100,  54, 1234);

    @Test
    void execute() {
        new HealthAction(100).execute(player1);
        assertEquals(200, player1.getHealth());
    }
}