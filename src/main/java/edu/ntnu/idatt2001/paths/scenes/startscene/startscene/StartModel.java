package edu.ntnu.idatt2001.paths.scenes.startscene.startscene;

import edu.ntnu.idatt2001.paths.scenes.startscene.achievementScene.AchievementController;
import edu.ntnu.idatt2001.paths.scenes.startscene.achievementScene.AchievementModel;
import edu.ntnu.idatt2001.paths.scenes.startscene.achievementScene.AchievementView;
import edu.ntnu.idatt2001.paths.scenes.startscene.helpScene.HelpController;
import edu.ntnu.idatt2001.paths.scenes.startscene.helpScene.HelpModel;
import edu.ntnu.idatt2001.paths.scenes.startscene.helpScene.HelpView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartModel {
  Stage stage;

    public StartModel(Stage stage) {
        this.stage = stage;
    }


  public void goHelpHandler() {

    HelpModel model = new HelpModel(stage);
    HelpController controller = new HelpController(model);
    HelpView view = new HelpView(controller, model);
    Scene scene = new Scene(view.getView(), stage.getWidth(), stage.getHeight());
    stage.setScene(scene);

  }

  public void goNewGameHandler() {
    System.out.println("Go new game");
  }

  public void goLoadGameHandler() {
    System.out.println("Go load game");
  }

  public void goAchievementsHandler() {
    AchievementModel model = new AchievementModel(stage);
    AchievementController controller = new AchievementController(model);
    AchievementView view = new AchievementView(controller, model);
    Scene scene = new Scene(view.asParent(), stage.getWidth(), stage.getHeight());
    stage.setScene(scene);

  }

  public void goExitHandler() {
    stage.close();
  }

}
