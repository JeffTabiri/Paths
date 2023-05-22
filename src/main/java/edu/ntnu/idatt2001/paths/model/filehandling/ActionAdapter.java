package edu.ntnu.idatt2001.paths.model.filehandling;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import edu.ntnu.idatt2001.paths.model.actions.Action;
import edu.ntnu.idatt2001.paths.model.actions.GoldAction;
import edu.ntnu.idatt2001.paths.model.actions.HealthAction;
import edu.ntnu.idatt2001.paths.model.actions.InventoryAction;
import edu.ntnu.idatt2001.paths.model.actions.ScoreAction;
import java.lang.reflect.Type;

/**
 * <h1>GameSave</h1>
 * This class is a custom ActionAdapter for the Gson library,
 * used to deserialize the Action interface.
 * It is used to deserialize the actions in the game save file.
 *
 * @version 1.0
 * @see com.google.gson.JsonDeserializer
 * @see Action
 * @see FileGameHandler
 * @since 06/02/2023
 */
public class ActionAdapter implements JsonDeserializer<Action> {

  @Override
  public Action deserialize(
          JsonElement jsonElement,
          Type type,
          JsonDeserializationContext jsonDeserializationContext)
          throws JsonParseException {

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
      throw new JsonParseException("Unknown element type: ");
    }
  }
}

