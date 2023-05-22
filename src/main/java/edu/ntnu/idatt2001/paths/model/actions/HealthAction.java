package edu.ntnu.idatt2001.paths.model.actions;

import edu.ntnu.idatt2001.paths.model.Player;

/**
 * <h1>HealthAction</h1>
 * {@code HealthAction} represents the action of changing the health of a player.
 * The amount of health is specified in the {@code HealthAction} constructor.
 * The class HealthAction implements the interface {@code Action}.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 1.0
 * @since 06/02/2023
 */
public class HealthAction implements Action {

  private final int health;

  /**
   * The constructor for the class HealthAction.
   *
   * @param health the amount of health to be added to the player's health.
   *               The health must be an integer.
   */
  public HealthAction(int health) {
    this.health = health;
  }

  /**
   * The {@code execute} method adds changes the health of the player.
   * If the health is negative, the player loses health.
   * If the health is positive, the player gains health.
   *
   * @throws IllegalArgumentException if the player is null.
   */
  public void execute(Player player) {

    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }

    if (health < 0) {
      player.removeHealth(health);
    } else if (health > 0) {
      player.addHealth(health);
    }

  }

  /**
  * The method {@code toString} returns a representation of the {@code HealthAction}.
  *
  * @return a string representation of the {@code HealthAction}.
  */
  @Override
  public String toString() {
    return "{Health}(" + health + ")";
  }

}
