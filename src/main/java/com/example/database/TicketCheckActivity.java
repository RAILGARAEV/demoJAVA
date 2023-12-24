package com.example.database;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.database.DbFunctions.DbFunctions;
import com.example.database.Models.Tickets;
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

public class TicketCheckActivity {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> columnData;

    @FXML
    private TableColumn<?, ?> columnFirstName;

    @FXML
    private TableColumn<?, ?> columnId;

    @FXML
    private TableColumn<?, ?> columnLastName;

    @FXML
    private TableColumn<?, ?> columnPhone;

    @FXML
    private TableColumn<?, ?> columnSecondName;

    @FXML
    private TableColumn<?, ?> columnServices;

    @FXML
    private TableColumn<?, ?> columnStatus;

    @FXML
    private TableColumn<?, ?> columnTime;

    @FXML
    private TableView<Tickets> tableView;

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
        new Loader().openNewScene(rootPane, "/com/example/database/AdminProfile-screen.fxml", "Профиль");
    }

    @FXML
    void initialize() {
        columnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        columnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        columnSecondName.setCellValueFactory(new PropertyValueFactory<>("secondName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        columnPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        columnData.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        columnServices.setCellValueFactory(new PropertyValueFactory<>("services"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableView.setItems(dbFunctions.getAllTickets());


        btnRefresh.setOnAction(event -> {
            tableView.setItems(dbFunctions.getAllTickets());
        });

        tableView.setOnMouseClicked(e ->{
            click++;
            if (click % 2 == 0) {
                Tickets tickets = tableView.getSelectionModel().getSelectedItem();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/database/update-tickets-screen.fxml"));
                    Parent parent = loader.load();
                    UpdateTickets updateTickets = loader.getController();
                    updateTickets.setData(tickets.getFirstName(), tickets.getSecondName(), tickets.getLastName(), tickets.getPhone(), tickets.getDate(), tickets.getTime(), tickets.getServices(), tickets.getStatus(), tickets.getId());
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(new Scene(parent));
                    stage.setTitle("Редактирование пользователя");
                    stage.showAndWait();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });


    }

}