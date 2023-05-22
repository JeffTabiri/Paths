package edu.ntnu.idatt2001.paths.controller;

import static edu.ntnu.idatt2001.paths.controller.ChooseStoryController.getStrings;

import edu.ntnu.idatt2001.paths.model.filehandling.FileGameHandler;
import edu.ntnu.idatt2001.paths.model.Game;
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
 * <h1>LoadController</h1>
 * The controller class for the application.
 * This class is responsible for handling the logic for the load view.
 *
 * @author Elementum
 * @version 1.0
 * @since 06/04/2023
 */
public class LoadController extends Controller {

  /**
   * Constructor for the controller.
   *
   * @param stage the stage to set the scene on.
   */
  public LoadController(Stage stage) {
    super(stage);
  }


  /**
   * Loads a game from a file and starts the game.
   *
   * @return the list of saved games.
   */
  public List<String> getSavedStoryList() {
    return getStoryList();
  }


  /**
   * Gets a list of all the stories in the stories' folder.
   *
   * @return a list of all the stories in the stories folder as strings
   */
  public List<String> getStoryList() {

    List<String> storyList = new ArrayList<>();

    File[] files = new File("src/main/resources/story/savedStory").listFiles();

    return getStrings(storyList, files);
  }


  /**
   * Responsible for create a new StartView and set the scene to the stage.
   */
  public void onActionReturn() {
    StartView view = new StartView(new StartController(getStage()));
    getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
  }


  /**
   * Loads a game from a file and starts the game.
   *
   * @throws IOException if the file is not found.
   */
  public void onActionLoadGame(String uniqueId) throws IOException {

    FileGameHandler fileGameHandler = new FileGameHandler();
    Game game = fileGameHandler.loadGame(uniqueId);

    GameManager gameManager = new GameManager(game);
    GameController gameController = new GameController(getStage(), gameManager);

    GameView view = new GameView(gameController, gameManager);
    getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
  }

  /**
   * Deletes a game from the saved games' folder.
   *
   * @param uniqueId the unique id of the game to be deleted.
   * @throws IOException if the file is not found.
   */
  public void onActionDeleteGame(String uniqueId) throws IOException {

    File file = new File("src/main/resources/story/savedStory/" + uniqueId + ".json");

    if (file.delete()) {
      return;
    }
    throw new IOException("Could not delete file");

  }

}
