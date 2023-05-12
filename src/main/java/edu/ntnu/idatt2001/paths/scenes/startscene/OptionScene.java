package edu.ntnu.idatt2001.paths.scenes.startscene;

import edu.ntnu.idatt2001.paths.utility.AudioEngine;
import edu.ntnu.idatt2001.paths.utility.ButtonEffects;
import edu.ntnu.idatt2001.paths.utility.GameStates;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class OptionScene {

    AudioEngine audioEngine = AudioEngine.getInstance();

    Stage stage;
    double prevWidth;
    double prevHeight;

    /**
     * Constructor for the option scene. Assigns the stage, previous width and previous height.
     *
     * @param stage the window to be displayed
     * @param prevWidth the width of the window before the scene was changed
     * @param prevHeight the height of the window before the scene was changed
     */
    public OptionScene(Stage stage, double prevWidth, double prevHeight) {
        this.stage = stage;
        this.prevWidth = prevWidth;
        this.prevHeight = prevHeight;
    }

    public Scene getScene() {

        audioEngine.playMusic(GameStates.HELP_MENU);

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


        root.setTop(buildTitle());
        root.setCenter(buildHelp());
        root.setBottom(buildBottomMenu(scene));

        root.getStylesheets().add("css/global.css");





        return scene;
    }

    private HBox buildTitle() {
        //Title text
        Text gameTitle = new Text("HELP");

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

    private VBox buildHelp() {
        //Options container
        VBox helpBox = new VBox();

        TextFlow textBox = new TextFlow();

        Text helpText = new Text("This game is a story based game where one navigates through the act of choosing paths");

        helpText.getStyleClass().add("title");

        textBox.getChildren().add(helpText);

        helpBox.getChildren().add(textBox);

        helpBox.setAlignment(Pos.CENTER);
        textBox.setTextAlignment(TextAlignment.CENTER);

        return helpBox;
    }

    private HBox buildBottomMenu(Scene scene) {

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
        back.getStyleClass().add("button");

        //Button effects
        ButtonEffects.addCursorImageChange(back, scene);
        ButtonEffects.addAudioChange(back);

        //Button actions
        back.setOnAction(e -> {
            StartScene startScene = new StartScene(stage, stage.getWidth(), stage.getHeight());
            stage.setScene(startScene.getScene());
        });

        return bottomMenu;
    }

}
