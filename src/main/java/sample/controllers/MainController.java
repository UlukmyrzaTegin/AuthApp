package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.models.User;
import sample.services.LoginService;

public class MainController {

    private LoginService loginService = LoginService.INSRANCE;

    double x = 0;
    double y = 0;

    @FXML
    private TextField txtLogin;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private Button btnEnter;

    @FXML
    private Button btnCancel;

/*    @FXML
    void pressed(MouseEvent event) {
        x = event.getSceneX();
        y = event.getSceneY();
    }

    @FXML
    void dragged(MouseEvent event) {
        Node node = (Node) event.getSource();
        Stage stage = (Stage) node.getScene().getWindow();
        stage.setX(event.getSceneX() - x);
        stage.setY(event.getSceneY() - y);
    }*/

    @FXML
    void onBtnEnterClicked(ActionEvent event) {
        String login = txtLogin.getText();
        String password = txtPassword.getText();

        User user = new User(login, password);  //Чтобы в классе User не добавилось параметры,
        loginService.auth(user);

        //System.out.println(login + " " + password);

    }

    @FXML
    void onBtnCancelClicked(ActionEvent event) {
        txtLogin.getScene().getWindow().hide();
    }


}
