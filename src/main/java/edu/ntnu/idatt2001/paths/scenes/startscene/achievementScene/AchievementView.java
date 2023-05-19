package edu.ntnu.idatt2001.paths.scenes.startscene.achievementScene;

import edu.ntnu.idatt2001.paths.Achievement;
import edu.ntnu.idatt2001.paths.AchievementList;
import edu.ntnu.idatt2001.paths.goals.GoldGoal;
import edu.ntnu.idatt2001.paths.goals.HealthGoal;
import edu.ntnu.idatt2001.paths.goals.InventoryGoal;
import edu.ntnu.idatt2001.paths.goals.ScoreGoal;
import edu.ntnu.idatt2001.paths.utility.ButtonEffects;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.List;

public class AchievementView {

    StackPane view = new StackPane();

    ListView<HBox> achievementListView = new ListView<>();

    AchievementModel model;

    AchievementController controller;

    AchievementList achievementList = AchievementList.getInstance();


    public AchievementView (AchievementController controller, AchievementModel model) {
        buildView();

        this.controller = controller;
        this.model = model;
    }

    public void buildView() {

        BorderPane root = new BorderPane();

        root.setTop(buildTitle());
        root.setCenter(buildAchievementList());
        root.setBottom(buildBottomMenu());

        view.getChildren().addAll(buildPane(), root);


        /*#######################
        # CSS Styling           #
        #######################*/

        view.getStylesheets().add("css/global.css");

    }

    public Parent asParent() {
        return view;
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


        goBackButton.setOnAction(e -> {
            controller.goBackHandler();
        });


        goBackButton.setOnMouseEntered(e -> ButtonEffects.buttonHover(goBackButton));
        goBackButton.setOnMouseExited(e -> ButtonEffects.buttonExit(goBackButton));
        addAchievements.setOnMouseEntered(e -> ButtonEffects.buttonHover(addAchievements));
        addAchievements.setOnMouseExited(e -> ButtonEffects.buttonExit(addAchievements));

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

        //Styling
        goBackButton.getStyleClass().add("menu-button");
        addAchievements.getStyleClass().add("menu-button");

        return bottom;
    }

    private void updateAchievementList() {
        List<Achievement> achievements = achievementList.getAchievements();
        for (Achievement achievement : achievements) {
            achievementList.getItems().add(achievement);
        }
    }


    private HBox buildTitle() {

        //Title text
        Text gameTitle = new Text("Achievements");

        //Title container
        HBox titleBox = new HBox();

        //Image
        ImageView image = new ImageView("/images/UI/title/UI_Flat_Banner_01_Upward.png");

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

        //Animation
        TranslateTransition translateTransition = new TranslateTransition();
        translateTransition.setDuration(javafx.util.Duration.seconds(1.5));
        translateTransition.setNode(titleBox);
        translateTransition.setFromY(-200);
        translateTransition.setToY(0);
        translateTransition.play();

        translateTransition.setOnFinished(event -> {
            TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(1.5));
            translateTransition2.setNode(titleBox);
            translateTransition2.setFromY(0);
            translateTransition2.setToY(-20);
            translateTransition2.autoReverseProperty().setValue(true);
            translateTransition2.setCycleCount(Animation.INDEFINITE);
            translateTransition2.play();
        });

        return titleBox;
    }

    private ListView<HBox> buildAchievementList() {

        achievementListView = new ListView<>();

        achievementList.getAchievements().forEach(achievement -> {
            HBox hBox = new HBox();
            hBox.getChildren().add(achievement);
            achievementListView.getItems().add(hBox);
        });

        achievementListView.paddingProperty().setValue(new Insets(10, 10, 10, 10));

        BorderPane.setMargin(achievementListView, new Insets(20, 20, 20, 20));

        return achievementListView;
    }

    private HBox buildAchievementBox(Achievement achievement) {

        Text achievementTitle = new Text(achievement.getTitle());

        Text achievementDescription = new Text(achievement.getDescription());

        Text achievementProgress = new Text(achievement.getIsFulfilled().toString());

        HBox achievementBox = new HBox();

        VBox achievementTextContainer = new VBox();

        achievementTextContainer.getChildren().addAll(achievementTitle, achievementDescription, achievementProgress);

        achievementTextContainer.setSpacing(10);

        Image achievementImage = new Image("images/cursor/cursor_grab.png", 64, 64, true, true);

        ImageView achievementImageView = new ImageView(achievementImage);
        achievementBox.getChildren().addAll(achievementImageView, achievementTextContainer);
        achievementBox.setSpacing(20);

        return achievementBox;
    }

    private String achievementGoalCheck(Achievement achievement) {

            if (achievement.getGoal() instanceof HealthGoal) {
                return "HEALTH";
            }

            if (achievement.getGoal() instanceof InventoryGoal) {
                return "INVENTORY";
            }

            if (achievement.getGoal() instanceof GoldGoal) {
                return "GOLD";
            }

            if (achievement.getGoal() instanceof ScoreGoal) {
                return "SCORE";
            }

            return null;
    }

    private String achievemntImageChoose(String goalType) {

        switch (goalType) {
            case "HEALTH":
                return "images/achievements/health.png";
            case "INVENTORY":
                return "images/achievements/inventory.png";
            case "GOLD":
                return "images/icon/achievementIcons/Coin.png";
            case "SCORE":
                return "images/achievements/score.png";
            default:
                return null;
        }
    }



    private void addAchievement(Achievement achievement) {
        controller.addAchievementHandler(achievement);
    }

    private Pane buildPane() {

        Pane pane = new Pane();

        ImageView imageView = new ImageView("/images/background/MainMenuBackground.png");
        imageView.fitWidthProperty().bind(pane.widthProperty());
        imageView.setPreserveRatio(true);

        pane.getChildren().add(imageView);

        return pane;
    }

}




