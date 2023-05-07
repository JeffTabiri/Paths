package edu.ntnu.idatt2001.paths.playerBuilder;

import java.util.ArrayList;
import java.util.List;

public class PlayerBuilder {

    final String name;
    int health;
    int score;
    int gold;
    List<String> inventory = new ArrayList<>();

    public PlayerBuilder(String name) {
        this.name = name;
    }

    public PlayerBuilder health(int health) {
        this.health = health;
        return this;
    }

    public PlayerBuilder score(int score) {
        this.score = score;
        return this;
    }

    public PlayerBuilder gold(int gold) {
        this.gold = gold;
        return this;
    }

    public PlayerBuilder inventory(List<String> inventory) {
        this.inventory = inventory;
        return this;
    }

    public Player build() {
        Player player = new Player(this);
        validatePlayerBuilder(player);
        return player;
    }

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
