package edu.ntnu.idatt2001.paths.model.goals;

import edu.ntnu.idatt2001.paths.model.Player;

/**
 * <h1>ScoreGoal</h1>
 * A {@code ScoreGoal} represents the expected change of the players score.
 */
public class ScoreGoal implements Goal {
  private final int score;

  /**
   * The constructor for the class {@code ScoreGoal}.
   *
   * @param score the score to be reached. It is a positive integer.
   * @throws IllegalArgumentException if the score is negative.
   */
  public ScoreGoal(int score) {
    if (score < 0) {
      throw new IllegalArgumentException("The score cannot be negative");
    }
    this.score = score;
  }

  /**
  * The method {@code isFulfilled} checks if the player has reached the goal.
  *
  * @param player the player to be checked.
  * @return true if the player has reached the goal, false if not.
  * @throws IllegalArgumentException if the player is null.
  */
  @Override
  public boolean isFulfilled(Player player) {

    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }

    return player.getScore() >= score;
  }

}