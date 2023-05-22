package edu.ntnu.idatt2001.paths.model;

import edu.ntnu.idatt2001.paths.model.goals.Goal;

/**
 * <h1>Achievement</h1>
 * An {@code Achievement} has a goal, a title, a description and a boolean that
 * represents if the achievement is fulfilled or not.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 1.0
 * @since 06/02/2023
 * @see Goal
 */
public class Achievement {
  private final Goal goal;
  private final String title;
  private final String description;
  private Boolean isFulfilled = false;


  /**
   * Constructor for the {@code Achievement}.
   *
   * @param goal is the {@code Goal} that the player is to achieve.
   * @param title represent a title for the achievement.
   * @param description represents the description of an achievement.
   * @throws IllegalArgumentException if any of the parameters are null
   */
  public Achievement(Goal goal, String title, String description) {

    if (goal == null) {
      throw new IllegalArgumentException("Goal cannot be null");
    }

    if (title == null) {
      throw new IllegalArgumentException("Title cannot be null");
    }

    if (description == null) {
      throw new IllegalArgumentException("Description cannot be null");
    }

    this.goal = goal;
    this.title = title;
    this.description = description;
  }


  /**
  * Checks if the achievement is fulfilled and updates the isFulfilled variable.
  */
  public void setIsFulfilled(boolean isFulfilled) {
    this.isFulfilled = isFulfilled;
  }


  /**
   * Gets the description of the {@code achievement}.
   *
   * @return a string with the description of the {@code achievement}.
   */
  public String getDescription() {
    return description;
  }

  /**
  * Gets the {@code goal} of the {@code achievement}.
  *
  * @return the {@code goal} of the {@code achievement}.
  */
  public Goal getGoal() {
    return goal;
  }

  /**
  * Gets the title of the {@code achievement}.
  *
  * @return a string with the title of the {@code achievement}.
  */
  public String getTitle() {
    return title;
  }

  /**
   * Checks if the {@code achievement} is fulfilled.
   *
   * @return true if the achievement is fulfilled, false if not
   */
  public Boolean getIsFulfilled() {
    return isFulfilled;
  }


  /**
   * Represents a {@code achievement}.
   *
   * @return a simple string with the title and description of the {@code achievement}.
   */
  @Override
  public String toString() {
    return getTitle()
            + "\n"
            + getDescription();
  }

}
