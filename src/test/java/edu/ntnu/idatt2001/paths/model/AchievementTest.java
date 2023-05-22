package edu.ntnu.idatt2001.paths.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2001.paths.model.goals.Goal;
import edu.ntnu.idatt2001.paths.model.goals.HealthGoal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;



@DisplayName("Test Class Achievement")
class AchievementTest {

  Achievement testAchievement;

  Goal testGoal;

  Player testPlayer;


  @BeforeEach
  void setUp() {
    testPlayer = new PlayerBuilder("Test player")
            .health(100)
            .build();

    testGoal = new HealthGoal(100);

    testAchievement = new Achievement(testGoal, "Test title", "Test description");

  }

  @DisplayName("Test Accessors")
  @Nested
  class AccessorsTest {

    @Test
    void getTitle() {

      String expectedValue = "Test title";
      String actualValue = testAchievement.getTitle();

      assertEquals(expectedValue, actualValue);
    }

    @Test
    void getDescription() {

      String expectedValue = "Test description";
      String actualValue = testAchievement.getDescription();

      assertEquals(expectedValue, actualValue);
    }

    @Test
    void getGoal() {
      Goal expectedValue = testGoal;
      Goal actualValue = testAchievement.getGoal();

      assertEquals(expectedValue, actualValue);
    }

    @Test
    void getIsFulfilled() {
      boolean expectedValue = false;
      boolean actualValue = testAchievement.getIsFulfilled();

      assertEquals(expectedValue, actualValue);
    }
  }

  @DisplayName("Test Mutators")
  @Nested
  class MutatorsTest {

    @Test
    void setIsFulfilled() {
      testAchievement.setIsFulfilled(true);
      boolean expectedValue = true;
      boolean actualValue = testAchievement.getIsFulfilled();

      assertEquals(expectedValue, actualValue);
    }
  }

}