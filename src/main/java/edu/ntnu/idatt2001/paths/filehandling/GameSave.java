package edu.ntnu.idatt2001.paths.filehandling;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonToken;
import edu.ntnu.idatt2001.paths.model.Game;
import edu.ntnu.idatt2001.paths.model.Passage;
import edu.ntnu.idatt2001.paths.model.Player;
import edu.ntnu.idatt2001.paths.model.actions.Action;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GameSave {


  Gson gson = new GsonBuilder()
          .setPrettyPrinting()
          .registerTypeAdapter(Action.class, new ActionAdapter())
          .create();


  public void saveGame(Game game) throws IOException {

    JsonObject jsonObject = new JsonObject();
    JsonArray jsonArray = new JsonArray();

    jsonObject.add("player", gson.toJsonTree(game.getPlayer()));
    jsonObject.add("storyTitle", gson.toJsonTree(game.getStory().getTitle()));
    jsonObject.add("openingPassage", gson.toJsonTree(game.getStory().getOpeningPassage()));
    ArrayList<Passage> passages = new ArrayList<>(game.getStory().getPassages());
    jsonObject.add("passages", gson.toJsonTree(passages));
    jsonArray.add(jsonObject);

    try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/story/savedStory/game.json"))) {
      System.out.println(gson.toJson(jsonArray));
      writer.write(gson.toJson(jsonArray));
    }
  }

  public void loadGame() throws IOException {
      File file = new File("src/main/resources/story/savedStory/game.json");
      BufferedReader reader = new BufferedReader(new FileReader(file));

      JsonArray jsonObject1 = gson.fromJson(reader, JsonArray.class);

      Player player = null;
      String storyTitle;
      Passage openingPassage;
      List<Passage> passages1;

    for (JsonElement jsonElement : jsonObject1) {
      jsonElement.getAsJsonObject();
        player = gson.fromJson(jsonElement.getAsJsonObject().get("player"), Player.class);
        storyTitle = gson.fromJson(jsonElement.getAsJsonObject().get("storyTitle"), String.class);
        openingPassage = gson.fromJson(jsonElement.getAsJsonObject().get("openingPassage"), Passage.class);
        passages1 = gson.fromJson(jsonElement.getAsJsonObject().get("passages"), new TypeToken<List<Passage>>() {
        }.getType());
    }

    System.out.println(player.toString());

  }



  }


