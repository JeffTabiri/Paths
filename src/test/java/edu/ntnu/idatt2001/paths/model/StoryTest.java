package edu.ntnu.idatt2001.paths.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class StoryTest {

  Story testStory;
  Passage testPassage;
  Link testLink;

  @BeforeEach
  void setUp() {
    testPassage = new Passage("Test title", "Test content");
    testLink = new Link("Test link", "Test reference");
    testStory = new Story("The Story", testPassage);
  }


  @DisplayName("Test Constructor")
  @Nested
  class ConstructorTest {

    @DisplayName("Test constructor with valid parameters")
    @Test
    void testConstructorWithValidParameters() {
      String expectedValue = "The Story";
      String actualValue = testStory.getTitle();
      assertEquals(expectedValue, actualValue);
    }

    @DisplayName("Test constructor with invalid parameters")
    @Test
    void testConstructorWithInvalidParameters() {
      assertThrows(IllegalArgumentException.class, () -> new Story("", null));
      assertThrows(IllegalArgumentException.class, () -> new Story("The Story", null));
    }

  }

  @DisplayName("Test Accessors")
  @Nested
  class AccessorsTest {

    @Test
    void getTitleTest() {
      String expectedValue = "The Story";
      String actualValue = testStory.getTitle();
      assertEquals(expectedValue, actualValue);
    }

    @Test
    void getOpeningPassageTest() {
      Passage expectedValue = testPassage;
      Passage actualValue = testStory.getOpeningPassage();
      assertEquals(expectedValue, actualValue);
    }

    @Test
    void getPassagesTest() {
      List<Passage> expectedValue = List.of(testPassage);
      Collection<Passage> actualValue = testStory.getPassages();

      assertTrue(expectedValue.containsAll(actualValue));
    }

    @Test
    void getBrokenLinks() {

      testStory.addPassage(testPassage);
      testPassage.addLink(testLink);
      boolean expectedValue = testStory.getBrokenLinks().contains(testLink);
      assertTrue(expectedValue);
    }

  }

  @DisplayName("Test Mutators")
  @Nested
  class MutatorsTest {

    @BeforeEach
    void setUp() {
      testStory.addPassage(testPassage);
    }

    @Test
    void addPassage() {

      Passage temporaryPassage = new Passage("Temporary title", "Temporary content");

      testStory.addPassage(temporaryPassage);

      boolean expectedValue = testStory.getPassages().contains(temporaryPassage);

      assertTrue(expectedValue);
    }

    @Test
    void addNullPassage() {
      assertThrows(IllegalArgumentException.class, () -> testStory.addPassage(null));
    }

    @Test
    void removePassage() {

      testStory.addPassage(testPassage);

      testStory.removePassage(new Link("Test title", "Test title"));

      boolean expectedValue = testStory.getPassages().contains(testPassage);

      assertFalse(expectedValue);
    }

    @Test
    void removeNullPassage() {
      assertThrows(IllegalArgumentException.class, () -> testStory.removePassage(null));
    }

    @DisplayName("Remove passage from story with links attached (removePassage()")
    @Test
    void removePassageWithLinksAttached() {

      testPassage.addLink(testLink);
      Passage testPassage2 = new Passage("Test title 2", "Test content 2");
      testPassage2.addLink(testLink);

      testStory.addPassage(testPassage2);
      testStory.addPassage(testPassage);
      Link newLink = new Link("newText", "newReference");

      assertThrows(IllegalArgumentException.class, () -> testStory.removePassage(newLink));
      assertThrows(IllegalArgumentException.class, () -> testStory.removePassage(testLink));
    }


  }
}
