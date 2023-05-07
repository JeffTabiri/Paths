package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.paths.Game;
import edu.ntnu.idatt2001.paths.goals.Goal;

/**
 * Represents an achievement. An achievement has a goal, a title, a description and a boolean that
 * represents if the achievement is fulfilled or not.
 * The class has methods of checking if the achievement is fulfilled and updating the isFulfilled variable.
 *
 * @author Created by Jeffrey Yaw Annor Tabiri and Ari Maman
 * @version 06/02/2023
 * @since JDK 17.0.6
 */
public class Achievement {
    Goal goal;
    String title;
    String description;
    Boolean isFulfilled = false;


    /**
     * Constructor for the Achievement class
     *
     * @param goal is the goal that the player is to achieve
     * @param title represent a title for the achievement
     * @param description represents the description of the achievement
     */
    public Achievement(Goal goal, String title, String description) {
        this.goal = goal;
        this.title = title;
        this.description = description;
    }


    /**
     * Checks if the achievement is fulfilled and updates the isFulfilled variable
     *
     * @param player the player to check the achievement for
     */
    public void checkProgress(Player player) {
        isFulfilled = goal.isFulfilled(player);
    }


    public String getDescription() {
        return description;
    }

    public Goal getGoal() {
        return goal;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Checks if the achievement is fulfilled
     *
     * @return true if the achievement is fulfilled, false if not
     */
    public Boolean getIsFulfilled() {
        return isFulfilled;
    }


    /**
     * Shows a general overview of the achievement in a string format
     *
     * @return a simple string with the title and description of the achievement
     */
    @Override
    public String toString() {
        return getTitle()
                + "\n"
                + getDescription();
    }
}
