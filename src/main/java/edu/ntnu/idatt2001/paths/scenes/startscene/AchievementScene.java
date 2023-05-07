package edu.ntnu.idatt2001.paths.scenes.startscene;

import edu.ntnu.idatt2001.paths.Achievement;
import edu.ntnu.idatt2001.paths.AchievementList;
import edu.ntnu.idatt2001.paths.goals.GoldGoal;
import edu.ntnu.idatt2001.paths.goals.HealthGoal;
import edu.ntnu.idatt2001.paths.goals.InventoryGoal;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AchievementScene {

    Stage stage;
    double prevWidth;
    double prevHeight;
    AchievementList achievementList = AchievementList.getInstance();

    ListView<HBox> achievementListView = new ListView<>();

    /**
     * Constructor for the achievement scene. Creates a new stage and sets the previous width and height.
     */
    public AchievementScene(Stage stage, double prevWidth, double prevHeight) {
        this.stage = stage;
        this.prevWidth = prevWidth;
        this.prevHeight = prevHeight;
    }



    public Scene getScene() {


        /*#######################
        # Stage size declaration#
        #######################*/

        stage.setWidth(prevWidth);
        stage.setHeight(prevHeight);

        //Root
        BorderPane root = new BorderPane();

        //Scene
        Scene scene = new Scene(root);


        /*#######################
        # Node placement        #
        #######################*/

        root.setTop(buildTitle());
        root.setCenter(buildAchievementList());
        root.setBottom(buildBottomMenu());


        /*#######################
        # CSS Styling           #
        #######################*/

        root.getStylesheets().add("css/global.css");
        scene.setCursor(new ImageCursor(new javafx.scene.image.Image("images/cursors/cursor_grab.png")));
        return scene;
    }

    private HBox buildBottomMenu() {
        //Buttons
        Button goBackButton = new Button("Return");
        Button addAchievements = new Button("Add achievement");

        //Bottom menubar container
        HBox leftBox = new HBox();
        HBox rightBox = new HBox();
        HBox bottom = new HBox();

        //Placing nodes in containers
        leftBox.getChildren().add(goBackButton);
        rightBox.getChildren().add(addAchievements);
        bottom.getChildren().addAll(leftBox, rightBox);


        /*#######################
        # Button actions       #
        #######################*/

        goBackButton.setOnAction(e ->
                stage.setScene(new StartScene(stage, prevWidth, prevHeight).getScene()));


        /*#######################
        # Node alignment        #
        #######################*/

        leftBox.setAlignment(Pos.CENTER_LEFT);
        rightBox.setAlignment(Pos.CENTER_RIGHT);


        /*#######################
        # Node justifications   #
        #######################*/

        HBox.setHgrow(leftBox, Priority.ALWAYS);
        HBox.setHgrow(rightBox, Priority.ALWAYS);
        bottom.setPadding(new Insets(10));

        return bottom;
    }


    private HBox buildTitle() {

        //Title text
        Text gameTitle = new Text("Achievements");

        //Title container
        HBox titleBox = new HBox();

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
        titleBox.getChildren().add(topBorderPane);

        //Set StackPane alignment
        StackPane.setAlignment(gameTitle, javafx.geometry.Pos.CENTER);
        StackPane.setAlignment(image, javafx.geometry.Pos.CENTER);

        //Set title box alignment
        titleBox.setAlignment(javafx.geometry.Pos.CENTER);

        //Styling
        gameTitle.getStyleClass().add("title");

        return titleBox;
    }

    private ListView<HBox> buildAchievementList() {
        ListView<HBox> achievementListView = new ListView<>();

        for (Achievement achievement : achievementList.getAchievements()) {
            achievementListView.getItems().add(addAchievement(achievement));
        }

        return achievementListView;
    }



    private HBox addAchievement(Achievement achievement) {
        Text achievementTitle = new Text(achievement.getTitle());
        Text achievementDescription = new Text(achievement.getDescription());
        Text achievementProgress = new Text(achievement.getIsFulfilled().toString());
        HBox achievementBox = new HBox();
        VBox achievementTextContainer = new VBox();
        achievementTextContainer.getChildren().addAll(achievementTitle, achievementDescription, achievementProgress);
        achievementTextContainer.setSpacing(10);

        Image achievementImage = new Image("images/cursors/cursor_grab.png", 64, 64, true, true);
        ImageView achievementImageView = new ImageView(achievementImage);
        achievementBox.getChildren().addAll(achievementImageView, achievementTextContainer);
        achievementBox.setSpacing(20);

        return achievementBox;


    }

}




