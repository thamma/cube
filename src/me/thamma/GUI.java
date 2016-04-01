package me.thamma;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Created by Dominic on 2/17/2016.
 */
public class GUI extends Application {

    public static void main (String... args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(new Pane(new Button("hi"))));
        primaryStage.show();
    }
}
