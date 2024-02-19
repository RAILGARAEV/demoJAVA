package com.example.database;

import com.example.database.DbFunctions.DbFunctions;
import com.example.database.DbFunctions.Request;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TicketProfileCheckActivity {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> columnData;

    @FXML
    private TableColumn<?, ?> columnEquipment;

    @FXML
    private TableColumn<?, ?> columnTip;

    @FXML
    private TableColumn<?, ?> columnIspolnitel;

    @FXML
    private TableColumn<?, ?> columnProblem;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnStatus;

    @FXML
    private TableColumn<?, ?> columnPriority;

    @FXML
    private TableView<Request> tableView;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private ImageView ImgBack;

    int click = 0;


    @FXML
    private Button btnRefresh;

    private final DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void backScreen(MouseEvent event) {
        new Loader().openNewScene(rootPane, "/com/example/database/Profile-screen.fxml", "Профиль");
    }

    @FXML
    void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnData.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnEquipment.setCellValueFactory(new PropertyValueFactory<>("equipment"));
        columnTip.setCellValueFactory(new PropertyValueFactory<>("tip"));
        columnPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        columnIspolnitel.setCellValueFactory(new PropertyValueFactory<>("ispolnitel"));
        columnProblem.setCellValueFactory(new PropertyValueFactory<>("problem"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
//        tableView.setItems(dbFunctions.getAllRequests());


        btnRefresh.setOnAction(event -> {
//            tableView.setItems(dbFunctions.getAllRequest());
        });

//        tableView.setOnMouseClicked(e -> {
//            click++;
//            if (click % 2 == 0) {
//                Request request = tableView.getSelectionModel().getSelectedItem();
//                try {
//                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/database/update-tickets-screen.fxml"));
//                    Parent parent = loader.load();
//                    UpdateRequests updateRequests = loader.getController();
//                    updateRequests.setData(request.getDate(), request.getEquipment(), request.getTip(), request.getPriority(), request.getIspolnitel(), request.getProblem(), request.getStatus(), request.getId());
//                    Stage stage = new Stage();
//                    stage.initModality(Modality.APPLICATION_MODAL);
//                    stage.initStyle(StageStyle.UNDECORATED);
//                    stage.setScene(new Scene(parent));
//                    stage.setTitle("Редактирование заявки");
//                    stage.showAndWait();
//                } catch (IOException ex) {
//                    throw new RuntimeException(ex);
//                }
//
//            }
//        });


    }

}