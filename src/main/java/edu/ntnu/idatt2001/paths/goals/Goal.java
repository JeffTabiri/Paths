package edu.ntnu.idatt2001.paths.goals;

import edu.ntnu.idatt2001.paths.playerBuilder.Player;

/**
 * A goal represents a final state of the players actions.
 * While the actions class has responsibility for changing the state of the game,
 * the goal class has responsibility for checking if the game has reached a final state.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 06/02/2023
 * @since JDK 17.0.6
 */
public interface Goal {

  /**
   * The method isFulfilled checks if the goal has been fulfilled.
   *
   * @return true if the goal has been fulfilled, false if not.
   *
   */
  boolean isFulfilled(Player player);
}
