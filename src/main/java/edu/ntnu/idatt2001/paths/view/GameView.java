package edu.ntnu.idatt2001.paths.view;

import edu.ntnu.idatt2001.paths.controller.GameController;
import edu.ntnu.idatt2001.paths.model.enums.StyleClass;
import edu.ntnu.idatt2001.paths.model.Passage;
import edu.ntnu.idatt2001.paths.model.manager.AchievementManager;
import edu.ntnu.idatt2001.paths.model.manager.AudioManager;
import edu.ntnu.idatt2001.paths.model.manager.GameManager;
import edu.ntnu.idatt2001.paths.model.manager.OptionManager;
import edu.ntnu.idatt2001.paths.model.utility.AlertUtility;
import edu.ntnu.idatt2001.paths.model.utility.ButtonUtility;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;

/**
 * <h1>GameView</h1>
 * {@code GameView} is the view class for the game view.
 * This class is responsible for building the game view.
 * This class receives information from the controller and the model.
 * This class also sends information to the controller and the model.
 *
 * @author Elementum
 * @version 1.0
 * @see GameController
 * @see GameManager
 * @since 06/04/2023
 */
public class GameView {
  private final Logger logger = Logger.getLogger(getClass().getName());
  private final AudioManager audioManager;
  private final OptionManager optionManager;
  private final AchievementManager achievementManager;
  private final GameController controller;
  private final GameManager gameManager;
  private final ListView<String> passageChoices;
  private final BorderPane view;

  /**
   * Constructor for the view. Responsible for building the view.
   *
   * @param controller  the controller for the view.
   * @param gameManager the gameManager for the view.
   */
  public GameView(GameController controller, GameManager gameManager) {

    /*
    Initialize variables
     */
    audioManager = AudioManager.getInstance();
    optionManager = OptionManager.getInstance();
    achievementManager = AchievementManager.getInstance();
    passageChoices = new ListView<>();
    this.gameManager = gameManager;
    this.controller = controller;
    view = new BorderPane();

    /*
     * Play music
     */
    playMusic(gameManager.getCurrentPassage());

    /*
    Styling
     */
    view.getStylesheets().add(optionManager.getCurrentStyleSheet());

    /*
    Build view
     */
    buildView();
  }


  /**
   * This method is responsible for building the bottom menu.
   *
   * @return the bottom menu.
   */
  public ListView<String> buildBottom() {

    passageChoices.getItems().clear();

    passageChoices.getStyleClass().add("file-view");

    passageChoices.getItems().addAll(getPassageChoices(gameManager.getCurrentPassage()));

    try {
      passageChoices.setOnMouseClicked(e -> {

        if (e.getClickCount() == 2) {

          if (passageChoices.getSelectionModel().getSelectedItem() == null) {
            AlertUtility.showErrorAlert("Error", "No passage selected. Please select a passage");
            logger.log(Level.WARNING, "No passage selected. Please select a passage");
            return;
          }

          if (gameManager.getCurrentPassage().hasLinks()) {
            controller.onActionNextPassage(passageChoices.getSelectionModel().getSelectedIndex());
            updateView();
          }
        }
      });

    } catch (Exception e) {
      AlertUtility.showErrorAlert("Error", e.getMessage());
      logger.log(Level.WARNING, e.getMessage());
    }

    return passageChoices;

  }


  /**
   * Builds the popup box for the load view.
   */
  public Parent asParent() {
    return view;
  }


  /**
   * This method is responsible for building the top menu.
   */
  public void updateView() {

    if (!gameManager.getCurrentPassage().getFileName().isEmpty()) {
      playMusic(gameManager.getCurrentPassage());
    }

    buildView();

    updateAchievements();

  }


  /**
   * This method is responsible checking for changes within the achievements.
   */
  public void updateAchievements() {
    achievementManager.getAchievements().stream()
            .filter(achievement -> !achievement.getIsFulfilled()).forEach(achievement -> {
              if (achievement.getGoal().isFulfilled(gameManager.getGame().getPlayer())) {
                achievement.setIsFulfilled(true);
                Notifications.create()
                        .title("Achievement unlocked")
                        .text(achievement.getDescription())
                        .showInformation();
              }
            });
  }

  /**
   * Method for building the top menu, center and bottom menu.
   */
  public void buildView() {

    //Choices placement
    view.setTop(buildTop());
    view.setCenter(buildCenter());
    view.setBottom(buildBottom());

  }


  private VBox buildCenter() {

    final VBox centerMenu = new VBox(10);

    final ImageView imageView;

    final TextFlow passageContent = buildPassageContent(gameManager.getCurrentPassage());

    //Nodes
    if (gameManager.getCurrentPassage().getUrl().isEmpty()) {
      imageView = buildImage("/images/background/ImageNotFound.png");
    } else {
      imageView = buildImage(gameManager.getCurrentPassage().getUrl());
    }


    //Alignment
    centerMenu.setAlignment(Pos.CENTER);
    passageContent.setTextAlignment(TextAlignment.CENTER);


    centerMenu.getChildren().addAll(imageView, passageContent);

    return centerMenu;

  }


  private VBox buildPlayerOverview() {

    //Outer overviewIcons box
    final VBox outerPlayerOverview = new VBox(20);

    //Inner overviewIcons
    final HBox innerPlayerOverview = new HBox(20);

    //Player stats
    final Text playerGold = new Text(gameManager.getGame().getPlayer().getGold() + " G");
    final Text playerHealth = new Text(gameManager.getGame().getPlayer().getHealth() + " HP");
    final Text playerScore = new Text(gameManager.getGame().getPlayer().getScore() + " PTS");
    final Text playerName = new Text(gameManager.getGame().getPlayer().getName());

    //HBox for player stats
    final HBox playerGoldBox = new HBox(5);
    final HBox playerHealthBox = new HBox(5);
    final HBox playerScoreBox = new HBox(5);
    final HBox playerNameBox = new HBox();

    //Images
    final Image healthImage = new Image(
            "images/icons/overviewIcons/Heart.png", 32, 32, false, false);

    final Image goldImage = new Image(
            "images/icons/overviewIcons/Coin.png", 32, 32, false, false);

    final Image scoreImage = new Image(
            "images/icons/overviewIcons/Score.png", 32, 32, false, false);

    final ImageView scoreImageView = new ImageView(scoreImage);
    final ImageView healthImageView = new ImageView(healthImage);
    final ImageView goldImageView = new ImageView(goldImage);


    /*
    Configure images
     */

    healthImageView.setFitWidth(32);
    healthImageView.setFitHeight(32);

    goldImageView.setFitWidth(32);
    goldImageView.setFitHeight(32);

    scoreImageView.setFitWidth(32);
    scoreImageView.setFitHeight(32);

    /*
    Add nodes to HBoxes
     */
    playerHealthBox.getChildren().addAll(healthImageView, playerHealth);
    playerGoldBox.getChildren().addAll(goldImageView, playerGold);
    playerScoreBox.getChildren().addAll(scoreImageView, playerScore);
    playerNameBox.getChildren().add(playerName);
    innerPlayerOverview.getChildren().addAll(playerHealthBox, playerGoldBox, playerScoreBox);
    outerPlayerOverview.getChildren().addAll(playerNameBox, innerPlayerOverview);


    /*
    Alignment
     */
    playerScoreBox.setAlignment(Pos.CENTER);
    playerGoldBox.setAlignment(Pos.CENTER);
    playerHealthBox.setAlignment(Pos.CENTER);
    innerPlayerOverview.setAlignment(Pos.CENTER);
    outerPlayerOverview.setAlignment(Pos.CENTER);
    playerNameBox.setAlignment(Pos.CENTER);
    BorderPane.setAlignment(outerPlayerOverview, Pos.CENTER_RIGHT);

    outerPlayerOverview.getStyleClass().add(StyleClass.CONTENT.getValue());

    /*
    Styling
     */
    playerGold.getStyleClass().add("overviewText");

    return outerPlayerOverview;
  }

  /**
   * Creates a container for the title of the story.
   *
   * @param passage the passage to get the title from.
   */
  public void playMusic(Passage passage) {
    if (passage.getGameState() != null) {
      audioManager.playMusic(passage.getGameState());
    }
  }

  private ImageView buildImage(String path) {
    Image image = new Image(path);
    ImageView imageView;
    imageView = new ImageView(image);
    imageView.setFitHeight(400);
    imageView.setPreserveRatio(true);


    return imageView;
  }

  private TextFlow buildPassageContent(Passage passage) {
    TextFlow passageContent = new TextFlow();
    Text passageText = new Text(passage.getContent());
    passageContent.getChildren().add(passageText);
    passageContent.setPadding(new Insets(5, 5, 5, 5));
    passageContent.setLineSpacing(5);
    passageText.getStyleClass().add(StyleClass.SECONDARY_CONTENT.getValue());
    return passageContent;
  }


  /**
   * Creates a container for the title of the story.
   *
   * @return a container for the title of the story as a HBox
   */
  private HBox buildTitle() {

    //Title text
    final Text titleText = new Text(gameManager.getGame().getStory().getTitle());

    //Title container
    final HBox titleBox = new HBox();


    //Add elements to titleBox
    titleBox.getChildren().add(new TextFlow(titleText));

    //Set title box alignment
    titleBox.setAlignment(javafx.geometry.Pos.CENTER);


    //Set title box alignment
    titleBox.setAlignment(javafx.geometry.Pos.CENTER);

    //Styling
    titleText.getStyleClass().add(StyleClass.TITLE.getValue());

    return titleBox;
  }


  /**
   * Returns a list of choices for the player to choose from.
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
      itemText.getStyleClass().add(StyleClass.CONTENT.getValue());
      items.add(itemFlow);
    });


    return items;
  }


  private HBox createOptionsTab() {

    //Constants
    int buttonSize = 32;

    //Options container
    final HBox optionsBox = new HBox(5);

    //Styling
    final String optionBoxStyle = StyleClass.OPTION_BUTTON.getValue();

    /*
    Initialize nodes
     */
    //Buttons
    final Button backPackButton = new Button();
    final Button saveButton = new Button();
    final Button optionsButton = new Button();
    final Button helpButton = new Button();
    final Button exitButton = new Button();

    final ImageView backPackImage = new ImageView("/images/icons/optionIcon/BackPack.png");
    final ImageView saveImage = new ImageView("/images/icons/optionIcon/Save.png");
    final ImageView optionsImage = new ImageView("/images/icons/optionIcon/Gear.png");
    final ImageView helpImage = new ImageView("/images/icons/optionIcon/Info.png");
    final ImageView exitImage = new ImageView("/images/icons/optionIcon/Exit.png");

    /*
    Configure nodes
     */

    //Backpack button
    backPackImage.setPreserveRatio(true);
    backPackImage.setFitHeight(buttonSize);
    backPackImage.setFitWidth(buttonSize);
    backPackButton.setGraphic(backPackImage);
    backPackButton.backgroundProperty().set(Background.EMPTY);


    //Save button
    saveImage.setPreserveRatio(true);
    saveImage.setFitHeight(buttonSize);
    saveImage.setFitWidth(buttonSize);
    saveButton.setGraphic(saveImage);
    saveButton.backgroundProperty().set(Background.EMPTY);


    //Options button
    optionsImage.setPreserveRatio(true);
    optionsImage.setFitHeight(buttonSize);
    optionsImage.setFitWidth(buttonSize);
    optionsButton.setGraphic(optionsImage);
    optionsButton.backgroundProperty().set(Background.EMPTY);


    //Help button
    helpImage.setPreserveRatio(true);
    helpImage.setFitHeight(buttonSize);
    helpImage.setFitWidth(buttonSize);
    helpButton.setGraphic(helpImage);
    helpButton.backgroundProperty().set(Background.EMPTY);


    //Exit button
    exitImage.setPreserveRatio(true);
    exitImage.setFitHeight(buttonSize);
    exitImage.setFitWidth(buttonSize);
    exitButton.setGraphic(exitImage);
    exitButton.backgroundProperty().set(Background.EMPTY);

    //Options box
    optionsBox.setSpacing(10);
    optionsBox.setPadding(new javafx.geometry.Insets(2, 2, 2, 2));


    /*
    Add nodes to optionsBox
     */
    optionsBox.getChildren().addAll(
            backPackButton, saveButton, optionsButton, helpButton, exitButton);


    /*
    Button actions
     */
    saveButton.setOnMouseEntered(event -> ButtonUtility.buttonHover(saveButton));
    helpButton.setOnMouseEntered(event -> ButtonUtility.buttonHover(helpButton));
    optionsButton.setOnMouseEntered(event -> ButtonUtility.buttonHover(optionsButton));
    backPackButton.setOnMouseEntered(event -> ButtonUtility.buttonHover(backPackButton));
    exitButton.setOnMouseEntered(event -> ButtonUtility.buttonHover(exitButton));

    saveButton.setOnMouseExited(event -> ButtonUtility.buttonExit(saveButton));
    helpButton.setOnMouseExited(event -> ButtonUtility.buttonExit(helpButton));
    optionsButton.setOnMouseExited(event -> ButtonUtility.buttonExit(optionsButton));
    exitButton.setOnMouseExited(event -> ButtonUtility.buttonExit(exitButton));
    backPackButton.setOnMouseExited(event -> ButtonUtility.buttonExit(backPackButton));

    exitButton.setOnAction(event -> controller.onActionExitGame());
    helpButton.setOnAction(event -> controller.onActionHelpGame());
    optionsButton.setOnAction(event -> controller.onActionOptionGame());
    backPackButton.setOnAction(event -> checkBackpack());

    saveButton.setOnAction(event -> {

      try {

        controller.onActionSaveGame();

      } catch (IllegalArgumentException e) {
        AlertUtility.showErrorAlert("Error!", e.getMessage());
        logger.log(Level.SEVERE, e.getMessage(), e);

      } catch (Exception e) {
        AlertUtility.showErrorAlert("Error!", "An error occurred while saving the game.");
        logger.log(Level.SEVERE, e.getMessage(), e);
      }

    });

    //Styling
    optionsBox.getStyleClass().add(optionBoxStyle);

    return optionsBox;

  }


  /**
   * Creates the backpack window for player to view their inventory.
   */
  private void checkBackpack() {

    /*
    Initialize nodes
     */
    final Stage dialog = new Stage();
    final TextFlow topTitle = new TextFlow(new Text("Inventory"));
    final BorderPane root = new BorderPane();
    final Scene dialogScene = new Scene(root, 200, 200);
    final ListView<TextFlow> listView = new ListView<>();

    /*
    Configure nodes
     */
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.initOwner(controller.getStage());
    dialog.setWidth(300);

    if (getItems().isEmpty()) {
      listView.getItems().add(new TextFlow(new Text("No items in inventory")));
    } else {
      listView.getItems().addAll(getItems());
    }

    /*
    Add nodes to containers
     */
    root.setTop(topTitle);
    root.setCenter(listView);

    /*
    Align nodes
     */
    BorderPane.setAlignment(topTitle, Pos.CENTER);
    topTitle.setTextAlignment(TextAlignment.CENTER);


    /*
    Styling
     */
    dialogScene.getStylesheets().add(optionManager.getCurrentStyleSheet());
    topTitle.getStyleClass().add(StyleClass.SECONDARY_TITLE.getValue());
    listView.getStyleClass().add(StyleClass.SECONDARY_TITLE.getValue());
    topTitle.setPadding(new Insets(10, 10, 10, 10));

    dialog.setScene(dialogScene);
    dialog.show();
  }


  /**
   * Builds the top of the game screen.
   *
   * @return VBox containing the top of the game screen.
   */
  private VBox buildTop() {

    //Bottom menubar container
    final HBox leftBox = new HBox();
    final HBox rightBox = new HBox();
    final HBox topBox = new HBox();
    final VBox topContainer = new VBox();

    //Padding
    leftBox.setPadding(new Insets(10, 0, 10, 10));
    rightBox.setPadding(new Insets(10, 10, 10, 10));

    //Button placement
    leftBox.getChildren().add(createOptionsTab());
    rightBox.getChildren().add(buildPlayerOverview());
    topBox.getChildren().addAll(leftBox, rightBox);
    topContainer.getChildren().addAll(topBox, buildTitle());

    //Node alignment
    leftBox.setAlignment(Pos.CENTER_LEFT);
    rightBox.setAlignment(Pos.CENTER_RIGHT);

    //Node padding
    HBox.setHgrow(leftBox, Priority.ALWAYS);
    HBox.setHgrow(rightBox, Priority.ALWAYS);

    return topContainer;
  }

}
