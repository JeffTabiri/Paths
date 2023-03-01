package edu.ntnu.idatt2001.paths.goals;

import edu.ntnu.idatt2001.paths.Player;
import edu.ntnu.idatt2001.paths.actions.Action;

/**
 * An HealthGoal represents a final state to the player's health.
 * The player's health must be greater than or equal to the minimum health
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 06/02/2023
 * @since JDK 17.0.6
 */
public class HealthGoal implements Goal {

  private final int minimumHealth;

  /**
   * The constructor for the class {@code HealthGoal}.
   *
   * @param minimumHealth is defined as the minimal health a player can have.
   *                      Gets assigned in the constructor. Is an integer.
   *
   */
  HealthGoal(int minimumHealth) {
    this.minimumHealth = minimumHealth;
  }

  /**
   * The method {@code isFulfilled} checks if the player has reached the goal.
   *
   * @return true or false, which is checked upon the class field.
   *
   */
  public boolean isFulfilled(Player player) {
    return player.getHealth() >= minimumHealth;
  }
}
