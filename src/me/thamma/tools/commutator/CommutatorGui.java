package me.thamma.tools.commutator;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class CommutatorGui extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(CommutatorGui.class.getResource("commutatorGui.fxml"));


        Scene scene = new Scene(root);

//        scene.getStylesheets().add(this.getClass().getResource("style.css").toExternalForm());

        stage.setTitle("CommutatorHelper");
        stage.getIcons().add(
                new Image(
                        CommutatorGui.class.getResourceAsStream("visualcube_icon.png")));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String... args) {
        Application.launch(args);
    }
}
