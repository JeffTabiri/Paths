package edu.ntnu.idatt2001.paths.controller;

import edu.ntnu.idatt2001.paths.model.manager.OptionManager;
import javafx.stage.Stage;

/**
 * <h1>OptionController</h1>
 * Controller class for the option view.
 * Responsible for saving the settings in the option view.
 *
 * @author Elementum
 * @version 1.0
 * @since 06/04/2023
 * @see Controller
 * @see OptionManager
 */
public class OptionController extends Controller {
  OptionManager optionManager = OptionManager.getInstance();


  /**
   * Creates controller instance and creates the option view.
   *
   * @param stage the stage to be used.
   */
  public OptionController(Stage stage) {
    super(stage);
  }


  /**
   * Responsible for saving the settings in the option view.
   *
   * @param darkMode true if dark mode, false if not.
   * @param fullscreen true if fullscreen, false if not.
   */
  public void saveSettings(boolean darkMode, boolean fullscreen) {
    setFullScreen(fullscreen);
    setDarkMode(darkMode);
  }


  /**
   * Responsible for setting the screen to dark mode or not.
   *
   * @param darkMode true if dark mode, false if not.
   */
  private void setDarkMode(boolean darkMode) {
    optionManager.setIsDarkMode(darkMode);
  }


  /**
   * Responsible for setting the screen to fullscreen or not.
   *
   * @param fullscreen true if fullscreen, false if not.
   */
  private void setFullScreen(boolean fullscreen) {
    stage.setMaximized(fullscreen);
    optionManager.setIsFullScreen(fullscreen);
  }


}
