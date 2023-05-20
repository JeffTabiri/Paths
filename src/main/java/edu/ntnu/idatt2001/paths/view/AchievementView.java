package edu.ntnu.idatt2001.paths.view;

import edu.ntnu.idatt2001.paths.model.Achievement;
import edu.ntnu.idatt2001.paths.model.AchievementList;
import edu.ntnu.idatt2001.paths.model.OptionManager;
import edu.ntnu.idatt2001.paths.model.goals.GoldGoal;
import edu.ntnu.idatt2001.paths.model.goals.HealthGoal;
import edu.ntnu.idatt2001.paths.model.goals.InventoryGoal;
import edu.ntnu.idatt2001.paths.model.goals.ScoreGoal;
import edu.ntnu.idatt2001.paths.controller.AchievementController;
import edu.ntnu.idatt2001.paths.utility.AlertUtility;
import edu.ntnu.idatt2001.paths.utility.AudioEngine;
import edu.ntnu.idatt2001.paths.utility.ButtonEffects;
import edu.ntnu.idatt2001.paths.utility.GameStates;
import javafx.animation.Animation;
import javafx.animation.TranslateTransition;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;



public class AchievementView {

    AudioEngine audioEngine = AudioEngine.getInstance();

    OptionManager optionManager = OptionManager.getInstance();

    StackPane view = new StackPane();

    ListView<HBox> achievementListView = new ListView<>();

    AchievementController controller;

    AchievementList achievementList = AchievementList.getInstance();


    public AchievementView (AchievementController controller) {
        buildView();
        audioEngine.playMusic(GameStates.MAIN_MENU);

        this.controller = controller;
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

        view.getStylesheets().add(optionManager.getCurrentStyle());

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

        addAchievements.setOnAction(e -> {
            addAchievement();
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
            achievementListView.getItems().add(buildAchievementBox(achievement));
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

        Image achievementImage = new Image(achievementImageChoose(achievementGoalCheck(achievement)), 64, 64, true, true);

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

    private String achievementImageChoose(String goalType) {

        return switch (goalType) {
            case "HEALTH" -> "images/icons/achievementIcons/RedPotion.png";
            case "INVENTORY" -> "images/icons/achievementIcons/Sword.png";
            case "GOLD" -> "images/icons/achievementIcons/Coin.png";
            case "SCORE" -> "images/icons/achievementIcons/Point.png";
            default -> null;
        };

    }



    private void addAchievement() {
        Stage popupBox = popupBox();
        popupBox.show();

        popupBox.setOnCloseRequest(e -> {
            System.out.println("Popup closed");
            updateAchievementList();
        });

    }

    private Stage popupBox() {

        final Stage dialog = new Stage();

        dialog.setTitle("Add achievement");

        dialog.initModality(Modality.APPLICATION_MODAL);

        dialog.resizableProperty().setValue(false);

        ComboBox<String> goalType = new ComboBox<>();

        goalType.getItems().addAll("Health", "Gold", "Score");

        VBox popupBox = new VBox(20);

        HBox inputBox = new HBox(20);


        TextField sumField = new TextField();

        sumField.setPromptText("Enter a goal");

        Button confirmButton = new Button("Confirm");

        popupBox.getChildren().addAll(goalType);
        inputBox.getChildren().addAll(sumField);

        popupBox.getChildren().addAll(inputBox, confirmButton);

        popupBox.setAlignment(Pos.CENTER);
        inputBox.setAlignment(Pos.CENTER);


        confirmButton.setOnMouseEntered(event -> ButtonEffects.buttonHover(confirmButton));
        confirmButton.setOnMouseExited(event -> ButtonEffects.buttonExit(confirmButton));

        confirmButton.setOnAction(event -> {
            try {
            controller.addAchievementHandler(goalType.getValue(), sumField.getText());
            updateAchievementList();
            dialog.close();
            } catch (IllegalArgumentException e) {
                AlertUtility.showErrorAlert("Invalid input", e.getMessage());

            }
        });

        //Styling
        popupBox.getStylesheets().add("css/global.css");
        confirmButton.getStyleClass().add("secondary-button");
        Scene popupScene = new Scene(popupBox, 300, 200);

        dialog.setScene(popupScene);

        return dialog;
    }

    private void updateAchievementList() {
        achievementListView.getItems().clear();
        achievementList.getAchievements().forEach(achievement -> {
            achievementListView.getItems().add(buildAchievementBox(achievement));
        });
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




