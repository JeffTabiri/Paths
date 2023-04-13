package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.paths.Player;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {
    Player testPlayer;

    @BeforeEach
    void setUp() {
        testPlayer = new Player("Player", 100,  0, 50);
    }

    @DisplayName("Test Constructor")
    @Nested
    class ConstructorTest {



        @Test
        @DisplayName("Test constructor with valid parameters")
        void testConstructorWithValidParameters() {
            Player player = new Player("Player", 100,  0, 0);

            assertEquals("Player", player.getName());
            assertEquals(100, player.getHealth());
            assertEquals(0, player.getGold());
            assertEquals(0, player.getScore());
        }

        @Test
        @DisplayName("Test constructor with invalid parameters")
        void testConstructorWithInvalidParameters() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Player("", 100,  0, 0));
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Player("Player", -100,  0, 0));
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Player("Player", 100,  -1, 0));
            Assertions.assertThrows(IllegalArgumentException.class, () -> new Player("Player", 100,  0, -1));
        }

    }


    @DisplayName("Test Accessors")
    @Nested
    class AccessorsTest {
        @DisplayName("Test access to name")
        @Test
        void getNameTest() {

            String expectedValue = "Player";
            String actualValue = testPlayer.getName();

            assertEquals(expectedValue, actualValue);
        }

        @DisplayName("Test access to inventory")
        @Test
        void getInventoryTest() {
            testPlayer.addInventory("Sword");
            assertTrue(testPlayer.getInventory().contains("Sword"));
        }

        @DisplayName("Test access to gold")
        @Test
        void getGoldTest() {
            int expectedValue = 50;
            int actualValue = testPlayer.getGold();

            assertEquals(expectedValue, actualValue);
        }

        @DisplayName("Test access to health")
        @Test
        void getHealthTest() {
            int expectedValue = 100;
            int actualValue = testPlayer.getHealth();

            assertEquals(expectedValue, actualValue);
        }

        @DisplayName("Test access to score")
        @Test
        void getScoreTest() {
            int expectedValue = 0;
            int actualValue = testPlayer.getScore();

            assertEquals(expectedValue, actualValue);
        }

    }

    @DisplayName("Test Mutators")
    @Nested
    class MutatorsTest {
        @DisplayName("Add item to inventory")
        @Test
        void addInventoryTest() {
            testPlayer.addInventory("Sword");
            assertTrue(testPlayer.getInventory().contains("Sword"));
        }

        @DisplayName("Add gold to player")
        @Test
        void addGoldTest() {
            testPlayer.addGold(100);

            int expectedValue = 150;
            int actualValue = testPlayer.getGold();

            assertEquals(expectedValue, actualValue);
        }

        @DisplayName("Add health to player")
        @Test
        void addHealthTest() {
            testPlayer.addHealth(100);

            int expectedValue = 200;
            int actualValue = testPlayer.getHealth();

            assertEquals(expectedValue, actualValue);
        }

        @DisplayName("Add score to player")
        @Test
        void addScoreTest() {
            testPlayer.addScore(100);
            int expectedValue = 100;
            int actualValue = testPlayer.getScore();
            assertEquals(expectedValue, actualValue);
        }

    }
}
