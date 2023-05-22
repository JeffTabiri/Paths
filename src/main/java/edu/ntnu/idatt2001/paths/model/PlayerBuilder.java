package edu.ntnu.idatt2001.paths.model;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>PlayerBuilder</h1>
 * {@code PlayerBuilder} is a builder class for the {@code Player} class.
 * It is used to create a {@code Player} object with the desired attributes.
 * A {@code PlayerBuilder} object is created with the name of the player.
 */
public class PlayerBuilder {
  final String name;
  int health;
  int score;
  int gold;
  List<String> inventory = new ArrayList<>();

  /**
  * Constructor which is responsible for the creation of the {@code Player} class.
  *
  * @param name is the name of the player.
  */
  public PlayerBuilder(String name) {
    this.name = name;
  }

  /**
   * A builder method which is responsible for building the {@code Player} gold.
   *
   * @param health is the health of the {@code Player} object.
   * @return the {@code PlayerBuilder} object with the health of the {@code Player} object.
   */
  public PlayerBuilder health(int health) {
    this.health = health;
    return this;
  }

  /**
   * A builder method which is responsible for building the {@code Player} score.
   *
   * @param score is the score of the {@code Player} object.
   * @return the {@code PlayerBuilder} object with the score of the {@code Player} object.
   */
  public PlayerBuilder score(int score) {
    this.score = score;
    return this;
  }

  /**
   * A builder method which is responsible for the gold of the {@code Player} object.
   *
   * @param gold is the gold of the {@code Player} object.
   * @return the {@code PlayerBuilder} object with the gold of the {@code Player} object.
   */
  public PlayerBuilder gold(int gold) {
    this.gold = gold;
    return this;
  }

  /**
   * A builder method which is responsible for building the {@code Player} inventory.
   *
   * @param inventory is the inventory of the {@code Player} object.
   *
   * @return the {@code PlayerBuilder} object with the inventory of the {@code Player} object.
   */
  public PlayerBuilder inventory(List<String> inventory) {
    this.inventory = inventory;
    return this;
  }

  /**
   * A method for building the {@code Player} object.
   *
   * @return the {@code Player} object as specified by the {@code PlayerBuilder} object.
   */
  public Player build() {
    Player player = new Player(this);
    validatePlayerBuilder(player);
    return player;
  }

  /**
   * A method for validating the {@code PlayerBuilder} object.
   *
   * @param player is the {@code Player} object to be validated.
   * @throws IllegalArgumentException if health, score or gold is negative or if the name is empty.
   *
   */
  private void validatePlayerBuilder(Player player) {
    if (player.getHealth() <= 0) {
      throw new IllegalArgumentException("Health cannot be negative");
    }

    if (player.getScore() < 0) {
      throw new IllegalArgumentException("Score cannot be negative");
    }

    if (player.getGold() < 0) {
      throw new IllegalArgumentException("Gold cannot be negative");
    }

    if (player.getName().isEmpty()) {
      throw new IllegalArgumentException("The name cannot be empty");
    }
  }

}
