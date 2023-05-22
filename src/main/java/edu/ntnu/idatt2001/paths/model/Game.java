package edu.ntnu.idatt2001.paths.model;

/**
 * <h1>Game</h1>
 * The class {@code Game} represents a game.
 * It contains fields objects such as player and story.
 * The game class is used to start the game and traverse to a new passage.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 1.0
 * @since 06/02/2023
 * @see Player
 * @see Story
 */
public class Game {
  private final Player player;
  private final Story story;

  /**
   * {@code Game} constructor.
   *
   * @param player is an object of the player class,
   * @param story  is an object of the story class
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

  /**
   * A method for accessing the player.
   *
   * @return a player object.
   */
  public Player getPlayer() {
    return player;
  }

  /**
   * A method for accessing the story.
   *
   * @return a story object.
   */
  public Story getStory() {
    return story;
  }

  /**
   * A method for getting the opening passage of the story.
   *
   * @return a passage object.
   */
  public Passage begin() {
    return story.getOpeningPassage();
  }


  /**
   * A {@code go} method traverses to new passages by utilizing the link object.
   *
   * @param link is the link that is used to traverse to a new passage.
   * @return a found passage object.
   */
  public Passage go(Link link) {
    return story.getPassage(link);
  }

}