package edu.ntnu.idatt2001.paths.model;

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

  /**
   * Game constructor.
   *
   * @param player is an object of the player class,
   * @param story is an object of the story class
   * @throws IllegalArgumentException if player, story or goals is null.
   */
  public Game(Player player, Story story) {

    if (player == null) {
      throw new IllegalArgumentException("Player cannot be null");
    }

    if (story == null) {
      throw new IllegalArgumentException("Story cannot be null");
    }


    this.story = story;
    this.player = player;

  }

  public Player getPlayer() {
    return player;
  }

  public Story getStory() {
    return story;
  }

  /**
   * A method for getting the opening passage of the story
   * @return a passage object
   */
  public Passage begin() {
    return story.getOpeningPassage();
  }


  /**
   * A method for traversing to a new passage depending on the link
   * @param link is the link that is used to traverse to a new passage
   * @return a passage object
   */
  public Passage go(Link link) {
    return story.getPassage(link);
  }

}