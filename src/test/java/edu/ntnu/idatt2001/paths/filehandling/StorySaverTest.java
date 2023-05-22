package edu.ntnu.idatt2001.paths.filehandling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import edu.ntnu.idatt2001.paths.model.Link;
import edu.ntnu.idatt2001.paths.model.Passage;
import edu.ntnu.idatt2001.paths.model.Story;
import edu.ntnu.idatt2001.paths.model.actions.GoldAction;
import edu.ntnu.idatt2001.paths.model.filehandling.StorySaver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


@DisplayName("StorySaver test")
class StorySaverTest {
  Passage passage;
  Link link;
  Story story;
  StorySaver storySaver;

  @BeforeEach
  void setUp() {

    //StorySaver Configurations
    storySaver = new StorySaver();

    //Link Configurations
    link = new Link("Test link", "Test reference");
    link.addAction(new GoldAction(10));

    //Passage Configurations
    passage = new Passage("Test title", "Test content");
    passage.addLink(link);

    //Story Configurations
    story = new Story("Test title", passage);
  }

  @DisplayName("Constructor test")
  @Nested
  class ConstructorTest {
    @DisplayName("Testing for saving a story into a string format")
    @Test
    void saveStory() {
      String expectedValue = """
              Test title

              ::Test title
              Test content
              [Test link](Test reference)
              {Gold}(10)""";

      String actualValue = storySaver.saveStory(story);
      assertEquals(expectedValue, actualValue);
    }

    @Test
    void saveStoryWithInvalidStory() {
      assertThrows(IllegalArgumentException.class, () -> storySaver.saveStory(null));
    }


  }
}
