package com.example.database.uchet;
import com.example.database.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

public class GraficController {
    @FXML
    private Button vozvrat;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void initialize() {
        vozvrat.setOnAction(event -> {
            new Loader().openNewScene(rootPane, "/com/example/database/come-screen.fxml", "Салон красоты Ирина");
        });
    }
}


