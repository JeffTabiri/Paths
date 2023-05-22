package edu.ntnu.idatt2001.paths.controller;

import edu.ntnu.idatt2001.paths.enums.GameStates;
import edu.ntnu.idatt2001.paths.model.manager.AudioManager;
import edu.ntnu.idatt2001.paths.model.manager.OptionManager;
import edu.ntnu.idatt2001.paths.view.AchievementView;
import edu.ntnu.idatt2001.paths.view.ChooseStoryView;
import edu.ntnu.idatt2001.paths.view.HelpView;
import edu.ntnu.idatt2001.paths.view.LoadView;
import edu.ntnu.idatt2001.paths.view.OptionView;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * <h1>StartController</h1>
 * Controller class for the start view.
 *
 * @author Elementum
 * @version 1.0
 * @see OptionManager
 * @since 06/04/2023
 */
public class StartController extends Controller {

  AudioManager audioManager = AudioManager.getInstance();


  /**
   * Creates controller instance and plays the music for the main menu.
   *
   * @param primaryStage the stage to be used.
   */
  public StartController(Stage primaryStage) {
    super(primaryStage);
    audioManager.playMusic(GameStates.MAIN_MENU);
  }


  /**
   * Creates controller instance and opens the help view.
   */
  public void onActionHelp() {
    HelpView view = new HelpView(new HelpController(getStage()));
    getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
  }

  /**
   * Creates controller instance and opens the choose-story view.
   */
  public void onActionNewGame() {
    ChooseStoryView view = new ChooseStoryView(
            new ChooseStoryController(getStage()));
    getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
  }

  /**
   * Creates controller instance and opens the load view.
   */
  public void onActionLoadGame() {
    LoadView view = new LoadView(new LoadController(getStage()));
    getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
  }

  /**
   * Creates controller instance and opens the achievement view.
   */
  public void onActionAchievement() {
    AchievementController controller = new AchievementController(getStage());
    AchievementView view = new AchievementView(controller);
    getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
  }

  /**
   * Creates controller instance and opens the options view.
   */
  public void onActionOptions() {

    Stage dialog = new Stage();
    OptionView view = new OptionView(new OptionController(stage));
    Scene scene = new Scene(view.asParent(), 500, 300);
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.setResizable(false);
    dialog.setScene(scene);
    dialog.setTitle("Options");
    dialog.show();

  }


  /**
   * Exits the application.
   */
  public void onActionExit() {
    stage.close();
  }


  @Override
  public Stage getStage() {
    return stage;
  }

}
