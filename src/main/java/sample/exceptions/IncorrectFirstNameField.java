package sample.exceptions;

/**
 * TheSusanin
 * 23 март 2021
 */
public class IncorrectFirstNameField extends RuntimeException {

    public IncorrectFirstNameField(String message) {
        super(message);
    }
}
