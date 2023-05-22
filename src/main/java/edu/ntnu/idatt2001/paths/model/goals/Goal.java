package edu.ntnu.idatt2001.paths.model.goals;

import edu.ntnu.idatt2001.paths.model.Player;

/**
 * <h1>Goal</h1>
 * A {@code Goal} represents an expected change provided by subclasses of {@code action}.
 * The {@code Goal} class has responsibility for checking if the game has reached a final state.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 1.0
 * @since 06/02/2023
 */
public interface Goal {

  /**
   * The method {@code isFulfilled} checks if {@code Goal} has been fulfilled.
   *
   * @return true if the goal has been fulfilled, false if not.
   *
   */
  boolean isFulfilled(Player player);
}
