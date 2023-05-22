package edu.ntnu.idatt2001.paths.model.manager;

import edu.ntnu.idatt2001.paths.model.Achievement;
import edu.ntnu.idatt2001.paths.model.Game;
import edu.ntnu.idatt2001.paths.model.Passage;
import edu.ntnu.idatt2001.paths.model.Player;
import edu.ntnu.idatt2001.paths.model.Story;

/**
 * <h1>GameManager</h1>
 * {@code GameManager} is a class that manages the game.
 * It contains fields such as game, achievementManager, isPlayerAlive, isGameWon and currentPassage.
 * The class is used to keep track of the game and the player.
 * The class is implemented as a singleton to ensure that there is only one instance of the class.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman.
 * @version 1.0
 * @see Game
 * @see AchievementManager
 * @see Passage
 * @see Player
 * @see Story
 * @see Achievement
 * @since 06/02/2023
 */
public class GameManager {

  private final Game game;
  private Boolean isPlayerAlive = true;
  private Boolean isGameWon = false;
  private Passage currentPassage;


  /**
   * The constructor {@code GameManager} returns the instance of the class.
   */
  public GameManager(Game game) {
    currentPassage = game.getStory().getOpeningPassage();
    this.game = game;
  }


  /**
   * The method {@code getCurrentPassage} returns the current passage.
   *
   * @return a passage object.
   */
  public Passage getCurrentPassage() {
    return currentPassage;
  }


  /**
   * The method {@code getIsPlayerAlive} traverses to a new passage.
   *
   * @return a boolean value, true if the player is alive, false if the player is dead.
   */
  public boolean getIsPlayerAlive() {

    isPlayerAlive = game.getPlayer().getHealth() > 0;

    return isPlayerAlive;
  }

  /**
   * The method {@code IsGameWon} checks if the game is won.
   * The game is won if the player is alive and there are no more links in the current passage.
   *
   * @return a boolean value, true if the game is won, false if the game is not won.
   */
  public boolean isGameWon() {
    isGameWon = isPlayerAlive && currentPassage.getLinks().isEmpty();

    return isGameWon;
  }

  /**
   * The method {@code setCurrentPassage} sets the current passage.
   */
  public void setCurrentPassage(Passage passage) {
    this.currentPassage = passage;
  }

  /**
   * The method {@code getGameWon} returns the boolean value of isGameWon.
   *
   * @return a boolean value, true if the game is won, false if the game is not won.
   */
  public Boolean getGameWon() {
    return isGameWon;
  }

  /**
   * The method {@code getGame} returns the {@code Game} object.
   *
   * @return a {@code Game} object.
   */
  public Game getGame() {
    return game;
  }

}
