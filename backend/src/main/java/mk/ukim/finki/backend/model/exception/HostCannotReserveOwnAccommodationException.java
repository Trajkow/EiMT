package mk.ukim.finki.backend.model.exception;

public class HostCannotReserveOwnAccommodationException extends RuntimeException {
    public HostCannotReserveOwnAccommodationException() {
        super("Host can not reserve its own accommodation!");
    }
}
