package com.example.database;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

import com.example.database.DbFunctions.DbFunctions;
import com.example.database.DbFunctions.Variables;
import com.example.database.Models.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainController {
    @FXML
    private Button buttonBack;

    @FXML
    private Button buttonUpdate;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TableColumn<?, ?> tableColumnId;

    @FXML
    private TableColumn<?, ?> tableColumnFirstName;

    @FXML
    private TableColumn<?, ?> tableColumnSecondName;

    @FXML
    private TableColumn<?, ?> tableColumnLastName;

    @FXML
    private TableColumn<?, ?> tableColumnLogin;

    @FXML
    private TableColumn<?, ?> tableColumnPassword;

    @FXML
    private TableColumn<?, ?> tableColumnRole;

    @FXML
    private TableColumn<?, ?> tableColumnStatus;

    @FXML
    private TableView<User> tableView;

    int click = 0;

    private final DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void initialize() {
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableColumnLogin.setCellValueFactory(new PropertyValueFactory<>("login"));
        tableColumnPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
        tableColumnFirstName.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        tableColumnSecondName.setCellValueFactory(new PropertyValueFactory<>("secondname"));
        tableColumnLastName.setCellValueFactory(new PropertyValueFactory<>("lastname"));
        tableColumnRole.setCellValueFactory(new PropertyValueFactory<>("role"));
        tableColumnStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        tableView.setItems(dbFunctions.getAllUsers());

        if (Variables.ROLE_USER.equals("Администратор")) {
            tableView.setOnMouseClicked(e -> {
                click++;
                if (click % 2 == 0) {
                    User user = tableView.getSelectionModel().getSelectedItem();
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/database/update_user-screen.fxml"));
                        Parent parent = loader.load();
                        UpdateUserController updateUserController = loader.getController();
                        updateUserController.setData(user.getLogin(), user.getPassword(), user.getRole(), user.getId());
                        Stage stage = new Stage();
                        stage.initModality(Modality.APPLICATION_MODAL);
                        stage.setScene(new Scene(parent));
                        stage.setTitle("Редактирование пользователя");
                        stage.showAndWait();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
        }
        buttonUpdate.setOnAction(e -> tableView.setItems(dbFunctions.getAllUsers()));

        buttonBack.setOnAction(e -> {
            dbFunctions.update_status("Офлайн", Variables.ACTIVE_USER);
            new Loader().openNewScene(rootPane, "/com/example/database/AdminProfile-screen.fxml", "Профиль");
            Variables.ROLE_USER = "";
            Variables.ACTIVE_USER = "";
        });
    }
}
