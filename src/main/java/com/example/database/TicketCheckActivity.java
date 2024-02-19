package com.example.database;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.database.DbFunctions.DbFunctions;
import com.example.database.Models.Tickets;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private TableColumn<?, ?> columnComment;

    @FXML
    private TableColumn<?, ?> columnColMaterials;

    @FXML
    private TableColumn<?, ?> columnRemont;

    @FXML
    private TableColumn<?, ?> columnTime;

    @FXML
    private TableColumn<?, ?> columnMaterials;

    @FXML
    private TableColumn<?, ?> columnCost;

    @FXML
    private TableColumn<?, ?> columnPriority;

    private final ObservableList<Tickets> ticketsList = FXCollections.observableArrayList();
    private final FilteredList<Tickets> filteredList = new FilteredList<>(ticketsList);

    @FXML
    private TableView<Tickets> tableView;

    @FXML
    private TextField search;

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
        columnData.setCellValueFactory(new PropertyValueFactory<>("date"));
        columnEquipment.setCellValueFactory(new PropertyValueFactory<>("equipment"));
        columnTip.setCellValueFactory(new PropertyValueFactory<>("tip"));
        columnPriority.setCellValueFactory(new PropertyValueFactory<>("priority"));
        columnIspolnitel.setCellValueFactory(new PropertyValueFactory<>("ispolnitel"));
        columnProblem.setCellValueFactory(new PropertyValueFactory<>("problem"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        columnRemont.setCellValueFactory(new PropertyValueFactory<>("remont"));
        columnTime.setCellValueFactory(new PropertyValueFactory<>("time"));
        columnMaterials.setCellValueFactory(new PropertyValueFactory<>("materials"));
        columnColMaterials.setCellValueFactory(new PropertyValueFactory<>("colMaterials"));
        columnCost.setCellValueFactory(new PropertyValueFactory<>("cost"));
        columnComment.setCellValueFactory(new PropertyValueFactory<>("comment"));
        tableView.setItems(dbFunctions.getAllTickets());


        btnRefresh.setOnAction(event -> {
            tableView.setItems(dbFunctions.getAllTickets());
        });

        tableView.setItems(filteredList);
        ticketsList.addAll(dbFunctions.getAllTickets());

        tableView.setOnMouseClicked(e ->{
            click++;
            if (click % 2 == 0) {
                Tickets tickets = tableView.getSelectionModel().getSelectedItem();
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/database/update-tickets-screen.fxml"));
                    Parent parent = loader.load();
                    UpdateTickets updateTickets = loader.getController();
                    updateTickets.setData(tickets.getDate(), tickets.getEquipment(), tickets.getTip(), tickets.getPriority(), tickets.getIspolnitel(), tickets.getProblem(), tickets.getStatus(), tickets.getRemont(), tickets.getTime(), tickets.getMaterials(), tickets.getColMaterials(), tickets.getCost(), tickets.getComment(), tickets.getId());
                    Stage stage = new Stage();
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(new Scene(parent));
                    stage.setTitle("Редактирование заявки");
                    stage.showAndWait();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        search.setOnKeyReleased(event -> {
            String searchText = search.getText().toLowerCase();
            filteredList.setPredicate(ticket -> {
                String id = ticket.getId().toLowerCase();
                String date = ticket.getDate().toLowerCase();
                String equipment = ticket.getEquipment().toLowerCase();
                String tip = ticket.getTip().toLowerCase();
                String priority = ticket.getPriority().toLowerCase();
                String ispolnitel = ticket.getIspolnitel().toLowerCase();
                String problem = ticket.getProblem().toLowerCase();
                String status = ticket.getStatus().toLowerCase();
                String remont = ticket.getRemont().toLowerCase();
                String time = ticket.getTime().toLowerCase();
                String materials = ticket.getMaterials().toLowerCase();
                String colmaterials = ticket.getColMaterials().toLowerCase();
                String cost = ticket.getCost().toLowerCase();
                String comment = ticket.getComment().toLowerCase();
                return id.contains(searchText) || date.contains(searchText) || equipment.contains(searchText) || tip.contains(searchText) || priority.contains(searchText)
                        || ispolnitel.contains(searchText) || problem.contains(searchText) || status.contains(searchText) || remont.contains(searchText)
                        || time.contains(searchText) || materials.contains(searchText) || colmaterials.contains(searchText) || cost.contains(searchText) || comment.contains(searchText);
            });
        });


    }

}