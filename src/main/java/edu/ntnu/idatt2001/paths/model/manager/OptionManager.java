package edu.ntnu.idatt2001.paths.model.manager;

import edu.ntnu.idatt2001.paths.enums.StyleSheets;

/**
 * <h1>OptionManager</h1>
 * The {@code OptionManager} class is used to manage the options of the game.
 * It is implemented as a singleton to ensure that there is only one instance of the class.
 * The class is responsible for options such as dark mode, sound and fullscreen.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 1.0
 * @see AudioManager
 * @see StyleSheets
 * @since 06/02/2023
 */
public class OptionManager {

  private static OptionManager optionManager;

  private String currentStyleSheet;

  private Boolean isDarkMode;

  private Boolean isFullScreen;


  /**
   * The constructor {@code OptionManager} sets the default values of the options.
   */
  private OptionManager() {
    isDarkMode = false;
    isFullScreen = false;
    currentStyleSheet = StyleSheets.LIGHT_THEME.getValue();
  }

  /**
   * The method {@code getInstance} returns the instance of the class.
   *
   * @return the instance of the {@code OptionManager} class.
   */
  public static synchronized OptionManager getInstance() {
    if (optionManager == null) {
      optionManager = new OptionManager();
    }
    return optionManager;
  }

  /**
   * The method {@code setIsDarkMode} sets the dark mode option.
   *
   * @param isDarkMode is the boolean value of the dark mode option.
   *                   If true, the dark mode is enabled.
   *                   If false, the dark mode is disabled.
   */
  public void setIsDarkMode(boolean isDarkMode) {

    if (isDarkMode) {
      currentStyleSheet = StyleSheets.DARK_THEME.getValue();
    } else {
      currentStyleSheet = StyleSheets.LIGHT_THEME.getValue();
    }

    this.isDarkMode = isDarkMode;
  }

  /**
   * The method {@code setIsFullScreen} sets the full screen option.
   *
   * @param isFullScreen is the boolean value of the full screen option.
   *                     If true, the full screen is enabled.
   *                     If false, the full screen is disabled.
   */
  public void setIsFullScreen(boolean isFullScreen) {
    this.isFullScreen = isFullScreen;
  }

  /**
   * The method {@code getIsDarkMode} returns the dark mode option.
   *
   * @return the dark mode option. Returns a boolean.
   */
  public Boolean getIsDarkMode() {
    return isDarkMode;
  }

  /**
   * The method {@code getIsFullScreen} returns the full screen option.
   *
   * @return the full screen option. Returns a boolean.
   */
  public Boolean getIsFullScreen() {
    return isFullScreen;
  }

  /**
   * The method {@code getCurrentStyleSheet} returns the current style sheet.
   *
   * @return the current style sheet. Returns a string.
   */
  public String getCurrentStyleSheet() {
    return currentStyleSheet;
  }
}

