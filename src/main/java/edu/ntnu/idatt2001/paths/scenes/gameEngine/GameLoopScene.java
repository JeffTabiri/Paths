package edu.ntnu.idatt2001.paths.scenes.gameEngine;

import edu.ntnu.idatt2001.paths.Game;
import edu.ntnu.idatt2001.paths.Passage;
import edu.ntnu.idatt2001.paths.Player;
import edu.ntnu.idatt2001.paths.Story;
import edu.ntnu.idatt2001.paths.filehandling.StoryLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Represent the main
 */
public class GameLoopScene {

    //Game variables
    Game currentGame;
    Player player;
    Story story;
    Passage currentPassage;

    public GameLoopScene(File chosenStory, String name) throws IOException {
        story = new StoryLoader(chosenStory).getStory();
        player = new Player(name, 100, 0, 0);
        currentGame = new Game(player, story, new ArrayList<>());
        currentPassage = story.getOpeningPassage();
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

        //Player stats
        Text playerGold = new Text();
        Text playerHealth = new Text();
        Text playerScore = new Text();
        Text playerName = new Text();

        //Player stats
        playerGold.setText(player.getGold() + "G");
        playerHealth.setText(player.getHealth() + "HP");
        playerScore.setText(player.getScore() + "PTS");
        playerName.setText(player.getName());

        //Passage to be displayed
        currentPassage = story.getOpeningPassage();

        //List of choices
        ListView<String> passageChoice = new ListView<String>();
        passageChoice.setPrefHeight(100);
        passageChoice.getItems().addAll(getPassageChoices(currentPassage));


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


        //HBox for player stats
        HBox playerStatsBox = new HBox(10);
        HBox playerGoldBox = new HBox(5);
        HBox playerHealthBox = new HBox(5);
        HBox playerScoreBox = new HBox(5);
        HBox playerNameBox = new HBox();

        playerNameBox.getChildren().add(playerName);


        Image healthImage = new Image("images/UI/hotbar/heart.png", 32, 32, false, false);
        ImageView healthImageView = new ImageView(healthImage);
        healthImageView.setFitWidth(32);
        healthImageView.setFitHeight(32);

        Image goldImage = new Image("images/UI/hotbar/goldCoin.png", 32, 32, false, false);
        ImageView goldImageView = new ImageView(goldImage);
        goldImageView.setFitWidth(32);
        goldImageView.setFitHeight(32);

        Image scoreImage = new Image("images/UI/hotbar/score.png", 32, 32, false, false);
        ImageView scoreImageView = new ImageView(scoreImage);
        scoreImageView.setFitWidth(32);
        scoreImageView.setFitHeight(32);

        playerHealthBox.getChildren().addAll(healthImageView, playerHealth);
        playerGoldBox.getChildren().addAll(goldImageView, playerGold);
        playerScoreBox.getChildren().addAll(scoreImageView, playerScore);

        String playerStatsStyle = "playerStats";

        playerHealth.getStyleClass().add(playerStatsStyle);
        playerGold.getStyleClass().add(playerStatsStyle);
        playerScore.getStyleClass().add(playerStatsStyle);
        playerName.getStyleClass().add(playerStatsStyle);

        Image image = new Image("images/background/Cave.jpg");
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(prevWidth);
        imageView.setFitHeight(1000);
        StackPane textAndImage = new StackPane();
        textAndImage.getChildren().addAll(imageView, passageContent);

        /*#######################
         # Actions              #
         #######################*/


        passageChoice.onMouseClickedProperty().set(e -> {

            currentPassage.getLinks().get(passageChoice.getSelectionModel().getSelectedIndex()).getActions().forEach(action -> action.execute(player));
            currentPassage.getLinks().get(passageChoice.getSelectionModel().getSelectedIndex()).getActions().clear();



            playerGold.setText(player.getGold() + "G");
            playerHealth.setText(player.getHealth() + "HP");
            playerScore.setText(player.getScore() + "PTS");
            currentPassage = currentGame.go(currentPassage.getLinks().get(passageChoice.getSelectionModel().getSelectedIndex()));


            updateGameLoop(passageContent, passageTitle, passageChoice, imageView);


        });


        playerStatsBox.getChildren().addAll(playerGoldBox, playerHealthBox, playerScoreBox, playerNameBox);
        playerStatsBox.setAlignment(Pos.CENTER);
        playerStatsBox.setMaxWidth(Double.MAX_VALUE);


        VBox ground = new VBox();
        ground.getChildren().addAll(playerStatsBox, passageChoice);

        //StackPane textAndImage = new StackPane();
        //ImageView image = new ImageView("images/Backgrounds/Cave.png");
        //textAndImage.getChildren().addAll(image, passageContent);


        //Content placement
        root.setCenter(textAndImage);
        root.setTop(passageTitle);
        BorderPane.setAlignment(passageTitle, Pos.CENTER);
        passageContent.setTextAlignment(TextAlignment.CENTER);

        //.setCenter(textAndImage);

        //Choices placement
        root.setBottom(ground);

        //root.getStyleClass().add("background");

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
     * Updates the content of the game loop scene
     */
    private void updateGameLoop(Text content, Text title, ListView<String> choices, ImageView image) {
        content.setText(currentPassage.getContent());
        title.setText(currentPassage.getTitle());
        choices.getItems().clear();
        choices.getItems().addAll(getPassageChoices(currentPassage));
        if (currentPassage.getFileName() != "" || currentPassage.getFileName() != null){
            try {
                image.setImage(new Image(new FileInputStream(currentPassage.getUrl())));
            } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
            }
        }
    }


}


