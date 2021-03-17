package sample.controllers;

/**
 * TheSusanin
 * 17 март 2021
 */


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import sample.view.YourView;
import java.net.URL;
import java.util.ResourceBundle;

public class YourController extends YourView implements Initializable {  //Controller. Адаптер FXML и java кода.

    @FXML
    private Button fxButton;
    @FXML
    private Label labelFx;
    @FXML
    private Label localLabel;


    @FXML
    void click(ActionEvent event) {
        System.out.println("Hello");
        fxButton.setText("Hey");
        labelLocalInitialize();

    }

    private void labelLocalInitialize() {
        localLabel = labelFx;
        localLabel.setText("Local variable control");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Инициализация и передача контроля в View класс labelFx
        setLabelFx("transfer of control in View \"labelFx\" variable");

        // Добавляем слушателя, по клику мышки на кнопку, выводит текст на консоль
        fxButton.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> System.out.println("Listener triggered"));

    }

/*   - Прямо закодировать в FXML вызов метода в контроллере.
     - Инициализировать используемые id и иже с ними node и передавать в необходимое место в коде,
              вплоть до присваивания локальным переменным этих node по их fx:id.
     - Происходит вывод на консоль "Hello World", у кнопки меняется текст на "Hey!", управление переходит в метод labelLocalInitialize(),
              в нем локально определенной переменной назначается объект labelFx. Следом, labelFx назначается новый текст.
     -Controller.java — класс, являющийся прослойкой между кодом на java и FXML файлом. Адаптер, если угодно.
         Через него происходит связь всех ссылок fx:id, в его методах обрабатываются вызовы интерактивных элементов интерфейса, тут же происходит инициализация.
    */
}
