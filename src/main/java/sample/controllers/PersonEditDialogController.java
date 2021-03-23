package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import sample.exceptions.IncorrectFirstNameField;
import sample.models.Person;
import sample.util.DateUtil;

import java.time.LocalDate;

/**
 * TheSusanin
 * 3/21/2021
 */
public class PersonEditDialogController {
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField cityField;
    @FXML
    private TextField streetField;
    @FXML
    private TextField postalCodeField;
    @FXML
    private TextField birthdayField;

    private Stage dialogStage;
    private Person person;
    private boolean okClicked = false;

    public PersonEditDialogController() {
    }

    @FXML
    private void initialize() {
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setPerson(Person person) {
        this.person = person;
        this.firstNameField.setText(person.getFirstName());
        this.lastNameField.setText(person.getLastName());
        this.streetField.setText(person.getStreet());
        this.postalCodeField.setText(Integer.toString(person.getPostalCode()));
        this.cityField.setText(person.getCity());
        this.birthdayField.setText(DateUtil.format(person.getBirthday()));
        this.birthdayField.setPromptText("dd.mm.yyyy");
    }

    public boolean isOkClicked() {
        return this.okClicked;
    }

    @FXML
    private void handleOk() {
        if (this.isInputValid()) {
            this.person.setFirstName(this.firstNameField.getText());
            this.person.setLastName(this.lastNameField.getText());
            this.person.setStreet(this.streetField.getText());
            this.person.setPostalCode(Integer.parseInt(this.postalCodeField.getText()));
            this.person.setCity(this.cityField.getText());
            this.person.setBirthday(DateUtil.parse(this.birthdayField.getText()));
            this.okClicked = true;
            this.dialogStage.close();
        }

    }

    @FXML
    private void handleCancel() {
        this.dialogStage.close();
    }

    private boolean isInputValid() {
        String errorMessage = "";
/*
        try {

        } catch (IncorrectFirstNameField ex) {

        }*/
        if (this.firstNameField.getText() == null || this.firstNameField.getText().length() == 0) {
            errorMessage = errorMessage + "Не заполнен поле фамилия!\n";
        }

        if (this.lastNameField.getText() == null || this.lastNameField.getText().length() == 0) {
            errorMessage = errorMessage + "Не заполнен поле имя!\n";
        }

        if (this.streetField.getText() == null || this.streetField.getText().length() == 0) {
            errorMessage = errorMessage + "Не заполнен поле улица!\n";
        }

        if (this.postalCodeField.getText() != null && this.postalCodeField.getText().length() != 0) {
            try {
                Integer.parseInt(this.postalCodeField.getText());
            } catch (NumberFormatException var3) {
                errorMessage = errorMessage + "Поле почтовый индекс должен числовым!\n";
            }
        } else {
            errorMessage = errorMessage + "Не заполнен поле почтовый индекс!\n";
        }

        if (this.cityField.getText() == null || this.cityField.getText().length() == 0) {
            errorMessage = errorMessage + "Не заполнен поле город!\n";
        }

        if (this.birthdayField.getText() != null && this.birthdayField.getText().length() != 0) {
            if (!DateUtil.validDate(this.birthdayField.getText())) {
                errorMessage = errorMessage + "Не заполнен поле дата рождения. Формат дд.мм.гггг!\n";
            }
        } else {
            errorMessage = errorMessage + "Не заполнен поле дата рождения!\n";
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(this.dialogStage);
            alert.setTitle("Недействительные поля");
            alert.setHeaderText("Пожалуйста, исправьте недопустимые поля");
            alert.setContentText(errorMessage);
            alert.showAndWait();
            return false;
        }
    }
}
