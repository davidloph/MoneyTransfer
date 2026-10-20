package domain;

public class DifferentCurrencyException extends RuntimeException {
    public DifferentCurrencyException(String message) {
        super(message);
    }
}
