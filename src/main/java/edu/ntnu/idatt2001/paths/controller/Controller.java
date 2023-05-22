package edu.ntnu.idatt2001.paths.controller;

import javafx.stage.Stage;

/**
 * The controller class for the application.
 *
 * @author Elementum
 * @version 1.0
 * @since 06/04/2023
 */
public class Controller {
  Stage stage;

  public Controller(Stage stage) {
    this.stage = stage;
  }

  public Stage getStage() {
    return stage;
  }

  public double getWidth() {
    return stage.getWidth();
  }

  public double getHeight() {
    return stage.getHeight();
  }
}
