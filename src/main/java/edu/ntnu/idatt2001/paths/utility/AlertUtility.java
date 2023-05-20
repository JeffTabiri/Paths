package edu.ntnu.idatt2001.paths.utility;

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
  public static boolean showErrorAlert(String headerText, String contentText) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();
    Optional<ButtonType> result = alert.showAndWait();

    return result.get() == ButtonType.OK;
  }

  public static boolean showConfirmationAlert(String headerText, String contentText, String title) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    Optional<ButtonType> result = alert.showAndWait();

    return result.get() == ButtonType.OK;
  }

  public static boolean showSaveAlert(Stage stage) {
    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle("Save");
    alert.setHeaderText("Are you sure you want to save?");
    alert.setContentText("You will overwrite any previous save.");
    Optional<ButtonType> result = alert.showAndWait();

    if (result.get() == ButtonType.OK) {
      return true;
    }

    return false;

  }

}
