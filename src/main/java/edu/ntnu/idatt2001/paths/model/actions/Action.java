package edu.ntnu.idatt2001.paths.model.actions;

import edu.ntnu.idatt2001.paths.model.Player;

/**
 * An Action represents a future change in the state of a player,
 * including changes in the player's score, health, gold inventory, or inventory.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 06/02/2023
 * @since JDK 17.0.6
 */
public interface Action {
  void execute(Player player);
}
