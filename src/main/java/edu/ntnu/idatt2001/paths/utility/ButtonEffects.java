package edu.ntnu.idatt2001.paths.utility;

import javafx.animation.ScaleTransition;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

public class ButtonEffects {

    //Cursor images
    static String cursorGrab = "images/cursor/cursor_grab.png";
    static String cursorGrabbing = "images/cursor/cursor_grabbing.png";


    public static void addCursorImageChange(Button button, Scene scene) {

        button.setOnMouseEntered(event -> {
            scene.setCursor(new ImageCursor(new javafx.scene.image.Image(cursorGrabbing)));
        });

        button.setOnMouseExited(event -> {
            scene.setCursor(new ImageCursor(new javafx.scene.image.Image(cursorGrab)));
        });
    }

    public static void audioOnEnter() {
        Media clickSound = new Media(ButtonEffects.class.getResource("/audio/buttoneffects/MenuHover.wav").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(clickSound);
        mediaPlayer.play();
    }


    private static void audioOnPressed() {
        Media clickSound = new Media(ButtonEffects.class.getResource("/audio/buttoneffects/MenuSelect.mp3").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(clickSound);
        mediaPlayer.play();
    }

    public static void animationChange(Button button) {

    }

    public static void animationChangeEntered(Button button) {
        scaleOnHover(button);
        audioOnEnter();
    }

    public static void animationChangeExited(Button button) {

    }

    public static void animationChangePressed(Button button) {
        audioOnPressed();


    }

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

    public static void buttonPressed(Button button) {
        audioOnPressed();
    }

    public static void buttonHover(Button button) {
        audioOnEnter();
        scaleOnHover(button);
    }

    public static void buttonExit(Button button) {
        scaleOnExit(button);
    }


}
