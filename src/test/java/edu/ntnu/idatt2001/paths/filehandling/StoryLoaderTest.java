package edu.ntnu.idatt2001.paths.filehandling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Test for file reader")
class StoryLoaderTest {

    StoryLoader storyLoader;

    File file;

    @BeforeEach
    void setUp() throws IOException {
        file = new File("src/main/resources/stories/HauntedHouse.paths");
        storyLoader = new StoryLoader(file);
    }


    @DisplayName("Test for reading a file and creating a story")
    @Test
    void StoryLoader() {
        String expectedValue = "Haunted House";
        String actualValue = storyLoader.getStory().getTitle();

        assertEquals(expectedValue, actualValue.trim());
    }

}