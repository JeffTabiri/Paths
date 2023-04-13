package edu.ntnu.idatt2001.paths;
import edu.ntnu.idatt2001.paths.scenes.startscene.StartScene;
import javafx.application.Application;
import javafx.stage.Stage;

public class    App extends Application {

    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        StartScene startScene = new StartScene();

        primaryStage.setMaximized(true);
        double currentHeight = primaryStage.getHeight();
        double currentWidth = primaryStage.getWidth();
        primaryStage.setScene(startScene.getScene(primaryStage, currentWidth, currentHeight));
        primaryStage.setTitle("Paths");
        primaryStage.show();
    }

    @Override
    public void stop() {
        System.out.println("Stage is closing");
    }
}
