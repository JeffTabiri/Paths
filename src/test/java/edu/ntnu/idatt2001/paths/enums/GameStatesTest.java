package edu.ntnu.idatt2001.paths.enums;

import static org.junit.jupiter.api.Assertions.assertEquals;

import edu.ntnu.idatt2001.paths.model.enums.GameStates;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class GameStatesTest {

  GameStates testGameStates;

  String address;

  @BeforeEach
  void setUp() {
    testGameStates = GameStates.MAIN_MENU;
    address = "/audio/background/Title Theme/";
  }

  @DisplayName("Enum Test")
  @Nested
  class EnumTest {
    @Test
    void getValue() {
      String expectedValue = address;
      String actualValue = testGameStates.getValue();
      assertEquals(expectedValue, actualValue);
    }

    @Test
    void valueOf() {
      GameStates expectedValue = testGameStates;
      GameStates actualValue = GameStates.valueOf("MAIN_MENU");
      assertEquals(expectedValue, actualValue);
    }
  }
}