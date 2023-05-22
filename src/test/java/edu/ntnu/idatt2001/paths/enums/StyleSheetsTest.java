package edu.ntnu.idatt2001.paths.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2001.paths.model.enums.StyleSheets;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class StyleSheetsTest {

  StyleSheets testStyleSheets;

  String address;


  @BeforeEach
  void setUp() {
    testStyleSheets = StyleSheets.DARK_THEME;
    address = "css/Dark.css";


  }

  @DisplayName("Enum Test")
  @Nested
  class EnumTest {
    @Test
    void getValue() {
      String expectedValue = address;
      String actualValue = testStyleSheets.getValue();
      assertEquals(expectedValue, actualValue);

    }

    @Test
    void valueOf() {
      StyleSheets expectedValue = testStyleSheets;
      StyleSheets actualValue = StyleSheets.valueOf("DARK_THEME");
      assertEquals(expectedValue, actualValue);
    }

  }
}