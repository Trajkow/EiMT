package mk.ukim.finki.backend.service.listener;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.backend.model.enitites.ActivityLog;
import mk.ukim.finki.backend.model.enitites.Accommodation;
import mk.ukim.finki.backend.model.event.AccommodationRentedEvent;
import mk.ukim.finki.backend.service.domain.ActivityLogService;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@AllArgsConstructor
public class AccommodationEventListener {

    private final ActivityLogService activityLogService;

    @EventListener
    public void handleAccommodationRented(AccommodationRentedEvent event) {
        Accommodation accommodation = event.getAccommodation();
        String name = accommodation.getName();

        log.info("EVENT: Accommodation '{}' (id={}) has been rented.", name, accommodation.getId());

        // Save activity log
        ActivityLog logEntry = new ActivityLog(name, LocalDateTime.now(), "RENTED");
        activityLogService.save(logEntry);

        // Check if fully occupied (numRooms reached 0)
        if (accommodation.getNumRooms() != null && accommodation.getNumRooms() == 0) {
            log.warn("EVENT: Accommodation '{}' (id={}) is now FULLY OCCUPIED – no free rooms remaining.",
                    name, accommodation.getId());
            ActivityLog fullLog = new ActivityLog(name, LocalDateTime.now(), "FULLY_OCCUPIED");
            activityLogService.save(fullLog);
        }
    }
}
