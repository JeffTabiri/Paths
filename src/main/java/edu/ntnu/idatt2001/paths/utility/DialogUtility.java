package edu.ntnu.idatt2001.paths.utility;

import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class DialogUtility {



    public static void helpBox() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        VBox root = new VBox();

        TextFlow topTitle = new TextFlow(new Text("Welcome to the game!"));

        topTitle.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));

        TextFlow centerText = new TextFlow (

                new Text(
                        """
                        Traverse through the story by navigating the passage choices.
                        When traversing through these choices you can earn gold coins and score points.
                        If youre health reaches zero you die.
                        
                        """));




        TextFlow bottomText = new TextFlow(new Text("Good Luck!"));

        //Node placement
        root.getChildren().addAll(topTitle, centerText, bottomText);

        //Alignment
        topTitle.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);
        centerText.setTextAlignment(TextAlignment.CENTER);
        bottomText.setTextAlignment(TextAlignment.CENTER);
        centerText.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        dialog.setHeight(300);

        Scene dialogScene = new Scene(root);
        dialog.setScene(dialogScene);
        dialog.show();

        //Styling
        root.getStylesheets().add("css/global.css");
        root.getStyleClass().add("help-content");

    }

}
