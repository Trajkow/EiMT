package mk.ukim.finki.backend.model.exception;

public class HostNotFound extends RuntimeException {
    public HostNotFound(String message) {
        super(message);
    }
}
