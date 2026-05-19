package mk.ukim.finki.backend.service.application.Impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.backend.model.dto.outputDTO.DisplayReservationDTO;
import mk.ukim.finki.backend.model.enitites.AppUser;
import mk.ukim.finki.backend.model.enitites.Reservation;
import mk.ukim.finki.backend.repository.ReservationRepository;
import mk.ukim.finki.backend.repository.UserRepository;
import mk.ukim.finki.backend.service.application.ReservationApplicationService;
import mk.ukim.finki.backend.service.domain.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class ReservationApplicationServiceImpl implements ReservationApplicationService {

    private final ReservationService reservationService;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;

    @Override
    public DisplayReservationDTO reserve(Long accommodationId, String username, LocalDateTime reservedAt, LocalDateTime releaseAt) {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Reservation reservation = reservationService.reserve(accommodationId, user.getId(), reservedAt, releaseAt);
        return DisplayReservationDTO.from(reservation);
    }

    @Override
    public List<DisplayReservationDTO> findAll() {
        return DisplayReservationDTO.from(reservationService.findAll());
    }
}
