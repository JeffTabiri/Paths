package edu.ntnu.idatt2001.paths.controller;

import edu.ntnu.idatt2001.paths.model.Game;
import edu.ntnu.idatt2001.paths.model.Player;
import edu.ntnu.idatt2001.paths.model.PlayerBuilder;
import edu.ntnu.idatt2001.paths.model.Story;
import edu.ntnu.idatt2001.paths.model.filehandling.StoryLoader;
import edu.ntnu.idatt2001.paths.model.manager.GameManager;
import edu.ntnu.idatt2001.paths.view.GameView;
import edu.ntnu.idatt2001.paths.view.StartView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * <h1>ChooseStoryController</h1>
 * The controller class for the application.
 * This class is responsible for handling the logic for the load view.
 *
 * @author Elementum
 * @version 1.0
 * @since 06/04/2023
 */
public class ChooseStoryController extends Controller {


  /**
   * Constructor for the controller.
   *
   * @param stage the stage to set the scene on.
   */
  public ChooseStoryController(Stage stage) {
    super(stage);
  }

  /**
   * Responsible for create a new StartView and set the scene to the stage.
   */
  public void onActionGoBack() {
    StartView view = new StartView(new StartController(getStage()));
    getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
  }

  /**
   * Loads a game from a file and starts the game.
   *
   * @param file the file to load the game from.
   * @param name the name of the player.
   * @throws IOException if the file is not found.
   */
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

      GameController gameController = new GameController(getStage(), gameManager);

      GameView view = new GameView(gameController, gameManager);
      getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));

    } catch (IOException e) {

      throw new IllegalArgumentException("Error. Could not load story.");

    } catch (IllegalArgumentException e) {

      throw new IllegalArgumentException(e.getMessage());

    }

  }


  /**
   * Gets a list of all the stories in the stories' folder.
   *
   * @return a list of all the stories in the stories folder as strings
   */
  public List<String> getStoryList() {

    List<String> storyList = new ArrayList<>();

    File[] files = new File("src/main/resources/story/preloadedStory").listFiles();

    return getStrings(storyList, files);
  }


  /**
   * Gets a list of all the saved stories in the saved stories' folder.
   *
   * @param storyList the list of stories
   * @param files    the files in the folder
   * @return a list of all the saved stories in the saved stories folder as strings
   */
  static List<String> getStrings(List<String> storyList, File[] files) {

    if (files == null) {
      throw new IllegalArgumentException("No stories found");
    }

    for (File file : files) {

      if (file == null) {
        throw new IllegalArgumentException("No stories found");
      }

      if (file.isFile()) {
        String name = file.getName();

        name = name.replace(".json", "");
        name = name.replace(".paths", "");

        storyList.add(name);
      }


    }

    return storyList;
  }

}
