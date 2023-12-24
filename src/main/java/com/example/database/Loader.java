package com.example.database;

import com.example.database.DbFunctions.DbFunctions;
import com.example.database.DbFunctions.Variables;
import com.example.database.Models.SceneModel;
import com.example.database.Models.StageModel;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Loader extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        StageModel.setMyStage(stage);
        FXMLLoader fxmlLoader = new FXMLLoader(Loader.class.getResource("come-screen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 390);
        SceneModel.setMyScene(scene);
        stage.setTitle("Салон красоты Ирина");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void stop() {
        new DbFunctions().update_status("Онлайн", Variables.ACTIVE_USER);
    }

    public void openNewScene(AnchorPane root, String window, String title) {
        try {
            AnchorPane anchorPane = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(window)));
            root.getChildren().setAll(anchorPane);
            StageModel.getMyStage().setTitle(title);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


}