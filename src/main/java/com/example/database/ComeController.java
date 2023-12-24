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
    private Button btnR;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button btnRegistration;

    @FXML
    private Button btnGr;


    @FXML
    void initialize() {
        btnReg.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/registration-screen.fxml", "Регистрация");
        });

        btnAuth.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/auth-screen.fxml", "Авторизация");
        });

        btnRegistration.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/registration-for-employees.fxml", "Услуги");
        });

        btnR.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/raspol-screen.fxml", "Местоположение салона");
        });
        btnGr.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/grafic-screen.fxml", "График работы");
        });

    }

}
