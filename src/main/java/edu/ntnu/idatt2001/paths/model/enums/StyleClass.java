package edu.ntnu.idatt2001.paths.model.enums;

/**
 * <h1>StyleClass</h1>
 * {@code StyleClass} is an enum class for the style classes.
 * The style classes are used to determine which style class should be used at a given time.
 *
 * @author Jeffrey Yaw Annor Tabiri
 * @version 1.0
 * @since 06/02/2023
 */
public enum StyleClass {

  //StyleClass
  MENU_BUTTON("menu-button"),
  OPTION_BUTTON("option-button"),
  SECONDARY_MENU_BUTTON("secondary-button"),
  TITLE("title"),
  CONTENT("content"),

  SECONDARY_CONTENT("secondary-content"),
  SECONDARY_TITLE("secondary-title");




  //Constant value
  private final String value;


  /**
   * The constructor {@code StyleClass} sets the value of the style class.
   *
   * @param value is the css tag.
   */
  StyleClass(String value) {
    this.value = value;
  }


  /**
   * The method {@code getValue} returns the value of the style class.
   *
   * @return the value of the style class. Returns a string.
   */
  public String getValue() {
    return value;
  }

}
