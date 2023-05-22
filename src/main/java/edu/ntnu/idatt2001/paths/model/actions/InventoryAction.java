package edu.ntnu.idatt2001.paths.model.actions;

import edu.ntnu.idatt2001.paths.model.Player;

/**
 * <h1>InventoryAction</h1>
 * The {@code InventoryAction} class is used to add an item to the player's inventory.
 * The item to be added is specified in the constructor.
 * The class {@code InventoryAction} implements the interface {@code Action}.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 1.0
 * @since 06/02/2023
 */
public class InventoryAction implements Action {
  private final String item;

  /**
   * The constructor for the class {@code InventoryAction}.
   *
   * @param item is the object to be added to the player's inventory. The item is a string.
   */
  public InventoryAction(String item) {

    if (item.isEmpty()) {
      throw new IllegalArgumentException("Item cannot be empty");
    }

    this.item = item;

  }

  /**
   * The method {@code execute} adds the item to the player's inventory.
   */
  public void execute(Player player) {

    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }

    player.addInventory(item);
  }

  /**
   * The method {@code toString} returns a representation of {@code InventoryAction}.
   *
   * @return a string representation of the {@code InventoryAction}.
   */
  @Override
  public String toString() {
    return "{Inventory}(" + item + ")";
  }

}
