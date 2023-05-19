package edu.ntnu.idatt2001.paths.scenes.startscene.achievementScene;

import edu.ntnu.idatt2001.paths.Achievement;

public class AchievementController {
  AchievementModel model;

  public AchievementController(AchievementModel model) {
    this.model = model;
  }

  public void goBackHandler() {
    model.goBackHandler();
  }

  public void addAchievementHandler(Achievement achievement){
    model.addAchievementHandler(achievement);
  }

}
