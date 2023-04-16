package edu.ntnu.idatt2001.paths.scenes.startscene;

import edu.ntnu.idatt2001.paths.scenes.gameEngine.GameLoopScene;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the scene where the user can choose a story to play. Also creates a new character
 *
 */
public class ChooseStoryScene {

    /**
     * Returns a scene with the GUI elements for the choose story scene
     *
     * @param stage the window the scene is displayed in
     * @param prevWidth the width of the previous scene
     * @param prevHeight the height of the previous scene
     * @return the scene with the GUI elements
     */
    public Scene getScene(Stage stage, double prevWidth, double prevHeight) {

        /*#######################
        # Stage size declaration #
        #######################*/

        stage.setHeight(prevHeight);
        stage.setWidth(prevWidth);

        /*#######################
        # GUI element creation #
         #######################*/

        BorderPane root = new BorderPane();

        Scene scene = new Scene(root);

        Button goBackButton = new Button("Return");
        Button startGameButton = new Button("Start Game");




        //Bottom menubar container
        HBox leftBox = new HBox();
        HBox rightBox = new HBox();
        HBox bottom = new HBox();

        leftBox.getChildren().add(goBackButton);
        rightBox.getChildren().add(startGameButton);
        bottom.getChildren().addAll(leftBox, rightBox);

        //Title container
        Text gameTitle = new Text("Choose Story");

        //Lists over stories
        ListView<String> storyListView = new ListView<String>();
        storyListView.setItems(FXCollections.observableArrayList(getStoryList()));

        /*#######################
        ¤ Button actions       ¤
        #######################*/

        goBackButton.setOnAction(e -> {

            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();
            stage.setScene(new StartScene().getScene(stage, currentWidth, currentHeight));

        });

        startGameButton.setOnAction(e -> {


            /*
            String storyAddress = "src/main/resources/stories/";

            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();

            File storyFile = new File(storyAddress + storyListView.getSelectionModel().getSelectedItem());

            try {
                stage.setScene(new GameLoopScene(storyFile).getScene(stage, currentWidth, currentHeight));
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            */

        });


        startGameButton.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        final Stage dialog = new Stage();
                        dialog.initModality(Modality.APPLICATION_MODAL);
                        dialog.initOwner(stage);
                        VBox popupBox = new VBox(20);
                        HBox inputBox = new HBox(20);
                        Text popupTitle = new Text("Choose a name");
                        TextField nameField = new TextField();
                        Button confirmButton = new Button("Confirm");
                        popupBox.getChildren().addAll(popupTitle);
                        inputBox.getChildren().addAll(nameField);
                        popupBox.getChildren().addAll(inputBox, confirmButton);
                        popupBox.setAlignment(Pos.CENTER);
                        inputBox.setAlignment(Pos.CENTER);


                        confirmButton.setOnAction(
                                new EventHandler<ActionEvent>() {
                                    @Override
                                    public void handle(ActionEvent event) {
                                        String storyAddress = "src/main/resources/stories/";

                                        dialog.close();

                                        double currentWidth = stage.getWidth();
                                        double currentHeight = stage.getHeight();

                                        File storyFile = new File(storyAddress + storyListView.getSelectionModel().getSelectedItem());
                                        String name = nameField.getText();

                                        try {
                                            stage.setScene(new GameLoopScene(storyFile, name).getScene(stage, currentWidth, currentHeight));
                                        } catch (IOException ex) {
                                            throw new RuntimeException(ex);
                                        }
                                    }
                                });


                        Scene popupScene = new Scene(popupBox, 300, 200);
                        dialog.setScene(popupScene);
                        dialog.show();
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
