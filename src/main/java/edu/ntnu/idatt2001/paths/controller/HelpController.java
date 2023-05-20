package edu.ntnu.idatt2001.paths.controller;

import edu.ntnu.idatt2001.paths.view.StartView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelpController extends Controller{

  public HelpController(Stage stage, double width, double height) {
    super(stage, width, height);
  }


  public void goBackHandler() {
    StartView view = new StartView(new StartController(getStage(), getWidth(), getHeight()));
    getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
  }


}
