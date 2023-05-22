package edu.ntnu.idatt2001.paths.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2001.paths.model.manager.GameManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


@DisplayName("Test Class GameManager")
class GameManagerTest {
  GameManager testGameManager;
  Passage testPassage;
  Game testGame;

  @BeforeEach
  void setUp() {

    testPassage = new Passage("Test passage", "Test content");


    Player testPlayer = new PlayerBuilder("Test").health(100).score(100).build();

    Story testStory = new Story("Test story", testPassage);

    testGame = new Game(testPlayer, testStory);

    testGameManager = new GameManager(testGame);
  }

  @DisplayName("Test Constructor")
  @Nested
  class ConstructorTest {
    @DisplayName("Test constructor with valid parameters")
    @Test
    void testConstructorWithValidParameters() {

      GameManager testGameManager = new GameManager(testGame);

      Game actualValue = testGameManager.getGame();

      Game expectedValue = testGame;

      assertEquals(expectedValue, actualValue);

    }

  }

  @DisplayName("Test Accessors")
  @Nested
  class AccessorTest {

    @Test
    void getCurrentPassage() {

      Passage expectedValue = testPassage;

      Passage actualValue = testGameManager.getCurrentPassage();

      assertEquals(expectedValue, actualValue);
    }

    @Test
    void getGameWon() {

      testGameManager.isGameWon();

      boolean expectedValue = true;

      boolean actualValue = testGameManager.getGameWon();

      assertEquals(expectedValue, actualValue);

    }

    @Test
    void getGame() {

      Game expectedValue = testGame;

      Game actualValue = testGameManager.getGame();

      assertEquals(expectedValue, actualValue);

    }

    @Test
    void getIsPlayerAlive() {

      boolean expectedValue = true;

      boolean actualValue = testGameManager.getIsPlayerAlive();

      assertEquals(expectedValue, actualValue);

    }

    @Test
    void isGameWon() {

      boolean expectedValue = true;

      boolean actualValue = testGameManager.isGameWon();

      assertEquals(expectedValue, actualValue);
    }

  }

  @DisplayName("Mutator Test")
  @Nested
  class MutatorTest {
    @Test
    void setCurrentPassage() {
      Passage expectedValue = new Passage("Test passage", "Test content");

      testGameManager.setCurrentPassage(expectedValue);

      Passage actualValue = testGameManager.getCurrentPassage();

      assertEquals(expectedValue, actualValue);
    }
  }
}