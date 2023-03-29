package edu.ntnu.idatt2001.paths.filehandling;

import edu.ntnu.idatt2001.paths.Link;
import edu.ntnu.idatt2001.paths.Passage;
import edu.ntnu.idatt2001.paths.Story;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test for file writer")
class FileWriterTest {

    Passage testPassage;
    Passage testPassage2;
    FileWriter fileWriter;
    Story testStory;

    @BeforeEach
    void setUp() {

        fileWriter = new FileWriter();

        //Passage 1 Configurations
        testPassage = new Passage("Test title", "Test content");
        testPassage.addLink(new Link("Test link", "Test reference"));

        //Passage 2 Configurations
        testPassage2 = new Passage("Test title 2", "Test content 2");
        testPassage2.addLink(new Link("Test link", "Test reference"));

        //Story Configurations
        testStory = new Story("Test title", testPassage);
        testStory.addPassage(testPassage2);
    }

    @DisplayName("Test for writing story")
    @Nested
    class WriteStoryTest {
        @DisplayName("Test for writing story")
        @Test
        void convertStoryIntoString() {
            String expectedValue = "Test title\n"
                                + "\n::Test title"
                                + "\n"
                                + "Test content"
                                + "\n[Test link](Test reference)"
                                + "\n"
                                + "\n::Test title 2"
                                + "\n"
                                + "Test content 2"
                                + "\n[Test link](Test reference)";

            String actualValue = fileWriter.convertStoryIntoString(testStory);
            assertEquals(expectedValue, actualValue);
        }
    }
}