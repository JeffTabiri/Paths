package edu.ntnu.idatt2001.paths.controller;

import edu.ntnu.idatt2001.paths.filehandling.StoryLoader;
import edu.ntnu.idatt2001.paths.model.*;
import edu.ntnu.idatt2001.paths.view.GameView;
import edu.ntnu.idatt2001.paths.view.StartView;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChooseStoryController extends Controller {


  public ChooseStoryController(Stage stage, double width, double height) {
    super(stage, width, height);
  }

    public void goBackHandler() {
      StartView view = new StartView(new StartController(getStage(), getWidth(), getHeight()));
      getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
    }

    public void startGameHandler(File file, String name) throws IOException {

      try {

        Story story = new StoryLoader(file).getStory();

        Player player = new PlayerBuilder(name)
                .health(100)
                .gold(0)
                .score(0)
                .build();

        Game game = new Game(player, story);

        GameManager gameManager = new GameManager(game);

        GameController gameController = new GameController(getStage(), getWidth(), getHeight(), gameManager);

        GameView view = new GameView(gameController, gameManager);
        getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));

      } catch (IOException e) {

        throw new IllegalArgumentException("Error. Could not load story.");

      } catch (IllegalArgumentException e) {

        throw new IllegalArgumentException(e.getMessage());

      }

    }


  /**
   * Gets a list of all the stories in the stories folder
   *
   * @return a list of all the stories in the stories folder as strings
   */
  public List<String> getStoryList() {

    List<String> storyList = new ArrayList<>();

    File[] files = new File("src/main/resources/story/preloadedStory").listFiles();

    for (File file : files) {

      if (file == null) {
        throw new IllegalArgumentException("No stories found");
      }

      if (file.isFile()) {
        storyList.add(file.getName());
      }
    }

    return storyList;
  }

}
