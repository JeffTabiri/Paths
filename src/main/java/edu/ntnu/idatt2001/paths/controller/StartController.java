package edu.ntnu.idatt2001.paths.controller;

import edu.ntnu.idatt2001.paths.utility.AudioEngine;
import edu.ntnu.idatt2001.paths.utility.GameStates;
import edu.ntnu.idatt2001.paths.view.ChooseStoryView;
import edu.ntnu.idatt2001.paths.view.AchievementView;
import edu.ntnu.idatt2001.paths.view.HelpView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartController extends Controller {

  AudioEngine audioEngine = AudioEngine.getInstance();


  public StartController(Stage primaryStage, double width, double height) {
    super(primaryStage, width, height);
    audioEngine.playMusic(GameStates.MAIN_MENU);
  }

  public void goHelpHandler() {
    HelpView view = new HelpView(new HelpController(getStage(), getWidth(), getHeight()));
    getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
  }

  public void goNewGameHandler() {
    ChooseStoryView view = new ChooseStoryView(new ChooseStoryController(getStage(), getWidth(), getHeight()));
    getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
  }

  public void goLoadGameHandler() {
    System.out.println("Go load game");
  }

  public void goAchievementsHandler() {
    AchievementController controller = new AchievementController(getStage(), getWidth(), getHeight());
    AchievementView view = new AchievementView(controller);
    Scene scene = new Scene(view.asParent(), stage.getWidth(), stage.getHeight());
    stage.setScene(scene);

  }

  public void goExitHandler() {
    stage.close();
  }

  public Stage getStage() {
      return stage;
  }

}
