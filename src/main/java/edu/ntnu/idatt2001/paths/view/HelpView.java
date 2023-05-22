package edu.ntnu.idatt2001.paths.view;

import edu.ntnu.idatt2001.paths.controller.HelpController;
import edu.ntnu.idatt2001.paths.model.enums.GameStates;
import edu.ntnu.idatt2001.paths.model.enums.StyleClass;
import edu.ntnu.idatt2001.paths.model.manager.AudioManager;
import edu.ntnu.idatt2001.paths.model.manager.OptionManager;
import edu.ntnu.idatt2001.paths.model.utility.ButtonUtility;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;

/**
 * <h1>HelpView</h1>
 * {@code HelpView} is the view class for the help view.
 * This class is responsible for building the help view.
 *
 * @author Elementum
 * @version 1.0
 * @see HelpController
 * @since 06/04/2023
 */
public class HelpView {
  AudioManager audioManager;
  OptionManager optionManager;
  StackPane view;
  HelpController controller;


  /**
   * Constructor for the view. Responsible for building the view.
   *
   * @param controller the controller for the view.
   */
  public HelpView(HelpController controller) {


    //Initializing variables
    audioManager = AudioManager.getInstance();
    optionManager = OptionManager.getInstance();
    view = new StackPane();
    this.controller = controller;

    //Playing music
    audioManager.playMusic(GameStates.MAIN_MENU);

    //Building view
    buildView();

  }

  /**
   * Gets the view.
   *
   * @return the view as a Parent.
   */
  public StackPane asParent() {
    return view;
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


  /**
   * Builds the nodes for the view.
   */
  public void buildView() {

    /*
     * Initializing variables
     */

    //Root container
    BorderPane root = new BorderPane();

    //Building root container
    root.setTop(buildTitle());
    root.setCenter(buildHelp());
    root.setBottom(buildBottomMenu());

    //Node placement
    view.getChildren().addAll(buildPane(), root);

    //CSS styling
    root.getStylesheets().add(optionManager.getCurrentStyleSheet());
  }


  /**
   * Builds the bottom menu. The bottom menu is the bottom part of the view.
   *
   * @return the bottom menu as a HBox.
   */
  private VBox buildHelp() {

    /*
    Initializing variables
     */
    final VBox helpBox = new VBox();

    final TextFlow textBox = new TextFlow();

    final Text helpText;

    /*
    Configurations
     */
    helpText = new Text(
            """
                    Embark on an extraordinary journey, filled with twists and turns.
                    Your fate lies in your hands as you make crucial decisions,
                    shaping the outcome of your adventure.
                    Search for gold coins, uncover hidden secrets and unlock your true potential.
                    But beware, danger lurks and your choices carry the weight of life and death.
                    Are you ready to leave your mark on this extraordinary adventure?
                    Welcome, brave adventurer!
                    """);

    helpText.setLineSpacing(5);


    /*
    Placement
     */
    textBox.getChildren().add(helpText);
    helpBox.getChildren().add(textBox);


    /*
    Alignment
     */
    helpBox.setAlignment(Pos.CENTER);
    textBox.setTextAlignment(TextAlignment.CENTER);

    /*
    Styling
     */
    helpText.getStyleClass().add(StyleClass.TITLE.getValue());

    return helpBox;
  }


  /**
   * Builds the title. The title is the top part of the view.
   *
   * @return the title as a HBox.
   */
  private HBox buildTitle() {

    /*
    Initializing variables
     */
    final Text gameTitle = new Text("HELP");
    final HBox title = new HBox();
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
    title.getChildren().add(topBorderPane);

    /*
    Alignment
    */
    StackPane.setAlignment(gameTitle, javafx.geometry.Pos.CENTER);
    StackPane.setAlignment(image, javafx.geometry.Pos.CENTER);
    title.setAlignment(javafx.geometry.Pos.CENTER);

    /*
    Style class
     */
    gameTitle.getStyleClass().add(StyleClass.TITLE.getValue());

    /*
    Animation
    */
    TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(1.5));
    translateTransition2.setNode(title);
    translateTransition2.setFromY(0);
    translateTransition2.setToY(-20);
    translateTransition2.autoReverseProperty().setValue(true);
    translateTransition2.setCycleCount(Animation.INDEFINITE);
    translateTransition2.play();

    return title;
  }


  /**
   * Builds the bottom menu.
   *
   * @return the bottom menu as a HBox.
   */
  private HBox buildBottomMenu() {

    /*
    Initializing variables
    */
    final HBox bottomMenu = new HBox();
    final Button back = new Button("Back");


    /*
    Configurations
    */
    bottomMenu.setPadding(new javafx.geometry.Insets(10, 20, 10, 20));
    back.setPadding(new javafx.geometry.Insets(10, 20, 10, 20));

    /*
    Placement
     */
    bottomMenu.getChildren().addAll(back);

    /*
    Alignment
     */
    bottomMenu.setAlignment(Pos.CENTER_LEFT);

    /*
    Styling
    */
    back.getStyleClass().add(StyleClass.MENU_BUTTON.getValue());

    /*
    Button Actions
    */
    back.setOnMouseEntered(e -> ButtonUtility.buttonHover(back));
    back.setOnMouseExited(e -> ButtonUtility.buttonExit(back));
    back.setOnAction(event -> controller.onActionReturn());

    return bottomMenu;
  }

}
