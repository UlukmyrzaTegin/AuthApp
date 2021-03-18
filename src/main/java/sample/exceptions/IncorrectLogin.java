package sample.exceptions;

/**
 * TheSusanin
 * 18 март 2021
 */
public class IncorrectLogin extends RuntimeException {

    public IncorrectLogin(String message) {
        super(message);
    }
}
