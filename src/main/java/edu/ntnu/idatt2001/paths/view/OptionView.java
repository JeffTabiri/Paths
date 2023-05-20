package edu.ntnu.idatt2001.paths.view;

import edu.ntnu.idatt2001.paths.controller.OptionController;
import edu.ntnu.idatt2001.paths.model.OptionManager;
import edu.ntnu.idatt2001.paths.utility.ButtonEffects;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import org.controlsfx.control.ToggleSwitch;

public class OptionView {

  BorderPane root = new BorderPane();
  ToggleSwitch darkModeButton = new ToggleSwitch();
  ToggleSwitch soundButton = new ToggleSwitch();
  ToggleSwitch fullscreenButton = new ToggleSwitch();

  OptionController controller;

  OptionManager optionManager = OptionManager.getInstance();


  public OptionView(OptionController controller) {
    this.controller = controller;
    buildView();
  }


  public void buildView() {
    root.setTop(buildTitle());
    root.setCenter(buildMenu());
    root.setBottom(buildBottomMenu());
    root.getStylesheets().add(optionManager.getCurrentStyle());
  }

  public Parent asParent() {
    return root;
  }


    private HBox buildTitle() {

      //Title container
      HBox titleContainer = new HBox();

      titleContainer.setPadding(new Insets(10, 10, 10, 10));

      //Title
      Label title = new Label("Options");

      titleContainer.getChildren().add(title);

      HBox.setHgrow(title, Priority.ALWAYS);

      titleContainer.setAlignment(Pos.CENTER);

      //Title styling
      title.getStyleClass().add("title");

      return titleContainer;
    }

    private VBox buildMenu() {

      VBox menuContainer = new VBox();


      //Dark mode container
      HBox rightDarkModeContainer = new HBox();
      HBox leftDarkModeContainer = new HBox();
      HBox darkModeContainer = new HBox();


      darkModeButton = new ToggleSwitch();
      darkModeButton.setSelected(optionManager.getDarkMode());

      TextFlow darkModeText = new TextFlow(new Text("Dark Mode "));


      rightDarkModeContainer.getChildren().add(darkModeButton);
      leftDarkModeContainer.getChildren().add(darkModeText);
      darkModeContainer.getChildren().addAll(leftDarkModeContainer, rightDarkModeContainer);

      HBox.setHgrow(leftDarkModeContainer, Priority.ALWAYS);
      HBox.setHgrow(rightDarkModeContainer, Priority.ALWAYS);

      rightDarkModeContainer.setAlignment(Pos.CENTER_RIGHT);
      leftDarkModeContainer.setAlignment(Pos.CENTER_LEFT);

      //Sound container
      HBox rightSoundContainer = new HBox();
      HBox leftSoundContainer = new HBox();
      HBox soundContainer = new HBox();

      soundButton = new ToggleSwitch();
      soundButton.setSelected(optionManager.getSound());


      TextFlow soundText = new TextFlow(new Text("Mute: "));
      rightSoundContainer.getChildren().add(soundButton);
      leftSoundContainer.getChildren().add(soundText);
      soundContainer.getChildren().addAll(leftSoundContainer, rightSoundContainer);

      HBox.setHgrow(leftSoundContainer, Priority.ALWAYS);
      HBox.setHgrow(rightSoundContainer, Priority.ALWAYS);

      rightSoundContainer.setAlignment(Pos.CENTER_RIGHT);
      leftSoundContainer.setAlignment(Pos.CENTER_LEFT);


      //Fullscreen container
      HBox rightFullscreenContainer = new HBox();
      HBox leftFullscreenContainer = new HBox();
      HBox fullscreenContainer = new HBox();

      fullscreenButton = new ToggleSwitch();
      fullscreenButton.setSelected(optionManager.getFullscreen());


      TextFlow fullscreenText = new TextFlow(new Text("Fullscreen:"));
      rightFullscreenContainer.getChildren().add(fullscreenButton);
      leftFullscreenContainer.getChildren().add(fullscreenText);
      fullscreenContainer.getChildren().addAll(leftFullscreenContainer, rightFullscreenContainer);


      HBox.setHgrow(leftFullscreenContainer, Priority.ALWAYS);
      HBox.setHgrow(rightFullscreenContainer, Priority.ALWAYS);
      rightFullscreenContainer.setAlignment(Pos.CENTER_RIGHT);
      leftFullscreenContainer.setAlignment(Pos.CENTER_LEFT);

      menuContainer.getChildren().addAll(darkModeContainer, soundContainer, fullscreenContainer);


      //Menu styling
      menuContainer.getStyleClass().add("menu");

      return menuContainer;
    }

    private HBox buildBottomMenu() {

      //Bottom menubar container
      HBox leftBox = new HBox();
      HBox rightBox = new HBox();
      HBox bottom = new HBox();

      //Buttons
      Button returnButton = new Button("Return");
      Button saveButton = new Button("Save");


      //Padding
      leftBox.setPadding(new Insets(20, 0, 20, 20));
      rightBox.setPadding(new Insets(20, 20, 20, 0));

      returnButton.setOnMouseEntered(event -> ButtonEffects.buttonHover(returnButton));
      saveButton.setOnMouseEntered(event -> ButtonEffects.buttonHover(saveButton));

      returnButton.setOnMouseExited(event -> ButtonEffects.buttonExit(returnButton));
      saveButton.setOnMouseExited(event -> ButtonEffects.buttonExit(saveButton));


      //Button placement
      leftBox.getChildren().add(returnButton);
      rightBox.getChildren().add(saveButton);
      bottom.getChildren().addAll(leftBox, rightBox);

      //Node alignment
      leftBox.setAlignment(Pos.CENTER_LEFT);
      rightBox.setAlignment(Pos.CENTER_RIGHT);

      //Node padding
      HBox.setHgrow(leftBox, Priority.ALWAYS);
      HBox.setHgrow(rightBox, Priority.ALWAYS);

      saveButton.setOnAction(event -> {
        controller.saveSettings(darkModeButton.isSelected(), soundButton.isSelected(), fullscreenButton.isSelected());
        Stage stage = (Stage) saveButton.getScene().getWindow();
        stage.close();
      });

      returnButton.setOnAction(event -> {
        controller.returnHandler();
      });

      //Styling
      returnButton.getStyleClass().add("secondary-button");
      saveButton.getStyleClass().add("secondary-button");

      return bottom;
    }


  }

