package edu.ntnu.idatt2001.paths.scenes.GameEngine;

import edu.ntnu.idatt2001.paths.Game;
import edu.ntnu.idatt2001.paths.Passage;
import edu.ntnu.idatt2001.paths.Player;
import edu.ntnu.idatt2001.paths.Story;
import edu.ntnu.idatt2001.paths.filehandling.StoryLoader;
import edu.ntnu.idatt2001.paths.scenes.startscene.StartScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class GameLoopScene {

    Game currentGame;
    Story story;

    Passage currentPassage;

    public GameLoopScene(File chosenStory) throws IOException {
        story = new StoryLoader(chosenStory).getStory();
        currentGame = new Game(new Player("Player", 100, 100, 100), story, new ArrayList<>());
    }


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


        //Scene creation
        Scene scene = new Scene(root, prevWidth, prevHeight);

        //Content in passage
        Text passageTitle = new Text();
        Text passageContent = new Text();
        passageTitle.setText(story.getTitle());
        passageContent.setText(story.getOpeningPassage().getContent());



        //Button
        Button quitButton = new Button("Quit");

        //HBOX for quit button
        HBox leftBox = new HBox();
        HBox rightBox = new HBox();

        HBox bottom = new HBox();

        leftBox.getChildren().add(passageTitle);

        rightBox.getChildren().add(quitButton);
        bottom.getChildren().addAll(leftBox, rightBox);

        //Passage to be displayed
        currentPassage = story.getOpeningPassage();

        //List of choices
        ListView<String> passageChoice = new ListView<String>();
        passageChoice.setPrefHeight(100);
        passageChoice.getItems().addAll(getPassageChoices(currentPassage));


        /*#######################
         # Actions              #
         #######################*/


        passageChoice.onMouseClickedProperty().set(e -> {
            currentPassage = currentGame.go(currentPassage.getLinks().get(passageChoice.getSelectionModel().getSelectedIndex()));

            updateGameLoop(passageContent, passageTitle, passageChoice);
        });

        //Quit button action
        quitButton.setOnAction(e -> {
            stage.setScene(new StartScene().getScene(stage, prevWidth, prevHeight));
        });



        /*#######################
         # Styling              #
         #######################*/

        root.getStylesheets().add("css/GameLoopScene.css");
        passageContent.getStyleClass().add("content");
        passageContent.wrappingWidthProperty().bind(stage.widthProperty().subtract(15));
        passageTitle.getStyleClass().add("title");



        /*#######################
         # Node placement #
         #######################*/

        //Title placement
        root.setTop(bottom);

        HBox.setHgrow(leftBox, Priority.ALWAYS);
        HBox.setHgrow(rightBox, Priority.ALWAYS);
        quitButton.setAlignment(Pos.TOP_RIGHT);



        //Content placement
        root.setCenter(passageContent);
        passageContent.setTextAlignment(TextAlignment.CENTER);

        //Choices placement
        root.setBottom(passageChoice);

        return scene;
    }


    /**
     * Returns a string with the passage info
     *
     * @param passage to analyze and check for info
     * @return a string with the passage info
     */
    private String getPassageInfo(Passage passage) {
        StringBuilder sb = new StringBuilder();
        sb.append(passage.getContent());
        sb.append("\nChoose one of the following options:");

        return sb.toString();
    }


    /**
     * Returns a list of choices for the player to choose from
     *
     * @param passage to analyze and check for choices
     * @return a list of all possible choices
     */
    private ArrayList<String> getPassageChoices(Passage passage) {

        ArrayList<String> choices = new ArrayList<>();

        for (int i = 0; i < passage.getLinks().size(); i++) {
            choices.add(i + 1 + ": " + passage.getLinks().get(i).getText());
        }

        return choices;
    }


    /**
     * Updates the game loop scene
     */
    private void updateGameLoop(Text content, Text title, ListView<String> choices) {
        content.setText(currentPassage.getContent());
        title.setText(currentPassage.getTitle());
        choices.getItems().clear();
        choices.getItems().addAll(getPassageChoices(currentPassage));
    }

    }


