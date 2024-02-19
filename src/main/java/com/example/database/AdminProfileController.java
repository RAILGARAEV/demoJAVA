package com.example.database;

import com.example.database.DbFunctions.DbFunctions;
import com.example.database.Models.Singleton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AdminProfileController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ImageView ImgBack;

    @FXML
    private Button zapic;

    @FXML
    private Button equipment;

    @FXML
    private Button material;

    @FXML
    private Button btnstat;

    @FXML
    private ImageView imgUser;

    @FXML
    private Label labelFirstName;

    @FXML
    private Label labelLastName;

    @FXML
    private Label labelPassport;

    @FXML
    private Button btnBid;

    @FXML
    private Label labelPhone;

    @FXML
    private Label labelSecondName;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button btnTck;

    DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void backScreen(MouseEvent event) {
        new Loader().openNewScene(rootPane, "/com/example/database/come-screen.fxml", "Единый сервисный центр");
    }

    @FXML
    void initialize() {
        dbFunctions.setData(Singleton.getInstance().getLogin());
        labelFirstName.setText(Singleton.getInstance().getFirstName());
        labelSecondName.setText(Singleton.getInstance().getSecondName());
        labelLastName.setText(Singleton.getInstance().getLastName());
        labelPassport.setText(Singleton.getInstance().getPassport());
        labelPhone.setText(Singleton.getInstance().getPhone());
        imgUser.setImage(new Image(Singleton.getInstance().getImg()));

        btnBid.setOnAction(event -> {
            new Loader().openNewScene(rootPane, "/com/example/database/main-screen.fxml", "Пользователи");
        });

        btnTck.setOnAction(event -> {
            new Loader().openNewScene(rootPane, "/com/example/database/Ticket-check-screen.fxml", "Заявки");
        });

        zapic.setOnAction(event -> {
            new Loader().openNewScene(rootPane, "/com/example/database/okno-screen.fxml", "Заявка");
        });

        btnstat.setOnAction(event -> {
            new Loader().openNewScene(rootPane, "/com/example/database/Statistika.fxml", "Статистика");
        });

        equipment.setOnAction(event -> {
            new Loader().openNewScene(rootPane, "/com/example/database/equipment.fxml", "Добавить оборудование");
        });

        material.setOnAction(event -> {
            new Loader().openNewScene(rootPane, "/com/example/database/material.fxml", "Добавить материал");
        });

    }
}
