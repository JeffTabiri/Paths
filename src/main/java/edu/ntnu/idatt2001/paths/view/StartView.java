package edu.ntnu.idatt2001.paths.view;

import edu.ntnu.idatt2001.paths.controller.StartController;
import edu.ntnu.idatt2001.paths.model.enums.GameStates;
import edu.ntnu.idatt2001.paths.model.enums.StyleClass;
import edu.ntnu.idatt2001.paths.model.manager.AudioManager;
import edu.ntnu.idatt2001.paths.model.manager.OptionManager;
import edu.ntnu.idatt2001.paths.model.utility.ButtonUtility;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Duration;



/**
 * <h1>StartView</h1>
 * {@code StartView} is the view class for the start view.
 * This class is responsible for building the start view.
 *
 * @author Elementum
 * @version 1.0
 * @see StartController
 * @since 06/04/2023
 * @hidden
 */
public class StartView {
  AudioManager audioManager;
  private final OptionManager optionManager;
  private final StackPane view;
  private final StartController controller;



  /**
   * Constructor for the view.
   * Responsible for building the view.
   *
   * @param controller the controller for the view.
   */
  public StartView(StartController controller) {

    //Initializing variables
    audioManager = AudioManager.getInstance();
    optionManager = OptionManager.getInstance();
    this.controller = controller;
    view = new StackPane();

    //Building view
    buildView();

    //Play music
    audioManager.playMusic(GameStates.MAIN_MENU);
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
   * Builds the view by calling the methods that builds the different parts of the view.
   */
  private void buildView() {

    //Root container
    BorderPane root = new BorderPane();

    //Building root container
    root.setTop(buildTitle());
    root.setCenter(buildMenu());
    root.setBottom(buildCredits());

    //CSS styling
    root.getStylesheets().add(optionManager.getCurrentStyleSheet());

    //Node placement
    view.getChildren().addAll(buildPane(), root);
  }


  private Pane buildPane() {

    /*
    Node initialization
     */
    Pane pane = new Pane();
    ImageView imageView = new ImageView("/images/background/MainMenuBackground.png");


    /*
    Node configuration
    */
    imageView.fitWidthProperty().bind(pane.widthProperty());
    imageView.setPreserveRatio(true);

    /*
    Node placement
    */
    pane.getChildren().add(imageView);

    /*
    Animation
     */
    TranslateTransition translateTransition =
            new TranslateTransition(Duration.seconds(3), imageView);

    translateTransition.setFromY(-imageView.getImage().getHeight());
    translateTransition.setToY(0);
    translateTransition.play();

    translateTransition.setOnFinished(event -> {
      TranslateTransition fadeTransition = new TranslateTransition(Duration.seconds(4), imageView);
      fadeTransition.setFromY(0);
      fadeTransition.setToY(-40);
      fadeTransition.setCycleCount(Animation.INDEFINITE);
      fadeTransition.setAutoReverse(true);
      fadeTransition.play();
    });

    return pane;
  }


  /**
   * Responsible for building the title box.
   *
   * @return the title box in a HBox container.
   */
  private HBox buildTitle() {

    /*
    Node initialization
    */

    //Title text
    final Text gameTitle = new Text("Paths");

    //Title container
    final HBox title = new HBox();

    //StackPane
    final StackPane topBorderPane = new StackPane();

    //Create image
    final ImageView image = new ImageView("/images/UI/title/UI_Flat_Banner_01_Upward.png");

    /*
    Node configuration
     */

    //Set image size
    image.setFitWidth(400);
    image.setPreserveRatio(true);

    /*
    Node placement
     */

    //Add elements to title box
    title.getChildren().add(topBorderPane);

    //Add elements to StackPane
    topBorderPane.getChildren().addAll(image, gameTitle);

    /*
    Node alignment
     */

    //Set StackPane alignment
    StackPane.setAlignment(gameTitle, javafx.geometry.Pos.CENTER);
    StackPane.setAlignment(image, javafx.geometry.Pos.CENTER);

    //Set title box alignment
    title.setAlignment(javafx.geometry.Pos.CENTER);


    /*
    Node styling
    */

    title.getStyleClass().add("title");

    /*
    Animation
     */
    TranslateTransition translateTransition = new TranslateTransition();
    translateTransition.setDuration(javafx.util.Duration.seconds(1.5));
    translateTransition.setNode(title);
    translateTransition.setFromY(-200);
    translateTransition.setToY(0);
    translateTransition.play();

    translateTransition.setOnFinished(event -> {
      TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(1.5));
      translateTransition2.setNode(title);
      translateTransition2.setFromY(0);
      translateTransition2.setToY(-20);
      translateTransition2.autoReverseProperty().setValue(true);
      translateTransition2.setCycleCount(Animation.INDEFINITE);
      translateTransition2.play();
    });

    return title;
  }


  /**
   * Builds the credits box for the start scene.
   *
   * @return the credits box in a HBox container.
   */
  private HBox buildCredits() {

    //Credits container
    final HBox credits = new HBox();

    //Credits text
    final Text creditsText = new Text("Created by the students of NTNU.");

    /*
    Node placement
     */
    credits.getChildren().add(creditsText);

    /*
    Node alignment
     */
    credits.setAlignment(Pos.BOTTOM_RIGHT);

    /*
    Node styling
     */
    creditsText.getStyleClass().add("title");

    /*
    Animation
     */
    TranslateTransition translateTransition = new TranslateTransition();
    translateTransition.setDuration(javafx.util.Duration.seconds(1.5));
    translateTransition.setNode(credits);
    translateTransition.setFromY(40);
    translateTransition.setToY(0);
    translateTransition.play();

    return credits;
  }

  /**
   * Builds the menu for the start scene.
   *
   * @return a VBox containing the menu with buttons.
   */
  private VBox buildMenu() {

    /*
    Node initialization
    */

    //Menu buttons
    final Button newGameButton = new Button("New Game");
    final Button loadGameButton = new Button("Load");
    final Button optionButton = new Button("Help");
    final Button achievementButton = new Button("Achievements");
    final Button optionsButton = new Button("Options");
    final Button exitButton = new Button("Exit");

    //Menu container
    final VBox menu = new VBox();

    /*
    Button events
    */

    //Button on hover
    newGameButton.setOnMouseExited(event -> ButtonUtility.buttonExit(newGameButton));
    loadGameButton.setOnMouseExited(event -> ButtonUtility.buttonExit(loadGameButton));
    optionButton.setOnMouseExited(event -> ButtonUtility.buttonExit(optionButton));
    achievementButton.setOnMouseExited(event -> ButtonUtility.buttonExit(achievementButton));
    exitButton.setOnMouseExited(event -> ButtonUtility.buttonExit(exitButton));
    optionsButton.setOnMouseExited(event -> ButtonUtility.buttonExit(optionsButton));

    //Button on hover exit
    newGameButton.setOnMouseEntered(event -> ButtonUtility.buttonHover(newGameButton));
    loadGameButton.setOnMouseEntered(event -> ButtonUtility.buttonHover(loadGameButton));
    optionButton.setOnMouseEntered(event -> ButtonUtility.buttonHover(optionButton));
    achievementButton.setOnMouseEntered(event -> ButtonUtility.buttonHover(achievementButton));
    exitButton.setOnMouseEntered(event -> ButtonUtility.buttonHover(exitButton));
    optionsButton.setOnMouseEntered(event -> ButtonUtility.buttonHover(optionsButton));


    //Button on pressed
    newGameButton.setOnAction(event -> {
      ButtonUtility.buttonPressed();
      controller.onActionNewGame();
    });

    loadGameButton.setOnAction(event -> {
      ButtonUtility.buttonPressed();
      controller.onActionLoadGame();
    });

    optionButton.setOnAction(event -> {
      ButtonUtility.buttonPressed();
      controller.onActionHelp();
    });

    achievementButton.setOnAction(event -> {
      ButtonUtility.buttonPressed();
      controller.onActionAchievement();
    });

    optionsButton.setOnAction(event -> {
      ButtonUtility.buttonPressed();
      controller.onActionOptions();
    });

    exitButton.setOnAction(event -> {
      ButtonUtility.buttonPressed();
      controller.onActionExit();
    });


    /*
    Node placement
     */
    menu.getChildren().addAll(newGameButton,
            loadGameButton,
            optionButton,
            achievementButton,
            optionsButton,
            exitButton);


    /*
    Node configuration
    */
    menu.setSpacing(20);

    /*
    Node alignment
     */
    menu.setAlignment(javafx.geometry.Pos.CENTER);

    /*
    Node styling
     */
    newGameButton.getStyleClass().add(StyleClass.MENU_BUTTON.getValue());
    loadGameButton.getStyleClass().add(StyleClass.MENU_BUTTON.getValue());
    optionButton.getStyleClass().add(StyleClass.MENU_BUTTON.getValue());
    achievementButton.getStyleClass().add(StyleClass.MENU_BUTTON.getValue());
    optionsButton.getStyleClass().add(StyleClass.MENU_BUTTON.getValue());
    exitButton.getStyleClass().add(StyleClass.MENU_BUTTON.getValue());

    /*
    Animation
    */
    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), menu);
    fadeTransition.setFromValue(0);
    fadeTransition.setToValue(1);
    fadeTransition.play();

    return menu;
  }

}
