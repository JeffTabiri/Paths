package edu.ntnu.idatt2001.paths.scenes.startscene;

import edu.ntnu.idatt2001.paths.utility.ButtonEffects;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class StartScene {
    Stage stage;
    double prevWidth;
    double prevHeight;


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
         # Button elements      #
         #######################*/

        //Button creation
        Button newGameButton = new Button("New Game");
        Button loadGameButton = new Button("Load");
        Button optionButton = new Button("Options");
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
        optionButton.setOnAction(event -> {
            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();
            stage.setScene(new OptionScene().getScene(stage, currentWidth, currentHeight));
        });


        newGameButton.setOnAction(event -> {
            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();
            stage.setScene(new ChooseStoryScene(stage, stage.getWidth(), stage.getHeight()).getScene());
        });


        loadGameButton.setOnAction(event -> {
            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();
            stage.setScene(new OptionScene().getScene(stage, currentWidth, currentHeight));
        });


        achievementButton.setOnAction(event -> {
            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();
            stage.setScene(new AchievementScene().getScene(stage, currentWidth, currentHeight));
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

}
