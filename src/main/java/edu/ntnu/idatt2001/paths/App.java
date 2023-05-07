package edu.ntnu.idatt2001.paths;
import edu.ntnu.idatt2001.paths.scenes.startscene.StartScene;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class    App extends Application {

    private Stage stage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 20);

        double currentHeight = primaryStage.getHeight();
        double currentWidth = primaryStage.getWidth();

        StartScene startScene = new StartScene(primaryStage, currentWidth, currentHeight);
        //primaryStage.getScene().setCursor(new ImageCursor(new javafx.scene.image.Image("images/cursors/cursor_grab.png")));

        primaryStage.setMaximized(true);


        primaryStage.setScene(startScene.getScene());
        primaryStage.setTitle("Paths");
        primaryStage.show();

        primaryStage.getScene().setOnKeyPressed(e -> {
            switch (e.getCode()) {
                case F11 -> primaryStage.setFullScreen(!primaryStage.isFullScreen());
                case ESCAPE -> primaryStage.setFullScreen(false);
            }
        });

    }

    @Override
    public void stop() {
        System.out.println("Stage is closing");
    }
}
