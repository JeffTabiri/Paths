package edu.ntnu.idatt2001.paths.model.manager;

import edu.ntnu.idatt2001.paths.enums.GameStates;
import java.util.Objects;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * This class is responsible for playing the music in the game.
 * It utilizes the singleton pattern to ensure
 * that only one instance of the class is created.
 * This ensures that the music is not played multiple times at once.
 * It also utilizes the state pattern to ensure that
 * the correct music is played at the correct time.
 * Finally game states are stored in an enum in the
 * GameStates class and the music is stored in the resources' folder.
 *
 * @author Elementum
 */
public class AudioManager {

  private static AudioManager audioManager = null;

  GameStates currentState;

  MediaPlayer mediaPlayer;


  /**
   * Constructor for the AudioEngine class.
   */
  private AudioManager() {

    currentState = GameStates.MAIN_MENU;

    mediaPlayer = new MediaPlayer(
            new Media(Objects.requireNonNull(AudioManager.class.getResource(
                    GameStates.MAIN_MENU.getValue())) + "LOOP.wav"));

    mediaPlayer.play();

    mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

  }


  /**
   * Constructor for the AudioManager class.
   *
   * @return the instance of the AudioManager class
   */
  public static synchronized AudioManager getInstance() {

    if (audioManager == null) {
      audioManager = new AudioManager();
    }
    return audioManager;
  }


  /**
   * This method plays the music for the current state.
   *
   * @param state the current state of the game
   */
  public void playMusic(GameStates state) {


    if (state.getValue() == null) {

      mediaPlayer.stop();

      currentState = state;

    } else if (!state.equals(currentState)) {

      mediaPlayer.stop();

      Media media = new Media(Objects.requireNonNull(
              AudioManager.class.getResource(
                      state.getValue())) + "LOOP.wav");

      mediaPlayer = new MediaPlayer(media);

      mediaPlayer.play();

      mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

      currentState = state;

    }

  }

}

