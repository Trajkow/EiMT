package mk.ukim.finki.backend.service.application;

import mk.ukim.finki.backend.model.dto.outputDTO.DisplayReservationDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface ReservationApplicationService {
    DisplayReservationDTO reserve(Long accommodationId, String username, LocalDateTime reservedAt, LocalDateTime releaseAt);
    List<DisplayReservationDTO> findAll();
}
