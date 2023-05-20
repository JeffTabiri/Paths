package edu.ntnu.idatt2001.paths;


import edu.ntnu.idatt2001.paths.model.actions.Action;
import edu.ntnu.idatt2001.paths.model.actions.InventoryAction;
import edu.ntnu.idatt2001.paths.model.Link;
import org.junit.jupiter.api.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@DisplayName("Test Class Link")
class LinkTest {
    Link testLink;
    Action testAction;
    List<Action> actions = new ArrayList<>();

    @BeforeEach
    void setUp() {
        testLink = new Link("Test link", "Test reference");
        testAction = new InventoryAction("Sword");
    }


    @DisplayName("Test Constructor")
    @Nested
    class ConstructorTest {
        @Test
        @DisplayName("Test constructor with valid parameters")
        void testConstructorWithValidParameters() {
            String expectedValue = "Test link";
            String actualValue = testLink.getText();

            assertEquals(expectedValue, actualValue);
        }

        @Test
        @DisplayName("Test constructor with invalid parameters")
        void testConstructorWithInvalidParameters() {

            assertThrows(IllegalArgumentException.class, () -> new Link("", "Test reference"));
            assertThrows(IllegalArgumentException.class, () -> new Link("Test link", ""));
            assertThrows(IllegalArgumentException.class, () -> new Link("", ""));
        }

    }


    @DisplayName("Test Accessors")
    @Nested
    class AccessorsTest {
        @Test
        void getText() {
            String expectedValue = "Test link";
            String actualValue = testLink.getText();

            assertEquals(expectedValue, actualValue);
        }

        @Test
        void getReference() {
            String expectedValue = "Test reference";
            String actualValue = testLink.getReference();

            assertEquals(expectedValue, actualValue);
        }

        @Test
        void  getActions() {
            testLink.addAction(testAction);
            List<Action> actualValue = testLink.getActions();
            assertTrue(actualValue.contains(testAction));

        }

        @Test
        void toStringTest() {
            String expectedValue = "Test link"
                    +
                    "\n"
                    +
                    "Test reference";

            String actualValue = testLink.toString();
            assertEquals(expectedValue, actualValue);
        }

    }


    @DisplayName("Test Mutators")
    @Nested
    class MutatorsTest {
        @Test
        void itemIsAddedToActionList() {
            testLink.addAction(testAction);
            assertTrue(testLink.getActions().contains(testAction));
        }

    }


}