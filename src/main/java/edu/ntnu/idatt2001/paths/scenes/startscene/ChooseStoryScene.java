package edu.ntnu.idatt2001.paths.scenes.startscene;

import edu.ntnu.idatt2001.paths.scenes.gameEngine.GameLoopScene;
import edu.ntnu.idatt2001.paths.utility.AlertUtility;
import edu.ntnu.idatt2001.paths.utility.AudioEngine;
import edu.ntnu.idatt2001.paths.utility.ButtonEffects;
import edu.ntnu.idatt2001.paths.utility.GameStates;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the scene where the user can choose a story to play. Also creates a new character
 *
 */
public class ChooseStoryScene {

    Stage stage;
    double prevWidth;
    double prevHeight;
    ListView<String> storyListView = new ListView<>();

    AudioEngine audioEngine = AudioEngine.getInstance();


  /**
   * A constructor for the choose story scene class
   * Assigns the stage, previous width and previous height
   * Sets the items in the listview to the story list
   *
   * @param stage the window to be displayed
   * @param prevWidth the width of the window before the scene was changed
   * @param prevHeight the height of the window before the scene was changed
   */
    public ChooseStoryScene(Stage stage, double prevWidth, double prevHeight) {
        storyListView.setItems(FXCollections.observableArrayList(getStoryList()));
        this.stage = stage;
        this.prevWidth = prevWidth;
        this.prevHeight = prevHeight;
    }


    /**
     * Returns a scene with the GUI elements for the choose story scene
     *
     * @return the scene with the GUI elements
     */
    public Scene getScene() {



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


        /*#######################
        # Node positioning      #
        #######################*/

        //Node positioning
        root.setBottom(buildBottomMenu(scene));
        root.setTop(buildTitle());
        root.setCenter(storyListView);

        //CSS styling
        root.getStylesheets().add("css/global.css");
        scene.setCursor(new ImageCursor(new javafx.scene.image.Image("images/cursors/cursor_grab.png")));

        return scene;
    }

  /**
   * Builds the bottom menu bar for the choose story scene.
   *
   * @param scene is the scene for the menu bar to be built on
   * @return the bottom menu bar in a HBox
   */
  private HBox buildBottomMenu(Scene scene) {

        //Bottom menubar container
        HBox leftBox = new HBox();
        HBox rightBox = new HBox();
        HBox bottom = new HBox();

        //Buttons
        Button returnButton = new Button("Return");
        Button startGameButton = new Button("Start Game");

        //Button Effects
        ButtonEffects.addCursorImageChange(startGameButton, scene);
        ButtonEffects.addCursorImageChange(returnButton, scene);
        ButtonEffects.addAudioChange(startGameButton);
        ButtonEffects.addAudioChange(returnButton);

        //Padding
        leftBox.setPadding(new Insets(20, 0, 20, 20));
        rightBox.setPadding(new Insets(20, 20, 20, 0));

        //Button actions
        returnButton.setOnAction(event ->
                stage.setScene(new StartScene(stage, stage.getWidth(), stage.getHeight()).getScene()));



        startGameButton.setOnAction(e -> {
            //stage.setScene(new GameLoopScene().getScene(stage, stage.getWidth(), stage.getHeight()));
        });

        startGameButton.setOnAction ( event -> {

          if (storyListView.getSelectionModel().getSelectedItem() == null) {
            AlertUtility.showErrorAlert("Error", "No story selected. Please select a story");
            return;
          }

          popupBox().show();


        });


        //Button placement
        leftBox.getChildren().add(returnButton);
        rightBox.getChildren().add(startGameButton);
        bottom.getChildren().addAll(leftBox, rightBox);

        //Node alignment
        leftBox.setAlignment(Pos.CENTER_LEFT);
        rightBox.setAlignment(Pos.CENTER_RIGHT);

        //Node padding
        HBox.setHgrow(leftBox, Priority.ALWAYS);
        HBox.setHgrow(rightBox, Priority.ALWAYS);

        return bottom;
    }



    private HBox buildTitle() {

        //Title text
        Text gameTitle = new Text("CHOOSE STORY");

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


    /**
     * Gets a list of all the stories in the stories folder
     *
     * @return a list of all the stories in the stories folder as strings
     */
    private List<String> getStoryList() {

        List<String> storyList = new ArrayList<>();

        File[] files = new File("src/main/resources/stories").listFiles();

        for (File file : files) {

            if (file == null) {
                throw new IllegalArgumentException("No stories found");
            }

            if (file.isFile()) {
                storyList.add(file.getName());
            }
        }

        return storyList;
    }


    private Stage popupBox() {
      System.out.println("popupBox() called");

      final Stage dialog = new Stage();
      dialog.initModality(Modality.APPLICATION_MODAL);
      dialog.initOwner(stage);

      VBox popupBox = new VBox(20);
      HBox inputBox = new HBox(20);

      Text popupTitle = new Text("Choose a name");

      TextField nameField = new TextField("");
      nameField.setPromptText("Please enter a name");

      Button confirmButton = new Button("Confirm");

      popupBox.getChildren().addAll(popupTitle);
      inputBox.getChildren().addAll(nameField);

      popupBox.getChildren().addAll(inputBox, confirmButton);

      popupBox.setAlignment(Pos.CENTER);
      inputBox.setAlignment(Pos.CENTER);


      confirmButton.setOnAction(
              event1 -> {
                String storyAddress = "src/main/resources/stories/";

                dialog.close();

                File storyFile = new File(storyAddress + storyListView.getSelectionModel().getSelectedItem());
                String name = nameField.getText();

                try {

                  stage.setScene(new GameLoopScene(storyFile, name, stage, stage.getWidth(), stage.getHeight()).getScene());

                } catch (IOException e) {

                  throw new IllegalArgumentException(e.getMessage());

                }

              });


      Scene popupScene = new Scene(popupBox, 300, 200);

      dialog.setScene(popupScene);

      return dialog;
    }
}
