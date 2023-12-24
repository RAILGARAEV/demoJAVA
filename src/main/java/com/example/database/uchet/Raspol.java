package com.example.database.uchet;

import com.example.database.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Raspol {

    @FXML
    private Button kop;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void initialize() {
        kop.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/come-screen.fxml", "Салон красоты Ирина");
        });

    }
}
