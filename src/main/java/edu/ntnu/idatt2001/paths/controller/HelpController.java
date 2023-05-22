package edu.ntnu.idatt2001.paths.controller;


import edu.ntnu.idatt2001.paths.view.StartView;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * <h1>HelpController</h1>
 * The controller class for the application.
 * This class is responsible for handling the logic for the help view.
 *
 * @author Elementum
 * @version 1.0
 * @since 06/04/2023
 */
public class HelpController extends Controller {



  /**
   * Constructor for the controller.
   *
   * @param stage the stage to set the scene on.
   */
  public HelpController(Stage stage) {
    super(stage);
  }


  /**
   * Responsible for create a new StartView and set the scene to the stage.
   */
  public void onActionReturn() {
    StartView view = new StartView(new StartController(getStage()));
    getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
  }

}
