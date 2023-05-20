package edu.ntnu.idatt2001.paths.controller;

import javafx.stage.Stage;

public class Controller {
  Stage stage;

  public Controller(Stage stage, double width, double height) {
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
