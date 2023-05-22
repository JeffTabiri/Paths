package edu.ntnu.idatt2001.paths.view;

import edu.ntnu.idatt2001.paths.controller.ChooseStoryController;
import edu.ntnu.idatt2001.paths.controller.OptionController;
import edu.ntnu.idatt2001.paths.model.manager.OptionManager;
import edu.ntnu.idatt2001.paths.utility.ButtonUtility;
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

/**
 * <h1>OptionView</h1>
 * {@code OptionView} is the view class for the option view.
 * This class is responsible for building the option view.
 *
 * @author Elementum
 * @version 1.0
 * @see ChooseStoryController
 * @since 06/04/2023
 */
public class OptionView {
  private final BorderPane root;
  private final ToggleSwitch darkModeButton;

  private final ToggleSwitch fullscreenButton;
  private final OptionController controller;
  private final OptionManager optionManager;

  /**
   * Constructor for the view. Responsible for building the view.
   *
   * @param controller the controller for the view.
   */
  public OptionView(OptionController controller) {

    optionManager = OptionManager.getInstance();
    root = new BorderPane();
    darkModeButton = new ToggleSwitch();

    fullscreenButton = new ToggleSwitch();
    this.controller = controller;

    buildView();
  }


  /**
   * Responsible for building the view.
   */
  private void buildView() {

    root.setTop(buildTitle());
    root.setCenter(buildMenu());
    root.setBottom(buildBottomMenu());
    root.getStylesheets().add(optionManager.getCurrentStyleSheet());

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

    final VBox menuContainer = new VBox();


    /*
     Dark mode container
     */

    final HBox rightDarkModeContainer = new HBox();
    final HBox leftDarkModeContainer = new HBox();
    final HBox darkModeContainer = new HBox();

    darkModeButton.setSelected(optionManager.getIsDarkMode());

    TextFlow darkModeText = new TextFlow(new Text("Dark Mode "));


    rightDarkModeContainer.getChildren().add(darkModeButton);
    leftDarkModeContainer.getChildren().add(darkModeText);
    darkModeContainer.getChildren().addAll(leftDarkModeContainer, rightDarkModeContainer);

    HBox.setHgrow(leftDarkModeContainer, Priority.ALWAYS);
    HBox.setHgrow(rightDarkModeContainer, Priority.ALWAYS);

    rightDarkModeContainer.setAlignment(Pos.CENTER_RIGHT);
    leftDarkModeContainer.setAlignment(Pos.CENTER_LEFT);


    //Fullscreen container
    final HBox rightFullscreenContainer = new HBox();
    final HBox leftFullscreenContainer = new HBox();
    final HBox fullscreenContainer = new HBox();

    fullscreenButton.setSelected(optionManager.getIsFullScreen());


    TextFlow fullscreenText = new TextFlow(new Text("Fullscreen:"));
    rightFullscreenContainer.getChildren().add(fullscreenButton);
    leftFullscreenContainer.getChildren().add(fullscreenText);
    fullscreenContainer.getChildren().addAll(leftFullscreenContainer, rightFullscreenContainer);


    HBox.setHgrow(leftFullscreenContainer, Priority.ALWAYS);
    HBox.setHgrow(rightFullscreenContainer, Priority.ALWAYS);
    rightFullscreenContainer.setAlignment(Pos.CENTER_RIGHT);
    leftFullscreenContainer.setAlignment(Pos.CENTER_LEFT);

    menuContainer.getChildren().addAll(darkModeContainer, fullscreenContainer);


    //Menu styling
    menuContainer.getStyleClass().add("menu");

    return menuContainer;
  }

  /**
   * Builds the bottom menu.
   *
   * @return the bottom menu in a HBox.
   */
  private HBox buildBottomMenu() {

    //Bottom menubar container
    final HBox leftBox = new HBox();
    final HBox rightBox = new HBox();
    final HBox bottom = new HBox();

    //Buttons
    final Button returnButton = new Button("Return");
    final Button saveButton = new Button("Save");

    //Padding
    leftBox.setPadding(new Insets(20, 0, 20, 20));
    rightBox.setPadding(new Insets(20, 20, 20, 0));

    returnButton.setOnMouseEntered(event -> ButtonUtility.buttonHover(returnButton));
    saveButton.setOnMouseEntered(event -> ButtonUtility.buttonHover(saveButton));

    returnButton.setOnMouseExited(event -> ButtonUtility.buttonExit(returnButton));
    saveButton.setOnMouseExited(event -> ButtonUtility.buttonExit(saveButton));

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
      controller.saveSettings(darkModeButton.isSelected(), fullscreenButton.isSelected());
      Stage stage = (Stage) saveButton.getScene().getWindow();
      stage.close();
    });

    returnButton.setOnAction(event -> {
      Stage stage = (Stage) returnButton.getScene().getWindow();
      stage.close();
    });

    //Styling
    returnButton.getStyleClass().add("secondary-button");
    saveButton.getStyleClass().add("secondary-button");

    return bottom;
  }


}

