package edu.ntnu.idatt2001.paths.scenes.startscene.startscene;

public class StartController {
  StartModel model;

    public StartController(StartModel model) {
        this.model = model;
    }

  public void goNewGame() {
    model.goNewGameHandler();
  }

  public void goLoadHandler() {
    model.goLoadGameHandler();
  }

  public void goAchievement() {
    model.goAchievementsHandler();
  }

  public void goHelpHandler() {
        model.goHelpHandler();
  }

  public void goExitHandler() {
      model.goExitHandler();
  }




}
