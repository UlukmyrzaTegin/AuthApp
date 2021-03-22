package sample;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.controllers.PersonEditDialogController;
import sample.controllers.PersonOverviewController;
import sample.controllers.RootLayoutController;
import sample.models.Person;
import sample.models.PersonListWrapper;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.prefs.Preferences;

public class Main extends Application {

    private Stage primaryStage;
    private Parent root;
    private BorderPane rootLayout;
    private ObservableList<Person> people = FXCollections.observableArrayList();

/*    @Override
    public void start(Stage primaryStage) throws Exception{
         Parent root = FXMLLoader.load(getClass().getResource("/rootlayout.fxml"));
        primaryStage.setTitle("Authorization");
        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();

        initRootLayout();
        showPersonOverview();
    }*/

    public void start(Stage primaryStage){
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Адресная книжка");
        this.primaryStage.getIcons().add(new Image("file:resources/image/addressbook.png"));
        this.initRootLayout();
        this.showPersonOverview();
    }

    //инициализация корновой макет
 public void initRootLayout() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/rootlayout.fxml"));
            this.rootLayout = loader.load();
            Scene scene = new Scene(this.rootLayout);
            this.primaryStage.setScene(scene);
            //Доступ к контроллеру к главному приложению
            RootLayoutController controller = loader.getController();
            controller.setMain(this);
            this.primaryStage.show();
            this.primaryStage.setResizable(false);
        }catch (IOException ex) {
            ex.printStackTrace();
        }

     // Загрузить последний открытый файл с адресатами
        File file = getPersonFilePath();
        if(file != null) {
            loadPersonDataFromFile(file);
        }

    }

    //Показ в корновом макете сведения
    public void showPersonOverview() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/personOverview.fxml"));
            AnchorPane personOverview = (AnchorPane)loader.load();
            this.rootLayout.setCenter(personOverview);

            //доступ к контроллеру
            PersonOverviewController controller = (PersonOverviewController)loader.getController();
            controller.setMain(this);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    //возвращаем главную окно
    public Stage getPrimaryStage() {
        return primaryStage;
    }

    public Main() {
        this.people.add(new Person("Dolon", "Askarbekov", "ул.Чуй 105",  "г.Бишкек"));
        this.people.add(new Person("Elina", "Elina", "ул.Токомбаева 89",  "г.Бишкек"));
        this.people.add(new Person("Dima", "Dima", "ул.Советская 56","г.Бишкек"));
        this.people.add(new Person("Adilet", "Adilet", "ул.Айтматова 89", "г.Бишкек"));
        this.people.add(new Person("Nurik", "Nurik", "ул.Магситраль32","г.Бишкек"));
    }

    //возвраю данные
    public ObservableList<Person> getPeople() {
        return this.people;
    }

    public boolean showPersonEditDialog(Person person) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("/personEditDialog.fxml"));
            AnchorPane page = (AnchorPane)loader.load();
            Stage dialogStage = new Stage();
            dialogStage.setTitle("Изменить сотрудника");
            dialogStage.initModality(Modality.WINDOW_MODAL);
            dialogStage.initOwner(this.primaryStage);
            Scene scene = new Scene(page);
            dialogStage.setScene(scene);
            PersonEditDialogController controller = (PersonEditDialogController)loader.getController();
            controller.setDialogStage(dialogStage);
            controller.setPerson(person);
            dialogStage.showAndWait();
            return controller.isOkClicked();
        } catch (IOException var7) {
            var7.printStackTrace();
            return false;
        }
    }

    public File getPersonFilePath() {
        Preferences preferences = Preferences.userNodeForPackage(Main.class);
        String filePath = preferences.get("filePath", null);
        if (filePath != null) {
            return  new File(filePath);
        } else  {
            return  null;
        }
    }

    public void setPersonFilePath(File file) {
        Preferences preferences = Preferences.userNodeForPackage(Main.class);
        if (file != null) {
            preferences.put("filePath", file.getPath());
            primaryStage.setTitle("AddressApp - " + file.getName());
        } else {
            preferences.remove("filePath");
            primaryStage.setTitle("AddressApp");
        }
    }

    public void loadPersonDataFromFile(File file) {
            try {
                JAXBContext context = JAXBContext.newInstance(PersonListWrapper.class);
                Unmarshaller unmarshaller = context.createUnmarshaller();

                //Чтение XML из файла и демаршализация
                PersonListWrapper wrapper = (PersonListWrapper) unmarshaller.unmarshal(file);

                people.clear();
                people.addAll(wrapper.getPersons());
                //Сохраняю путь к файлу в реестре
                setPersonFilePath(file);
            } catch (JAXBException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Не удалось загрузить данные");
                alert.setContentText("Не удалось загрузить данные из файла: \n" + file.getPath());
                alert.showAndWait();

            }
        }

        public void savePersonDataToFile(File file) {
            try {
                JAXBContext jaxbContext = JAXBContext.newInstance(PersonListWrapper.class);
                Marshaller marshaller = jaxbContext.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                //Обертываем наши данные об адресатах
                PersonListWrapper wrapper = new PersonListWrapper();
                wrapper.setPersons(people);

                //Маршализация и сохраняем XML в файл
                marshaller.marshal(wrapper, file);

                //Сохраняем путь к файлу в реестре
                setPersonFilePath(file);
            } catch (JAXBException e) {
                Alert alert = new Alert(AlertType.ERROR);
                alert.setTitle("Ошибка");
                alert.setHeaderText("Не удалось загрузить данные");
                alert.setContentText("Не удалось загрузить данные из файла: \n" + file.getPath());
                alert.showAndWait();
            }
    }

    public static void main(String[] args) {
         launch(args);
    }
}
