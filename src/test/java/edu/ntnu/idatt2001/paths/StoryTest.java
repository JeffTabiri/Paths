package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.paths.Passage;
import edu.ntnu.idatt2001.paths.Story;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StoryTest {

    @BeforeEach
    void setUp() {
    }
    Passage testPassage = new Passage("Test title", "Test content");
    Story testStory = new Story("The Story", testPassage);




    @AfterEach
    void tearDown() {
    }

    @Test
    void getTitle() {
        Assertions.assertEquals("The Story", new Story("The Story",
                                new Passage("Test title", "Test content")).getTitle());
    }

    @Test
    void getOpeningPassage() {
        Assertions.assertEquals("Test title", new Story("The Story",
                                new Passage("Test title", "Test content")).getOpeningPassage().getTitle());
    }

    @Test
    void getPassages() {
        Assertions.assertEquals(0, testStory.getPassages().size());
    }

    @Test
    void addPassage() {
        testStory.addPassage(testPassage);
        Assertions.assertEquals(1, testStory.getPassages().size());
    }

    @Test
    void removePassage() {
        /*
        testStory.addPassage(testPassage);
        Link link = new Link("Test title", "Test content");
        testPassage.addLink(link);
        testStory.removePassage(link);

        int expected = 0;
        int actual = testStory.getPassages().size();
        Assertions.assertEquals(expected, actual)
         */
    }

    @Test
    void getBrokenLinks() {
        /*
        testStory.addPassage(testPassage);
        Assertions.assertEquals(1, testStory.getBrokenLinks().size());
        */
    }
}