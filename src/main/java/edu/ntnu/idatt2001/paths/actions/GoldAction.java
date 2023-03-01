package edu.ntnu.idatt2001.paths.actions;
import edu.ntnu.idatt2001.paths.Player;

/**
 * The class {@code GoldAction} represents an action that adds gold to the player.
 * The amount of gold added is specified in the constructor.
 * The class {@code GoldAction} implements the interface {@code Action}.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 06/02/2023
 * @since JDK 17.0.6
 *
 */
public class GoldAction implements Action {
  private final int gold;

  /**
   * The constructor for the class {@code GoldAction}.
   *
   * @param gold the amount of gold to be added to the player's gold inventory.
   *             The gold must be an integer.
   *
   */
  public GoldAction(int gold) {
    this.gold = gold;
  }


  /**
   * The method {@code execute} adds the gold to the player's gold inventory.
   */
  public void execute(Player player) {
    player.addGold(gold);
  }

}
