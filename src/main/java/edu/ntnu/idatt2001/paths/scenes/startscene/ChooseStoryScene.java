package edu.ntnu.idatt2001.paths.scenes.startscene;

import edu.ntnu.idatt2001.paths.scenes.GameEngine.GameLoopScene;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ChooseStoryScene {
    public Scene getScene(Stage stage, double prevWidth, double prevHeight) {

        /*#######################
        # Stage size declaration #
        #######################*/

        stage.setHeight(prevHeight);
        stage.setWidth(prevWidth);

        /*#######################
        # GUI element creation #
         #######################*/

        //Root container
        BorderPane root = new BorderPane();

        //Scene container
        Scene scene = new Scene(root);

        //Button containers
        Button goBackButton = new Button("Return");
        Button startGameButton = new Button("Start Game");

        //HBox containers
        HBox leftBox = new HBox();
        HBox rightBox = new HBox();
        HBox bottom = new HBox();

        leftBox.getChildren().add(goBackButton);
        rightBox.getChildren().add(startGameButton);
        bottom.getChildren().addAll(leftBox, rightBox);

        //Title container
        Text gameTitle = new Text("Choose Story");

        //ListView container
        ListView<String> storyListView = new ListView<String>();
        storyListView.setItems(FXCollections.observableArrayList(getStoryList()));

        /*#######################
        ¤ Button actions       ¤
        #######################*/

        //Button actions
        goBackButton.setOnAction(e -> {

            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();
            stage.setScene(new StartScene().getScene(stage, currentWidth, currentHeight));

        });

        startGameButton.setOnAction(e -> {

            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();
            String storyAddress = "src/main/resources/stories/";

            File storyFile = new File(storyAddress + storyListView.getSelectionModel().getSelectedItem());

            try {
                stage.setScene(new GameLoopScene(storyFile).getScene(stage, currentWidth, currentHeight));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }

        });

        /*#######################
        # Cursor handling       #
        #######################*/

        //Cursor images
        String cursorGrab = "images/cursors/cursor_grab.png";
        String cursorGrabbing = "images/cursors/cursor_grabbing.png";

        //Cursor change on hover
        goBackButton.setOnMouseEntered(e -> {
            stage.getScene().setCursor(new ImageCursor(new javafx.scene.image.Image(cursorGrabbing)));
        });

        startGameButton.setOnMouseEntered(e -> {
            stage.getScene().setCursor(new ImageCursor(new javafx.scene.image.Image(cursorGrabbing)));
        });

        goBackButton.setOnMouseExited(e -> {
            stage.getScene().setCursor(new ImageCursor(new javafx.scene.image.Image(cursorGrab)));
        });

        startGameButton.setOnMouseExited(e -> {
            stage.getScene().setCursor(new ImageCursor(new javafx.scene.image.Image(cursorGrab)));
        });


        /*#######################
        # Node positioning      #
        #######################*/

        //Node alignment
        leftBox.setAlignment(Pos.CENTER_LEFT);
        rightBox.setAlignment(Pos.CENTER_RIGHT);
        BorderPane.setAlignment(gameTitle, Pos.CENTER);

        //Node positioning
        root.setBottom(bottom);
        root.setTop(gameTitle);
        root.setCenter(storyListView);

        //Node padding
        HBox.setHgrow(leftBox, Priority.ALWAYS);
        HBox.setHgrow(rightBox, Priority.ALWAYS);
        bottom.setPadding(new Insets(10));

        //CSS styling
        root.getStylesheets().add("css/ChooseStoryScene.css");
        gameTitle.getStyleClass().add("gameTitle");
        scene.setCursor(new ImageCursor(new javafx.scene.image.Image("images/cursors/cursor_grab.png")));

        return scene;
    }

    /**
     * Gets a list of all the stories in the stories folder
     *
     * @return a list of all the stories in the stories folder as strings
     */
    private List<String> getStoryList() {

        List<String> storyList = new ArrayList<>();

        File[] files = new File("src/main/resources/stories").listFiles();

        for (File file : files) {
            if (file.isFile()) {
                storyList.add(file.getName());
            }
        }

        return storyList;
    }

}
