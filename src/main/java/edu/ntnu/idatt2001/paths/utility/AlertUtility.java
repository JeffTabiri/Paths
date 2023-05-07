package edu.ntnu.idatt2001.paths.utility;

import javafx.scene.control.Alert;

public class AlertUtility {

  /**
   * Shows an error alert with the given header text and content text.
   *
   * @param headerText  the header text of the alert
   * @param contentText the content text of the alert
   */
  public static void showErrorAlert(String headerText, String contentText) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle("Error");
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();
  }

}
