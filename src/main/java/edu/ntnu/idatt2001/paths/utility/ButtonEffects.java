package edu.ntnu.idatt2001.paths.utility;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;


public class ButtonEffects {

    //Cursor images
    static String cursorGrab = "images/cursors/cursor_grab.png";
    static String cursorGrabbing = "images/cursors/cursor_grabbing.png";


    public static void addCursorImageChange(Button button, Scene scene) {

        button.setOnMouseEntered(event -> {
            scene.setCursor(new ImageCursor(new javafx.scene.image.Image(cursorGrabbing)));
        });

        button.setOnMouseExited(event -> {
            scene.setCursor(new ImageCursor(new javafx.scene.image.Image(cursorGrab)));
        });
    }

    public static void addAudioChange(Button button) {

        Media sound = new Media(ButtonEffects.class.getResource("/audio/buttoneffects/Menu Selection Click.wav").toString());

        button.setOnMouseEntered(event -> {

            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();

        });


    }
}
