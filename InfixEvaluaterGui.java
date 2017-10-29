package infixevaluatergui;

import java.io.FileNotFoundException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InfixEvaluaterGui extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root;
        Scene scene;
        root  = FXMLLoader.load(getClass().getResource("FXMLDocument.fxml"));
        scene = new Scene(root);
        stage.setScene(scene);
        stage.sizeToScene();
        stage.show();
        stage.resizableProperty();
    }

    public static void main(String[] args) throws FileNotFoundException {
        launch(args);
    }

}
