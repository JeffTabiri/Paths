package edu.ntnu.idatt2001.paths.model.goals;

import edu.ntnu.idatt2001.paths.model.Player;

/**
 * <h1>HealthGoal</h1>
 * An {@code HealthGoal} represents a final state to the player's health that must be fulfilled.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 1.0
 * @since 06/02/2023
 */
public class HealthGoal implements Goal {

  private final int minimumHealth;

  /**
   * The constructor for the class {@code HealthGoal}.
   *
   * @param minimumHealth is defined as the minimal health a
   *                      player can have for the goal to be fulfilled.
   *                      Gets assigned in the constructor. Is an integer.
   *
   * @throws IllegalArgumentException if the minimum health is negative.
   */
  public HealthGoal(int minimumHealth) {

    if (minimumHealth < 0) {
      throw new IllegalArgumentException("Minimum health cannot be negative");
    }

    this.minimumHealth = minimumHealth;
  }

  /**
   * The method {@code isFulfilled} checks if the player has reached the goal.
   *
   * @return true if the player has reached the goal, false if not.
   *
   */
  public boolean isFulfilled(Player player) {

    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }

    return player.getHealth() >= minimumHealth;
  }
}
