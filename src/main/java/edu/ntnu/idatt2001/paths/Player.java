package edu.ntnu.idatt2001.paths;

import java.util.ArrayList;
import java.util.List;

/**
 * A {@code Player} class represents a player in the game.
 * It contains fields such as name, health, score and gold.
 * The attributes of the player can be accessed using the getters
 * and changed using designated methods.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 06/02/2023
 * @since JDK 17.0.6
 */
public class Player {
  private final String name;
  private int health;
  private int score;
  private int gold;
  private List<String> inventory = new ArrayList<>();

  /**
   * Constructor which is responsible for the creation of the player class.
   *
   * @param name is the name of the player.
   * @param health is the health of the player.
   * @param score is the score of the player.
   * @param gold is the gold of the player. It is used to buy items. It is an integer.
   *
   * @throws IllegalArgumentException if health, score or gold is negative.
   */
  public Player(String name, int health, int score, int gold) {

    if (health <= 0) {
      throw new IllegalArgumentException("Health cannot be negative");
    }

    if (score < 0) {
      throw new IllegalArgumentException("Score cannot be negative");
    }

    if (gold < 0) {
      throw new IllegalArgumentException("Gold cannot be negative");
    }
    if (name.isEmpty()) {
      throw new IllegalArgumentException("The name cannot be empty");
    }

    this.name = name;
    this.health = health;
    this.score = score;
    this.gold = gold;
  }

  public String getName() {
    return name;
  }

  public int getHealth() {
    return health;
  }

  public int getScore() {
    return score;
  }

  public int getGold() {
    return gold;
  }

  public List<String> getInventory() {
    return inventory;
  }


  /**
   * A method for adding health to the player.
   *
   * @param health is the amount of health to be added. It is a positive integer.
   *
   * @throws IllegalArgumentException if health is negative.
   */
  public void addHealth(int health) {
    if (health < 0) {
      throw new IllegalArgumentException("The additional health cannot be negative");
    }
    this.health += health;
  }

  /**
   * A method for removing health from the player.
   *
   * @param health is the amount of health it is to be removed. It is a positive integer.
   *
   * @throws IllegalArgumentException if health is negative.
   */
  public void removeHealth(int health) {
    this.health += health;
  }


  /**
   * A method for adding score to the player
   *
   * @param score is the amount of score to be added. It is a positive integer.
   *
   * @throws IllegalArgumentException if score is negative
   */
  public void addScore(int score) {

    if (score < 0) {
      throw new IllegalArgumentException("The additional score cannot be negative");
    }

    this.score += score;
  }

    /**
     * A method for removing score from the player
     *
     * @param score is the amount of score to be removed. It is a positive integer
     *
     * @throws IllegalArgumentException if score is negative
     */
  public void removeScore(int score) {

    if (score < 0) {
      throw new IllegalArgumentException("The additional score cannot be negative");
    }

    if (score > this.score) {
      score = this.score;
    }

    this.score -= score;
  }


  /**
   * A method for adding gold to the player
   *
   * @param gold is the amount of gold to be added. It is a positive integer
   *
   * @throws IllegalArgumentException if gold is negative
   */
  public void addGold(int gold) {

    if (gold < 0) {
      throw new IllegalArgumentException("The additional gold cannot be negative");
    }

    this.gold += gold;
  }

  /**
   * A method for removing gold from the player
   *
   * @param gold is the amount of gold to be removed. It is a positive integer
   * @throws IllegalArgumentException if gold is negative or the player does not have enough gold
   */
  public void removeGold(int gold) {

    if (gold < 0) {
      throw new IllegalArgumentException("The additional gold cannot be negative");
    }

    if (gold > this.gold) {
      throw new IllegalArgumentException("The player does not have enough gold");
    }

    this.gold -= gold;
  }


  /**
   * A method for adding an item to the inventory
   *
   * @param item is the instance to be added to the inventory
   * @throws IllegalArgumentException if the item is empty
   */
  public void addInventory(String item) {

    if (item.isEmpty()) {
      throw new IllegalArgumentException("The inventory cannot be empty");
    }

    this.inventory.add(item);
  }


  /**
   * A method for removing an item from the players inventory
   *
   * @param item is the instance to be removed from the players inventory
   * @throws IllegalArgumentException if the item is empty or not in the inventory
   */
  public void removeInventory(String item) {

    if (item.isEmpty()) {
      throw new IllegalArgumentException("The item cannot be empty");
    }

    if (!inventory.contains(item)) {
      throw new IllegalArgumentException("The item is not in the inventory");
    }

    inventory.remove(item);
  }

  /**
   * A toString method for the player class.
   * Shows the name, health, score, gold and inventory of the player.
   * Acts as a summary of the player.
   *
   * @return a string with the name, health, score, gold and inventory of the player.
   */
  @Override
  public String toString() {
    return "Player{"
        +  "name='"
        + name
        + '\''
        + ", health="
        + health
        + ", score="
        + score
        + ", gold="
        + gold
        + ", inventory="
        + inventory
        + '}';
  }

}
