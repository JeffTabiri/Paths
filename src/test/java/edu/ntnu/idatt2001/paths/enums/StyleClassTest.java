package edu.ntnu.idatt2001.paths.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2001.paths.model.enums.StyleClass;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class StyleClassTest {

  StyleClass testStyleClass;

  String address;

  @BeforeEach
  void setUp() {
    testStyleClass = StyleClass.MENU_BUTTON;
    address = "menu-button";
  }

  @DisplayName("Enum Test")
  @Nested
  class EnumTest {
    @Test
    void getValue() {
      String expectedValue = address;
      String actualValue = testStyleClass.getValue();
      assertEquals(expectedValue, actualValue);
    }

    @Test
    void valueOf() {
      StyleClass expectedValue = testStyleClass;
      StyleClass actualValue = StyleClass.valueOf("MENU_BUTTON");
      assertEquals(expectedValue, actualValue);
    }
  }
}