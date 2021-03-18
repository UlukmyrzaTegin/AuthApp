package sample.services.impl;

import sample.exceptions.IncorrectLoginAndPassword;
import sample.models.User;
import sample.services.LoginService;

/**
 * TheSusanin
 * 16 март 2021
 */
public class LoginServiceImpl implements LoginService {

    @Override
    public void auth(User user) throws IncorrectLoginAndPassword {

        if (!(user.getLogin().equals("admin") && user.getPassword().equals("12345"))){
            throw  new IncorrectLoginAndPassword("Неверная авторизация");
        }

    }

}
