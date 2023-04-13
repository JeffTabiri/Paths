package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.paths.Link;
import edu.ntnu.idatt2001.paths.Passage;
import edu.ntnu.idatt2001.paths.Story;
import edu.ntnu.idatt2001.paths.actions.Action;
import edu.ntnu.idatt2001.paths.actions.InventoryAction;
import edu.ntnu.idatt2001.paths.goals.Goal;
import edu.ntnu.idatt2001.paths.goals.GoldGoal;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class LinkTest {

    Link testLink = new Link("Test link", "Test reference");

    Passage testPassage = new Passage("Test title", "Test content");
    Story testStory = new Story("Test title", testPassage);
    List<Goal> testGoals = List.of(new GoldGoal(100));

    @Test
    void getText() {
        assertEquals("Test link", testLink.getText());
    }

    @Test
    void getReference() {
        assertEquals("Test reference", testLink.getReference());
    }

    @Test
    void getActions() {
        Action testAction = new InventoryAction("Sword");
        assertEquals(testAction, testLink.getActions().get(0));
    }

    @Test
    void addAction() {
        Action testAction = new InventoryAction("Sword");
        testLink.addAction(testAction);
        assertEquals(1, testLink.getActions().size());
        testLink.getActions().removeAll(testLink.getActions());
    }


}