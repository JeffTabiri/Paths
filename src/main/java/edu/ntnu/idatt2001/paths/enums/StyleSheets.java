package edu.ntnu.idatt2001.paths.enums;


import edu.ntnu.idatt2001.paths.model.manager.OptionManager;

/**
 * <h1>StyleSheets</h1>
 * This is an enum class which contains the different style sheets.
 * The style sheets are used to determine which style sheet should be used at a given time.
 * Utilized by the {@code OptionManager} class.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 1.0
 * @see OptionManager
 * @since 06/02/2023
 */
public enum StyleSheets {

  //Style sheet addresses
  DARK_THEME("css/Dark.css"),
  LIGHT_THEME("css/Light.css");


  //Constant value
  private final String value;


  /**
   * The constructor {@code StyleSheets} sets the value of the style sheet.
   *
   * @param value is the file address of the style sheet.
   */
  StyleSheets(String value) {
    this.value = value;
  }

  /**
   * The method {@code getValue} returns the value of the style sheet.
   *
   * @return the value of the style sheet. Returns a string.
   */
  public String getValue() {
    return value;
  }

}
