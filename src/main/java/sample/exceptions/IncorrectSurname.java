package sample.exceptions;

/**
 * TheSusanin
 * 3/18/2021
 */
public class IncorrectSurname extends RuntimeException {

    public IncorrectSurname(String message) {
        super(message);
    }
}
