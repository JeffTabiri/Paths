package edu.ntnu.idatt2001.paths.controller;

import edu.ntnu.idatt2001.paths.model.Achievement;
import edu.ntnu.idatt2001.paths.model.goals.GoldGoal;
import edu.ntnu.idatt2001.paths.model.goals.HealthGoal;
import edu.ntnu.idatt2001.paths.model.goals.InventoryGoal;
import edu.ntnu.idatt2001.paths.model.goals.ScoreGoal;
import edu.ntnu.idatt2001.paths.model.manager.AchievementManager;
import edu.ntnu.idatt2001.paths.view.StartView;
import java.util.List;
import javafx.scene.Scene;
import javafx.stage.Stage;



/**
 * <h1>AchievementController</h1>
 * Controller class for the achievement view.
 *
 * @author Elementum
 * @version 1.0
 * @see AchievementManager
 * @see Achievement
 * @since 06/04/2023
 */
public class AchievementController extends Controller {

  /**
   * Creates a controller instance and creates the achievement view.
   *
   * @param primaryStage the stage to be used.
   */
  public AchievementController(Stage primaryStage) {
    super(primaryStage);
  }

  AchievementManager achievementManager = AchievementManager.getInstance();


  public void onActionGoBack() {
    StartView startView = new StartView(new StartController(getStage()));
    getStage().setScene(new Scene(startView.asParent()));
  }

  /**
   * Adds an achievement handler to the achievement manager.
   *
   * @param goalType the type of goal to create. Can be "Health", "Gold" or "Score".
   * @param goalValue the value of the goal to create.
   */
  public void onActionAddAchievement(String goalType, String goalValue) {

    if (goalType == null) {
      throw new IllegalArgumentException("Please choose a goal type");
    }

    if (goalValue == null) {
      throw new IllegalArgumentException("Please enter a goal");
    }

    if (goalType.isEmpty()) {
      throw new IllegalArgumentException("Goal type cannot be empty");
    }

    if (goalValue.isEmpty()) {
      throw new IllegalArgumentException("Goal value cannot be empty");
    }

    if (goalType.equalsIgnoreCase("Health")) {

      try {
        Integer.parseInt(goalValue);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Health value must be a number");
      }

      achievementManager.addAchievement(new Achievement(new HealthGoal(Integer.parseInt(goalValue)),
              "An apple a day keeps the doctor away",
              "You achieved " + goalValue + " health!"));
    }

    if (goalType.equalsIgnoreCase("Gold")) {

      try {
        Integer.parseInt(goalValue);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Gold value must be a number");
      }

      achievementManager.addAchievement(new Achievement(new GoldGoal(Integer.parseInt(goalValue)),
              "Mafia Boss",
              "You gained " + goalValue + " gold!"));

    }

    if (goalType.equalsIgnoreCase("Score")) {

      try {
        Integer.parseInt(goalValue);
      } catch (NumberFormatException e) {
        throw new IllegalArgumentException("Score value must be a number");
      }

      achievementManager.addAchievement(new Achievement(new ScoreGoal(Integer.parseInt(goalValue)),
              "Score Collector",
              "You gained" + goalValue + " points!"));
    }

    if (goalType.equalsIgnoreCase("Inventory")) {
      achievementManager.addAchievement(new Achievement(new InventoryGoal(List.of(goalValue)),
              "Kleptomaniac",
              "You found " + goalValue));
    }

  }

  /**
   * Checks the type of goal of an achievement.
   *
   * @param achievement the achievement to check.
   * @return the type of goal of the achievement.
   */
  public String achievementGoalCheck(Achievement achievement) {

    if (achievement.getGoal() instanceof HealthGoal) {
      return "HEALTH";
    }

    if (achievement.getGoal() instanceof InventoryGoal) {
      return "INVENTORY";
    }

    if (achievement.getGoal() instanceof GoldGoal) {
      return "GOLD";
    }

    if (achievement.getGoal() instanceof ScoreGoal) {
      return "SCORE";
    }

    throw new IllegalArgumentException("Invalid goal type");
  }

  public void onActionRemoveAchievement(Achievement achievement) {
    achievementManager.removeAchievement(achievement);
  }

}
