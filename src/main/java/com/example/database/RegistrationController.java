package com.example.database;

import com.example.database.DbFunctions.DbFunctions;
import com.example.database.Models.Singleton;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class RegistrationController {

    @FXML
    private Label ErrorText;

    @FXML
    private AnchorPane rootPane;

    @FXML
    private Button btnimg;

    @FXML
    private TextField captchaField;

    @FXML
    private Label captchaLabel;

    @FXML
    private ImageView imgreg;

    @FXML
    private Button btnRegistration;

    @FXML
    private DatePicker datePicker;

    @FXML
    private TextField textFieldLastName;

    @FXML
    private TextField textFieldFirstName;

    @FXML
    private TextField textFieldPass;

    @FXML
    private TextField textFieldSecondName;

    @FXML
    private MaskField textFieldPhone;

//    @FXML
//    private TextField textFieldPhone;

    private final DbFunctions dbFunctions = new DbFunctions();

    final FileChooser fileChooser = new FileChooser();


    String image = " ";

    Stage stage = new Stage();







    @FXML
    void backScreen() {
        new Loader().openNewScene(rootPane, "/com/example/database/come-screen.fxml", "Салон красоты Ирина");
    }

    @FXML
    void initialize() {


        btnRegistration.setOnAction(e -> validation());


        btnimg.setOnAction(actionEvent -> {
           File file = fileChooser.showOpenDialog(stage);
           image = file.getAbsolutePath();
           imgreg.setImage(new Image(image));
           
        });

    }

    private void validation() {



        String FirstName = textFieldFirstName.getText();
        String SecondName = textFieldSecondName.getText();
        String LastName = textFieldLastName.getText();
        String Passport = textFieldPass.getText();
        String Phone = textFieldPhone.getText();
        LocalDate Datee = datePicker.getValue();

        int codeError = dbFunctions.check_login(FirstName);

        if (FirstName.isEmpty() || SecondName.isEmpty() || LastName.isEmpty() || Passport.isEmpty() || Passport.isEmpty() || Datee.toString().isEmpty()) {
            ErrorText.setText("Какое-то поле не заполнено");
            captcha();
        } else if (codeError == 0) {
            ErrorText.setText("Такой логин уже существует");
            captcha();
        } else if (codeError == 404) {
            ErrorText.setText("Какая-то ошибка");
        }   else if (!captchaField.getText().equals(captchaLabel.getText()))
        {
            ErrorText.setText("Неправильная капча");
            captcha();
        }
        else {

//            Singleton.getInstance().setFirstName(FirstName);
//            Singleton.getInstance().setSecondName(SecondName);
//            Singleton.getInstance().setLastName(LastName);
//            Singleton.getInstance().setPhone(Phone);
//            Singleton.getInstance().setPassport(Passport);
//            Singleton.getInstance().setImg(image);

            ErrorText.setText("");
            dbFunctions.createUser(FirstName, SecondName, LastName, Passport, Phone, Datee, image);
            new Loader().openNewScene(rootPane, "/com/example/database/auth-screen.fxml", "Авторизация");
        }
    }
//    private void installDatePicker(){
//        int year = Integer.parseInt(new SimpleDateFormat("yyyy").format(new Date()));
//        int month = Integer.parseInt(new SimpleDateFormat("MM").format(new Date()));
//        int day = Integer.parseInt(new SimpleDateFormat("dd").format(new Date()));
//        datePicker.setValue(LocalDate.of(year,month,day));
//        datePicker.setPromptText("dd-MM-yyyy");
//    }

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



