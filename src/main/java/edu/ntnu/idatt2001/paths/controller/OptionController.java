package edu.ntnu.idatt2001.paths.controller;

import edu.ntnu.idatt2001.paths.model.OptionManager;
import edu.ntnu.idatt2001.paths.utility.AudioEngine;
import javafx.stage.Stage;

public class OptionController extends Controller {
  AudioEngine audioEngine = AudioEngine.getInstance();
  OptionManager optionManager = OptionManager.getInstance();
  public OptionController(Stage stage, double width, double height) {
    super(stage, width, height);
  }


    public void saveSettings(boolean darkMode, boolean sound, boolean fullscreen) {
    setFullScreen(fullscreen);
    setDarkMode(darkMode);
    setSound(sound);
  }


    private void setDarkMode(boolean darkMode) {
      optionManager.setDarkMode(darkMode);
    }

    private void setFullScreen(boolean fullscreen) {
      optionManager.setFullscreen(fullscreen);
    }

    private void setSound(boolean sound) {
    optionManager.setSound(sound);
    }


    public void returnHandler() {
    stage.close();
    }







}
