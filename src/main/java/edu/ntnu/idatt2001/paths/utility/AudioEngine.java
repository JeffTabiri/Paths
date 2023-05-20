package edu.ntnu.idatt2001.paths.utility;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 * This class is responsible for playing the music in the game.
 * It utilizes the singleton pattern to ensure that only one instance of the class is created.
 * This ensures that the music is not played multiple times at once.
 * It also utilizes the state pattern to ensure that the correct music is played at the correct time.
 * Finally game states are stored in an enum in the GameStates class and the music is stored in the resources folder.
 *
 * @author Elementum
 */
public class AudioEngine {

    private static AudioEngine audioEngine;

    GameStates currentState;

    MediaPlayer mediaPlayer;

    /**
     * Constructor for the AudioEngine class.
     */
    private AudioEngine() {
      currentState = GameStates.MAIN_MENU;
      mediaPlayer = new MediaPlayer(new Media(AudioEngine.class.getResource(GameStates.MAIN_MENU.getValue()).toString() + "LOOP.wav"));

      mediaPlayer.play();
      mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

    }

  /**
   * This method returns the instance of the AudioEngine class.
   * @return the instance of the AudioEngine class
   */
  public static AudioEngine getInstance() {

      if (audioEngine == null) {
        audioEngine = new AudioEngine();
      }


      return audioEngine;
    }



    /**
     * This method plays the music for the current state.
     *
     * @param state the current state of the game
     */
    public void playMusic(GameStates state) {

      if (state.getValue() == null) {
      }

      else if (!state.equals(currentState)) {

        mediaPlayer.stop();

        Media media = new Media(AudioEngine.class.getResource(state.getValue()).toString() + "LOOP.wav");

        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.play();

        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

        currentState = state;

      }

    }


    public void mute() {
      mediaPlayer.setVolume(0);
    }

    public void unmute() {
      mediaPlayer.setVolume(0.5);
    }

}

