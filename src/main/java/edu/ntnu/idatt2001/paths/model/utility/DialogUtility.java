package edu.ntnu.idatt2001.paths.model.utility;

import edu.ntnu.idatt2001.paths.model.manager.OptionManager;
import edu.ntnu.idatt2001.paths.view.OptionView;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * <h1>DialogUtility</h1>
 * A utility class for creating dialog boxes.
 *
 * @author Elementum
 * @version 1.0
 * @since 06/04/2023
 */
public class DialogUtility {

  private DialogUtility() {
  }

  /**
   * The method {@code optionBox} creates a dialog box with options.
   *
   * @param view the view to be displayed in the dialog box.
   */
  public static void optionBox(OptionView view) {
    Stage dialog = new Stage();
    Scene scene = new Scene(view.asParent(), 500, 300);
    dialog.initModality(Modality.APPLICATION_MODAL);
    dialog.setResizable(false);
    dialog.setScene(scene);
    dialog.setTitle("Options");
    dialog.show();
  }

  /**
   * The method {@code helpBox} creates a dialog box with information about the game.
   */
  public static void helpBox() {
    final OptionManager optionManager = OptionManager.getInstance();
    final Stage dialog = new Stage();
    final TextFlow topTitle = new TextFlow(new Text("Welcome to the game!"));
    final VBox root = new VBox();
    final TextFlow centerText = new TextFlow(

            new Text(
                    """
                            Traverse through the story by navigating the passage choices.
                            When traversing through these choices
                            you can earn gold coins and score points.
                            If your health reaches zero you die and the game is over.      \s
                            """));
    final TextFlow bottomText = new TextFlow(new Text("Good Luck!"));

    /*
    Node configuration
     */
    dialog.initModality(Modality.APPLICATION_MODAL);
    topTitle.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
    centerText.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));


    //Node placement
    root.getChildren().addAll(topTitle, centerText, bottomText);


    //Alignment
    topTitle.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
    centerText.setTextAlignment(TextAlignment.CENTER);
    bottomText.setTextAlignment(TextAlignment.CENTER);


    //Styling
    root.getStylesheets().add(optionManager.getCurrentStyleSheet());
    root.getStyleClass().add("help-content");

    dialog.setScene(new Scene(root, 300, 300));
    dialog.setResizable(false);
    dialog.show();
  }
}
