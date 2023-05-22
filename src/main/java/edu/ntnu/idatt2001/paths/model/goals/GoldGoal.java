package edu.ntnu.idatt2001.paths.model.goals;

import edu.ntnu.idatt2001.paths.model.Player;

/**
 * <h1>GoldGoal</h1>
 * An {@code GoldGoal} represents the expected change of the players gold.
 * The {@code GoldGoal} class has responsibility for checking if the player has reached the goal.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 06/02/2023
 * @since JDK 17.0.6
 */

public class GoldGoal implements Goal {

  private final int minimumGold;

  /**
   * The constructor for the class {@code GoldGoal}.
   *
   * @param minimumGold is the minimum amount of gold the
   *                    player must have to reach the {@code Goal}.
   * @throws IllegalArgumentException if the minimum gold is negative.
   */
  public GoldGoal(int minimumGold) {

    if (minimumGold < 0) {
      throw new IllegalArgumentException("Minimum gold cannot be negative");
    }

    this.minimumGold = minimumGold;

  }

  /**
   * The method {@code isFulfilled} checks if the player has reached {@code Goal}.
   *
   * @return true if the player has reached the goal, false if not.
   */
  public boolean isFulfilled(Player player) {

    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }

    return player.getGold() >= minimumGold;
  }
}
