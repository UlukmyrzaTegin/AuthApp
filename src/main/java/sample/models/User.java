package sample.models;

/**
 * TheSusanin
 * 16 март 2021
 */
public class User {
    private String name;
    private String login;
    private String password;

    public User(String name, String login, String password) {
        this.name = name;
        this.login = login;
        this.password = password;
    }

    public User(String login, String password) {
       setLogin(login);  //вызов метода
       setPassword(password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login){ // throws Exception - выброшу ошибку
        if (login.isEmpty() || login.length() < 5)
            throw new RuntimeException("Неверное значение для поле логин");
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() < 5)
            throw new RuntimeException("Пароль не должен меньше пяти цифр!");
        this.password = password;
    }
}
