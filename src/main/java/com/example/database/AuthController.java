package com.example.database;

import com.example.database.DbFunctions.DbFunctions;
import com.example.database.DbFunctions.Variables;
import com.example.database.Models.Singleton;
import com.example.database.Models.StageModel;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class AuthController {
    @FXML
    private Label ErrorText;

    @FXML
    private TextField pfPassword;

    @FXML
    private CheckBox passwordCheck;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button btnAuth;

    @FXML
    private TextField captchaField;

    @FXML
    private Label captchaLabel;

    @FXML
    private TextField textFieldLogin;

    @FXML
    private PasswordField textFieldPassword;

    private final DbFunctions dbFunctions = new DbFunctions();

    @FXML
    void nazad() {
        new Loader().openNewScene(rootPane, "/com/example/database/come-screen.fxml", "Единый сервисный центр");
    }

    @FXML
    void initialize() {
        btnAuth.setOnAction(e -> validation());

        passwordCheck.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (passwordCheck.isSelected()) {
                    pfPassword.setText(textFieldPassword.getText());
                    pfPassword.setVisible(true);
                    textFieldPassword.setVisible(false);
                    return;
                }
                textFieldPassword.setText(pfPassword.getText());
                textFieldPassword.setVisible(true);
                pfPassword.setVisible(false);
            }
        });
    }
    @FXML

    private void validation() {

        String login = textFieldLogin.getText();
        String password= textFieldPassword.getText();
        int codeError = dbFunctions.signIn(login, password);
        if (login.isEmpty() || password.isEmpty()) {
            ErrorText.setText("Какое-то поле не заполнено");
            captcha();
        } else if(codeError == 0) {
            ErrorText.setText("Не найден аккаунт");
            captcha();
        } else if(codeError == 404) {
            ErrorText.setText("Какая-то ошибка");
        } else if (!captchaField.getText().equals(captchaLabel.getText()))
        {
            ErrorText.setText("Неправильная капча");

            captcha();
        }
        else {
            } if (Variables.ROLE_USER.equals("Исполнитель")) {
                Singleton.getInstance().setLogin(textFieldLogin.getText());
                ErrorText.setText("");
                new Loader().openNewScene(rootPane, "/com/example/database/Profile-screen.fxml", "Профиль");
                dbFunctions.update_status("Онлайн", login);


            } else if (Variables.ROLE_USER.equals("Сотрудник")) {
                Singleton.getInstance().setLogin(textFieldLogin.getText());
                ErrorText.setText("");
                new Loader().openNewScene(rootPane, "/com/example/database/AdminProfile-screen.fxml", "Профиль");
                dbFunctions.update_status("Онлайн", login);

            }
        }

    private void captcha() {
        captchaField.setVisible(true);
        captchaLabel.setVisible(true);



        captchaLabel.setText(generateCaptcha());


    }


    private String generateCaptcha() {
        int[] num = new int[1];
        int a = (int) Math.round(Math.random()*9);
        String[] spec = new String[] {
                "!", "@", "#", "$"
        };
        String randSpec = String.valueOf((getRandomElement(spec)));
        StringBuilder captcha = new StringBuilder();
        Random r = new Random();
        char z = (char)(r.nextInt(26)+'a');
        char x = (char)(r.nextInt(26)+'a');
        String c = String.valueOf((getRandomElement(spec)));

        String[] word = new String[]{
                String.valueOf(a), String.valueOf(z), String.valueOf(x), String.valueOf(c)
        };

        Random random = new Random();
        for (int i = 0; i < word.length - 1; i++) {
            int index = random.nextInt(i+1, word.length);
            String temp = String.valueOf(word[i]);
            word[i] = word[index];
            word[index] = String.valueOf(temp);
        }

        for (int i = 0; i < word.length; i++) {
            captcha.append(word[i]);
            System.out.print(word[i]);
        }
        return String.valueOf(captcha);
    }

    private static String getRandomElement(String[] spec) {
        return spec[ThreadLocalRandom.current().nextInt(spec.length)];
    }

}
