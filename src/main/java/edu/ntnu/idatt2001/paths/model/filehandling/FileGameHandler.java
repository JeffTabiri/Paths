package edu.ntnu.idatt2001.paths.model.filehandling;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import edu.ntnu.idatt2001.paths.model.Game;
import edu.ntnu.idatt2001.paths.model.Passage;
import edu.ntnu.idatt2001.paths.model.Player;
import edu.ntnu.idatt2001.paths.model.Story;
import edu.ntnu.idatt2001.paths.model.actions.Action;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <h1>FileGameHandler</h1>
 * Class for handling the saving and loading of a game.
 */
public class FileGameHandler {

  BufferedReader reader;
  SimpleDateFormat formatter = new SimpleDateFormat("yyyy");
  String uniqueId = "";

  Gson gson = new GsonBuilder()
          .setPrettyPrinting()
          .registerTypeAdapter(Action.class, new ActionAdapter())
          .create();


  /**
   * Method for saving a game.
   *
   * @param game The game to be saved.
   * @throws IllegalArgumentException If the file is not found.
   */
  public void saveGame(Game game, Passage currentPassage) throws IllegalArgumentException {

    uniqueId = game.getPlayer().getName() + " " + formatter.format(new Date());

    final JsonObject jsonObject = new JsonObject();
    final JsonArray jsonArray = new JsonArray();

    //Convert the list of passages to an arraylist.
    ArrayList<Passage> passages = new ArrayList<>(game.getStory().getPassages());

    //Divide the game into different parts and add them to the json object.
    jsonObject.add("player", gson.toJsonTree(game.getPlayer()));

    jsonObject.add("storyTitle", gson.toJsonTree(game.getStory().getTitle()));

    jsonObject.add("passages", gson.toJsonTree(passages));

    jsonObject.add("openingPassage", gson.toJsonTree(currentPassage));

    //Add the json object to the json array.
    jsonArray.add(jsonObject);

    if (!new File("src/main/resources/story/savedStory").exists()) {
      new File("src/main/resources/story/savedStory").mkdir();
    }

    //Write the json array to the file.
    try (BufferedWriter writer = new BufferedWriter(
            new FileWriter("src/main/resources/story/savedStory/" + uniqueId + ".json"))) {
      writer.write(gson.toJson(jsonArray));
    } catch (IOException e) {
      throw new IllegalArgumentException("File not found");
    }

  }

  /**
   * Method for loading a game.
   *
   * @throws IOException If the file is not found
   */
  public Game loadGame(String uniqueId) throws IOException {

    File file = new File("src/main/resources/story/savedStory/" + uniqueId + ".json");

    reader = new BufferedReader(new FileReader(file));

    JsonArray jsonObject = gson.fromJson(reader, JsonArray.class);

    Player player = null;
    String storyTitle = "";
    Passage openingPassage = null;
    List<Passage> passages = null;

    for (JsonElement jsonElement : jsonObject) {

      jsonElement.getAsJsonObject();

      player = gson.fromJson(jsonElement.getAsJsonObject().get("player"), Player.class);

      storyTitle = gson.fromJson(jsonElement.getAsJsonObject().get("storyTitle"), String.class);

      openingPassage = gson.fromJson(
              jsonElement.getAsJsonObject().get("openingPassage"), Passage.class);

      passages = gson.fromJson(
              jsonElement.getAsJsonObject().get("passages"), new TypeToken<List<Passage>>() {
              }.getType());

    }

    if (player == null) {
      throw new IllegalArgumentException("Player could not be loaded.");
    }
    if (storyTitle == null) {
      throw new IllegalArgumentException("Story title could not be loaded.");
    }
    if (openingPassage == null) {
      throw new IllegalArgumentException("Opening passage could not be loaded.");
    }
    if (passages == null) {
      throw new IllegalArgumentException("Passages could not be loaded.");
    }

    Story story = new Story(storyTitle, openingPassage);


    for (Passage passage : passages) {
      story.addPassage(passage);
    }

    return new Game(player, story);
  }

  /**
   * Method for getting the unique id of the game.
   *
   * @return The unique id of the game.
   */
  public String getUniqueId() {
    return uniqueId;
  }

}


