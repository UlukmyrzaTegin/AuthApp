package sample.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.exceptions.IncorrectLogin;
import sample.exceptions.IncorrectPassword;
import sample.models.User;
import sample.services.LoginService;
import sample.util.Crypto;

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
     /*   if (login.isEmpty()) {
            throw  new RuntimeException("Необходимо заполнить поле логин");
        }*/
        String password = txtPassword.getText();
        /*if (password.isEmpty()) {
            throw new RuntimeException("Необходимо заполнить поле пароль");
        }*/

        System.out.println(Crypto.getEncodedStringAlg1(txtLogin.getText(), txtPassword.getText()));
        
       try {
            User user = new User(login, password);  //Чтобы в классе User не добавилось параметры,
            loginService.auth(user);
        } catch (IncorrectLogin ex) {
            txtLogin.setText(null);
            txtLogin.setPromptText("Неверный логин!");
        } catch (IncorrectPassword ex) {
            txtPassword.setText(null);
            txtPassword.setPromptText("Неверный пароль!");
        } catch (RuntimeException ex) {
            System.out.println("Системная ошибка");                               
        }

       // System.out.println(login + " " + password);
       // printStackTrace - вывод на консоль стека трассировки ошибки в классе Exception.
       // Блок finally выполняется в любом случае, возникло ли исключение в блоке try или нет:
       // Метод getMessage() возвращает сообщение об исключении
       // Метод getStackTrace() возвращает массив, содержащий трассировку стека исключения
       // Метод printStackTrace() отображает трассировку стека

    }

    @FXML
    void onBtnCancelClicked(ActionEvent event) {
        txtLogin.getScene().getWindow().hide();
    }


/*    -Controller.java — класс, являющийся прослойкой между кодом на java и FXML файлом. Адаптер, если угодно.
         Через него происходит связь всех ссылок fx:id, в его методах обрабатываются вызовы интерактивных элементов интерфейса, тут же происходит инициализация.
    */


}
