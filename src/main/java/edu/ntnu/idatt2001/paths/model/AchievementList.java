package edu.ntnu.idatt2001.paths.model;

import edu.ntnu.idatt2001.paths.model.goals.GoldGoal;
import edu.ntnu.idatt2001.paths.model.goals.HealthGoal;
import edu.ntnu.idatt2001.paths.model.goals.InventoryGoal;

import java.util.ArrayList;
import java.util.List;

/**
 * Singleton class that holds a list of all achievements.
 */
public class AchievementList {

    private static AchievementList achievementList = null;
    List<Achievement> achievements = null;

    private AchievementList() {
    }

    public static synchronized AchievementList getInstance() {
        if (achievementList == null) {
            achievementList = new AchievementList();
            achievementList.achievements = new ArrayList<>();
            achievementList.setAchievements();
        }
        return achievementList;
    }

    public void addAchievement(Achievement achievement) {
        achievements.add(achievement);
    }

    public List<Achievement> getAchievements() {
        return achievements;
    }

    public void removeAchievement(Achievement achievement) {
        achievements.remove(achievement);
    }

    public int getNumberOfAchievements() {
        return achievements.size();
    }


    private void setAchievements() {
        achievementList.addAchievement(new Achievement(new GoldGoal(1000), "Gold 1000", "You have 1000 gold"));
        achievementList.addAchievement(new Achievement(new HealthGoal(100), "Health 100", "You have 100 health"));
        achievementList.addAchievement(new Achievement(new GoldGoal(2000), "Gold 2000", "You have 2000 gold"));
        ArrayList<String> inventory = new ArrayList<>();
        inventory.add("Excalibur");
        achievementList.addAchievement(new Achievement(new InventoryGoal(inventory), "Excalibur", "You have Excalibur"));
    }

}
