package com.example.database.uchet;
import com.example.database.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class studController {

    @FXML
    private Button button;
    @FXML
    private AnchorPane rootPane;

    @FXML
    void initialize() {
        button.setOnAction(event -> {
            new Loader().openNewScene(rootPane, "/com/example/database/akcia-screen.fxml", "Акции");
        });

    }
}
