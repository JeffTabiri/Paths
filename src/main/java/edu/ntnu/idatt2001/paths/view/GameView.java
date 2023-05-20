package edu.ntnu.idatt2001.paths.view;

import edu.ntnu.idatt2001.paths.controller.GameController;
import edu.ntnu.idatt2001.paths.model.AchievementList;
import edu.ntnu.idatt2001.paths.model.GameManager;
import edu.ntnu.idatt2001.paths.model.OptionManager;
import edu.ntnu.idatt2001.paths.model.Passage;
import edu.ntnu.idatt2001.paths.utility.AlertUtility;
import edu.ntnu.idatt2001.paths.utility.AudioEngine;
import edu.ntnu.idatt2001.paths.utility.ButtonEffects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;


import java.io.IOException;
import java.util.ArrayList;

public class GameView {

  AudioEngine audioEngine = AudioEngine.getInstance();
  OptionManager optionManager = OptionManager.getInstance();
  AchievementList achievementList = AchievementList.getInstance();
  GameController controller;
  GameManager gameManager;
  ListView<String> passageChoices = new ListView<>();


  BorderPane view = new BorderPane();

    public GameView(GameController controller , GameManager gameManager)  {

      passageChoices.getStyleClass().add("file-view");

      this.gameManager = gameManager;
      this.controller = controller;

      passageChoices.getItems().addAll(getPassageChoices(gameManager.getCurrentPassage()));

      passageChoices.setOnMouseClicked(e -> {
        if (e.getClickCount() == 2) {
          if (passageChoices.getSelectionModel().getSelectedItem() == null) {
            AlertUtility.showErrorAlert("Error", "No passage selected. Please select a passage");
            return;
          }
          controller.goPassageHandler(passageChoices.getSelectionModel().getSelectedIndex());
          updateView();
        }
      });

      view.getStylesheets().add(optionManager.getCurrentStyle());

      buildView();
      playMusic(gameManager.getCurrentPassage());
    }



    public Parent asParent(){
      return view;
    }

    public void updateView() {


      buildView();

      if (!gameManager.getIsPlayerAlive()) {
        controller.playerDied();
      }

      updateChoices();
      playMusic(gameManager.getCurrentPassage());
      updateAchievements();
    }


    public void updateChoices(){
      passageChoices.getItems().clear();
      passageChoices.getItems().addAll(getPassageChoices(gameManager.getCurrentPassage()));
    }

    public void updateAchievements() {
      achievementList.getAchievements().stream().filter(achievement -> !achievement.getIsFulfilled()).forEach(achievement -> {
        if (achievement.getGoal().isFulfilled(gameManager.getGame().getPlayer())) {
          achievement.setIsFulfilled(true);
          Notifications.create().title("Achievement unlocked").text(achievement.getDescription()).showInformation();
        }
      });


    }

    public void buildView() {
      //Choices placement
      view.setTop(buildTopMenu());
      view.setCenter(buildCenterMenu());
      view.setBottom(passageChoices);
    }



    private VBox buildCenterMenu() {

      VBox centerMenu = new VBox(10);

      ImageView imageView;

      //Nodes
      if (gameManager.getCurrentPassage().getUrl().isEmpty()) {
        imageView = buildImage("/images/background/ImageNotFound.png");
      }
      else {
        imageView = buildImage(gameManager.getCurrentPassage().getUrl());
      }


      TextFlow passageContent = buildPassageContent(gameManager.getCurrentPassage());

      //Alignment
        centerMenu.setAlignment(Pos.CENTER);
        passageContent.setTextAlignment(TextAlignment.CENTER);


      centerMenu.getChildren().addAll(imageView, passageContent);

      return centerMenu;

    }



  private VBox buildHotbarBox() {

    //Outer hotbar box
    VBox outerHotbarBox = new VBox(20);

    //Hotbar
    HBox innerHotbarBox = new HBox(20);

    //ImageView
    ImageView hotbarImageView = new ImageView("/images/UI/hotbar/HotbarBackground.png");
    hotbarImageView.setPreserveRatio(true);
    hotbarImageView.setFitWidth(512);

    //Player stats
    Text playerGold = new Text(gameManager.getGame().getPlayer().getGold() + " G");
    Text playerHealth = new Text( gameManager.getGame().getPlayer().getHealth() + " HP");
    Text playerScore = new Text( gameManager.getGame().getPlayer().getScore() + " PTS");
    Text playerName = new Text( gameManager.getGame().getPlayer().getName());

    //HBox for player stats
    HBox playerGoldBox = new HBox(5);
    HBox playerHealthBox = new HBox(5);
    HBox playerScoreBox = new HBox(5);
    HBox playerNameBox = new HBox();

    playerNameBox.getChildren().add(playerName);

    Image healthImage = new Image("images/UI/hotbar/Heart.png", 32, 32, false, false);
    ImageView healthImageView = new ImageView(healthImage);
    healthImageView.setFitWidth(32);
    healthImageView.setFitHeight(32);

    Image goldImage = new Image("images/UI/hotbar/Coin.png", 32, 32, false, false);
    ImageView goldImageView = new ImageView(goldImage);
    goldImageView.setFitWidth(32);
    goldImageView.setFitHeight(32);

    Image scoreImage = new Image("images/UI/hotbar/Score.png", 32, 32, false, false);
    ImageView scoreImageView = new ImageView(scoreImage);
    scoreImageView.setFitWidth(32);
    scoreImageView.setFitHeight(32);

    playerHealthBox.getChildren().addAll(healthImageView, playerHealth);
    playerHealthBox.setAlignment(Pos.CENTER);
    playerGoldBox.getChildren().addAll(goldImageView, playerGold);
    playerGoldBox.setAlignment(Pos.CENTER);

    playerScoreBox.getChildren().addAll(scoreImageView, playerScore);
    playerScoreBox.setAlignment(Pos.CENTER);

    innerHotbarBox.getChildren().addAll(playerHealthBox, playerGoldBox, playerScoreBox);
    outerHotbarBox.getChildren().addAll(playerNameBox, innerHotbarBox);

    innerHotbarBox.setAlignment(Pos.CENTER);
    outerHotbarBox.setAlignment(Pos.CENTER);
    playerNameBox.setAlignment(Pos.CENTER);

    outerHotbarBox.getStyleClass().add("hotbar");

    BorderPane.setAlignment(outerHotbarBox, Pos.CENTER_RIGHT);

    return outerHotbarBox;

  }

  public void playMusic(Passage passage) {
    if (passage.getGameState() != null) {
      audioEngine.playMusic(passage.getGameState());
    }
  }

  private ImageView buildImage(String path) {
    Image image = new Image(path);
    ImageView imageView;
    imageView = new ImageView(image);
    imageView.setPreserveRatio(true);
    imageView.setFitHeight(250);
    return imageView;
  }

  private TextFlow buildPassageContent(Passage passage) {
      TextFlow passageContent = new TextFlow();
      Text passageText = new Text(passage.getContent());
      passageContent.getChildren().add(passageText);
      passageContent.setPadding(new Insets(10, 10, 10, 10));

      passageText.getStyleClass().add("content");
      return passageContent;
  }


  /**
   * Creates a container for the title of the story.
   *
   * @return a container for the title of the story as a HBox
   */
  private HBox buildTitle() {

    //Title text
    Text titleText = new Text(gameManager.getGame().getStory().getTitle());


    //Title container
    HBox titleBox = new HBox();

    //StackPane
    StackPane topStackPane = new StackPane();

    //Image
    ImageView image = new ImageView("/images/UI/title/UI_Flat_Banner_01_Upward.png");


    //Set image size
    image.setFitWidth(400);
    image.setPreserveRatio(true);


    topStackPane.getChildren().addAll(image, titleText);


    //Add elements to titleBox
    titleBox.getChildren().add(topStackPane);

    //Set title box alignment
    titleBox.setAlignment(javafx.geometry.Pos.CENTER);


    //Set title box alignment
    titleBox.setAlignment(javafx.geometry.Pos.CENTER);

    //Styling
    titleText.getStyleClass().add("secondary-title");

    return titleBox;
  }


  /**
   * Returns a list of choices for the player to choose from
   *
   * @param passage to analyze and check for choices
   * @return a list of all possible choices
   */
  private ArrayList<String> getPassageChoices(Passage passage) {

    ArrayList<String> choices = new ArrayList<>();

    for (int i = 0; i < passage.getLinks().size(); i++) {
      choices.add(i + 1 + ": " + passage.getLinks().get(i).getText());
    }

    return choices;
  }

  private ArrayList<TextFlow> getItems() {
    ArrayList<TextFlow> items = new ArrayList<>();

    gameManager.getGame().getPlayer().getInventory().forEach(item -> {
      TextFlow itemFlow = new TextFlow();
      Text itemText = new Text(item);
      itemFlow.getChildren().add(itemText);
      itemText.getStyleClass().add("content");
      items.add(itemFlow);
    });


    return items;
  }


  private HBox createOptionsTab() {

    //Options container
    HBox optionsBox = new HBox(5);

    //Styling
    String optionBoxStyle = "option-button";

    int buttonSize = 32;

    //BackPack button
    Button backPackButton = new Button();
    ImageView backPackImage = new ImageView("/images/icons/optionIcon/BackPack.png");
    backPackImage.setPreserveRatio(true);
    backPackImage.setFitHeight(buttonSize);
    backPackImage.setFitWidth(buttonSize);
    backPackButton.setGraphic(backPackImage);
    backPackButton.backgroundProperty().set(Background.EMPTY);


    //Save button
    Button saveButton = new Button();
    ImageView saveImage = new ImageView("/images/icons/optionIcon/Save.png");
    saveImage.setPreserveRatio(true);
    saveImage.setFitHeight(buttonSize);
    saveImage.setFitWidth(buttonSize);
    saveButton.setGraphic(saveImage);
    saveButton.backgroundProperty().set(Background.EMPTY);


    //Options button
    Button optionsButton = new Button();
    ImageView optionsImage = new ImageView("/images/icons/optionIcon/Gear.png");
    optionsImage.setPreserveRatio(true);
    optionsImage.setFitHeight(buttonSize);
    optionsImage.setFitWidth(buttonSize);
    optionsButton.setGraphic(optionsImage);
    optionsButton.backgroundProperty().set(Background.EMPTY);


    //Help button
    Button helpButton = new Button();
    ImageView helpImage = new ImageView("/images/icons/optionIcon/Info.png");
    helpImage.setPreserveRatio(true);
    helpImage.setFitHeight(buttonSize);
    helpImage.setFitWidth(buttonSize);
    helpButton.setGraphic(helpImage);
    helpButton.backgroundProperty().set(Background.EMPTY);

    //Exit button
    Button exitButton = new Button();
    ImageView exitImage = new ImageView("/images/icons/optionIcon/Exit.png");
    exitImage.setPreserveRatio(true);
    exitImage.setFitHeight(buttonSize);
    exitImage.setFitWidth(buttonSize);
    exitButton.setGraphic(exitImage);
    exitButton.backgroundProperty().set(Background.EMPTY);

    optionsBox.getChildren().addAll(backPackButton, saveButton, optionsButton, helpButton, exitButton);
    optionsBox.setSpacing(10);
    optionsBox.setPadding(new javafx.geometry.Insets(2, 2, 2, 2));


    //Button effects
    saveButton.setOnMouseEntered(event -> ButtonEffects.buttonHover(saveButton));
    saveButton.setOnMouseExited(event -> ButtonEffects.buttonExit(saveButton));

    helpButton.setOnMouseEntered(event -> ButtonEffects.buttonHover(helpButton));
    helpButton.setOnMouseExited(event -> ButtonEffects.buttonExit(helpButton));

    optionsButton.setOnMouseEntered(event -> ButtonEffects.buttonHover(optionsButton));
    optionsButton.setOnMouseExited(event -> ButtonEffects.buttonExit(optionsButton));

    exitButton.setOnMouseEntered(event -> ButtonEffects.buttonHover(exitButton));
    exitButton.setOnMouseExited(event -> ButtonEffects.buttonExit(exitButton));

    backPackButton.setOnMouseEntered(event -> ButtonEffects.buttonHover(backPackButton));
    backPackButton.setOnMouseExited(event -> ButtonEffects.buttonExit(backPackButton));


    //Button actions
    exitButton.setOnAction(event -> controller.exitGame());

    helpButton.setOnAction(event -> controller.helpGame());

    saveButton.setOnAction(event -> {
      try {
        controller.saveGame();
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    });

    optionsButton.setOnAction(event -> controller.optionGame());

    backPackButton.setOnAction(event -> {

      Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      dialog.initOwner(controller.getStage());
      BorderPane root = new BorderPane();
      dialog.setWidth(300);

      TextFlow topTitle = new TextFlow(new Text("Inventory"));
      BorderPane.setAlignment(topTitle, javafx.geometry.Pos.CENTER);
      root.setTop(topTitle);
      ListView<TextFlow> listView = new ListView<>();
      root.setCenter(listView);

      topTitle.setTextAlignment(TextAlignment.CENTER);

      if (getItems().size() > 0) {
        listView.getItems().addAll(getItems());
      } else {
        listView.getItems().add(new TextFlow(new Text("No items in inventory")));
      }

      Scene dialogScene = new Scene(root, 300, 300);
      dialogScene.getStylesheets().add("css/global.css");
      dialog.setScene(dialogScene);

      topTitle.getStyleClass().add("secondary-title");
      listView.getStyleClass().add("secondary-title");
      topTitle.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
      dialog.show();
    });

    //Styling
    optionsBox.getStyleClass().add(optionBoxStyle);

    return optionsBox;

  }



  private VBox buildTopMenu() {

    //Bottom menubar container
    HBox leftBox = new HBox();
    HBox rightBox = new HBox();
    HBox bottom = new HBox();
    VBox topBox = new VBox();

    //Padding
    leftBox.setPadding(new Insets(10, 0, 10, 10));
    rightBox.setPadding(new Insets(10, 10, 10, 10));

    //Button placement
    leftBox.getChildren().add(createOptionsTab());
    rightBox.getChildren().add(buildHotbarBox());
    bottom.getChildren().addAll(leftBox, rightBox);
    topBox.getChildren().addAll(bottom, buildTitle());

    //Node alignment
    leftBox.setAlignment(Pos.CENTER_LEFT);
    rightBox.setAlignment(Pos.CENTER_RIGHT);

    //Node padding
    HBox.setHgrow(leftBox, Priority.ALWAYS);
    HBox.setHgrow(rightBox, Priority.ALWAYS);

    return topBox;

  }


}
