package edu.ntnu.idatt2001.paths.model.actions;

import edu.ntnu.idatt2001.paths.model.Player;

/**
 * The class {@code ScoreAction} represents an action that adds score points to the player.
 * The amount of points added is specified in the constructor.
 * The class {@code ScoreAction} implements the interface {@code Action}.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 06/02/2023
 * @since JDK 17.0.6
 */
public class ScoreAction implements Action {
  private final int points;

  /**
   * The constructor for the class {@code ScoreAction}.
   *
   * @param points the amount of points to be added to the player's score.
   *               The points must be a integer.
   *
   */
  public ScoreAction(int points) {
    this.points = points;
  }

  /**
   * The method {@code execute} adds the points to the player's score.
   */
  public void execute(Player player) {
    player.addScore(points);
  }

  @Override
  public String toString() {
    return "{Score}(" + points + ")";
  }
}
