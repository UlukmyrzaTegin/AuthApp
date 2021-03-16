package sample.services;

import sample.models.User;
import sample.services.impl.LoginServiceImpl;

/**
 * TheSusanin
 * 16 март 2021
 */
public interface LoginService {   //интерфейсе нет реализации от интерфейса нельзя создать обьекты, чтобы реализовать интерфейс нам нужен класс. Alt+Enter -> Implement interface

    LoginService INSRANCE = new LoginServiceImpl();  //Шаблон проектирования, как создается шаблон иниализируем переменную INSTANCE, чтобы не создавать другие обьекты. Принцип - singletone pattern
    void auth(User user);
}
