package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.paths.actions.Action;

import java.util.List;
import java.util.Objects;

/**
 *  A {@code Link} class is used to bind multiple parts of a story. A link makes it possible to go
 * from a path to another.
 * It contains fields such as text, reference and a list of action type objects.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 06/02/2023
 * @since JDK 17.0.6
 */
public class Link {
  private final String text;
  private final String reference;
  private List<Action> actions;

  /**
   * Constructor which is responsible for the creation of the link class.
   *
   * @param text is a descriptive text that indicates some form of drastic choice in a story.
   *                This text is only visible to the player.
   *
   * @param reference a string that uniquely identifies a passage (part of a story).
   *                  In practice this will be the title of the passage you wish to refer to.
   */
  Link(String text, String reference) {

    if (text.isEmpty()) {
      throw new IllegalArgumentException("The text can't be empty.");
    }

    if (reference.isEmpty()) {
      throw new IllegalArgumentException("The reference can't be empty.");
    }

    this.text = text;
    this.reference = reference;
  }

  public String getText() {
    return text;
  }

  public String getReference() {
    return reference;
  }

  /**
   * A method for adding an action to the list of actions.
   *
   * @param action is an action object that is added to the list of actions.
   */
  public void addAction(Action action) {
    actions.add(action);
  }

  /**
   * A method for getting the list of actions.
   *
   * @return a list of actions.
   */
  public List<Action> getActions() {
    return actions;
  }

  /**
   * A method for checking the equality of two instances of the same class.
   *
   * @param o is the object instance we are checking against.
   *
   * @return a boolean value which checks if the two objects are identical
   *         Can be either true or false.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Link link = (Link) o;
    return Objects.equals(text, link.text) && Objects.equals(reference, link.reference);
  }

  /**
   * Overridden method which will help the program function with hash-based collections.
   *
   * @return a hashcode, which acts as an identifier for the class, is usually in a int.
   */
  @Override
  public int hashCode() {
    return Objects.hash(text, reference);
  }

  /**
   * A toString() which will provide a general representation of the class.
   *
   * @return a string.
   */
  @Override
  public String toString() {
    return "edu.ntnu.idatt2001.Paths.Link{"
        + "text='"
        + text
        + '\''
        + ", reference='"
        + reference
        + '\''
        + '}';
  }
}
