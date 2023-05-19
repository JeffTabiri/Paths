package edu.ntnu.idatt2001.paths.scenes.startscene.achievementScene;

import edu.ntnu.idatt2001.paths.Achievement;
import edu.ntnu.idatt2001.paths.AchievementList;
import edu.ntnu.idatt2001.paths.scenes.startscene.startscene.StartController;
import edu.ntnu.idatt2001.paths.scenes.startscene.startscene.StartModel;
import edu.ntnu.idatt2001.paths.scenes.startscene.startscene.StartView;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AchievementModel {

  AchievementList achievementList = AchievementList.getInstance();

  Stage stage;

  public AchievementModel(Stage stage) {
    this.stage = stage;
  }

    public void goBackHandler() {

      StartModel model = new StartModel(stage);
      StartController controller = new StartController(model);
      StartView view = new StartView(model, controller);
      Scene scene = new Scene(view.asParent(), stage.getWidth(), stage.getHeight());
      stage.setScene(scene);

    }

    public void addAchievementHandler(Achievement achievement) {

      achievementList.addAchievement(achievement);

      Text achievementTitle = new Text(achievement.getTitle());
      Text achievementDescription = new Text(achievement.getDescription());
      Text achievementProgress = new Text(achievement.getIsFulfilled().toString());
      HBox achievementBox = new HBox();
      VBox achievementTextContainer = new VBox();
      achievementTextContainer.getChildren().addAll(achievementTitle, achievementDescription, achievementProgress);
      achievementTextContainer.setSpacing(10);

      Image achievementImage = new Image("images/cursor/cursor_grab.png", 64, 64, true, true);
      ImageView achievementImageView = new ImageView(achievementImage);
      achievementBox.getChildren().addAll(achievementImageView, achievementTextContainer);
      achievementBox.setSpacing(20);
      System.out.println("Add achievement");

    }


}
