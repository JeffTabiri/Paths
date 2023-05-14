package edu.ntnu.idatt2001.paths.scenes.startscene;

import edu.ntnu.idatt2001.paths.utility.AudioEngine;
import edu.ntnu.idatt2001.paths.utility.ButtonEffects;
import edu.ntnu.idatt2001.paths.utility.GameStates;
import javafx.animation.Animation;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class StartScene {
    Stage stage;
    double prevWidth;
    double prevHeight;

    AudioEngine audioEngine = AudioEngine.getInstance();


    /**
     * Constructor for the start scene.
     *
     * @param stage the window to be displayed
     * @param prevWidth the width of the window before the scene was changed
     * @param prevHeight the height of the window before the scene was changed
     */
    public StartScene(Stage stage, double prevWidth, double prevHeight) {
        this.stage = stage;
        this.prevWidth = prevWidth;
        this.prevHeight = prevHeight;
    }


    public Scene getScene() {

        /*#######################
        # Stage size declaration #
        #######################*/

        stage.setWidth(prevWidth);
        stage.setHeight(prevHeight);

        /*#######################
        # GUI element creation #
        #######################*/

        //Root container
        BorderPane root = new BorderPane();

        //Menu container
        StackPane menuContainer = new StackPane();

        //Scene container
        Scene scene = new Scene(menuContainer);


        /*#######################
        # Background elements   #
        #######################*/


        menuContainer.getChildren().addAll(buildPane(), root);

        /*#######################
        # Root Node positioning #
        #######################*/

        root.setTop(buildTitle());

        root.setCenter(buildMenu());

        root.setBottom(buildCredits());

        /*#######################
        # Styling           #
        #######################*/

        //CSS styling
        root.getStylesheets().add("css/global.css");
        scene.setCursor(new ImageCursor(new javafx.scene.image.Image("images/cursor/cursor_grab.png")));

        return scene;
    }

    private Pane buildPane() {
        Pane pane = new Pane();

        ImageView imageView = new ImageView("/images/background/MainMenuBackground.png");
        imageView.fitWidthProperty().bind(pane.widthProperty());
        imageView.setPreserveRatio(true);
        pane.getChildren().add(imageView);

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(3), imageView);
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
     * Builds the menu for the start scene.
     *
     * @param scene the scene which the menu is to be added to
     * @return a VBox containing the menu
     */
    private VBox buildMenu() {


        /*#######################
         # Audio elements       #
         #######################*/

        audioEngine.playMusic(GameStates.MAIN_MENU);


        /*#######################
         # Button elements      #
         #######################*/

        //Button creation
        Button newGameButton = new Button("New Game");
        Button loadGameButton = new Button("Load");
        Button optionButton = new Button("Help");
        Button achievementButton = new Button("Achievements");

        newGameButton.getStyleClass().add("menu-button");
        loadGameButton.getStyleClass().add("menu-button");
        optionButton.getStyleClass().add("menu-button");
        achievementButton.getStyleClass().add("menu-button");


        newGameButton.setOnAction(event -> {
                    stage.setScene(new ChooseStoryScene(stage, stage.getWidth(), stage.getHeight()).getScene());
                    ButtonEffects.buttonPressed(newGameButton);
        });

        loadGameButton.setOnAction(event -> {
            ButtonEffects.buttonPressed(loadGameButton);
        });

        optionButton.setOnAction(event -> {
            stage.setScene(new OptionScene(stage, stage.getWidth(), stage.getHeight()).getScene());
            ButtonEffects.buttonPressed(optionButton);
        });

        achievementButton.setOnAction(event -> {
            stage.setScene(new AchievementScene(stage, stage.getWidth(), stage.getHeight()).getScene());
            ButtonEffects.buttonPressed(achievementButton);
        });


        newGameButton.setOnMouseExited(event -> ButtonEffects.buttonExit(newGameButton));
        loadGameButton.setOnMouseExited(event -> ButtonEffects.buttonExit(loadGameButton));
        optionButton.setOnMouseExited(event -> ButtonEffects.buttonExit(optionButton));
        achievementButton.setOnMouseExited(event -> ButtonEffects.buttonExit(achievementButton));

        newGameButton.setOnMouseEntered(event -> ButtonEffects.buttonHover(newGameButton));
        loadGameButton.setOnMouseEntered(event -> ButtonEffects.buttonHover(loadGameButton));
        optionButton.setOnMouseEntered(event -> ButtonEffects.buttonHover(optionButton));
        achievementButton.setOnMouseEntered(event -> ButtonEffects.buttonHover(achievementButton));


        //Menu container
        VBox menu = new VBox();
        menu.getChildren().addAll(newGameButton, loadGameButton, optionButton, achievementButton);


        //Menu styling
        menu.setSpacing(40);
        menu.setAlignment(javafx.geometry.Pos.CENTER);

        //Animation
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(3), menu);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);
        fadeTransition.play();

        return menu;
    }


    /**
     * Builds the title box.
     *
     * @return the title box in a HBox container
     */
    private HBox buildTitle() {

        //Title text
        Text gameTitle = new Text("PATHS");

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
     * Builds the credits box.
     *
     * @return the credits box in a HBox container
     */
    private HBox buildCredits() {

        //Credits container
        HBox credits = new HBox();

        //Credits text
        Text creditsText = new Text("Created by students at NTNU.");

        //Add elements to HBox
        credits.getChildren().add(creditsText);

        //Set HBox alignment
        credits.setAlignment(Pos.BOTTOM_RIGHT);

        //Styling
        creditsText.getStyleClass().add("title");

        //Animation
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(javafx.util.Duration.seconds(1.5));
        translateTransition.setNode(credits);
        translateTransition.setFromY(40);
        translateTransition.setToY(0);
        translateTransition.play();

        return credits;
    }

}
