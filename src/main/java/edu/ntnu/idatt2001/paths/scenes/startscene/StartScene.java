package edu.ntnu.idatt2001.paths.scenes.startscene;

import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class StartScene {
    public Scene getScene(Stage stage, double prevWidth, double prevHeight) {

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

        //Buttons nodes
        Button newGameButton = new Button("New Game");
        Button loadGameButton = new Button("Load");
        Button optionButton = new Button("Options");
        Button achievementButton = new Button("Achievements");

        //Title node
        Text gameTitle = new Text("PATHS");

        //Menu box container
        VBox menu = new VBox();
        menu.getChildren().addAll(newGameButton, loadGameButton, optionButton, achievementButton);
        menu.setSpacing(20);

        /*#######################
        # Mouse cursor handling #
         #######################*/

        //Cursor images
        String cursorGrab = "images/cursors/cursor_grab.png";
        String cursorGrabbing = "images/cursors/cursor_grabbing.png";

        //Cursor change on hover
        optionButton.setOnMouseEntered(e -> {
            scene.setCursor(new ImageCursor(new javafx.scene.image.Image(cursorGrabbing)));
        });

        newGameButton.setOnMouseEntered(e -> {
            scene.setCursor(new ImageCursor(new javafx.scene.image.Image(cursorGrabbing)));
        });

        loadGameButton.setOnMouseEntered(e -> {
            scene.setCursor(new ImageCursor(new javafx.scene.image.Image(cursorGrabbing)));
        });

        achievementButton.setOnMouseEntered(e -> {
            scene.setCursor(new ImageCursor(new javafx.scene.image.Image(cursorGrabbing)));
        });

        //Cursor change on exit
        optionButton.setOnMouseExited(e -> {
            scene.setCursor(new ImageCursor(new javafx.scene.image.Image(cursorGrab)));
        });

        newGameButton.setOnMouseExited(e -> {
            scene.setCursor(new ImageCursor(new javafx.scene.image.Image(cursorGrab)));
        });

        loadGameButton.setOnMouseExited(e -> {
            scene.setCursor(new ImageCursor(new javafx.scene.image.Image(cursorGrab)));
        });

        achievementButton.setOnMouseExited(e -> {
            scene.setCursor(new ImageCursor(new javafx.scene.image.Image(cursorGrab)));
        });

        //Button actions
        optionButton.setOnAction(e -> {
            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();
            stage.setScene(new OptionScene().getScene(stage, currentWidth, currentHeight));
        });

        newGameButton.setOnAction(e -> {
            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();
            stage.setScene(new ChooseStoryScene().getScene(stage, currentWidth, currentHeight));
        });

        loadGameButton.setOnAction(e -> {
            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();
            stage.setScene(new OptionScene().getScene(stage, currentWidth, currentHeight));
        });

        achievementButton.setOnAction(e -> {
            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();
            stage.setScene(new AchievementScene().getScene(stage, currentWidth, currentHeight));
        });

        /*#######################
        # Node positioning      #
        #######################*/

        //Node positioning
        root.setTop(gameTitle);
        root.setCenter(menu);
        root.setPadding(new javafx.geometry.Insets(30, 10, 10, 10));

        //Node alignment
        BorderPane.setAlignment(gameTitle, javafx.geometry.Pos.CENTER);
        menu.setAlignment(javafx.geometry.Pos.CENTER);

        /*#######################
        # Styling           #
        #######################*/

        //CSS styling
        root.getStylesheets().add("css/StartScene.css");
        gameTitle.getStyleClass().add("gameTitle");
        scene.setCursor(new ImageCursor(new javafx.scene.image.Image("images/cursors/cursor_grab.png")));


        return scene;
    }

}
