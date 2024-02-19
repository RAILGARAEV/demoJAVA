package com.example.database;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import com.example.database.DbFunctions.DbFunctions;
import com.example.database.DbFunctions.Request;
import com.example.database.Models.Singleton;

import com.example.database.Models.StageModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

public class ProfileController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button requests;

    @FXML
    private ImageView ImgBack;

    @FXML
    private ImageView imgUser;

    @FXML
    private Label labelFirstName;

    @FXML
    private Label labelLastName;

    @FXML
    private Label labelPassport;

    @FXML
    private Label labelPhone;

    @FXML
    private Label labelSecondName;

    @FXML
    private AnchorPane rootPane;

    private DbFunctions dbFunctions = new DbFunctions();

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

//        requests.setOnAction(event -> {
//            openUserRequestsScene();
//        });
//    }
//
//    private void openUserRequestsScene() {
//        String username = Singleton.getInstance().getLogin();
//        List<Request> userRequests = dbFunctions.getUserRequests(username);
//
//        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/database/TicketProfile-check-screen.fxml"));
//        try {
//            AnchorPane anchorPane = loader.load();
//            rootPane.getChildren().setAll(anchorPane);
//            StageModel.getMyStage().setTitle("Заявки");
//
//            TicketProfileCheckActivity controller = loader.getController();
//            controller.setUserRequests(userRequests);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


}