package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.paths.model.Player;
import edu.ntnu.idatt2001.paths.model.PlayerBuilder;
import org.junit.jupiter.api.*;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerTest {
    Player testPlayer;

    @BeforeEach
    void setUp() {
         testPlayer = new PlayerBuilder("Test").gold(100).health(100).score(100).build();
    }

    @DisplayName("Test Constructor")
    @Nested
    class ConstructorTest {



        @Test
        @DisplayName("Test constructor with valid parameters")
        void testConstructorWithValidParameters() {
            testPlayer = new PlayerBuilder("Test").gold(100).health(100).score(100).build();


            assertEquals("Test", testPlayer.getName());
            assertEquals(100, testPlayer.getHealth());
            assertEquals(100, testPlayer.getGold());
            assertEquals(100, testPlayer.getScore());
        }

        @Test
        @DisplayName("Test constructor with invalid parameters")
        void testConstructorWithInvalidParameters() {
            Assertions.assertThrows(IllegalArgumentException.class, () -> new PlayerBuilder("Player").gold(-100).health(100).score(100).inventory(new ArrayList<>()).build());
            Assertions.assertThrows(IllegalArgumentException.class, () -> new PlayerBuilder("Player").gold(100).health(-100).score(100).build());
            Assertions.assertThrows(IllegalArgumentException.class, () -> new PlayerBuilder("Player").gold(100).health(100).score(-100).build());
            Assertions.assertThrows(IllegalArgumentException.class, () -> new PlayerBuilder("").gold(-100).health(-100).score(100).build());
        }

    }


    @DisplayName("Test Accessors")
    @Nested
    class AccessorsTest {
        @DisplayName("Test access to name")
        @Test
        void getNameTest() {

            String expectedValue = "Test";
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
            int expectedValue = 100;
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
            int expectedValue = 100;
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

            int expectedValue = 200;
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
            int expectedValue = 200;
            int actualValue = testPlayer.getScore();
            assertEquals(expectedValue, actualValue);
        }

    }
}
