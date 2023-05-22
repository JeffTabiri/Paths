package edu.ntnu.idatt2001.paths.model.enums;

import edu.ntnu.idatt2001.paths.model.manager.AudioManager;

/**
 * <h1>GameStates</h1>
 * This is an enum class which contains the different game states.
 * The game states are used to determine which music should be played at a given time.
 *
 * @author Jeffrey Yaw Annor Tabiri
 * @version 1.0
 * @since 06/02/2023
 * @see AudioManager
 */
public enum GameStates {

  // Game state constants which are used to determine which music should be played at a given time.

  MAIN_MENU("/audio/background/Title Theme/"),

  BEGINNING("/audio/background/And The Journey Begins"),

  EXPLORE("/audio/background/Exploring The Unknown/"),

  CAVE("/audio/background/The Icy Cave/"),

  HELP_MENU(null),

  ENEMY("/audio/background/Prepare for Battle!/"),

  BOSS("/audio/background/Decisive Battle/"),

  REST(("/audio/background/Take some rest and eat some food!/")),

  SHOP("/audio/background/Minigame/");


  private final String value;

  /**
   * The constructor {@code GameStates} sets the address of the music state.
   *
   * @param value is the address of the music state, which is a string.
   */
  GameStates(String value) {
    this.value = value;
  }

  /**
   * The method {@code getValue} returns the address of the music state.
   *
   * @return the address of the music state, which is a string.
   */
  public String getValue() {
    return value;
  }

}

