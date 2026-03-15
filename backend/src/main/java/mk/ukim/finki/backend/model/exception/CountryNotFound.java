package mk.ukim.finki.backend.model.exception;

public class CountryNotFound extends RuntimeException {
    public CountryNotFound(String message) {
        super(message);
    }
}
