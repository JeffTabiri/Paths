package edu.ntnu.idatt2001.paths;
import edu.ntnu.idatt2001.paths.scenes.startscene.StartScene;
import edu.ntnu.idatt2001.paths.scenes.startscene.startscene.StartController;
import edu.ntnu.idatt2001.paths.scenes.startscene.startscene.StartModel;
import edu.ntnu.idatt2001.paths.scenes.startscene.startscene.StartView;
import javafx.application.Application;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 20);

        StartModel model = new StartModel(primaryStage);
        StartController controller = new StartController(model);
        StartView view = new StartView(model, controller);
        Scene scene = new Scene(view.asParent(), 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();

        primaryStage.setMinHeight(600);
        primaryStage.setMinWidth(800);
    }

    @Override
    public void stop() {
        System.out.println("Stage is closing");
    }
}
