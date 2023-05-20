package edu.ntnu.idatt2001.paths.filehandling;

import com.google.gson.*;
import edu.ntnu.idatt2001.paths.model.actions.*;
import java.lang.reflect.Type;

public class ActionAdapter implements JsonDeserializer<Action> {

  @Override
  public Action deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {

    JsonObject jsonObject = jsonElement.getAsJsonObject();

    if (jsonObject.has("gold")) {
      return jsonDeserializationContext.deserialize(jsonElement, GoldAction.class);
    } else if (jsonObject.has("health")) {
      return jsonDeserializationContext.deserialize(jsonElement, HealthAction.class);
    } else if (jsonObject.has("points")) {
      return jsonDeserializationContext.deserialize(jsonElement, ScoreAction.class);
    } else if (jsonObject.has("item")) {
        return jsonDeserializationContext.deserialize(jsonElement, InventoryAction.class);
    } else {
      throw new JsonParseException("Unknown element type: " + jsonElement.toString());
    }

  }
}

