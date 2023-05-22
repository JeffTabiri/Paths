package edu.ntnu.idatt2001.paths.view;

import edu.ntnu.idatt2001.paths.controller.AchievementController;
import edu.ntnu.idatt2001.paths.model.Achievement;
import edu.ntnu.idatt2001.paths.model.enums.GameStates;
import edu.ntnu.idatt2001.paths.model.enums.StyleClass;
import edu.ntnu.idatt2001.paths.model.enums.StyleSheets;
import edu.ntnu.idatt2001.paths.model.manager.AchievementManager;
import edu.ntnu.idatt2001.paths.model.manager.AudioManager;
import edu.ntnu.idatt2001.paths.model.manager.OptionManager;
import edu.ntnu.idatt2001.paths.model.utility.AlertUtility;
import edu.ntnu.idatt2001.paths.model.utility.ButtonUtility;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
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
 * <h1>AchievementView</h1>
 * {@code AchievementView} is the view class for the achievement view.
 * This class is responsible for rendering the achievement view.
 *
 * @author Elementum
 * @version 1.0
 * @see AchievementController
 * @since 06/04/2023
 */
public class AchievementView {

  Logger logger = Logger.getLogger(getClass().getName());
  AudioManager audioManager;
  OptionManager optionManager;
  AchievementManager achievementManager;
  StackPane view;
  ListView<HBox> achievementListView;
  AchievementController controller;


  /**
   * Constructor for the view. Responsible for initializing variables and building the view.
   *
   * @param controller the controller for the view.
   */
  public AchievementView(AchievementController controller) {

    /*
    Initializing variables
     */
    audioManager = AudioManager.getInstance();
    optionManager = OptionManager.getInstance();
    achievementManager = AchievementManager.getInstance();
    view = new StackPane();
    achievementListView = new ListView<>();
    this.controller = controller;

    /*
    Playing music
     */
    audioManager.playMusic(GameStates.MAIN_MENU);

    /*
    Building view
     */
    buildView();
  }


  /**
   * Builds the view by calling the methods that builds the different parts of the view.
   */
  public void buildView() {

    //Building root
    BorderPane root = new BorderPane();

    //Building root
    root.setTop(buildTitle());
    root.setCenter(buildAchievementList());
    root.setBottom(buildBottomMenu());

    //Adding root to view
    view.getChildren().addAll(buildPane(), root);

    //Adding stylesheet
    view.getStylesheets().add(optionManager.getCurrentStyleSheet());

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
   * Builds the node that is used for the view.
   *
   * @return the pane as a HBox.
   */
  private HBox buildBottomMenu() {


    /*
    Initializing variables
     */
    //Buttons
    Button goBack = new Button("Return");
    Button addAchievement = new Button("Add");
    Button removeAchievement = new Button("Remove");

    //Bottom menubar container
    HBox leftBox = new HBox();
    HBox rightBox = new HBox();
    HBox middleBox = new HBox();
    HBox bottom = new HBox();


    /*
    Node placement
     */
    leftBox.getChildren().add(goBack);
    middleBox.getChildren().add(removeAchievement);
    rightBox.getChildren().add(addAchievement);
    bottom.getChildren().addAll(leftBox, middleBox, rightBox);


    /*
    Event handlers
     */

    //On action
    goBack.setOnAction(e -> controller.onActionGoBack());
    addAchievement.setOnAction(e -> addAchievement());
    removeAchievement.setOnAction(e -> {
      if (achievementListView.getSelectionModel().getSelectedIndex() == -1) {

        AlertUtility.showErrorAlert(
                "No achievement selected",
                "Please select an achievement to remove");

        Logger.getLogger(getClass().getName()).log(Level.WARNING, "No achievement selected");

      } else {
        if (AlertUtility.showConfirmationAlert("Remove achievement",
                "Are you sure you want to remove this achievement?",
                "This action cannot be undone")) {

          controller.onActionRemoveAchievement(achievementManager.getAchievements().get(
                  achievementListView.getSelectionModel().getSelectedIndex()));

          updateAchievementList();

        }
      }
    });


    //On hover
    goBack.setOnMouseEntered(e -> ButtonUtility.buttonHover(goBack));
    addAchievement.setOnMouseEntered(e -> ButtonUtility.buttonHover(addAchievement));
    removeAchievement.setOnMouseEntered(e -> ButtonUtility.buttonHover(removeAchievement));

    //On exit
    goBack.setOnMouseExited(e -> ButtonUtility.buttonExit(goBack));
    addAchievement.setOnMouseExited(e -> ButtonUtility.buttonExit(addAchievement));
    removeAchievement.setOnMouseExited(e -> ButtonUtility.buttonExit(removeAchievement));

    /*
    Node alignments
     */
    leftBox.setAlignment(Pos.CENTER_LEFT);
    rightBox.setAlignment(Pos.CENTER_RIGHT);
    middleBox.setAlignment(Pos.CENTER);


    /*
    Node configurations
    */
    HBox.setHgrow(leftBox, Priority.ALWAYS);
    HBox.setHgrow(rightBox, Priority.ALWAYS);
    HBox.setHgrow(middleBox, Priority.ALWAYS);
    bottom.setPadding(new Insets(10));

    //Styling
    goBack.getStyleClass().add(StyleClass.MENU_BUTTON.getValue());
    addAchievement.getStyleClass().add(StyleClass.MENU_BUTTON.getValue());
    removeAchievement.getStyleClass().add(StyleClass.MENU_BUTTON.getValue());

    return bottom;
  }


  /**
   * Responsible for building the title box.
   *
   * @return the title box in a HBox container.
   */
  private HBox buildTitle() {

    /*
    Initializing variables
    */
    //Title text
    final Text gameTitle = new Text("Achievements");

    //Title container
    final HBox titleBox = new HBox();

    //Image
    final ImageView image = new ImageView("/images/UI/title/UI_Flat_Banner_01_Upward.png");

    //StackPane
    StackPane topBorderPane = new StackPane();

    /*
    Node configurations
     */
    image.setFitWidth(400);
    image.setPreserveRatio(true);


    /*
    Node placement
     */
    topBorderPane.getChildren().addAll(image, gameTitle);
    titleBox.getChildren().add(topBorderPane);

    /*
    Node alignments
     */
    StackPane.setAlignment(gameTitle, javafx.geometry.Pos.CENTER);
    StackPane.setAlignment(image, javafx.geometry.Pos.CENTER);
    titleBox.setAlignment(javafx.geometry.Pos.CENTER);

    /*
    Styling
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

  /**
   * Builds the ListView of achievements.
   *
   * @return the ListView of HBoxes.
   */
  private ListView<HBox> buildAchievementList() {

    /*
    Building ListView
     */
    achievementManager.getAchievements().forEach(achievement -> {

      if ((achievement != null)) {
        achievementListView.getItems().add(buildAchievementBox(achievement));
      }

    });

    /*
    Node configurations
     */
    achievementListView.paddingProperty().setValue(new Insets(10, 10, 10, 10));
    BorderPane.setMargin(achievementListView, new Insets(20, 20, 20, 20));

    /*
    Styling
     */
    achievementListView.getStyleClass().add("file-view");

    return achievementListView;
  }


  /**
   * Gives additional information about the achievement.
   *
   * @param isFulfilled boolean that checks if the achievement is fulfilled.
   * @return a string that says if the achievement is fulfilled or not.
   */
  private String completionCheck(boolean isFulfilled) {
    if (isFulfilled) {
      return "Completed";
    } else {
      return "Not completed";
    }
  }


  /**
   * Responsible for building the achievement box.
   *
   * @param achievement the achievement to be built.
   * @return the achievement box in a HBox container.
   */
  private HBox buildAchievementBox(Achievement achievement) {

    /*
     * Initializing variables
     */

    Text achievementTitle = new Text(achievement.getTitle());

    Text achievementDescription = new Text(achievement.getDescription());

    Text achievementProgress = new Text(completionCheck(achievement.getIsFulfilled()));

    HBox achievementBox = new HBox();

    VBox achievementTextContainer = new VBox();

    ImageView achievementImageView = new ImageView(new Image(
            achievementImageChoose(controller.achievementGoalCheck(achievement)),
            64, 64, true, true));


    /*
     * Node placements
     */
    achievementTextContainer.getChildren().addAll(
            achievementTitle, achievementDescription, achievementProgress);

    achievementBox.getChildren().addAll(achievementImageView, achievementTextContainer);

    /*
     * Node configurations
     */

    achievementTextContainer.setSpacing(10);
    achievementBox.setSpacing(20);
    achievementBox.setPadding(new Insets(10, 10, 10, 10));

    return achievementBox;
  }


  /**
   * Responsible fo assigning an appropriate image to the achievement.
   *
   * @param goalType the type of goal.
   * @return the image path.
   */
  private String achievementImageChoose(String goalType) {
    return switch (goalType) {
      case "HEALTH" -> "images/icons/achievementIcons/RedPotion.png";
      case "INVENTORY" -> "images/icons/achievementIcons/Sword.png";
      case "GOLD" -> "images/icons/achievementIcons/Coin.png";
      case "SCORE" -> "images/icons/achievementIcons/Point.png";
      default -> null;
    };

  }

  /**
   * Responsible for adding an achievement.
   */
  private void addAchievement() {
    Stage popupBox = popupBox();
    popupBox.show();

    popupBox.setOnCloseRequest(e ->
            updateAchievementList());

  }

  /**
   * Pop up box for adding an achievement.
   */
  private Stage popupBox() {

    final Stage dialog = new Stage();

    final ComboBox<String> goalType = new ComboBox<>();

    final VBox popupBox = new VBox(20);

    final HBox inputBox = new HBox(20);

    final TextField sumField = new TextField();

    final Button confirmButton = new Button("Confirm");

    /*
    Node configurations
     */
    dialog.setTitle("Add achievement");
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.resizableProperty().setValue(false);

    goalType.getItems().addAll("Health", "Gold", "Score", "Inventory");
    sumField.setPromptText("Enter a goal");


    /*
    Node placements
     */
    popupBox.getChildren().addAll(goalType);
    inputBox.getChildren().addAll(sumField);
    popupBox.getChildren().addAll(inputBox, confirmButton);

    /*
    Node alignments
     */
    popupBox.setAlignment(Pos.CENTER);
    inputBox.setAlignment(Pos.CENTER);


    /*
    Button actions
     */

    //
    confirmButton.setOnMouseEntered(event -> ButtonUtility.buttonHover(confirmButton));

    confirmButton.setOnMouseExited(event -> ButtonUtility.buttonExit(confirmButton));


    confirmButton.setOnAction(event -> {

      try {

        controller.onActionAddAchievement(goalType.getValue(), sumField.getText());
        updateAchievementList();
        dialog.close();

      } catch (IllegalArgumentException e) {

        AlertUtility.showErrorAlert("Invalid input", e.getMessage());
        logger.log(Level.WARNING, e.getMessage());

      } catch (NullPointerException e) {

        AlertUtility.showErrorAlert("Invalid input", "Please fill in the fields");
        logger.log(Level.WARNING, e.getMessage());

      } catch (Exception e) {

        AlertUtility.showErrorAlert("Error", e.getMessage());
        logger.log(Level.WARNING, e.getMessage());
      }

    });


    /*
    Styling
     */
    popupBox.getStylesheets().add(StyleSheets.LIGHT_THEME.getValue());
    confirmButton.getStyleClass().add(StyleClass.SECONDARY_MENU_BUTTON.getValue());


    dialog.setScene(new Scene(popupBox, 300, 200));

    return dialog;
  }


  /**
   * Responsible for updating the achievement listview.
   * Called when an achievement is added.
   */
  private void updateAchievementList() {
    achievementListView.getItems().clear();
    achievementManager.getAchievements().forEach(achievement ->
            achievementListView.getItems().add(buildAchievementBox(achievement)));
  }


  /**
   * Builds the pane that contains the background image.
   *
   * @return the pane.
   */
  private Pane buildPane() {

    Pane pane = new Pane();

    ImageView imageView = new ImageView("/images/background/passages/MainMenuBackground.png");
    imageView.fitWidthProperty().bind(pane.widthProperty());
    imageView.setPreserveRatio(true);

    pane.getChildren().add(imageView);

    return pane;

  }

}




