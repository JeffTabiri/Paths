package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.paths.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerTest {
    Player player1 = new Player("Player", 100,  54, 1234);

    @Test
    void constructorTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Player("Player", -100,  0, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Player("Player", 100,  -1, 0));
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Player("Player", 100,  0, -1));
    }

    @Test
    void getNameTest() {
        Assertions.assertEquals("Player", player1.getName());
    }

    @Test
    void getInventoryTest() {
        Assertions.assertEquals(0, player1.getInventory().size());
    }

    @Test
    void getGoldTest() {
        Assertions.assertEquals(1234, player1.getGold());
    }


    @Test
    void getHealthTest() {
        Assertions.assertEquals(100, player1.getHealth());
    }

    @Test
    void getScoreTest() {
        Assertions.assertEquals(54, player1.getScore());
    }


    @Test
    void addInventoryTest() {
        player1.addInventory("Sword");
        Assertions.assertEquals("Sword", player1.getInventory().get(0));
    }
    @Test
    void addGoldTest() {
        player1.addGold(100);
        Assertions.assertEquals(1334, player1.getGold());
    }
    @Test
    void addHealthTest() {
        player1.addHealth(100);
        Assertions.assertEquals(200, player1.getHealth());
    }
    @Test
    void addScoreTest() {
        player1.addScore(100);
        Assertions.assertEquals(154, player1.getScore());
    }
}
