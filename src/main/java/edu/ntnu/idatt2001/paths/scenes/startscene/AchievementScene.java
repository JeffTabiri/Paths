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
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class AchievementScene {

    AchievementList achievementList = AchievementList.getInstance();
    List<HBox> achievements = new ArrayList<>();

    public AchievementScene() {
    }



    public Scene getScene(Stage stage, double prevWidth, double prevHeight) {


        ListView<HBox> achievementListView = new ListView<>();
        for (Achievement achievement : achievementList.getAchievements()) {
            achievementListView.getItems().add(addAchievement(achievement));
        }



        /*#######################
        # Stage size declaration#
        #######################*/

        stage.setWidth(prevWidth);
        stage.setHeight(prevHeight);

        //Root
        BorderPane root = new BorderPane();

        //Scene
        Scene scene = new Scene(root);

        //Title
        Text achievementsTitle = new Text("Achievements");

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

        goBackButton.setOnAction(e -> {
            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();
            stage.setScene(new StartScene().getScene(stage, currentWidth, currentHeight));
        });


        /*#######################
        # Node alignment        #
        #######################*/

        BorderPane.setAlignment(achievementsTitle, Pos.CENTER);
        leftBox.setAlignment(Pos.CENTER_LEFT);
        rightBox.setAlignment(Pos.CENTER_RIGHT);


        /*#######################
        # Node justifications   #
        #######################*/

        HBox.setHgrow(leftBox, Priority.ALWAYS);
        HBox.setHgrow(rightBox, Priority.ALWAYS);
        bottom.setPadding(new Insets(10));


        /*#######################
        # Node placement        #
        #######################*/

        root.setTop(achievementsTitle);
        root.setCenter(achievementListView);
        root.setBottom(bottom);


        /*#######################
        # CSS Styling           #
        #######################*/

        root.getStylesheets().add("css/ChooseStoryScene.css");
        achievementsTitle.getStyleClass().add("gameTitle");
        scene.setCursor(new ImageCursor(new javafx.scene.image.Image("images/cursors/cursor_grab.png")));
        return scene;
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




