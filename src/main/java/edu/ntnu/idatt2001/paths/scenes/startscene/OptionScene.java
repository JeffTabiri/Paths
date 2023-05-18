package edu.ntnu.idatt2001.paths.scenes.startscene;

import edu.ntnu.idatt2001.paths.utility.ButtonEffects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

public class OptionScene {

    Stage stage;
    double prevWidth;
    double prevHeight;


    public OptionScene(Stage stage, double prevWidth, double prevHeight) {
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
        # Root Node positioning #
        #######################*/

        root.setTop(buildTitle());

        root.setCenter(buildMenu());

        root.setBottom(buildBottomMenu());

        /*#######################
        # Styling           #
        #######################*/

        //CSS styling
        root.getStylesheets().add("css/global.css");
        scene.setCursor(new ImageCursor(new javafx.scene.image.Image("images/cursor/cursor_grab.png")));

        return scene;
    }

    private HBox buildTitle() {

        //Title container
        HBox titleContainer = new HBox();

        //Title
        Label title = new Label("Options");

        titleContainer.getChildren().add(title);

        HBox.setHgrow(title, Priority.ALWAYS);

        titleContainer.setAlignment(Pos.CENTER);

        //Title styling
        title.getStyleClass().add("title");

        return titleContainer;
    }

    private VBox buildMenu() {

        VBox menuContainer = new VBox();

        /*#######################
        # Menu elements         #
        ###################### */

        //Dark mode container
        HBox rightDarkModeContainer = new HBox();
        HBox leftDarkModeContainer = new HBox();
        HBox darkModeContainer = new HBox();
        ToggleButton darkModeButton = new ToggleButton();
        TextFlow darkModeText = new TextFlow(new Text("Dark Mode "));
        rightDarkModeContainer.getChildren().add(darkModeButton);
        leftDarkModeContainer.getChildren().add(darkModeText);
        darkModeContainer.getChildren().addAll(leftDarkModeContainer, rightDarkModeContainer);

        HBox.setHgrow(leftDarkModeContainer, Priority.ALWAYS);
        HBox.setHgrow(rightDarkModeContainer, Priority.ALWAYS);

        rightDarkModeContainer.setAlignment(Pos.CENTER_RIGHT);
        leftDarkModeContainer.setAlignment(Pos.CENTER_LEFT);

        //Sound container
        HBox rightSoundContainer = new HBox();
        HBox leftSoundContainer = new HBox();
        HBox soundContainer = new HBox();
        ToggleButton soundButton = new ToggleButton();
        TextFlow soundText = new TextFlow(new Text("Mute: "));
        rightSoundContainer.getChildren().add(soundButton);
        leftSoundContainer.getChildren().add(soundText);
        soundContainer.getChildren().addAll(leftSoundContainer, rightSoundContainer);

        HBox.setHgrow(leftSoundContainer, Priority.ALWAYS);
        HBox.setHgrow(rightSoundContainer, Priority.ALWAYS);

        rightSoundContainer.setAlignment(Pos.CENTER_RIGHT);
        leftSoundContainer.setAlignment(Pos.CENTER_LEFT);


        //Fullscreen container
        HBox rightFullscreenContainer = new HBox();
        HBox leftFullscreenContainer = new HBox();
        HBox fullscreenContainer = new HBox();

        ToggleButton fullscreenButton = new ToggleButton();
        TextFlow fullscreenText = new TextFlow(new Text("Fullscreen :"));
        rightFullscreenContainer.getChildren().add(fullscreenButton);
        leftFullscreenContainer.getChildren().add(fullscreenText);
        fullscreenContainer.getChildren().addAll(leftFullscreenContainer, rightFullscreenContainer);

        HBox.setHgrow(leftFullscreenContainer, Priority.ALWAYS);
        HBox.setHgrow(rightFullscreenContainer, Priority.ALWAYS);
        rightFullscreenContainer.setAlignment(Pos.CENTER_RIGHT);
        leftFullscreenContainer.setAlignment(Pos.CENTER_LEFT);



        menuContainer.getChildren().addAll(darkModeContainer, soundContainer, fullscreenContainer);


        //Menu styling
        menuContainer.getStyleClass().add("menu");

        return menuContainer;
    }

    private HBox buildBottomMenu() {

        //Bottom menubar container
        HBox leftBox = new HBox();
        HBox rightBox = new HBox();
        HBox bottom = new HBox();

        //Buttons
        Button returnButton = new Button("Return");
        Button startGameButton = new Button("Save");


        //Padding
        leftBox.setPadding(new Insets(20, 0, 20, 20));
        rightBox.setPadding(new Insets(20, 20, 20, 0));

        //Button actions
        returnButton.setOnAction(event ->
                stage.setScene(new StartScene(stage, stage.getWidth(), stage.getHeight()).getScene()));

        returnButton.setOnMouseEntered(event -> ButtonEffects.buttonHover(returnButton));
        startGameButton.setOnMouseEntered(event -> ButtonEffects.buttonHover(startGameButton));

        returnButton.setOnMouseExited(event -> ButtonEffects.buttonExit(returnButton));
        startGameButton.setOnMouseExited(event -> ButtonEffects.buttonExit(startGameButton));


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
        returnButton.getStyleClass().add("secondary-button");
        startGameButton.getStyleClass().add("secondary-button");

        return bottom;
    }


}
