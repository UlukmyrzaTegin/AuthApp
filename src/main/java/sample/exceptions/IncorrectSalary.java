package sample.exceptions;

/**
 * TheSusanin
 * 3/18/2021
 */
public class IncorrectSalary extends RuntimeException {
    public IncorrectSalary(String message) {
        super(message);
    }
}
