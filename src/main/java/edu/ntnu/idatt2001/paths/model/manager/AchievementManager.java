package edu.ntnu.idatt2001.paths.model.manager;

import edu.ntnu.idatt2001.paths.model.Achievement;
import edu.ntnu.idatt2001.paths.model.goals.GoldGoal;
import edu.ntnu.idatt2001.paths.model.goals.HealthGoal;
import edu.ntnu.idatt2001.paths.model.goals.InventoryGoal;
import edu.ntnu.idatt2001.paths.model.goals.ScoreGoal;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>AchievementList</h1>
 * {@code AchievementManager} is a singleton class that contains
 * a list of all the achievements in the game.
 * It is used to keep track of the achievements that the player has reached.
 * The class is implemented as a singleton to ensure
 * that there is only one instance of the class.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 1.0
 * @since 06/02/2023
 * @see Achievement
 */
public class AchievementManager {

  private static AchievementManager achievementManager = null;
  List<Achievement> achievements = null;

  private AchievementManager() {
  }

  /**
   * The method {@code getInstance} returns the instance of the class.
   *
   * @return the instance of the class.
   */
  public static synchronized AchievementManager getInstance() {
    if (achievementManager == null) {
      achievementManager = new AchievementManager();
      achievementManager.achievements = new ArrayList<>();
      achievementManager.setAchievements();
    }
    return achievementManager;
  }


  /**
  * The method {@code addAchievement} adds an achievement to the list of achievements.
   *
  * @param achievement the achievement to be added.
  */
  public void addAchievement(Achievement achievement) {
    achievements.add(achievement);
  }


  /**
  * The method {@code getAchievements} returns the list of achievements.
  *
  * @return the list of achievements.
  */
  public List<Achievement> getAchievements() {
    return achievements;
  }


  /**
   * The method {@code removeAchievement} removes an achievement from the list of achievements.
   *
   * @param achievement the achievement to be removed.
   */
  public void removeAchievement(Achievement achievement) {
    achievements.remove(achievement);
  }


  /**
  * The method {@code getNumberOfAchievements} adds a set of already-made achievements.
  */
  private void setAchievements() {

    achievementManager.addAchievement(new Achievement(new GoldGoal(1000),
            "Money on my mind?",
            "You have gained 1000 gold"));

    achievementManager.addAchievement(new Achievement(new HealthGoal(150),
            "Healthy Player!",
            "You have gained 150 health"));

    achievementManager.addAchievement(new Achievement(new GoldGoal(2000),
            "BIG MONEY!",
            "You have 2000 gold"));

    ArrayList<String> inventory = new ArrayList<>();
    inventory.add("Excalibur");

    achievementManager.addAchievement(new Achievement(new InventoryGoal(inventory),
            "The Greatest Sword of All Time",
            "You attained Excalibur"));

    achievementManager.addAchievement(new Achievement(new GoldGoal(5000),
            "The chase for the greatest riches.",
            "You have 5000 gold"));

    achievementManager.addAchievement(new Achievement(new GoldGoal(10000),
            "Whole world on your shoulders.",
            "You have 10000 gold"));

    achievementManager.addAchievement(new Achievement(new ScoreGoal(200),
            "Chasing the high score.",
            "You have 200 score"));

    achievementManager.addAchievement(new Achievement(new ScoreGoal(700),
            "POINTS FOR DAYS!",
            "You have 700 score"));

    achievementManager.addAchievement(new Achievement(new ScoreGoal(200),
            "The chase for the greatest riches.",
            "You have 200 score"));

    achievementManager.addAchievement(new Achievement(new InventoryGoal(List.of("Zephyr")),
            "A bow of the finest quality",
            "You attained Zephyr"));

    achievementManager.addAchievement(new Achievement(new HealthGoal(300),
            "UNDYING!",
            "You have gained 300 health"));

    }

  }

