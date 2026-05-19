package mk.ukim.finki.backend.service.domain;

import mk.ukim.finki.backend.model.enitites.Reservation;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationService {
    Reservation reserve(Long accommodationId, Long userId, LocalDateTime reservedAt, LocalDateTime releaseAt);
    List<Reservation> findAll();
}
