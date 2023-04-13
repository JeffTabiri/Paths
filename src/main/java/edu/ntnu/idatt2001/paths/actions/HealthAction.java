package edu.ntnu.idatt2001.paths.actions;

import edu.ntnu.idatt2001.paths.Player;

/**
 * The class HealthAction represents the action of changing the health of a player.
 * The amount of health is specified in the constructor.
 * The class HealthAction implements the interface Action.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 06/02/2023
 * @since JDK 17.0.6
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
   * The method execute adds the health to the player's health.
   */
  public void execute(Player player) {
    player.addHealth(health);
  }

  @Override
  public String toString() {
    return "{Health}(" + health + ")";
  }
}
