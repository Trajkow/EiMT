package mk.ukim.finki.backend.model.event;

import mk.ukim.finki.backend.model.enitites.Accommodation;
import org.springframework.context.ApplicationEvent;

public class AccommodationRentedEvent extends ApplicationEvent {

    private final Accommodation accommodation;

    public AccommodationRentedEvent(Object source, Accommodation accommodation) {
        super(source);
        this.accommodation = accommodation;
    }

    public Accommodation getAccommodation() {
        return accommodation;
    }
}
