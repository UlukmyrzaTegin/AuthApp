package sample.controllers;

/**
 * TheSusanin
 * 16 март 2021
 */

import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import java.util.Optional;

public class RegistrationController {

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtPwd1;

    @FXML
    private PasswordField txtPwd2;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;


    @FXML
    void onButtonClicked(ActionEvent event) {
        if (event.getSource().equals(btnSave)) {
            System.out.println("Сохранен");
            ButtonType buttonTypeOk = new ButtonType("Да");
            Alert alert = new Alert(AlertType.CONFIRMATION, "Все данные верны ?", buttonTypeOk, ButtonType.CANCEL);
            alert.setTitle(null);
            alert.setHeaderText(null);
            Optional<ButtonType> buttonType = alert.showAndWait();
            if (buttonType.get().equals(buttonTypeOk)) {
                System.out.println("Пользователь согласен");
            }

          /*  final JFXTextField validationField = new JFXTextField();
            RequiredFieldValidator validator = new RequiredFieldValidator();
            validator.setMessage("Input Required");
            validationField.getValidators().add(validator);
            validationField.focusColorProperty().addListener((o,oldVal, newVal) -> {
                if (!newVal) validationField.validate();
            });*/

        } else if (event.getSource().equals(btnCancel)){
            btnCancel.getScene().getWindow().hide(); // закрыть окно
        }

        String name = txtName.getText();
        String login = txtLogin.getText();
        String pasw1 = txtPwd1.getText();
        String pasw2 = txtPwd2.getText();
        System.out.println(name + " " + login + " " + pasw1 + " " + pasw2);
    }


}
