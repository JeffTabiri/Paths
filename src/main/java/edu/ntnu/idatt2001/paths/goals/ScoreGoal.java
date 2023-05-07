package edu.ntnu.idatt2001.paths.goals;

import edu.ntnu.idatt2001.paths.playerBuilder.Player;

public class ScoreGoal implements Goal {
  private final int score;

  /**
   * The constructor for the class ScoreGoal.
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
     * The method isFulfilled checks if the player has reached the goal.
     * @param player the player to check if the goal is fulfilled.
     * @return true if the player has reached the goal, false if not.
     */
    @Override
    public boolean isFulfilled(Player player) {
        return player.getScore() >= score;
    }

}