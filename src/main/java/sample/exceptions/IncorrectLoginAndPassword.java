package sample.exceptions;

/**
 * TheSusanin
 * 18 март 2021
 */
public class IncorrectLoginAndPassword extends Exception {

    public IncorrectLoginAndPassword(String message) {
        super(message);
    }
}
