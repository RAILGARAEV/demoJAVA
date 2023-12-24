package com.example.database.uchet;


import com.example.database.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class Vak2Controller {

    @FXML
    private Button button;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void initialize() {
        button.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/vakan-screen.fxml", "Актуальные вакансии");
        });

    }
}
