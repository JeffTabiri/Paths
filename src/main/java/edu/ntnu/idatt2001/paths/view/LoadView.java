package edu.ntnu.idatt2001.paths.view;

import edu.ntnu.idatt2001.paths.controller.ChooseStoryController;
import edu.ntnu.idatt2001.paths.controller.LoadController;
import edu.ntnu.idatt2001.paths.model.OptionManager;
import edu.ntnu.idatt2001.paths.utility.AlertUtility;
import edu.ntnu.idatt2001.paths.utility.AudioEngine;
import edu.ntnu.idatt2001.paths.utility.ButtonEffects;
import edu.ntnu.idatt2001.paths.utility.GameStates;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;

public class LoadView {

  OptionManager optionManager = OptionManager.getInstance();

  AudioEngine audioEngine = AudioEngine.getInstance();

  StackPane view = new StackPane();

  ListView<String> storyListView = new ListView<>();

  LoadController controller;

  public LoadView(LoadController controller) {
    storyListView.getStyleClass().add("file-view");

    audioEngine.playMusic(GameStates.MAIN_MENU);

    storyListView.setItems(FXCollections.observableArrayList(controller.getSavedStoryList()));

    storyListView.setOnMouseClicked(event -> {
      if (event.getClickCount() == 2) {
        if (storyListView.getSelectionModel().getSelectedItem() == null) {
          AlertUtility.showErrorAlert("Error", "No story selected. Please select a story");
          return;
        }
        popupBox();
      }
    });



    this.controller = controller;

    buildView();
  }


  public void buildView() {

    BorderPane root = new BorderPane();

    root.setBottom(buildBottomMenu());
    root.setTop(buildTitle());
    root.setCenter(storyListView);
    root.setPadding(new Insets(10, 20, 10, 20));

    //CSS styling
    root.getStylesheets().add(optionManager.getCurrentStyle());

    view.getChildren().addAll(buildPane(), root);

  }

  public Parent asParent() {
    return view;
  }


  /**
   * Builds the bottom menu bar for the choose story scene.
   *
   * @return the bottom menu bar in a HBox
   */
  private HBox buildBottomMenu() {

    //Bottom menubar container
    HBox leftBox = new HBox();
    HBox rightBox = new HBox();
    HBox bottom = new HBox();

    //Buttons
    Button returnButton = new Button("Return");
    Button startGameButton = new Button("Start Game");

    //Button actions
    returnButton.setOnAction(e -> controller.goBackHandler());

    startGameButton.setOnAction(e -> {

      if (storyListView.getSelectionModel().getSelectedItem() == null) {
        AlertUtility.showErrorAlert("Error!", "No story selected. Please select a story");

        return;
      }

      try {
        controller.loadGame();
      } catch (IOException ex) {
        throw new RuntimeException(ex);
      }
      popupBox();

    });

    //Hover effects
    returnButton.setOnMouseEntered(e -> ButtonEffects.buttonHover(returnButton));
    startGameButton.setOnMouseEntered(e -> ButtonEffects.buttonHover(startGameButton));

    //Exit hover effects
    returnButton.setOnMouseExited(e -> ButtonEffects.buttonExit(returnButton));
    startGameButton.setOnMouseExited(e -> ButtonEffects.buttonExit(startGameButton));


    //Padding
    leftBox.setPadding(new Insets(20, 0, 20, 20));
    rightBox.setPadding(new Insets(20, 20, 20, 0));

    //Button placement
    leftBox.getChildren().add(returnButton);
    rightBox.getChildren().add(startGameButton);
    bottom.getChildren().addAll(leftBox, rightBox);

    //Node alignment
    leftBox.setAlignment(Pos.CENTER_LEFT);
    rightBox.setAlignment(Pos.CENTER_RIGHT);

    //Node padding
    HBox.setHgrow(leftBox, Priority.ALWAYS);
    HBox.setHgrow(rightBox, Priority.ALWAYS);

    //Styling
    returnButton.getStyleClass().add("menu-button");
    startGameButton.getStyleClass().add("menu-button");

    return bottom;
  }


  private Pane buildPane() {
    Pane pane = new Pane();

    ImageView imageView = new ImageView("/images/background/MainMenuBackground.png");
    imageView.fitWidthProperty().bind(pane.widthProperty());
    imageView.setPreserveRatio(true);

    pane.getChildren().add(imageView);

    return pane;
  }

  private HBox buildTitle() {

    //Title text
    Text gameTitle = new Text("CHOOSE STORY");

    //Title container
    HBox titleBox = new HBox();

    //Image
    ImageView image = new ImageView("/images/UI/title/UI_Flat_Banner_01_Upward.png");

    //Set image size
    image.setFitWidth(400);
    image.setPreserveRatio(true);

    //StackPane
    StackPane topBorderPane = new StackPane();

    //Add elements to StackPane
    topBorderPane.getChildren().addAll(image, gameTitle);

    //Add elements to HBox
    titleBox.getChildren().add(topBorderPane);

    //Set StackPane alignment
    StackPane.setAlignment(gameTitle, javafx.geometry.Pos.CENTER);
    StackPane.setAlignment(image, javafx.geometry.Pos.CENTER);

    //Set title box alignment
    titleBox.setAlignment(javafx.geometry.Pos.CENTER);

    //Styling
    gameTitle.getStyleClass().add("title");

    //Animation
    TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(1.5));
    translateTransition2.setNode(titleBox);
    translateTransition2.setFromY(0);
    translateTransition2.setToY(-20);
    translateTransition2.autoReverseProperty().setValue(true);
    translateTransition2.setCycleCount(Animation.INDEFINITE);
    translateTransition2.play();

    return titleBox;
  }



  private void popupBox() {
    //pLAY LOADED GAME
  }




}
