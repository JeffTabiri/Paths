package edu.ntnu.idatt2001.paths.controller;

import edu.ntnu.idatt2001.paths.filehandling.GameSave;
import edu.ntnu.idatt2001.paths.view.StartView;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LoadController extends Controller {

  public LoadController(Stage stage, double width, double height) {
    super(stage, width, height);
  }


  public List<String> getSavedStoryList() {
    return getStoryList();
  }


  /**
   * Gets a list of all the stories in the stories folder
   *
   * @return a list of all the stories in the stories folder as strings
   */
  public List<String> getStoryList() {

    List<String> storyList = new ArrayList<>();

    File[] files = new File("src/main/resources/story/savedStory").listFiles();

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

  public void goBackHandler() {
    StartView view = new StartView(new StartController(getStage(), getWidth(), getHeight()));
    getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
  }

  public void loadGame() throws IOException {
    GameSave gameSave = new GameSave();
    gameSave.loadGame();
  }
}
