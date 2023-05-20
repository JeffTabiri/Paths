package edu.ntnu.idatt2001.paths.model;

import edu.ntnu.idatt2001.paths.App;
import edu.ntnu.idatt2001.paths.utility.AudioEngine;

public class OptionManager {
  AudioEngine audioEngine = AudioEngine.getInstance();

    private static OptionManager optionManager = null;

    String lightModeStyle  = "css/global.css";
    String darkModeStyle = "css/darkMode.css";

    String currentStyle = lightModeStyle;

    Boolean darkMode = false;

    Boolean sound = false;

    Boolean fullscreen = false;

    private OptionManager() {
    }

    public static synchronized OptionManager getInstance() {
      if (optionManager == null) {
        optionManager = new OptionManager();
      }
      return optionManager;
    }

    public void setDarkMode(Boolean darkMode) {

      if (darkMode) {
        currentStyle = darkModeStyle;
      }
      else {
        currentStyle = lightModeStyle;
      }

      this.darkMode = darkMode;
    }

    public void setSound(Boolean sound) {

      if (sound) {
        audioEngine.mute();
      }
      else {
        audioEngine.unmute();
      }

      this.sound = sound;
    }

    public void setFullscreen(Boolean fullscreen) {

      if (fullscreen) {
        App.getStage().setMaximized(fullscreen);
      }
      else {
        App.getStage().setMaximized(fullscreen);
      }

      this.fullscreen = fullscreen;
    }

    public Boolean getDarkMode() {
      return darkMode;
    }

    public Boolean getSound() {
      return sound;
    }

    public Boolean getFullscreen() {
      return fullscreen;
    }

    public String getCurrentStyle() {
      return currentStyle;
    }




  }

