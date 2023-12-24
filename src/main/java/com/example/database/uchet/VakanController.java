package com.example.database.uchet;

import com.example.database.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class VakanController {

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private Button btn3;

    @FXML
    private Button btn4;

    @FXML
    private Button btn5;

    @FXML
    private Button nazad;

    @FXML
    private AnchorPane rootPane;

    @FXML
    void initialize() {
        nazad.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/Client-screen.fxml", "Профиль");
        });
        btn1.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/vak1-screen.fxml", "Администратор на ресепшн");
        });
        btn2.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/vak2-screen.fxml", "Косметолог");
        });
        btn3.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/vak3-screen.fxml", "Мастер ногтевого сервиса");
        });
        btn4.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/vak4-screen.fxml", "Стилист-колорист");
        });
        btn5.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/vak5-screen.fxml", "Врач-дерматолог");
        });

    }
}
