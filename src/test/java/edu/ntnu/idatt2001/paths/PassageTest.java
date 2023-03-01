package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.paths.Link;
import edu.ntnu.idatt2001.paths.Passage;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PassageTest {

    Passage testPassage = new Passage("Test title", "Test content");
    Link testLink = new Link("Test link", "Test reference");

    @Test
    void getTitle() {
        assertEquals("Test title", testPassage.getTitle());
    }

    @Test
    void getContent() {
        assertEquals("Test content", testPassage.getContent());
    }

    @Test
    void getLinks() {
        testPassage.addLink(testLink);
        assertEquals(1, testPassage.getLinks().size());
        testPassage.getLinks().removeAll(testPassage.getLinks());
    }

    @Test
    void addLink() {
        testPassage.addLink(testLink);
        assertEquals(testLink, testPassage.getLinks().get(0));
        testPassage.getLinks().removeAll(testPassage.getLinks());
    }

    @Test
    void hasLinks() {
        assertFalse(testPassage.hasLinks());
        testPassage.addLink(testLink);
        assertTrue(testPassage.hasLinks());

        testPassage.getLinks().removeAll(testPassage.getLinks());
    }
}