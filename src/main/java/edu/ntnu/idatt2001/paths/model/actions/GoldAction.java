package edu.ntnu.idatt2001.paths.model.actions;

import edu.ntnu.idatt2001.paths.model.Player;

/**
 * <h1>GoldAction</h1>
 *  {@code GoldAction} represents an action that adds gold to the player.
 * The amount of gold added to the player is specified in the {@code GoldAction} constructor.
 * The class {@code GoldAction} implements the interface {@code Action}.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 1.0
 * @since 06/02/2023
 * @see Action
 * @see Player
 */
public class GoldAction implements Action {
  private final int gold;


  /**
   * The constructor for the class {@code GoldAction}.
   *
   * @param gold the amount of gold to be added to the player.
   *             The gold must be an integer.
   *
   */
  public GoldAction(int gold) {
    this.gold = gold;
  }


  /**
   * The method {@code execute} adds the gold to the player.
   *
   * @param player the player to add the gold to.
   *               If the gold is negative, the player loses gold.
   *               If the gold is positive, the player gains gold.
   *
   * @throws IllegalArgumentException if the player is null.
   */
  public void execute(Player player) {

    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }

    if (gold < 0) {
      player.removeGold(gold);

    } else {

      player.addGold(gold);
    }

  }


  /**
   * The method {@code toString} returns a representation of the {@code GoldAction}.
   *
   * @return a string representation of the {@code GoldAction}.
   */
  @Override
  public String toString() {
    return "{Gold}(" + gold + ")";
  }


}
