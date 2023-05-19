package edu.ntnu.idatt2001.paths.scenes.startscene.helpScene;

import edu.ntnu.idatt2001.paths.scenes.startscene.startscene.StartController;
import edu.ntnu.idatt2001.paths.scenes.startscene.startscene.StartModel;
import edu.ntnu.idatt2001.paths.scenes.startscene.startscene.StartView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelpModel {

  Stage stage;

    public HelpModel(Stage stage) {
        this.stage = stage;
    }

  public void goBackHandler() {

    StartModel model = new StartModel(stage);
    StartController controller = new StartController(model);
    StartView view = new StartView(model, controller);
    Scene scene = new Scene(view.asParent(), stage.getWidth(), stage.getHeight());
    stage.setScene(scene);


  }

  public Stage getStage() {
    return stage;
  }
}
