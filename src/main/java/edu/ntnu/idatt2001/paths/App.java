package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.paths.controller.StartController;
import edu.ntnu.idatt2001.paths.view.StartView;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;


/**
 * <h1>App</h1>
 * The main class of the application.
 * This class is responsible for starting the application.
 *
 * @version 1.0
 * @since 06/04/2023
 * @author  Elementum
 */
public class App extends Application {

  public static void main(String[] args) {
    launch(args);
  }

  @Override
  public void start(Stage primaryStage) {

    //Font load
    Font.loadFont(getClass().getResourceAsStream("/textFont/PressStart2P-Regular.ttf"), 14);

    //Primary Stage Config
    primaryStage.setWidth(800);
    primaryStage.setHeight(900);
    primaryStage.setMinWidth(800);
    primaryStage.setMinHeight(900);
    primaryStage.setTitle("Paths");


    //Start View
    StartController controller = new StartController(primaryStage);
    StartView view = new StartView(controller);
    Scene scene = new Scene(view.asParent());

    //Set Scene
    primaryStage.setScene(scene);
    primaryStage.show();
  }


  /**
   * Function whenever the application is closed.
   */
  @Override
  public void stop() {
    Logger.getLogger(getClass().getName()).info("Application closed");
  }

}
