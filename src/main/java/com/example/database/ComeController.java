package com.example.database;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;

public class ComeController {

    @FXML
    private Button btnAuth;

    @FXML
    private Button btnReg;

    @FXML
    private AnchorPane rootPane;


    @FXML
    void initialize() {
        btnReg.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/registration-screen.fxml", "Регистрация");
        });

        btnAuth.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/auth-screen.fxml", "Авторизация");
        });

    }

}
