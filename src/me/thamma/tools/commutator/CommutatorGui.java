package me.thamma.tools.commutator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Created by Dominic on 3/24/2016.
 */
public class CommutatorGui extends Application{

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(CommutatorGui.class.getResource("commutatorGui.fxml"));

        Scene scene = new Scene(root);

        scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());

        stage.setTitle("CommutatorHelper");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String... args) {
        Application.launch(args);
    }
}
