package mk.ukim.finki.backend.model.dto.inputDTO;

import java.time.LocalDateTime;

public record CreateReservationDTO(
        LocalDateTime reservedAt,
        LocalDateTime releaseAt
) {
}
