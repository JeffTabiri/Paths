package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.paths.model.goals.Goal;
import edu.ntnu.idatt2001.paths.model.goals.GoldGoal;
import edu.ntnu.idatt2001.paths.model.Game;
import edu.ntnu.idatt2001.paths.model.Link;
import edu.ntnu.idatt2001.paths.model.Passage;
import edu.ntnu.idatt2001.paths.model.Story;
import edu.ntnu.idatt2001.paths.model.Player;
import edu.ntnu.idatt2001.paths.model.PlayerBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test Class Game")
class GameTest {

    Game testGame;
    Player testPlayer;
    List<Goal> testGoals;
    Goal testGoal;
    Story testStory;
    Passage testPassage;

    @BeforeEach
    void setUp() {
        testPlayer = new PlayerBuilder("Test").gold(100).health(100).score(100).build();
        testPassage = new Passage("Test passage", "Test content");
        testGoal = new GoldGoal(100);
        testGoals = new ArrayList<>();
        testGoals.add(testGoal);
        testStory = new Story("Test story", testPassage);
        testStory.addPassage(testPassage);
        testGame = new Game(testPlayer, testStory);

    }

    @DisplayName("Test Constructor")
    @Nested
    class ConstructorTest {
            @DisplayName("Test constructor with valid parameters")
            @Test
            void testConstructorWithValidParameters() {
                Game testGame = new Game(testPlayer, testStory);
                assertEquals(testPlayer, testGame.getPlayer());
                assertEquals(testStory, testGame.getStory());

            }
    }


    @DisplayName("Test Accessors")
    @Nested
    class AccessorsTest {
        @Test
        void getPlayer() {
            String expectedValue = "Test";
            String actualValue = testGame.getPlayer().getName();
            assertEquals(expectedValue, actualValue);
        }

        @Test
        void getStory() {
            String expectedValue = "Test story";
            String actualValue = testGame.getStory().getTitle();
            assertEquals(expectedValue, actualValue);
        }

    }

    @DisplayName("Test Mutators")
    @Nested
    class MutatorsTest {
        @DisplayName("Test the begin returns the opening passage of the story")
        @Test
        void beginTest() {
            Passage expectedValue = testPassage;
            Passage actualValue = testGame.begin();

            assertEquals(expectedValue, actualValue);
        }

        @DisplayName("Test that the go method returns the passage that the link points to")
        @Test
        void goTest() {
            Link testLink = new Link("Test passage", "Test passage");
            Passage expectedValue = testPassage;
            Passage actualValue = testGame.go(testLink);
            assertEquals(expectedValue, actualValue);
        }
    }

}