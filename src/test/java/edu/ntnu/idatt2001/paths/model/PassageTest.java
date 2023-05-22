package edu.ntnu.idatt2001.paths.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class PassageTest {

  Passage testPassage;
  Link testLink;

  @BeforeEach
  void setUp() {
    testPassage = new Passage("Test title", "Test content");
    testLink = new Link("Test link", "Test reference");
  }

  @DisplayName("Test Constructor")
  @Nested
  class ConstructorTest {
    @Test
    @DisplayName("Test constructor with valid parameters")
    void testConstructorWithValidParameters() {

      String expectedValue = "Test title";
      String actualValue = testPassage.getTitle();
      assertEquals(expectedValue, actualValue);

    }

    @Test
    @DisplayName("Test constructor with invalid parameters")
    void testConstructorWithInvalidParameters() {

      assertThrows(IllegalArgumentException.class, () -> new Passage("", "Test content"));
      assertThrows(IllegalArgumentException.class, () -> new Passage("Test title", ""));
      assertThrows(IllegalArgumentException.class, () -> new Passage("", ""));

    }

  }

  @DisplayName("Test Accessors")
  @Nested
  class AccessorsTest {

    @Test
    void getTitleTest() {
      String expectedValue = "Test title";
      String actualValue = testPassage.getTitle();

      assertEquals(expectedValue, actualValue);
    }

    @Test
    void getContentTest() {
      String expectedValue = "Test content";
      String actualValue = testPassage.getContent();

      assertEquals(expectedValue, actualValue);
    }

    @Test
    void toStringTest() {
      String expectedValue = "Test title"
              +
              "\n"
              +
              "Test content";

      String actualValue = testPassage.toString();

      assertEquals(expectedValue, actualValue);

    }

  }

  @Test
  @DisplayName("Test the url")
  void testUrl() {
    Passage testPassage = new Passage("Test title", "Test content", new ArrayList<>(), "test");
    String expectedValue = "/images/background/passages/test.png";
    String actualValue = testPassage.getUrl();

    assertEquals(expectedValue, actualValue);
  }

  @DisplayName("Test Mutators")
  @Nested
  class MutatorsTest {
    @Test
    @DisplayName("Check if the getLinks method returns a list of links")
    void getLinks() {
      Passage tempPassage = new Passage("Temporary title", "Temporary content");
      tempPassage.addLink(testLink);

      testPassage.addLink(testLink);
      assertEquals(testPassage.getLinks(), tempPassage.getLinks());
    }

    @Test
    @DisplayName("Check if link is added to passage")
    void checkIfLinkIsAdded() {
      testPassage.addLink(testLink);
      assertTrue(testPassage.getLinks().contains(testLink));
    }

    @Test
    @DisplayName("Check if list exists in passage")
    void testCheckIfListHasLink() {
      testPassage.addLink(testLink);
      assertTrue(testPassage.getLinks().contains(testLink));
    }
  }
}