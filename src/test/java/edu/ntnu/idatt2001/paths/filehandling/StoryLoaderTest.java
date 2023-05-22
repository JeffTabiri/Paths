package edu.ntnu.idatt2001.paths.filehandling;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


@DisplayName("Test for file reader")
class StoryLoaderTest {

  StoryLoader storyLoader;
  File file;

  @BeforeEach
  void setUp() throws IOException {
    file = new File("src/main/resources/story/preloadedStory/Haunted House.paths");
    storyLoader = new StoryLoader(file);
  }

  @DisplayName("Test Constructor")
  @Nested
  class ConstructorTest {

    @Test
    void loadStory() {
      String expectedValue = "Haunted House";
      String actualValue = storyLoader.getStory().getTitle();

      assertEquals(expectedValue, actualValue.trim());
    }

    @Test
    void loadStoryWithInvalidFile() {
      assertThrows(IllegalArgumentException.class, () -> new StoryLoader(null));
    }

  }

}