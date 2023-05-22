package edu.ntnu.idatt2001.paths.filehandling;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.Gson;
import edu.ntnu.idatt2001.paths.model.actions.GoldAction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class ActionAdapterTest {

  GoldAction goldAction;
  Gson gson;

  @BeforeEach
  void setUp() {
    goldAction = new GoldAction(100);
    gson = new Gson();
  }

  @DisplayName("Test deserialize")
  @Nested
  class DeserializeTest {
    @Test
    void deserialize() {
      String json = gson.toJson(goldAction);

      GoldAction expectedValue = goldAction;
      GoldAction actualValue = gson.fromJson(json, GoldAction.class);

      assertEquals(expectedValue.toString(), actualValue.toString());
    }
  }
}