package com.example.database.uchet;

import com.example.database.Loader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;


public class UchetController {

    @FXML
    private Button button1;

    @FXML
    private Button button4;

    @FXML
    private Button profile;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView image;


    @FXML
    void initialize() {
        button1.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/prib-screen.fxml", "Прибыль");
        });

        button4.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/otchet-screen.fxml", "Сводный отчет по прибыли");
        });

        profile.setOnAction(e -> {
            new Loader().openNewScene(rootPane, "/com/example/database/AdminProfile-screen.fxml", "Профиль");
        });
    }
}

