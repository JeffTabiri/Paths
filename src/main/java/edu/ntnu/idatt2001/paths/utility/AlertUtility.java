package edu.ntnu.idatt2001.paths.utility;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

/**
 * <h1>AlertUtility</h1>
 * {@code AlertUtility} is a utility class for showing alerts.
 * This class is responsible for showing alerts.
 *
 * @version 1.0
 * @since 06/04/2023
 * @author Elementum
 */
public class AlertUtility {

  /**
   * Private constructor to prevent instantiation.
   */
  private AlertUtility() {
  }

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
  }

  /**
   * Shows a death alert with the given header text and content text.
   *
   * @param headerText is the header text of the alert
   * @param contentText is the content text of the alert
   * @return  true if the user presses OK, false if the user presses CANCEL
   */
  public static boolean showDeathAlert(String title, String headerText, String contentText) {
    Alert alert = new Alert(Alert.AlertType.ERROR);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();
    return alert.getResult() == ButtonType.OK || alert.getResult() == ButtonType.CANCEL;
  }


  /**
   * Shows an information alert with the given header text and content text.
   *
   * @param headerText  the header text of the alert
   * @param contentText the content text of the alert
   */
  public static boolean showConfirmationAlert(String headerText, String contentText, String title) {

    Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(contentText);
    alert.showAndWait();
    return alert.getResult() == ButtonType.OK;

  }

}
