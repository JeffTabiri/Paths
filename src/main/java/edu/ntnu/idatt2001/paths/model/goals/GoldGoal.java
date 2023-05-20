package edu.ntnu.idatt2001.paths.model.goals;

import edu.ntnu.idatt2001.paths.model.Player;

/**
 * An GoldGoal represents the final state of gold in the inventory of a player.
 * The gold is set to a minimum value and the player must collect gold to reach
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
   * @param minimumGold the minimum amount of gold the player must have to reach the goal.
   */
  public GoldGoal(int minimumGold) {
    this.minimumGold = minimumGold;
  }

  /**
   * The method {@code isFulfilled} checks if the player has reached the goal.
   *
   * @return true if the player has reached the goal, false if not.
   */
  public boolean isFulfilled(Player player) {
    return player.getGold() >= minimumGold;
  }
}
