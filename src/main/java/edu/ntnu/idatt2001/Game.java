package edu.ntnu.idatt2001;

import java.util.List;

/**
 * The class {@code Game} represents a game.
 * It contains fields objects such as player and story.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 06/02/2023
 * @since JDK 17.0.6
 *
 */
public class Game {
  private final Player player;
  private final Story story;
  private final List<Goal> goals;

  /**
   * Game constructor.
   *
   * @param player is an object of the player class,
   * @param story is an object of the story class
   * @param goals is the list of goals in the
   */
  public Game(Player player, Story story, List<Goal> goals) {
    this.story = story;
    this.player = player;
    this.goals = goals;
  }

  public Player getPlayer() {
    return player;
  }

  public Story getStory() {
    return story;
  }

  public List<Goal> getGoals() {
    return goals;
  }

  public Passage begin() {
    return story.getOpeningPassage();
  }

  public Passage go(Link link) {
    return story.getPassage(link);
  }
}