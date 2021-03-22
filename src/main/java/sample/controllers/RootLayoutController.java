package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.stage.FileChooser;
import sample.Main;

import java.io.File;

/**
 * TheSusanin
 * 3/22/2021
 */
public class RootLayoutController {

    private Main main;

    public void setMain(Main main) {
        this.main = main;
    }

    @FXML
    void handleNew() {
        main.getPeople().clear();
        main.setPersonFilePath(null);

    }

    @FXML
    void handleOpen() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showOpenDialog(main.getPrimaryStage());

        if (file != null) {
            main.loadPersonDataFromFile(file);
        }
    }

    @FXML
    void handleSave() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files(*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);
        File personFile = main.getPersonFilePath();
        if (personFile != null) {
            main.savePersonDataToFile(personFile);
        } else  {
            handleSaveAs();
        }
    }

    @FXML
    void handleSaveAs() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extensionFilter = new FileChooser.ExtensionFilter("XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extensionFilter);

        File file = fileChooser.showSaveDialog(main.getPrimaryStage());
        if (file != null) {
            file = new File(file.getPath() + ".xml");
        }
        main.savePersonDataToFile(file);
    }
    @FXML
    void handleExit() {
        System.exit(0);
    }
}
