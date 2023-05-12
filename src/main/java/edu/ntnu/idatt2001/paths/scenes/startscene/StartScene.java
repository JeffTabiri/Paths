package edu.ntnu.idatt2001.paths.scenes.startscene;

import edu.ntnu.idatt2001.paths.utility.AudioEngine;
import edu.ntnu.idatt2001.paths.utility.ButtonEffects;
import edu.ntnu.idatt2001.paths.utility.GameStates;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;


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


        //Scene container
        Scene scene = new Scene(root);


        /*#######################
        # Node positioning      #
        #######################*/

        root.setTop(buildTitle());

        root.setCenter(buildMenu(scene));

        root.setBottom(buildCredits());

        /*#######################
        # Styling           #
        #######################*/

        //CSS styling
        root.getStylesheets().add("css/global.css");
        scene.setCursor(new ImageCursor(new javafx.scene.image.Image("images/cursors/cursor_grab.png")));

        return scene;
    }


    /**
     * Builds the menu for the start scene.
     *
     * @param scene the scene which the menu is to be added to
     * @return a VBox containing the menu
     */
    private VBox buildMenu(Scene scene) {


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


        //Cursor change on hover
        ButtonEffects.addCursorImageChange(newGameButton, scene);
        ButtonEffects.addCursorImageChange(loadGameButton, scene);
        ButtonEffects.addCursorImageChange(optionButton, scene);
        ButtonEffects.addCursorImageChange(achievementButton, scene);

        ButtonEffects.addAudioChange(newGameButton);
        ButtonEffects.addAudioChange(loadGameButton);
        ButtonEffects.addAudioChange(optionButton);
        ButtonEffects.addAudioChange(achievementButton);


        //Button actions
        optionButton.setOnAction(event ->
                stage.setScene(new OptionScene(stage, stage.getWidth(), stage.getHeight()).getScene()));


        newGameButton.setOnAction(event ->
                stage.setScene(new ChooseStoryScene(stage, stage.getWidth(), stage.getHeight()).getScene()));


        /*
        loadGameButton.setOnAction(event ->
                stage.setScene(new OptionScene().getScene(stage, stage.getWidth(), stage.getHeight())));
        */

        achievementButton.setOnAction(event -> {
            stage.setScene(new AchievementScene(stage, stage.getWidth(), stage.getHeight()).getScene());
        });


        //Menu container
        VBox menu = new VBox();
        menu.getChildren().addAll(newGameButton, loadGameButton, optionButton, achievementButton);


        //Menu styling
        menu.setSpacing(20);
        menu.setAlignment(javafx.geometry.Pos.CENTER);



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
        ImageView image = new ImageView("/images/UI_Flat_Banner_01_Upward.png");

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

        return credits;
    }

    private void playMusic() {
        Media backgroundMusic = new Media(StartScene.class.getResource("/audio/backgroundMusic/mainMenu.mp3").toString());
        MediaPlayer mediaPlayer = new MediaPlayer(backgroundMusic);
        mediaPlayer.play();
    }

}
