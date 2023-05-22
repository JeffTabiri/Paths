package edu.ntnu.idatt2001.paths.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.ntnu.idatt2001.paths.model.actions.Action;
import edu.ntnu.idatt2001.paths.model.actions.InventoryAction;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;



@DisplayName("Test Class Link")
class LinkTest {
  Link testLink;
  Action testAction;

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
    void getActions() {
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

    @Test
    void itemIsRemovedFromActionList() {
      testLink.addAction(testAction);
      testLink.removeAction(testAction);
      assertTrue(testLink.getActions().isEmpty());
    }

  }


}