package sample.exceptions;

/**
 * TheSusanin
 * 18 март 2021
 */
public class IncorrectPassword extends RuntimeException {

    public IncorrectPassword(String message) {
        super(message);
    }
}
