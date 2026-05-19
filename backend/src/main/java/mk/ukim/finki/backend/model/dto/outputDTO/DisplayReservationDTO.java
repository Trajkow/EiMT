package mk.ukim.finki.backend.model.dto.outputDTO;

import mk.ukim.finki.backend.model.enitites.Reservation;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public record DisplayReservationDTO(
        Long id,
        Long accommodationId,
        String accommodationName,
        String username,
        LocalDateTime reservedAt,
        LocalDateTime releaseAt
) {
    public static DisplayReservationDTO from(Reservation reservation) {
        return new DisplayReservationDTO(
                reservation.getId(),
                reservation.getAccommodation().getId(),
                reservation.getAccommodation().getName(),
                reservation.getUser().getUsername(),
                reservation.getReservedAt(),
                reservation.getReleaseAt()
        );
    }

    public static List<DisplayReservationDTO> from(List<Reservation> reservations) {
        return reservations.stream()
                .map(DisplayReservationDTO::from)
                .collect(Collectors.toList());
    }
}
