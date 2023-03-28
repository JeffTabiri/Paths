package edu.ntnu.idatt2001.paths.filehandling;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Test for file reader")
class FileReaderTest {

    FileReader fileReader;
    File file;

    @BeforeEach
    void setUp() {
        fileReader = new FileReader();
        file = new File("src/main/resources/filehandletest.paths");
    }

    @DisplayName("Test for creating story")
    @Nested
    class BuildStoryTest {


        @Test
        @DisplayName("Test for creating passages")
        void getPassages() {

            fileReader.createStory(file);
            assertFalse(fileReader.getPassages().isEmpty());
        }


        @Test
        @DisplayName("Test for creating passages")
        void readFromFileAndCreateStory() {
            fileReader.readFromFileAndCreateStory(file);
            fileReader.createStory(file);

            String expectedValue = "Haunted House";
            String actualValue = fileReader.getStory().getTitle();
            assertEquals(expectedValue, actualValue);
        }


        @Test
        @DisplayName("Test for creating story")
        void createStory() {
            fileReader.createStory(file);

            String expectedValue = "Haunted House";
            String actualValue = fileReader.getStory().getTitle();
            assertEquals(expectedValue, actualValue);
        }
    }
}