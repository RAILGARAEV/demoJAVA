package com.example.database;

import com.example.database.DbFunctions.DbFunctions;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

public class UpdateUserController {

    @FXML
    private Label ErrorLabel;

    @FXML
    private TextField pfPassword;

    @FXML
    private CheckBox passwordCheck;

    @FXML
    private Button buttonDelete;

    @FXML
    private Button buttonSave;

    @FXML
    private TextField loginTextField;

    @FXML
    private TextField passwordTextField;

    @FXML
    private ComboBox <String> roleTextField;

    DbFunctions dbFunctions = new DbFunctions();
    String idUser = "";


    @FXML
    void initialize() {
        buttonSave.setOnAction(e -> {

            updateDataUser();

        });
        buttonDelete.setOnAction(e -> deleteDataUser());

        roleTextField.getItems().add("Администратор");
        roleTextField.getItems().add("Клиент");
        roleTextField.getItems().add("Пользователь");

        passwordCheck.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (passwordCheck.isSelected()) {
                    pfPassword.setText(passwordTextField.getText());
                    pfPassword.setVisible(true);
                    passwordTextField.setVisible(false);
                    return;
                }
                passwordTextField.setText(pfPassword.getText());
                passwordTextField.setVisible(true);
                pfPassword.setVisible(false);
            }
        });

    }

    private void deleteDataUser() {
        if (idUser.equals("")) {
            ErrorLabel.setText("Повторите попытку позже");
        } else {
            dbFunctions.deleteDataUser(idUser);
            buttonDelete.getScene().getWindow().hide();

        }

    }

    private void updateDataUser(){

        String login = loginTextField.getText();
        String password = passwordTextField.getText();
        String role = roleTextField.getValue();
        int codeError = dbFunctions.check_login(login);
        if (login.isEmpty()) {
            ErrorLabel.setText("Логин пустой");
        } else if (password.isEmpty()) {
            ErrorLabel.setText("Пароль пустой");
        } else if (idUser.equals("")) {
            ErrorLabel.setText("Повторите попытку позже");
        } else if (codeError == 0) {
            ErrorLabel.setText("Такой логин уже существует");
        } else if (codeError == 404) {
            ErrorLabel.setText("Какая-то ошибка");
        } else {
            dbFunctions.updateDataUser(login, password, role, idUser);
            buttonSave.getScene().getWindow().hide();

        }

    }

    public void setData(String login, String password, String role, String id) {
        loginTextField.setText(login);
        passwordTextField.setText(password);
        roleTextField.setValue(role);
        idUser = id;
    }


}
