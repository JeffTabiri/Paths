package edu.ntnu.idatt2001.paths.model.utility;

import java.util.Objects;
import javafx.animation.ScaleTransition;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

/**
 * <h1>ButtonEffects</h1>
 * {@code ButtonEffects} is a utility class for adding effects to buttons.
 *
 * @author Elementum
 * @version 1.0
 * @since 06/04/2023
 */
public class ButtonUtility {

  /**
   * Private constructor to prevent instantiation.
   */
  private ButtonUtility() {
  }


  /**
   * Adds a sound effect to a button when the mouse enters the button.
   */
  public static void audioOnEnter() {
    Media clickSound = new Media(
            Objects.requireNonNull(
                    ButtonUtility.class.getResource(
                            "/audio/button-effects/MenuHover.wav")).toString());

    MediaPlayer mediaPlayer = new MediaPlayer(clickSound);
    mediaPlayer.play();
  }


  /**
  * Adds a sound effect to a button when the button is pressed.
  */
  private static void audioOnPressed() {
    Media clickSound = new Media(
            Objects.requireNonNull(
                    ButtonUtility.class.getResource(
                            "/audio/button-effects/MenuSelect.mp3")).toString());

    MediaPlayer mediaPlayer = new MediaPlayer(clickSound);
    mediaPlayer.play();
  }


  /**
   * Helper method for scaling a button on hover.
   *
   * @param button the button to scale.
   */
  private static void scaleOnHover(Button button) {
    ScaleTransition scaleTransition = new ScaleTransition();
    scaleTransition.setDuration(Duration.millis(100));
    scaleTransition.setNode(button);
    scaleTransition.setFromX(1);
    scaleTransition.setFromY(1);
    scaleTransition.setToX(1.25);
    scaleTransition.setToY(1.25);
    scaleTransition.autoReverseProperty().setValue(true);
    scaleTransition.play();
  }

  /**
   * Helper method for scaling a button on exit.
   *
   * @param button the button to scale.
   */
  private static void scaleOnExit(Button button) {
    ScaleTransition scaleTransition = new ScaleTransition();
    scaleTransition.setDuration(Duration.millis(100));
    scaleTransition.setNode(button);
    scaleTransition.setFromX(1.25);
    scaleTransition.setFromY(1.25);
    scaleTransition.setToX(1);
    scaleTransition.setToY(1);
    scaleTransition.autoReverseProperty().setValue(true);
    scaleTransition.play();
  }


  /**
   * Adds a button effect to a button when the button is pressed.
   *
   */
  public static void buttonPressed() {
    audioOnPressed();
  }


  /**
   * Adds a scale effect to a button when the mouse enters the button.
   *
   * @param button the button to add the effect to.
   */
  public static void buttonHover(Button button) {
    audioOnEnter();
    scaleOnHover(button);
  }


  /**
   * Adds a scale effect to a button when the mouse exits the button.
   *
   * @param button the button to add the effect to.
   */
  public static void buttonExit(Button button) {
    scaleOnExit(button);
  }

}
