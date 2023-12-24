package com.example.database.uchet;

import com.example.database.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class RachetController {

    @FXML
    private Button vozvrat;
    @FXML
    private AnchorPane rootPane;

    @FXML
    void initialize() {
        vozvrat.setOnAction(event -> {
            new Loader().openNewScene(rootPane, "/com/example/database/uchet-screen.fxml", "Учет деятельности салона красоты");
        });

    }
}
