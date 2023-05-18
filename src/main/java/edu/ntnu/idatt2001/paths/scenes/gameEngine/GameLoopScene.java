package edu.ntnu.idatt2001.paths.scenes.gameEngine;

import edu.ntnu.idatt2001.paths.Game;
import edu.ntnu.idatt2001.paths.Passage;
import edu.ntnu.idatt2001.paths.Story;
import edu.ntnu.idatt2001.paths.filehandling.StoryLoader;
import edu.ntnu.idatt2001.paths.playerBuilder.Player;
import edu.ntnu.idatt2001.paths.playerBuilder.PlayerBuilder;
import edu.ntnu.idatt2001.paths.scenes.startscene.OptionScene;
import edu.ntnu.idatt2001.paths.utility.AlertUtility;
import edu.ntnu.idatt2001.paths.utility.AudioEngine;
import edu.ntnu.idatt2001.paths.utility.ButtonEffects;
import edu.ntnu.idatt2001.paths.utility.DialogUtility;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
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


        VBox ground = new VBox();
        ground.getChildren().addAll(buildPassageChoices(root));


        //Choices placement
        root.setTop(buildTopMenu());
        root.setBottom(ground);
        root.setCenter(buildPassageContent());

        //Styling
        root.getStylesheets().add("css/global.css");
        return scene;
    }

    private ImageView buildImage(String path) {
        Image image = new Image(path);
        ImageView imageView;
        imageView = new ImageView(image);
        return imageView;
    }


    /**
     * Creates a list of choices for the player to choose from.
     *
     * @return a list of choices from the current passage in a ListView
     */
    private ListView buildPassageChoices(BorderPane root) {

        ListView<String> passageChoice = new ListView<>();

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

            VBox ground = new VBox();
            ground.getChildren().addAll(buildPassageChoices(root));

            //Choices placement
            root.setTop(buildTopMenu());
            root.setBottom(ground);
            root.setCenter(buildPassageContent());
        });

        return passageChoice;
    }


    /**
     * Creates a container for the content of the passage.
     *
     * @return a container for the content of the current passage
     */
    private VBox buildPassageContent() {


        //Build passage content
        TextFlow passageContent = new TextFlow(new Text(currentPassage.getContent()));

        passageContent.setPadding(new Insets(20,20,20,20));

        //Image container
        ImageView image = buildImage(currentPassage.getUrl());
        image.setPreserveRatio(true);
        image.setFitWidth(600);
        image.maxWidth(600);


        stage.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal.doubleValue() < 600) {
                image.setFitWidth((double) newVal / 2);
            } else {
                image.setFitWidth(600);
            }
        });


        /*
        Create code which scales the image to fit so the passage choice list is not pushed down
         */



        //Container for text and image
        VBox verticalBox = new VBox();

        verticalBox.getChildren().addAll(passageContent, image);


        verticalBox.setAlignment(Pos.CENTER);
        passageContent.setTextAlignment(TextAlignment.CENTER);

        VBox.setVgrow(passageContent, Priority.ALWAYS);
        verticalBox.setPadding(new Insets(20,20,20,20));

        //Styling
        verticalBox.getStyleClass().add("game-content");

        return verticalBox;
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
        ImageView image = new ImageView("/images/UI/title/UI_Flat_Banner_01_Upward.png");

        //Set image size
        image.setFitWidth(storyTitle.getLayoutBounds().getWidth() + 300);
        image.setPreserveRatio(true);

        //Add elements to StackPane
        topStackPane.getChildren().addAll(image, storyTitle);

        //Add elements to titleBox
        titleBox.getChildren().add(topStackPane);

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

        //Options container
        HBox optionsBox = new HBox(10);

        //Stylinaaa
        String optionBoxStyle = "option-button";

        //Save button
        Button saveButton = new Button();
        ImageView saveImage = new ImageView("/images/icon/Save.png");
        saveImage.setPreserveRatio(true);
        saveImage.setFitHeight(32);
        saveImage.setFitWidth(32);
        saveButton.setGraphic(saveImage);
        saveButton.backgroundProperty().set(Background.EMPTY);


        //Options button
        Button optionsButton = new Button();
        ImageView optionsImage = new ImageView("/images/icon/Gear.png");
        optionsImage.setPreserveRatio(true);
        optionsImage.setFitHeight(32);
        optionsImage.setFitWidth(32);
        optionsButton.setGraphic(optionsImage);
        optionsButton.backgroundProperty().set(Background.EMPTY);


        //Help button
        Button helpButton = new Button();
        ImageView helpImage = new ImageView("/images/icon/Info.png");
        helpImage.setPreserveRatio(true);
        helpImage.setFitHeight(32);
        helpImage.setFitWidth(32);
        helpButton.setGraphic(helpImage);
        helpButton.backgroundProperty().set(Background.EMPTY);

        //Exit button
        Button exitButton = new Button();
        ImageView exitImage = new ImageView("/images/icon/Exit.png");
        exitImage.setPreserveRatio(true);
        exitImage.setFitHeight(32);
        exitImage.setFitWidth(32);
        exitButton.setGraphic(exitImage);
        exitButton.backgroundProperty().set(Background.EMPTY);

        optionsBox.getChildren().addAll(saveButton, optionsButton, helpButton, exitButton);
        optionsBox.setSpacing(10);
        optionsBox.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));


        //Button effects
        saveButton.setOnMouseEntered(event -> ButtonEffects.buttonHover(saveButton));
        saveButton.setOnMouseExited(event -> ButtonEffects.buttonExit(saveButton));

        helpButton.setOnMouseEntered(event -> ButtonEffects.buttonHover(helpButton));
        helpButton.setOnMouseExited(event -> ButtonEffects.buttonExit(helpButton));

        optionsButton.setOnMouseEntered(event -> ButtonEffects.buttonHover(optionsButton));
        optionsButton.setOnMouseExited(event -> ButtonEffects.buttonExit(optionsButton));

        exitButton.setOnMouseEntered(event -> ButtonEffects.buttonHover(exitButton));
        exitButton.setOnMouseExited(event -> ButtonEffects.buttonExit(exitButton));

        saveButton.setOnAction(event -> {
            if (AlertUtility.showSaveAlert(stage)) {
            }
        });


        exitButton.setOnAction(event -> AlertUtility.showConfirmationAlert(stage));

            helpButton.setOnAction(event -> DialogUtility.helpBox(stage));

        optionsButton.setOnAction(event -> {
            Stage dialog = new Stage();
            dialog.initModality(Modality.APPLICATION_MODAL);
            dialog.initOwner(stage);
            dialog.setTitle("Options");
            dialog.setScene(new OptionScene(dialog, 400, 400).getScene());
            dialog.show();
        });



        //Styling
        optionsBox.getStyleClass().add(optionBoxStyle);

        return optionsBox;
    }

    private VBox buildTopMenu() {

        //Bottom menubar container
        HBox leftBox = new HBox();
        HBox rightBox = new HBox();
        HBox bottom = new HBox();
        VBox topBox = new VBox();

        //Padding
        leftBox.setPadding(new Insets(20, 0, 20, 20));
        rightBox.setPadding(new Insets(20, 20, 20, 0));

        //Button placement
        leftBox.getChildren().add(createOptionsTab());
        rightBox.getChildren().add(buildHotbarBox());
        bottom.getChildren().addAll(leftBox, rightBox);
        topBox.getChildren().addAll(bottom, buildTitle());

        //Node alignment
        leftBox.setAlignment(Pos.CENTER_LEFT);
        rightBox.setAlignment(Pos.CENTER_RIGHT);

        //Node padding
        HBox.setHgrow(leftBox, Priority.ALWAYS);
        HBox.setHgrow(rightBox, Priority.ALWAYS);

        return topBox;
    }

    private VBox buildHotbarBox() {

        //Outer hotbar box
        VBox outerHotbarBox = new VBox(20);

        //Hotbar
        HBox innerHotbarBox = new HBox(20);

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
        playerScore.setText("SCORE: " + player.getScore());
        playerName.setText("Name: " + player.getName());

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

        innerHotbarBox.setAlignment(Pos.CENTER);
        outerHotbarBox.setAlignment(Pos.CENTER);
        playerNameBox.setAlignment(Pos.CENTER);

        outerHotbarBox.getStyleClass().add("hotbar");

        BorderPane.setAlignment(outerHotbarBox, Pos.CENTER_RIGHT);

        return outerHotbarBox;

}






}


