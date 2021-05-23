import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage)   {

        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource(  "test.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage.setTitle("Hello World");

        Scene testScene = new Scene(root, 300, 275);


        primaryStage.setScene(testScene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}