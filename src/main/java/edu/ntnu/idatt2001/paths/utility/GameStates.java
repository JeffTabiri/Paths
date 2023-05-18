package edu.ntnu.idatt2001.paths.utility;

/**
 * This is an enum class which contains the different game states.
 * The game states are used to determine which music should be played at a given time.
 *
 * @author Jeffrey Yaw Annor Tabiri
 */
public enum GameStates {

  // Game state constants which are used to determine which music should be played at a given time.

  MAIN_MENU("/audio/background/Title Theme/"),

  HELP_MENU(null),

  EXPLORE("/audio/background/Exploring The Unknown/"),

  ENEMY("/audio/background/Prepare for Battle!/"),

  BOSS("/audio/background/Decisive Battle/"),

  REST(("/audio/background/Take some rest and eat some food!/")),

  SHOP(null);


  private String value;
  private GameStates(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

}

