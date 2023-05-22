package edu.ntnu.idatt2001.paths.view;

import edu.ntnu.idatt2001.paths.controller.ChooseStoryController;
import edu.ntnu.idatt2001.paths.enums.GameStates;
import edu.ntnu.idatt2001.paths.model.manager.AudioManager;
import edu.ntnu.idatt2001.paths.model.manager.OptionManager;
import edu.ntnu.idatt2001.paths.utility.AlertUtility;
import edu.ntnu.idatt2001.paths.utility.ButtonUtility;
import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;
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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;


/**
 * <h1>ChooseStoryView</h1>
 * {@code ChooseStoryView} is the view class for the choose story view.
 * This class is responsible for building the choose story view.
 *
 * @author Elementum
 * @version 1.0
 * @see ChooseStoryController
 * @since 06/04/2023
 */
public class ChooseStoryView {
  Logger logger = Logger.getLogger(getClass().getName());
  OptionManager optionManager;
  AudioManager audioManager;
  StackPane view;
  ListView<String> storyListView;
  ChooseStoryController controller;


  /**
   * Constructor for the view. Responsible for building the view.
   *
   * @param controller the controller for the view.
   */
  public ChooseStoryView(ChooseStoryController controller) {
    optionManager = OptionManager.getInstance();
    audioManager = AudioManager.getInstance();
    view = new StackPane();
    storyListView = new ListView<>();
    this.controller = controller;


    audioManager.playMusic(GameStates.MAIN_MENU);

    buildView();
  }


  /**
   * Builds the title for the choose story scene.
   */
  private void buildView() {

    //Initializing root
    BorderPane root = new BorderPane();

    //Building root
    root.setTop(buildTitle());
    root.setCenter(buildCenter());
    root.setBottom(buildBottomMenu());

    //Configuring root
    root.setPadding(new Insets(10, 20, 10, 20));

    //CSS styling
    root.getStylesheets().add(optionManager.getCurrentStyleSheet());

    //Building root
    view.getChildren().addAll(buildPane(), root);

  }

  /**
   * Builds the title for the choose story scene.
   *
   * @return the title in a HBox.
   */
  public ListView<String> buildCenter() {

    storyListView.setItems(FXCollections.observableArrayList(controller.getStoryList()));

    Text placeholder = new Text("No stories found");

    storyListView.setPlaceholder(placeholder);

    storyListView.setOnMouseClicked(event -> {
      if (event.getClickCount() == 2) {
        if (storyListView.getSelectionModel().getSelectedItem() == null) {
          AlertUtility.showErrorAlert("Error", "No story selected. Please select a story");
          logger.warning("No story selected");
          return;
        }
        popupBox().show();
      }
    });

    placeholder.getStyleClass().add("content");
    storyListView.getStyleClass().add("file-view");



    return storyListView;
  }

  /**
   * Builds the popup box for the load view.
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

    //Buttons
    Button returnButton = new Button("Return");
    Button startGameButton = new Button("Start Game");

    //Button actions
    returnButton.setOnAction(e -> controller.onActionGoBack());

    startGameButton.setOnAction(e -> {

      if (storyListView.getSelectionModel().getSelectedItem() == null) {
        AlertUtility.showErrorAlert("Error!", "No story selected. Please select a story");
        logger.warning("No story selected");
        return;
      }

      popupBox().show();

    });

    //Hover effects
    returnButton.setOnMouseEntered(e -> ButtonUtility.buttonHover(returnButton));
    startGameButton.setOnMouseEntered(e -> ButtonUtility.buttonHover(startGameButton));

    //Exit hover effects
    returnButton.setOnMouseExited(e -> ButtonUtility.buttonExit(returnButton));
    startGameButton.setOnMouseExited(e -> ButtonUtility.buttonExit(startGameButton));

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


  /**
   * Builds the popup box for the choose story scene.
   *
   * @return the popup box in a Stage.
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
   * Builds the popup box for the choose story scene.
   *
   * @return the popup box in a Stage.
   */
  private HBox buildTitle() {

    //Title text
    Text gameTitle = new Text("CHOOSE STORY");

    //Title container
    final HBox titleBox = new HBox();

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


  /**
  * Creates a popup box for the user to enter their name.
  *
  * @return the popup box
  */
  private Stage popupBox() {

    final Stage dialog = new Stage();

    dialog.initModality(Modality.APPLICATION_MODAL);

    dialog.resizableProperty().setValue(false);


    VBox popupBox = new VBox(20);
    HBox inputBox = new HBox(20);

    Text popupTitle = new Text("Choose a name: ");

    TextField nameField = new TextField("");
    nameField.setPromptText("Please enter a name");

    Button confirmButton = new Button("Confirm");

    popupBox.getChildren().addAll(popupTitle);
    inputBox.getChildren().addAll(nameField);

    popupBox.getChildren().addAll(inputBox, confirmButton);

    popupBox.setAlignment(Pos.CENTER);
    inputBox.setAlignment(Pos.CENTER);


    confirmButton.setOnAction(
            event -> {

              ButtonUtility.buttonPressed();

              try {

                String storyAddress = "src/main/resources/story/preloadedStory/";
                String name = nameField.getText();
                String ending = ".paths";

                controller.startGameHandler(new File(
                        storyAddress
                                + storyListView.getSelectionModel().getSelectedItem() + ending),
                        name);

                dialog.close();

              } catch (IOException e) {

                AlertUtility.showErrorAlert("xf:", e.getMessage());
                logger.warning(e.getMessage());

              } catch (IllegalArgumentException e) {

                AlertUtility.showErrorAlert("Error", e.getMessage());
                logger.warning(e.getMessage());

              } catch (Exception e) {
                AlertUtility.showErrorAlert("Unspecified Error", e.getMessage());
                logger.warning(e.getMessage());
              }

            });

    confirmButton.setOnMouseEntered(event -> ButtonUtility.buttonHover(confirmButton));

    confirmButton.setOnMouseExited(event -> ButtonUtility.buttonExit(confirmButton));

    //Styling
    popupBox.getStylesheets().add("css/Light.css");
    confirmButton.getStyleClass().add("secondary-button");
    popupTitle.getStyleClass().add("secondary-text");

    Scene popupScene = new Scene(popupBox, 300, 200);

    dialog.setScene(popupScene);

    return dialog;

  }
}
