package edu.ntnu.idatt2001.paths.view;

import edu.ntnu.idatt2001.paths.controller.HelpController;
import edu.ntnu.idatt2001.paths.model.OptionManager;
import edu.ntnu.idatt2001.paths.utility.AudioEngine;
import edu.ntnu.idatt2001.paths.utility.ButtonEffects;
import edu.ntnu.idatt2001.paths.utility.GameStates;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

public class HelpView {

  AudioEngine audioEngine = AudioEngine.getInstance();



  OptionManager optionManager = OptionManager.getInstance();
  StackPane view = new StackPane();
  HelpController controller;


  public HelpView(HelpController controller) {

    buildView();

    audioEngine.playMusic(GameStates.MAIN_MENU);
    this.controller = controller;
  }

  public StackPane asParent() {
  return view;
  }

  private Pane buildPane() {

    Pane pane = new Pane();

    ImageView imageView = new ImageView("/images/background/MainMenuBackground.png");
    imageView.fitWidthProperty().bind(pane.widthProperty());
    imageView.setPreserveRatio(true);

    pane.getChildren().add(imageView);

    return pane;
  }

  public void buildView() {

    //Root container
    BorderPane root = new BorderPane();

    //Building root container
    root.setTop(buildTitle());
    root.setCenter(buildHelp());
    root.setBottom(buildBottomMenu());

    //Animation
    FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5));
    fadeTransition.setNode(view);
    fadeTransition.setFromValue(0.75);
    fadeTransition.setToValue(1);
    fadeTransition.play();

    //Node placement
    view.getChildren().addAll(buildPane(), root);

    //CSS styling
    root.getStylesheets().add(optionManager.getCurrentStyle());

  }

  private VBox buildHelp() {

    //Options container
    VBox helpBox = new VBox();

    TextFlow textBox = new TextFlow();

    Text helpText;
    helpText = new Text(
            """
                    Embark on an extraordinary journey, filled with twists and turns.
                    Your fate lies in your hands as you make crucial decisions,
                    shaping the outcome of your adventure.\s
                    Earn coveted gold coins, uncover hidden secrets, and unlock your true potential.
                    But beware, danger lurks and your choices carry the weight of life and death.
                    Are you ready to leave your mark on this extraordinary adventure? Welcome, brave adventurer!
              """);

    helpText.getStyleClass().add("title");

    textBox.getChildren().add(helpText);

    helpBox.getChildren().add(textBox);

    helpBox.setAlignment(Pos.CENTER);

    textBox.setTextAlignment(TextAlignment.CENTER);


    return helpBox;
  }

  private HBox buildTitle() {
    //Title text
    Text gameTitle = new Text("HELP");

    //Title container
    HBox title = new HBox();

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
    title.getChildren().add(topBorderPane);

    //Set StackPane alignment
    StackPane.setAlignment(gameTitle, javafx.geometry.Pos.CENTER);
    StackPane.setAlignment(image, javafx.geometry.Pos.CENTER);

    //Set title box alignment
    title.setAlignment(javafx.geometry.Pos.CENTER);

    //Styling
    gameTitle.getStyleClass().add("title");

    //Animation
    TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(1.5));
    translateTransition2.setNode(title);
    translateTransition2.setFromY(0);
    translateTransition2.setToY(-20);
    translateTransition2.autoReverseProperty().setValue(true);
    translateTransition2.setCycleCount(Animation.INDEFINITE);
    translateTransition2.play();

    return title;

  }

  private HBox buildBottomMenu( ) {

    //Bottom menu container
    HBox bottomMenu = new HBox();

    //Buttons
    Button back = new Button("Back");

    back.setPadding(new javafx.geometry.Insets(10, 20, 10, 20));

    bottomMenu.setPadding(new javafx.geometry.Insets(10, 20, 10, 20));

    //Add buttons to container
    bottomMenu.getChildren().addAll(back);

    //Set button alignment
    bottomMenu.setAlignment(Pos.CENTER_LEFT);

    //Styling
    back.getStyleClass().add("menu-button");

    //Button actions
    back.setOnAction(event -> controller.goBackHandler());

    back.setOnMouseEntered(e -> ButtonEffects.buttonHover(back));

    back.setOnMouseExited(e -> ButtonEffects.buttonExit(back));

    return bottomMenu;
  }

}
