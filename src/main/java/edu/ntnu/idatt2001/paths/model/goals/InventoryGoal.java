package edu.ntnu.idatt2001.paths.model.goals;

import edu.ntnu.idatt2001.paths.model.Player;
import java.util.HashSet;
import java.util.List;

/**
 * <h1>InventoryGoal</h1>
 * {@code InventoryGoal} represents a an expected item in the players inventory,
 * in order to reach the goal.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 1.0
 * @since 06/02/2023
 */
public class InventoryGoal implements Goal {
  private final List<String> mandatoryItems;

  /**
   * The constructor for the class {@code InventoryGoal}.
   *
   * @param mandatoryItems is a list of items that the player must have to reach the goal.
   */
  public InventoryGoal(List<String> mandatoryItems) {
    this.mandatoryItems = mandatoryItems;
  }

  /**
   * The method {@code isFulfilled} checks if the player has reached the goal.
   *
   * @return true if the player has reached the goal, false otherwise.
   * @throws IllegalArgumentException if the player is null.
   */
  public boolean isFulfilled(Player player) {

    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }

    return new HashSet<>(player.getInventory()).containsAll(mandatoryItems);
  }

}
