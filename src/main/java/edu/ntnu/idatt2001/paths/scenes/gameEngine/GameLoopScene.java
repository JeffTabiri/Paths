package edu.ntnu.idatt2001.paths.scenes.gameEngine;

import edu.ntnu.idatt2001.paths.Game;
import edu.ntnu.idatt2001.paths.Passage;
import edu.ntnu.idatt2001.paths.Story;
import edu.ntnu.idatt2001.paths.filehandling.StoryLoader;
import edu.ntnu.idatt2001.paths.playerBuilder.Player;
import edu.ntnu.idatt2001.paths.playerBuilder.PlayerBuilder;
import edu.ntnu.idatt2001.paths.utility.AudioEngine;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Represent the main
 */
public class GameLoopScene {

    //Stage variables

    Stage stage;

    double prevWidth;

    double prevHeight;

    //Game variables

    Game game;
    Player player;
    Story story;
    Passage currentPassage;

    public GameLoopScene(File chosenStory, String name, Stage stage, double currentWidth, double currentHeight) throws IOException {

        story = new StoryLoader(chosenStory).getStory();
        player = new PlayerBuilder(name)
                .health(100)
                .score(0)
                .build();

        game = new Game(player, story, new ArrayList<>());
        currentPassage = story.getOpeningPassage();

        this.stage = stage;
        this.prevWidth = currentWidth;
        this.prevHeight = currentHeight;
    }


    public Scene getScene() {


        /*#######################
        # Stage size declaration #
        #######################*/

        stage.setHeight(prevHeight);
        stage.setWidth(prevWidth);


        /*#######################
         # GUI element creation #
         #######################*/

        //Root
        BorderPane root = new BorderPane();

        //Scene creation
        Scene scene = new Scene(root, prevWidth, prevHeight);


        //Content placement
        VBox topBox = new VBox();
        topBox.getChildren().addAll(createOptionsTab(), buildTitle());


        VBox ground = new VBox();
        ground.getChildren().addAll(buildHotbarBox(), buildPassageChoices(root));


        //Choices placement
        root.setTop(topBox);
        root.setCenter(buildPassageContent());
        root.setBottom(ground);

        //Styling


        root.getStylesheets().add("css/global.css");

        return scene;
    }

    /**
     * Creates a list of choices for the player to choose from.
     *
     * @return a list of choices from the current passage in a ListView
     */
    private ListView buildPassageChoices(BorderPane root) {

        ListView<String> passageChoice = new ListView<>();
        passageChoice.setPrefHeight(150);
        passageChoice.getItems().addAll(getPassageChoices(currentPassage));

        passageChoice.onMouseClickedProperty().set(e -> {

            currentPassage.getLinks().get(passageChoice.getSelectionModel().getSelectedIndex()).getActions().forEach(action ->
                    action.execute(player));

            currentPassage.getLinks().get(passageChoice.getSelectionModel().getSelectedIndex()).getActions().clear();

            currentPassage = game.go(currentPassage.getLinks().get(passageChoice.getSelectionModel().getSelectedIndex()));

            if(currentPassage.getGameState() != null) {
                AudioEngine.getInstance().playMusic(currentPassage.getGameState());
            }

            passageChoice.getItems().clear();

            passageChoice.getItems().addAll(getPassageChoices(currentPassage));

            //Content placement
            VBox topBox = new VBox();
            topBox.getChildren().addAll(createOptionsTab(), buildTitle());

            VBox ground = new VBox();
            ground.getChildren().addAll(buildHotbarBox(), buildPassageChoices(root));

            root.setTop(topBox);
            root.setCenter(buildPassageContent());
            root.setBottom(ground);
        });

        return passageChoice;
    }

    /**
     * Creates a container for the content of the passage.
     *
     * @return a container for the content of the current passage
     */
    private HBox buildPassageContent() {

        //Build passage content
        TextFlow passageContent = new TextFlow(
                new Text(currentPassage.getContent())
        );

        //Build passage content container
        HBox passageContentBox = new HBox(passageContent);

        //Set passage content alignment
        passageContentBox.setAlignment(Pos.CENTER);

        //Styling
        passageContentBox.getStyleClass().add("content");


        return passageContentBox;
    }

    /**
     * Creates a container for the title of the story.
     *
     * @return a container for the title of the story as a HBox
     */
    private HBox buildTitle() {

        //Title text
        Text storyTitle = new Text(currentPassage.getTitle());

        //Title container
        HBox titleBox = new HBox();

        //StackPane
        StackPane topStackPane = new StackPane();

        //Image
        ImageView image = new ImageView("/images/UI_Flat_Banner_01_Upward.png");

        //Set image size
        image.setFitWidth(storyTitle.getLayoutBounds().getWidth() + 400);
        image.setPreserveRatio(true);

        //Add elements to StackPane
        topStackPane.getChildren().addAll(image, storyTitle);

        //Add elements to titleBox
        titleBox.getChildren().add(topStackPane);

        //Set StackPane alignment
        //StackPane.setAlignment(storyTitle, javafx.geometry.Pos.CENTER);
        //StackPane.setAlignment(image, javafx.geometry.Pos.CENTER);

        //Set title box alignment
        titleBox.setAlignment(javafx.geometry.Pos.CENTER);

        //Styling
        storyTitle.getStyleClass().add("title");

        return titleBox;
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


    private HBox createOptionsTab() {

        HBox optionsBox = new HBox(10);
        Button saveButton = new Button();
        ImageView saveImage = new ImageView("/images/icons/Save.png");

        saveImage.setPreserveRatio(true);
        saveImage.setFitHeight(32);
        saveImage.setFitWidth(32);
        saveButton.setGraphic(saveImage);


        Button optionsButton = new Button();
        ImageView optionsImage = new ImageView("/images/icons/Gear.png");
        optionsImage.setPreserveRatio(true);
        optionsImage.setFitHeight(32);
        optionsImage.setFitWidth(32);
        optionsButton.setGraphic(optionsImage);



        Button helpButton = new Button();
        ImageView helpImage = new ImageView("/images/icons/Info.png");
        helpImage.setPreserveRatio(true);
        helpImage.setFitHeight(32);
        helpImage.setFitWidth(32);
        helpButton.setGraphic(helpImage);
        optionsBox.getChildren().addAll(saveButton, optionsButton, helpButton);


        optionsBox.setSpacing(10);
        optionsBox.setAlignment(Pos.CENTER_RIGHT);
        optionsBox.setMaxWidth(25);
        optionsBox.setMaxHeight(25);
        optionsBox.setAlignment(Pos.CENTER_RIGHT);

        optionsBox.getStylesheets().add("css/global.css");
        optionsBox.getStyleClass().add("optionButton");

        return optionsBox;

    }

    private StackPane buildHotbarBox() {

        //Outer hotbar box
        VBox outerHotbarBox = new VBox(20);

        //Hotbar
        HBox innerHotbarBox = new HBox(20);

        //StackPane
        StackPane hotbarStackPane = new StackPane();

        //ImageView
        ImageView hotbarImageView = new ImageView("/images/UI/hotbar/HotbarBackground.png");
        hotbarImageView.setPreserveRatio(true);
        hotbarImageView.setFitWidth(512);

        //Player stats
        Text playerGold = new Text();
        Text playerHealth = new Text();
        Text playerScore = new Text();
        Text playerName = new Text();

        //Player stats values
        playerGold.setText(player.getGold() + "G");
        playerHealth.setText(player.getHealth() + "HP");
        playerScore.setText("SCORE:" + player.getScore());
        playerName.setText(player.getName());


        //HBox for player stats
        HBox playerGoldBox = new HBox(5);
        HBox playerHealthBox = new HBox(5);
        HBox playerScoreBox = new HBox(5);
        HBox playerNameBox = new HBox();

        playerNameBox.getChildren().add(playerName);


        Image healthImage = new Image("images/UI/hotbar/Heart.png", 32, 32, false, false);
        ImageView healthImageView = new ImageView(healthImage);
        healthImageView.setFitWidth(32);
        healthImageView.setFitHeight(32);

        Image goldImage = new Image("images/UI/hotbar/Coin.png", 32, 32, false, false);
        ImageView goldImageView = new ImageView(goldImage);
        goldImageView.setFitWidth(32);
        goldImageView.setFitHeight(32);

        Image scoreImage = new Image("images/UI/hotbar/Score.png", 32, 32, false, false);
        ImageView scoreImageView = new ImageView(scoreImage);
        scoreImageView.setFitWidth(32);
        scoreImageView.setFitHeight(32);

        playerHealthBox.getChildren().addAll(healthImageView, playerHealth);
        playerHealthBox.setAlignment(Pos.CENTER);
        playerGoldBox.getChildren().addAll(goldImageView, playerGold);
        playerGoldBox.setAlignment(Pos.CENTER);

        playerScoreBox.getChildren().addAll(scoreImageView, playerScore);
        playerScoreBox.setAlignment(Pos.CENTER);

        innerHotbarBox.getChildren().addAll(playerHealthBox, playerGoldBox, playerScoreBox);
        outerHotbarBox.getChildren().addAll(playerNameBox, innerHotbarBox);
        hotbarStackPane.getChildren().addAll(hotbarImageView, outerHotbarBox);

        innerHotbarBox.setAlignment(Pos.CENTER);
        outerHotbarBox.setAlignment(Pos.CENTER);
        playerNameBox.setAlignment(Pos.CENTER);


        //Styling
        hotbarStackPane.getStyleClass().add("UI-text");

        return hotbarStackPane;

    }




}


