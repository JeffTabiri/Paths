package edu.ntnu.idatt2001.paths;

import edu.ntnu.idatt2001.paths.model.Link;
import edu.ntnu.idatt2001.paths.model.Passage;
import edu.ntnu.idatt2001.paths.model.Story;
import org.junit.jupiter.api.*;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        }
    }

    @DisplayName("Test Accessors")
    @Nested
    class AccessorsTest {

        @DisplayName("Access title of story (getTitle()")
        @Test
        void getTitleTest() {
            String expectedValue = "The Story";
            String actualValue = testStory.getTitle();
            assertEquals(expectedValue, actualValue);
        }

        @DisplayName("Access opening passage of story (getOpeningPassage()")
        @Test
        void getOpeningPassageTest() {
            Passage expectedValue = testPassage;
            Passage actualValue = testStory.getOpeningPassage();
            assertEquals(expectedValue, actualValue);
        }

        @DisplayName("Access passages of story (getPassages()")
        @Test
        void getPassagesTest() {
            List<Passage> expectedValue = List.of(testPassage);
            Collection<Passage> actualValue =  testStory.getPassages();

            assertTrue(expectedValue.containsAll(actualValue));
        }

    }

    @DisplayName("Test Mutators")
    @Nested
    class MutatorsTest {


        @BeforeEach
        void setUp() {
            testStory.addPassage(testPassage);
        }

        @DisplayName("Add passage to story (addPassage()")
        @Test
        void addPassage() {
            Passage temporaryPassage = new Passage("Temporary title", "Temporary content");
            testStory.addPassage(temporaryPassage );
            assertTrue(testStory.getPassages().contains(temporaryPassage));
        }

        @DisplayName("Remove passage from story (removePassage()")
        @Test
        void removePassage() {
            testStory.addPassage(testPassage);
            testStory.removePassage(new Link("Test title", "Test title"));
            assertFalse(testStory.getPassages().contains(testPassage));
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

        @DisplayName("Get broken links (getBrokenLinks()")
        @Test
        void getBrokenLinks() {
            testStory.addPassage(testPassage);
            testPassage.addLink(testLink);

            assertTrue(testStory.getBrokenLinks().contains(testLink));

        }

    }
}