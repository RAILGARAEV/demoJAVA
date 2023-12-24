package com.example.database.uchet;
import com.example.database.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class AkciaController {

    @FXML
    private Button act;

    @FXML
    private Button prof;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button stud;

    @FXML
    void initialize() {
        prof.setOnAction(event -> {
            new Loader().openNewScene(rootPane, "/com/example/database/Client-screen.fxml", "Профиль");
        });
        act.setOnAction(event -> {
            new Loader().openNewScene(rootPane, "/com/example/database/actual-screen.fxml", "Актуальные акции");
        });
        stud.setOnAction(event -> {
            new Loader().openNewScene(rootPane, "/com/example/database/stud-screen.fxml", "Акции для студентов");
        });


    }
}


