package edu.ntnu.idatt2001.paths.model.actions;

import edu.ntnu.idatt2001.paths.model.Player;

/**
 * The InventoryAction class is used to add items to the player's inventory.
 * The item to be added is specified in the constructor.
 * The execute method adds the item to the player's inventory.
 * The class InventoryAction implements the interface Action.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 06/02/2023
 * @since JDK 17.0.6
 */
public class InventoryAction implements Action {
  private final String item;

  /**
   * The constructor for the class InventoryAction.
   *
   * @param item the item to be added to the player's inventory. The item must be a string.
   */
  public InventoryAction(String item) {
    this.item = item;
  }

  /**
   * The method execute adds the item to the player's inventory.
   */
  public void execute(Player player) {
    player.addInventory(item);
  }

  @Override
  public String toString() {
    return "{Inventory}(" + item + ")";
  }
}
