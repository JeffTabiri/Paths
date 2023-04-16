package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.paths.Game;
import edu.ntnu.idatt2001.paths.goals.Goal;

public class Achievement {
    Goal goal;
    String title;
    String description;

    Boolean isFulfilled = false;

    public Achievement(Goal goal, String title, String description) {
        this.goal = goal;
        this.title = title;
        this.description = description;
    }

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

    public Boolean getIsFulfilled() {
        return isFulfilled;
    }



    @Override
    public String toString() {
        return getTitle()
                + "\n"
                + getDescription();
    }
}
