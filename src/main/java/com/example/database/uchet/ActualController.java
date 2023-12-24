package com.example.database.uchet;
import com.example.database.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ActualController {

    @FXML
    private Button btn;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void initialize() {
        btn.setOnAction(event -> {
            new Loader().openNewScene(rootPane, "/com/example/database/akcia-screen.fxml", "Акции");
        });

    }
}
