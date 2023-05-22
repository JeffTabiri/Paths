package edu.ntnu.idatt2001.paths.view;

import edu.ntnu.idatt2001.paths.controller.LoadController;
import edu.ntnu.idatt2001.paths.enums.GameStates;
import edu.ntnu.idatt2001.paths.enums.StyleClass;
import edu.ntnu.idatt2001.paths.model.manager.AudioManager;
import edu.ntnu.idatt2001.paths.model.manager.OptionManager;
import edu.ntnu.idatt2001.paths.utility.AlertUtility;
import edu.ntnu.idatt2001.paths.utility.ButtonUtility;
import java.io.IOException;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.util.Duration;


/**
 * <h1>LoadView</h1>
 * {@code LoadView} is the view class for the load view.
 * This class is responsible for building the load view.
 *
 * @author Elementum
 * @version 1.0
 * @see LoadController
 * @since 06/04/2023
 */
public class LoadView {
  private final Logger logger = Logger.getLogger(getClass().getName());
  private static final String ERROR_MESSAGE = "No saved stories found.";
  private final OptionManager optionManager;
  AudioManager audioManager;
  private final StackPane view;
  private final ListView<String> storyListView;
  private final LoadController controller;

  /**
   * Constructor for the view. Responsible for building the view.
   *
   * @param controller the controller for the view.
   */
  public LoadView(LoadController controller) {

    //Initializing variables
    optionManager = OptionManager.getInstance();
    audioManager = AudioManager.getInstance();
    view = new StackPane();
    storyListView = new ListView<>();
    this.controller = controller;

    //Playing music
    audioManager.playMusic(GameStates.MAIN_MENU);

    //Building view
    buildView();
  }


  /**
   * Builds the popup box for the load view.
   */
  public void buildView() {

    //Initializing root
    BorderPane root = new BorderPane();

    //Building root
    root.setTop(buildTitle());
    root.setCenter(buildCenter());
    root.setBottom(buildBottomMenu());

    //Configurations
    root.setPadding(new Insets(10, 20, 10, 20));

    //CSS styling
    view.getStylesheets().add(optionManager.getCurrentStyleSheet());


    view.getChildren().addAll(buildPane(), root);
  }

  /**
   * Builds the title for the load view.
   *
   * @return the title in a HBox.
   */
  public ListView<String> buildCenter() {

    Text placeholder = new Text("No saved stories found");
    storyListView.setPlaceholder(placeholder);

    try {
      storyListView.setItems(FXCollections.observableArrayList(controller.getSavedStoryList()));
    } catch (IllegalArgumentException e) {
      AlertUtility.showErrorAlert("No loaded files.", "Could not load saved stories");
      logger.warning("Could not load saved stories");
    } catch (Exception e) {
        AlertUtility.showErrorAlert("Error", e.getMessage());
        logger.warning(e.getMessage());
    }

    storyListView.setOnMouseClicked(event -> {
      if (event.getClickCount() == 2
              && (storyListView.getSelectionModel().getSelectedItem() == null)) {

        AlertUtility.showErrorAlert("Error", ERROR_MESSAGE);
        logger.warning(ERROR_MESSAGE);

      }
    });

    placeholder.getStyleClass().add("content");
    storyListView.getStyleClass().add("file-view");


    return storyListView;
  }

  /**
   * Gets the view.
   *
   * @return the view as a Parent.
   */
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
    final HBox leftBox = new HBox();
    final HBox rightBox = new HBox();
    final HBox bottom = new HBox();
    final HBox middleBox = new HBox();

    //Buttons
    Button returnGame = new Button("Return");
    Button loadGame = new Button("Start Game");
    Button deleteGame = new Button("Delete Game");

    //Button actions
    returnGame.setOnAction(e -> controller.onActionReturn());

    loadGame.setOnAction(e -> {

      if (storyListView.getSelectionModel().getSelectedItem() == null) {
        AlertUtility.showErrorAlert("Error:", "No story selected. Please select a story");
        logger.warning("No story selected");
      }

      try {
        controller.onActionLoadGame(storyListView.getSelectionModel().getSelectedItem());
      } catch (IOException ex) {
        AlertUtility.showErrorAlert("Error:", "Could not load game");
        logger.warning("Could not load game");
      }

    });

    deleteGame.setOnAction(e -> {

      if (storyListView.getSelectionModel().getSelectedItem() == null) {
        AlertUtility.showErrorAlert("Error!", "No story selected. Please select a story");
        logger.warning("No story selected");
      } else {

        if (AlertUtility.showConfirmationAlert(
                "Delete game",
                "Are you sure you want to delete this game?",
                "This action cannot be undone")) {

          try {
            controller.onActionDeleteGame(storyListView.getSelectionModel().getSelectedItem());
          } catch (IOException ex) {
            AlertUtility.showErrorAlert("Error!", "Could not delete game");
            logger.warning("Could not delete game");
          }
          buildView();
        }
      }
    });

    //Hover effects
    returnGame.setOnMouseEntered(e -> ButtonUtility.buttonHover(returnGame));
    loadGame.setOnMouseEntered(e -> ButtonUtility.buttonHover(loadGame));
    deleteGame.setOnMouseEntered(e -> ButtonUtility.buttonHover(deleteGame));

    //Exit hover effects
    returnGame.setOnMouseExited(e -> ButtonUtility.buttonExit(returnGame));
    loadGame.setOnMouseExited(e -> ButtonUtility.buttonExit(loadGame));
    deleteGame.setOnMouseExited(e -> ButtonUtility.buttonExit(deleteGame));


    //Padding
    leftBox.setPadding(new Insets(20, 0, 20, 20));
    rightBox.setPadding(new Insets(20, 20, 20, 0));
    middleBox.setPadding(new Insets(20, 0, 20, 0));

    //Button placement
    leftBox.getChildren().add(returnGame);
    rightBox.getChildren().add(loadGame);
    middleBox.getChildren().add(deleteGame);
    bottom.getChildren().addAll(leftBox, middleBox, rightBox);

    //Node alignment
    leftBox.setAlignment(Pos.CENTER_LEFT);
    rightBox.setAlignment(Pos.CENTER_RIGHT);
    middleBox.setAlignment(Pos.CENTER);

    //Node padding
    HBox.setHgrow(leftBox, Priority.ALWAYS);
    HBox.setHgrow(rightBox, Priority.ALWAYS);
    HBox.setHgrow(middleBox, Priority.ALWAYS);

    //Styling
    returnGame.getStyleClass().add(StyleClass.MENU_BUTTON.getValue());
    loadGame.getStyleClass().add(StyleClass.MENU_BUTTON.getValue());
    deleteGame.getStyleClass().add(StyleClass.MENU_BUTTON.getValue());

    return bottom;
  }


  /**
   * Builds the background for the load view.
   *
   * @return the background as a Pane.
   */
  private Pane buildPane() {

    Pane pane = new Pane();

    ImageView imageView = new ImageView("/images/background/MainMenuBackground.png");
    imageView.fitWidthProperty().bind(pane.widthProperty());
    imageView.setPreserveRatio(true);

    pane.getChildren().add(imageView);

    return pane;
  }


  /**
   * Builds the title for the choose story scene.
   *
   * @return the title in a HBox.
   */
  private HBox buildTitle() {

    /*
     * Initialize elements
     */
    final Text gameTitle = new Text("LOAD GAME");
    final HBox titleBox = new HBox();
    final ImageView image = new ImageView("/images/UI/title/UI_Flat_Banner_01_Upward.png");
    final StackPane topBorderPane = new StackPane();

    /*
    Configurations
     */
    image.setFitWidth(400);
    image.setPreserveRatio(true);

    /*
    Placement
     */
    topBorderPane.getChildren().addAll(image, gameTitle);
    titleBox.getChildren().add(topBorderPane);

    /*
    Alignment
     */
    StackPane.setAlignment(gameTitle, javafx.geometry.Pos.CENTER);
    StackPane.setAlignment(image, javafx.geometry.Pos.CENTER);
    titleBox.setAlignment(javafx.geometry.Pos.CENTER);

    /*
    Alignment
     */
    gameTitle.getStyleClass().add("title");

    /*
    Animation
     */
    TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(1.5));
    translateTransition2.setNode(titleBox);
    translateTransition2.setFromY(0);
    translateTransition2.setToY(-20);
    translateTransition2.autoReverseProperty().setValue(true);
    translateTransition2.setCycleCount(Animation.INDEFINITE);
    translateTransition2.play();

    return titleBox;
  }

}
