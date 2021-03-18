package sample.controllers;

/**
 * TheSusanin
 * 3/18/2021
 */

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import sample.exceptions.IncorrectAge;
import sample.exceptions.IncorrectName;
import sample.exceptions.IncorrectSalary;
import sample.exceptions.IncorrectSurname;
import sample.models.Employee;

public class EmployeeController {



    @FXML
    private TextField txtSurname;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtSalary;

    @FXML
    private TextField txtPosition;

    @FXML
    private TextField txtMiddlename;

    @FXML
    private TextField txtAge;

    @FXML
    private Button btnSave;

    @FXML
    private Button btnCancel;

    @FXML
    void onButtonSaveClicked(ActionEvent event) {
        String surname = txtSurname.getText();
        String name = txtName.getText();
        int  age = Integer.parseInt(txtAge.getText());
        double salary = Double.parseDouble(txtSalary.getText());

        try {
            Employee employee = new Employee(surname, name, age, salary);
        } catch (IncorrectSalary incorrectSalary) {
            incorrectSalary.printStackTrace();
        } catch (IncorrectName incorrectName) {
            incorrectName.printStackTrace();
        } catch (IncorrectAge incorrectAge) {
            incorrectAge.printStackTrace();
        } catch (IncorrectSurname incorrectSurname) {
            incorrectSurname.printStackTrace();
        }

     /*   try {
            Employee employee = new Employee(surname, name, age, salary);
        } catch (IncorrectSurname ex) {
            txtSurname.setText(null);
            txtSurname.setPromptText("Фамилия не должен быть пустым!");
        } catch (IncorrectName ex) {
            txtName.setText(null);
            txtName.setPromptText("Имя не должен быть пустым!");
        } catch (IncorrectAge ex) {
            txtAge.setText(null);
            txtAge.setPromptText("Возраст не должен быть < 18 и > 65!");
        } catch (IncorrectSalary ex) {
            txtSalary.setText(null);
            txtSalary.setPromptText("ЗП не должен отрицательным");
        }*/

    }

    @FXML
    void onButtonCancelClicked(ActionEvent event) {
        btnCancel.getScene().getWindow().hide();


    }

}
