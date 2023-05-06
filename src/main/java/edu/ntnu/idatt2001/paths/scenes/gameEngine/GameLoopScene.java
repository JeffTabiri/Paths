package edu.ntnu.idatt2001.paths.scenes.gameEngine;

import edu.ntnu.idatt2001.paths.*;
import edu.ntnu.idatt2001.paths.filehandling.StoryLoader;
import edu.ntnu.idatt2001.paths.scenes.startscene.StartScene;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represent the main
 */
public class GameLoopScene {


    //Game variables
    Game currentGame;
    Player player;
    Story story;
    Passage currentPassage;
    AchievementList achievementList = AchievementList.getInstance();

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


        //Button creation
        Button goBackButton = new Button("Return");


        //Content in passage
        Text passageTitle = new Text();
        Text passageContent = new Text();
        passageTitle.setText(story.getTitle());
        passageContent.setText(story.getOpeningPassage().getContent());

        //HBox for player stats
        HBox passageTitleBox = new HBox();
        HBox optionsBox = new HBox();

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

            updateGameLoop(passageContent, passageTitle, passageChoice);
        });


        goBackButton.setOnAction(e -> {
            //stage.setScene(new StartScene().getScene(stage, prevWidth, prevHeight));
        });


        playerStatsBox.getChildren().addAll(playerGoldBox, playerHealthBox, playerScoreBox, playerNameBox);
        playerStatsBox.setAlignment(Pos.CENTER);
        playerStatsBox.setMaxWidth(Double.MAX_VALUE);


        VBox ground = new VBox();
        ground.getChildren().addAll(playerStatsBox, passageChoice);


        //Content placement
        root.setCenter(passageContent);
        HBox titleBox = new HBox();
        titleBox.getChildren().addAll(passageTitle);
        titleBox.setAlignment(Pos.CENTER);
        VBox topBox = new VBox();
        topBox.getChildren().addAll(createOptionsTab(), titleBox);
        root.setTop(topBox);
        BorderPane.setAlignment(titleBox, Pos.CENTER);
        passageContent.setTextAlignment(TextAlignment.CENTER);

        //Choices placement
        root.setBottom(ground);

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
    private void updateGameLoop(Text content, Text title, ListView<String> choices) {

        content.setText(currentPassage.getContent());
        title.setText(currentPassage.getTitle());
        choices.getItems().clear();
        choices.getItems().addAll(getPassageChoices(currentPassage));
        updateAchievements();

    }




    private void updateAchievements() {

        AchievementList.getInstance().getAchievements().forEach(achievement -> {
            achievement.checkProgress(player);
        });

    }




    private HBox createOptionsTab() {

        HBox optionsBox = new HBox(10);

        ImageView saveImage = new ImageView("/images/icons/note.png");
        saveImage.setPreserveRatio(true);
        saveImage.setFitHeight(25);
        saveImage.setFitWidth(25);

        ImageView optionsImage = new ImageView("/images/icons/settings.png");
        optionsImage.setPreserveRatio(true);
        optionsImage.setFitHeight(25);
        optionsImage.setFitWidth(25);

        ImageView exitImage = new ImageView("/images/icons/close.png");
        exitImage.setPreserveRatio(true);
        exitImage.setFitHeight(25);
        exitImage.setFitWidth(25);

        ImageView helpImage = new ImageView("/images/icons/help.png");
        helpImage.setPreserveRatio(true);
        helpImage.setFitHeight(25);
        helpImage.setFitWidth(25);

        optionsBox.getChildren().addAll(saveImage, optionsImage, helpImage, exitImage);

        optionsBox.setSpacing(10);
        optionsBox.setAlignment(Pos.CENTER_RIGHT);
        optionsBox.setMaxWidth(25);
        optionsBox.setMaxHeight(25);
        optionsBox.setAlignment(Pos.CENTER_RIGHT);

        return optionsBox;

    }




}


