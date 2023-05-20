package edu.ntnu.idatt2001.paths.controller;

import edu.ntnu.idatt2001.paths.controller.Controller;
import edu.ntnu.idatt2001.paths.controller.StartController;
import edu.ntnu.idatt2001.paths.model.Achievement;
import edu.ntnu.idatt2001.paths.model.AchievementList;
import edu.ntnu.idatt2001.paths.model.goals.GoldGoal;
import edu.ntnu.idatt2001.paths.model.goals.HealthGoal;
import edu.ntnu.idatt2001.paths.model.goals.ScoreGoal;
import edu.ntnu.idatt2001.paths.view.StartView;
import javafx.scene.Scene;
import javafx.stage.Stage;



public class AchievementController extends Controller {

    public AchievementController(Stage primaryStage, double width, double height) {
        super(primaryStage, width, height);
    }

  AchievementList achievementList = AchievementList.getInstance();


  public void goBackHandler() {
    StartView startView = new StartView(new StartController(getStage(), getWidth(), getHeight()));
    getStage().setScene(new Scene(startView.asParent(), getWidth(), getHeight()));
  }

  public void addAchievementHandler(String goalType, String goalValue) {

    if (goalType.isEmpty()) {
      throw new IllegalArgumentException("Goal type cannot be empty");
    }

    if (goalValue.isEmpty()) {
      throw new IllegalArgumentException("Goal value cannot be empty");
    }

    try {
      Integer.parseInt(goalValue);
    } catch (NumberFormatException e) {
      throw new IllegalArgumentException("Goal value must be a number");
    }

    if (goalType.equals("Health")) {
      achievementList.addAchievement(new Achievement(new HealthGoal(Integer.parseInt(goalValue)), "Sum of health", "You have reached" + goalValue + " health"));
    }
    if (goalType.equals("Gold")) {
      achievementList.addAchievement(new Achievement(new GoldGoal(Integer.parseInt(goalValue)), "Sum of gold", "You have reached " + goalValue + " gold"));

    }
    if (goalType.equals("Score")) {
      achievementList.addAchievement(new Achievement(new ScoreGoal(Integer.parseInt(goalValue)), "Sum of score", "You have reached " + goalValue + " score"));
    }

  }

}
