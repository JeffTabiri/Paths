package edu.ntnu.idatt2001.paths.controller;

import edu.ntnu.idatt2001.paths.model.*;
import edu.ntnu.idatt2001.paths.utility.AlertUtility;
import edu.ntnu.idatt2001.paths.utility.DialogUtility;
import edu.ntnu.idatt2001.paths.view.ChooseStoryView;
import edu.ntnu.idatt2001.paths.view.OptionView;
import edu.ntnu.idatt2001.paths.view.StartView;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class GameController extends Controller {

  GameManager gameManager;
  public GameController(Stage stage, double width, double height , GameManager gameManager) {
    super(stage, width, height);
    this.gameManager = gameManager;

  }


  public void helpGame() {
    DialogUtility.helpBox();
  }

  public void optionGame() {

    Stage dialog = new Stage();
    OptionView view = new OptionView(new OptionController(dialog, getWidth(), getHeight()));
    Scene scene = new Scene(view.asParent(), 500, 300);
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.setResizable(false);
    dialog.setScene(scene);
    dialog.setTitle("Options");
    dialog.show();

  }

  public void saveGame() {

    if (AlertUtility.showConfirmationAlert("Save game", "Are you sure you want to save the game?", "Save game")) {
      StartView view = new StartView(new StartController(getStage(), getWidth(), getHeight()));
      getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
    }

  }

  public void playerDied() {
    AlertUtility.showErrorAlert("You died", "You died a horrible death! May your next run be more successful.");
    ChooseStoryView view = new ChooseStoryView(new ChooseStoryController(getStage(), getWidth(), getHeight()));
    getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
  }


  public void goPassageHandler(int index) {
    if(isGameFinished(index)) {
      updatePlayerStats(index);
      updatePassage(index);
    }

  }

  private void updatePlayerStats(int passageChoiceIndex) {

    Passage currentPassage = gameManager.getCurrentPassage();

    Player player = gameManager.getGame().getPlayer();

    currentPassage.getLinks().get(passageChoiceIndex).getActions().forEach(action ->
            action.execute(player));

  }


  private void updatePassage(int passageChoiceIndex) {

        Passage currentPassage = gameManager.getCurrentPassage();

        currentPassage = gameManager.getGame().go(currentPassage.getLinks().get(passageChoiceIndex));

        gameManager.setCurrentPassage(currentPassage);

  }

  public void exitGame() {
    if (AlertUtility.showConfirmationAlert("Exit game", "Are you sure you want to exit the game?", "Exit game")) {
      StartView view = new StartView(new StartController(getStage(), getWidth(), getHeight()));
      getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
    }
  }

  public boolean isGameFinished(int index) {

    if (gameManager.getGame().go(gameManager.getCurrentPassage().getLinks().get(index)) == null) {
      if (AlertUtility.showConfirmationAlert("Exit game", "You Finished the game!", "Exit game")) {
        StartView view = new StartView(new StartController(getStage(), getWidth(), getHeight()));
        getStage().setScene(new Scene(view.asParent(), getWidth(), getHeight()));
      }
    return false;
    }
    return true;
  }

}

