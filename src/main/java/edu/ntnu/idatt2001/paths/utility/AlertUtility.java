package edu.ntnu.idatt2001.paths.utility;

import edu.ntnu.idatt2001.paths.scenes.startscene.StartScene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

import java.util.Optional;

public class AlertUtility {

  /**
   * Shows an error alert with the given header text and content text.
   *
   * @param headerText  the header text of the alert
   * @param contentText the content text of the alert
   */
  public static boolean  showErrorAlert(String headerText, String contentText) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();


    return alert.getResult() == ButtonType.OK;
  }

  public static void showConfirmationAlert(Stage stage) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Exit");
    alert.setHeaderText("Are you sure you want to exit?");
    alert.setContentText("You will lose all unsaved progress.");
    Optional<ButtonType> result = alert.showAndWait();

    if (result.get() == ButtonType.OK) {
      stage.setScene(new StartScene(stage, stage.getWidth(), stage.getHeight()).getScene());
    }

  }

  public static boolean showSaveAlert(Stage stage) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Save");
    alert.setHeaderText("Are you sure you want to save?");
    alert.setContentText("You will overwrite any previous save.");
    Optional<ButtonType> result = alert.showAndWait();

    if (result.get() == ButtonType.OK) {
      stage.setScene(new StartScene(stage, stage.getWidth(), stage.getHeight()).getScene());
      return true;
    }

    return false;

  }

}
