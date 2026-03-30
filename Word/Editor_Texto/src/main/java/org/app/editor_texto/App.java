package org.app.editor_texto;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("View/MainView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setMaximized(true);
        stage.setTitle("EDITOR DE TEXTO");
        stage.setScene(scene);
        stage.show();
    }

    public void style(Scene scene){

    }

    public static void main(String[] args) {
        Application.launch(App.class, args);
    }

}
