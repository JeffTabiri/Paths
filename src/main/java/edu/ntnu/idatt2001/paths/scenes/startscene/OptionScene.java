package edu.ntnu.idatt2001.paths.scenes.startscene;

import edu.ntnu.idatt2001.paths.scenes.startscene.StartScene;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class OptionScene {
    public Scene getScene(Stage stage, double prevWidth, double prevHeight) {

        /*#######################
        # Stage size declaration #
        #######################*/
        stage.setWidth(prevWidth);
        stage.setHeight(prevHeight);

        //Title for the screen
        Text text = new Text("Paths");


        Button goBackButton = new Button("Return");

        goBackButton.setOnAction(e -> {
            double currentWidth = stage.getWidth();
            double currentHeight = stage.getHeight();

            stage.setScene(new StartScene().getScene(stage, currentWidth, currentHeight));
        });

        BorderPane root = new BorderPane();

        root.setTop(text);
        VBox menu = new VBox();
        menu.getChildren().addAll(goBackButton);

        return new Scene(root);
    }

}
