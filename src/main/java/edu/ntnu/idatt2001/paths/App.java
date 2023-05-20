package edu.ntnu.idatt2001.paths;
import edu.ntnu.idatt2001.paths.controller.StartController;
import edu.ntnu.idatt2001.paths.view.StartView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class App extends Application {

    static Stage stage;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {

        stage = primaryStage;



        //Primary Stage Config
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);

        primaryStage.setMinWidth(800);
        primaryStage.setMinHeight(600);


        primaryStage.setTitle("Paths");

        Font font = Font.loadFont(getClass().getResourceAsStream("/fonts/PressStart2P-Regular.ttf"), 20);

        StartController controller = new StartController(primaryStage,
                primaryStage.getWidth(),
                primaryStage.getHeight());


        StartView view = new StartView(controller);

        Scene scene = new Scene(view.asParent());





        primaryStage.setScene(scene);
        primaryStage.show();


    }

    @Override
    public void stop() {
        System.out.println("Stage is closing");
    }

    public static Stage getStage() {
        return stage;
    }
}
