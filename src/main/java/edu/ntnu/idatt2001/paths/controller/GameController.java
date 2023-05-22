package edu.ntnu.idatt2001.paths.controller;

import edu.ntnu.idatt2001.paths.model.enums.GameStates;
import edu.ntnu.idatt2001.paths.model.filehandling.FileGameHandler;
import edu.ntnu.idatt2001.paths.model.Passage;
import edu.ntnu.idatt2001.paths.model.Player;
import edu.ntnu.idatt2001.paths.model.manager.AudioManager;
import edu.ntnu.idatt2001.paths.model.manager.GameManager;
import edu.ntnu.idatt2001.paths.model.utility.AlertUtility;
import edu.ntnu.idatt2001.paths.model.utility.DialogUtility;
import edu.ntnu.idatt2001.paths.view.ChooseStoryView;
import edu.ntnu.idatt2001.paths.view.OptionView;
import edu.ntnu.idatt2001.paths.view.StartView;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * <h1>GameController</h1>
 * The controller class for the application.
 */
public class GameController extends Controller {

  AudioManager audioManager = AudioManager.getInstance();
  GameManager gameManager;

  /**
   * Constructor for the controller.
   *
   * @param stage the stage to set the scene on.
   * @param gameManager the gameManager to use.
   */
  public GameController(Stage stage, GameManager gameManager) {
    super(stage);
    this.gameManager = gameManager;

  }


  /**
   * Responsible for create a new StartView and set the scene to the stage.
   */
  public void onActionHelpGame() {
    DialogUtility.helpBox();
  }

  /**
   * Responsible for create a new StartView and set the scene to the stage.
   */
  public void onActionOptionGame() {
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
   * Responsible for create a new StartView and set the scene to the stage.
   */
  public void onActionSaveGame() {

    if (AlertUtility.showConfirmationAlert("Save game",
            "Are you sure you want to save the game?", "Save game")) {
      FileGameHandler fileGameHandler = new FileGameHandler();
      fileGameHandler.saveGame(gameManager.getGame(), gameManager.getCurrentPassage());
      StartView view = new StartView(new StartController(getStage()));
      getStage().setScene(new Scene(view.asParent()));
    }

  }


  /**
  * Responsible for create a new StartView and set the scene to the stage.
  */
  public void isPlayerDead() {
    if (AlertUtility.showDeathAlert("Death",
            "You died!", "You experienced a horrible death, "
                    + "may the gods smile upon your next life.")) {

      audioManager.playMusic(GameStates.MAIN_MENU);

      ChooseStoryView view = new ChooseStoryView(new ChooseStoryController(getStage()));
      getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));

    }

  }

  /**
   * Responsible for transitioning to the next passage.
   */
  public void onActionNextPassage(int index) {

    updatePlayerStats(index);

    if (gameManager.getGame().getPlayer().getHealth() == 0) {
      isPlayerDead();
    } else {
      if (!isGameFinished(index)) {
        audioManager.playMusic(GameStates.MAIN_MENU);

        ChooseStoryView view = new ChooseStoryView(new ChooseStoryController(getStage()));
        getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
      } else {
        updatePassage(index);
      }
    }
  }


  /**
   * Responsible for updating the player stats.
   *
   * @param passageChoiceIndex the index of the passage choice.
   */
  private void updatePlayerStats(int passageChoiceIndex) {

    Passage currentPassage = gameManager.getCurrentPassage();

    Player player = gameManager.getGame().getPlayer();

    currentPassage.getLinks().get(passageChoiceIndex).getActions().forEach(action ->
            action.execute(player));

    currentPassage.getLinks().get(passageChoiceIndex).getActions().clear();
  }


  /**
   * Responsible for updating the passage.
   *
   * @param passageChoiceIndex the index of the passage choice.
   */
  private void updatePassage(int passageChoiceIndex) {

    Passage currentPassage = gameManager.getCurrentPassage();

    currentPassage = gameManager.getGame().go(currentPassage.getLinks().get(passageChoiceIndex));

    gameManager.setCurrentPassage(currentPassage);

  }

  /**
   * Responsible for create a new StartView and set the scene to the stage.
   */
  public void onActionExitGame() {
    if (AlertUtility.showConfirmationAlert("Exiting Game",
            "Are you sure you want to exit the game?",
            "Exiting Game")) {

      audioManager.playMusic(GameStates.MAIN_MENU);
      StartView view = new StartView(new StartController(getStage()));
      getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
    }
  }

  /**
   * Responsible for checking if the game is finished.
   */
  public boolean isGameFinished(int index) {

    if (gameManager.getGame().go(gameManager.getCurrentPassage().getLinks().get(index)) == null) {
      if (AlertUtility.showDeathAlert("Exit game",
              "You Finished the game!",
              "You have finished the game, congratulations!")) {

        audioManager.playMusic(GameStates.MAIN_MENU);

        StartView view = new StartView(new StartController(getStage()));
        getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
      }
      return false;
    }
    return true;
  }

}

