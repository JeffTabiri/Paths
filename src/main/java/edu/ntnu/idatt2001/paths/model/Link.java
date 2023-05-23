package edu.ntnu.idatt2001.paths.model;

import edu.ntnu.idatt2001.paths.model.actions.Action;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * <h1>Link</h1>
 * A {@code Link} class is used to bind multiple parts of a story.
 * A {@code Link} makes it possible to go
 * from a path to another. It contains fields such as text,
 * reference and a list of action type objects.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 1.0
 * @see Action
 * @since 06/02/2023
 */
public class Link {
  private final String text;
  private final String reference;
  private final List<Action> actions = new ArrayList<>();
  private final String itemRequired;

  /**
   * Constructor which is responsible for the creation of the {@code Link} class.
   *
   * @param text      is a descriptive text that indicates some form of drastic choice in a story.
   *                  This text is only visible to the player.
   * @param reference a string that uniquely identifies a passage (part of a story).
   *                  In practice this will be the title of the passage you wish to refer to.
   * @throws IllegalArgumentException if the text or reference is empty.
   */
  public Link(String text, String reference, String itemRequired) {

    if (text.isEmpty()) {
      throw new IllegalArgumentException("The text can't be empty.");
    }

    if (reference.isEmpty()) {
      throw new IllegalArgumentException("The reference can't be empty.");
    }

    this.text = text;
    this.reference = reference;
    this.itemRequired = itemRequired;

  }

  /**
   * Constructor which is responsible for the creation of the {@code Link} class.
   *
   * @param text      is a descriptive text that indicates some form of drastic choice in a story.
   *                  This text is only visible to the player.
   * @param reference a string that uniquely identifies a passage (part of a story).
   *                  In practice this will be the title of the passage you wish to refer to.
   * @throws IllegalArgumentException if the text or reference is empty.
   */
  public Link(String text, String reference) {

    if (text.isEmpty()) {
      throw new IllegalArgumentException("The text can't be empty.");
    }

    if (reference.isEmpty()) {
      throw new IllegalArgumentException("The reference can't be empty.");
    }

    this.text = text;
    this.reference = reference;
    this.itemRequired = "";
  }

  /**
   * A method for getting the text.
   *
   * @return a string which is the text.
   */
  public String getText() {
    return text;
  }

  /**
   * A method for getting the reference.
   *
   * @return a string which is the reference.
   */
  public String getReference() {
    return reference;
  }

  /**
   * A method for adding an {@code action} to the list of actions.
   *
   * @param action is an action object that is added to the list of actions.
   * @throws IllegalArgumentException if the action is null.
   */
  public void addAction(Action action) {

    if (action == null) {
      throw new IllegalArgumentException("The action can't be null.");
    }

    actions.add(action);
  }

  /**
   * A method for removing an {@code action} from the list of actions.
   *
   * @param action is an action object that is removed from the list of actions
   * @throws IllegalArgumentException if the action does not exist
   */
  public void removeAction(Action action) {

    if (!actions.contains(action)) {
      throw new IllegalArgumentException("The action does not exist.");
    }

    actions.remove(action);
  }

  /**
   * A method for getting the required item.
   *
   * @return a string which is the required item.
   */
  public String getRequiredItem() {
    return itemRequired;
  }


  /**
   * A method for getting actions.
   *
   * @return a list of actions.
   */
  public List<Action> getActions() {
    return actions;
  }

  /**
   * Equals method which will evaluate if two objects are equal.
   *
   * @param o is an object to be compared to the link.
   * @return a boolean value, true if the objects are equal, false if they are not.
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

    return reference.equals(link.reference);
  }

  /**
   * Overridden method which will help the program function with hash-based collections.
   *
   * @return a hashcode, which acts as an identifier for the class. Is an int.
   */
  @Override
  public int hashCode() {
    return Objects.hash(reference);
  }

  /**
   * A {@code toString()} which will provide a general representation of the link.
   *
   * @return a string.
   */
  @Override
  public String toString() {
    return text
            +
            "\n"
            +
            reference;

  }

}
