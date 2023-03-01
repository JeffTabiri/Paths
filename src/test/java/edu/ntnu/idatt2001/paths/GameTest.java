package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.paths.*;
import edu.ntnu.idatt2001.paths.actions.InventoryAction;
import edu.ntnu.idatt2001.paths.goals.Goal;
import edu.ntnu.idatt2001.paths.goals.GoldGoal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    Player testPlayer = new Player("Test player", 100, 100, 1234);
    InventoryAction testAction = new InventoryAction("Sword");

    Passage testPassage = new Passage( "Test passage title", "test passage content");
    Story testStory = new Story("Test Story Title", testPassage);

    GoldGoal testGoldGoal = new GoldGoal(100);

    List<Goal> testGoal = new ArrayList<>();

    @Test
    void getPlayer() {
        assertEquals(testPlayer, new Game(testPlayer, testStory, testGoal).getPlayer());
    }

    @Test
    void getGoldGoal(){
        List<Goal> testGoal = new ArrayList<>();
        GoldGoal testGoldGoal = new GoldGoal(100);
        testGoal.add(testGoldGoal);

        assertEquals(testGoldGoal, testGoal.get(0));}

    @Test
    void getStory() {
        assertEquals(testStory, new Game(testPlayer, testStory, testGoal).getStory());
    }

    @Test
    void getGoals() {
        assertEquals(testGoal, new Game(testPlayer, testStory, testGoal).getGoals());
    }

    @Test
    void begin() {
        assertEquals(testStory.getOpeningPassage(), new Game(testPlayer, testStory, testGoal).begin());
    }

    @Test
    void go() {
        Link testLink = new Link("This is the test text", "This is the test reference");
        testPassage.addLink(testLink);
        assertEquals(testPassage, new Game(testPlayer, testStory, testGoal).go(testLink));
    }
}