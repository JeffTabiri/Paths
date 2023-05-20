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

      //TODO refactor this method to use the state pattern

      if (!state.equals(currentState)) {

        if (state.getValue() == null) {
          mediaPlayer.stop();
          currentState = null;
          return;
        }

        Media media = new Media(AudioEngine.class.getResource(state.getValue()).toString() + "FULL.wav");

        mediaPlayer = new MediaPlayer(media);

        mediaPlayer.play();

        mediaPlayer.setOnEndOfMedia(() -> {
          mediaPlayer.stop();
          mediaPlayer = new MediaPlayer(new Media(AudioEngine.class.getResource(state.getValue()).toString() + "LOOP.wav"));
          mediaPlayer.play();
          mediaPlayer.setVolume(0.5);
          mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
        });



        mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);

      }

      currentState = state;
    }


  /**
   * @deprecated
   * @param state the current state of the game
   */
  public void evaluateState(GameStates state) {

      if (state.getValue() == null) {
        mediaPlayer.stop();
        currentState = null;
      }

      if (!state.equals(currentState)) {

        currentState = state;

      }

      return;
    }

    public void mute() {
      mediaPlayer.setVolume(0);
    }

    public void unmute() {
      mediaPlayer.setVolume(0.5);
    }
}

