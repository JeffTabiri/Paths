package edu.ntnu.idatt2001.paths.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idatt2001.paths.model.goals.HealthGoal;
import edu.ntnu.idatt2001.paths.model.manager.AchievementManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class AchievementManagerTest {

  AchievementManager testAchievementManager;
  Achievement testAchievement;

  @BeforeEach
  void setUp() {
    testAchievementManager = AchievementManager.getInstance();
    testAchievement = new Achievement(new HealthGoal(100), "Test title", "Test description");
  }


  @DisplayName("Test Accessors")
  @Nested
  class AccessorsTest {
    @Test
    void getInstance() {
      AchievementManager expectedValue = testAchievementManager;
      AchievementManager actualValue = AchievementManager.getInstance();

      assertEquals(expectedValue, actualValue);
    }

    @Test
    void getAchievements() {
      testAchievementManager.addAchievement(testAchievement);

      boolean actualValue = testAchievementManager.getAchievements().contains(testAchievement);

      assertTrue(actualValue);

    }
  }


  @DisplayName("Test Mutators")
  @Nested
  class MutatorTest {

    @Test
    void addAchievement() {
      testAchievementManager.addAchievement(testAchievement);

      boolean actualValue = testAchievementManager.getAchievements().contains(testAchievement);

      assertTrue(actualValue);

    }

    @Test
    void removeAchievement() {
      testAchievementManager.addAchievement(testAchievement);
      testAchievementManager.removeAchievement(testAchievement);

      boolean actualValue = testAchievementManager.getAchievements().contains(testAchievement);

      assertFalse(actualValue);
    }
  }
}