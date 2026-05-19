package mk.ukim.finki.backend.service.domain;

import lombok.AllArgsConstructor;
import mk.ukim.finki.backend.model.enitites.Accommodation;
import mk.ukim.finki.backend.model.enitites.AppUser;
import mk.ukim.finki.backend.model.enitites.Reservation;
import mk.ukim.finki.backend.model.exception.HostCannotReserveOwnAccommodationException;
import mk.ukim.finki.backend.repository.AccommodationRepository;
import mk.ukim.finki.backend.repository.ReservationRepository;
import mk.ukim.finki.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final AccommodationRepository accommodationRepository;
    private final UserRepository userRepository;

    @Override
    public Reservation reserve(Long accommodationId, Long userId, LocalDateTime reservedAt, LocalDateTime releaseAt) {
        Accommodation accommodation = accommodationRepository.findById(accommodationId)
                .orElseThrow(() -> new IllegalArgumentException("Accommodation not found"));

        AppUser user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Host cannot reserve own accommodation
        if (accommodation.getHost() != null && accommodation.getHost().getId().equals(user.getId())) {
            throw new HostCannotReserveOwnAccommodationException();
        }

//        if (reservationRepository.existsByUser(user)) {
//            throw new IllegalArgumentException("User has already reserved an accommodation");
//        }

        if (reservationRepository.existsOverlappingReservation(accommodationId, reservedAt, releaseAt)) {
            throw new IllegalArgumentException("Accommodation is already reserved for that period");
        }

        Reservation reservation = new Reservation();
        reservation.setAccommodation(accommodation);
        reservation.setUser(user);
        reservation.setReservedAt(reservedAt);
        reservation.setReleaseAt(releaseAt);

        return reservationRepository.save(reservation);
    }

    @Override
    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }
}
