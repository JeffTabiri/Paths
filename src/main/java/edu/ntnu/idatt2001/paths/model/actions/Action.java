package edu.ntnu.idatt2001.paths.model.actions;

import edu.ntnu.idatt2001.paths.model.Player;

/**
 * <h1>Action</h1>
 * An Action represents a change in the state of the player.
 * Is an interface that is implemented by all actions that can be performed by the player.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @since 06/02/2023
 * @version 1.0
 */
public interface Action {
  void execute(Player player);
}
