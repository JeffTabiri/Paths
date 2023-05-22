package edu.ntnu.idatt2001.paths.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;



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

    @Test
    void addInventoryTest() {

      testPlayer.addInventory("Sword");

      boolean actualValue = testPlayer.getInventory().contains("Sword");

      assertTrue(actualValue);
    }

    @Test
    void addEmptyItem() {
      assertThrows(IllegalArgumentException.class, () -> testPlayer.addInventory(""));
    }

    @DisplayName("Remove item from inventory")
    @Test
    void removeInventoryTest() {

      testPlayer.addInventory("Sword");

      testPlayer.removeInventory("Sword");

      boolean actualValue = testPlayer.getInventory().contains("Sword");

      assertFalse(actualValue);
    }

    @Test
    void removeEmptyItem() {
      assertThrows(IllegalArgumentException.class, () -> testPlayer.removeInventory(""));
    }

    @Test
    void removeNonExistingItem() {
      assertThrows(IllegalArgumentException.class, () -> testPlayer.removeInventory("Sword"));
    }

    @Test
    void addGoldTest() {
      testPlayer.addGold(100);

      int expectedValue = 200;
      int actualValue = testPlayer.getGold();

      assertEquals(expectedValue, actualValue);
    }

    @Test
    void addNegativeGoldTest() {
      assertThrows(IllegalArgumentException.class, () -> testPlayer.addGold(-101));
    }

    @Test
    void removeGoldTest() {
      testPlayer.removeGold(-100);

      int expectedValue = 0;
      int actualValue = testPlayer.getGold();

      assertEquals(expectedValue, actualValue);
    }

    @Test
    void removeNegativeGoldTest() {
      assertThrows(IllegalArgumentException.class, () -> testPlayer.removeGold(-101));
    }

    @Test
    void addHealthTest() {
      testPlayer.addHealth(100);

      int expectedValue = 200;
      int actualValue = testPlayer.getHealth();

      assertEquals(expectedValue, actualValue);
    }

    @Test
    void addNegativeHealthTest() {
      assertThrows(IllegalArgumentException.class, () -> testPlayer.addHealth(-101));
    }

    @Test
    void removeHealthTest() {
      testPlayer.removeHealth(-100);

      int expectedValue = 0;
      int actualValue = testPlayer.getHealth();

      assertEquals(expectedValue, actualValue);
    }

    @Test
    void removeNegativeHealthTest() {
      testPlayer.removeHealth(-101);
      int expectedValue = 0;
      int actualValue = testPlayer.getHealth();
      assertEquals(expectedValue, actualValue);
    }

    @Test
    void addScoreTest() {
      testPlayer.addScore(100);
      int expectedValue = 200;
      int actualValue = testPlayer.getScore();
      assertEquals(expectedValue, actualValue);
    }

    @Test
    void addNegativeScoreTest() {
      assertThrows(IllegalArgumentException.class, () -> testPlayer.addScore(-101));
    }

    @Test
    void removeScoreTest() {
      testPlayer.removeScore(-100);
      int expectedValue = 0;
      int actualValue = testPlayer.getScore();
      assertEquals(expectedValue, actualValue);
    }

    @Test
    void removeNegativeScoreTest() {
      testPlayer.removeScore(-101);
      int expectedValue = 0;
      int actualValue = testPlayer.getScore();
      assertEquals(expectedValue, actualValue);
    }
  }
}
