package sample.view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

/**
 * TheSusanin
 * 17 март 2021
 */
public class YourView {  //View. Отвечает за то, что отображает приложение.

    @FXML
    private Label labelFx;

    public void setLabelFx(String text) {
        labelFx.setText(text);
    }
}
