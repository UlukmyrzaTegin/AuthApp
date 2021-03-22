package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import sample.Main;
import sample.models.Person;
import sample.util.DateUtil;

/**
 * TheSusanin
 * 3/21/2021
 */
public class PersonOverviewController {
    @FXML
    private TableView<Person> personTable;
    @FXML
    private TableColumn<Person, String> firstNameColumn;
    @FXML
    private TableColumn<Person, String> lastNameColumn;
    @FXML
    private TableColumn<Person, String> streetColumn;
    @FXML
    private TableColumn<Person, String> postalCodeColumn;
    @FXML
    private TableColumn<Person, String> cityColumn;

    @FXML
    private Label firstNameLabel;
    @FXML
    private Label lastNameLabel;
    @FXML
    private Label streetNameLabel;
    @FXML
    private Label portalCodeLabel;
    @FXML
    private Label cityLabel;
    @FXML
    private Label birthdayLabel;

    private Main main;

    public PersonOverviewController() {
    }
    @FXML
    public void initialize() {
        firstNameColumn.setCellValueFactory(cellData -> cellData.getValue().firstNameProperty());
        lastNameColumn.setCellValueFactory(cellData -> cellData.getValue().lastNameProperty());
        streetColumn.setCellValueFactory(cellData -> cellData.getValue().streetProperty());
      //  postalCodeColumn.setCellValueFactory(cellData -> cellData.getValue().postalCodeProperty());
        cityColumn.setCellValueFactory(cellData -> cellData.getValue().cityProperty());

        showPersonDetails(null); // очистка дополнительной информации
        personTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            showPersonDetails(newValue); // отображение после изменение
        });
    }

    public void setMain(Main main) {
        this.main = main;
        personTable.setItems(main.getPeople());   // добавление в таблицу данных из списка
    }

    private void showPersonDetails(Person person) {
        if (person != null) {
            //заполнение таблицы информацией из обьекта персон
            this.firstNameLabel.setText(person.getFirstName());
            this.lastNameLabel.setText(person.getLastName());
            this.streetNameLabel.setText(person.getStreet());
            this.portalCodeLabel.setText(Integer.toString(person.getPostalCode()));
            this.cityLabel.setText(person.getCity());
            this.birthdayLabel.setText(DateUtil.format(person.getBirthday()));
        } else {
            this.firstNameLabel.setText("");
            this.lastNameLabel.setText("");
            this.streetNameLabel.setText("");
            this.portalCodeLabel.setText("");
            this.cityLabel.setText("");
            this.birthdayLabel.setText("");
        }

    }

    @FXML
    void handleDeletePerson(ActionEvent event) {
        int selectedIndex = this.personTable.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            this.personTable.getItems().remove(selectedIndex);
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(this.main.getPrimaryStage());
            alert.setTitle("Не выбран");
            alert.setHeaderText("Не выбран сотрудник");
            alert.setContentText("Выберите из таблицы сотрудника");
            alert.showAndWait();
        }

    }

    @FXML
    void handleEditPerson(ActionEvent event) {
        Person selectedPerson = (Person)this.personTable.getSelectionModel().getSelectedItem();
        if (selectedPerson != null) {
            boolean okClicked = this.main.showPersonEditDialog(selectedPerson);
            if (okClicked) {
                this.showPersonDetails(selectedPerson);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.initOwner(this.main.getPrimaryStage());
            alert.setTitle("Не выбран");
            alert.setHeaderText("Не выбран сотрудник");
            alert.setContentText("Выберите из таблицы сотрудника");
            alert.showAndWait();
        }

    }

    @FXML
    void handleNewPerson(ActionEvent event) {
        Person tempPerson = new Person();
        boolean okClicked = this.main.showPersonEditDialog(tempPerson);
        if (okClicked) {
            this.main.getPeople().add(tempPerson);
        }

    }
}
