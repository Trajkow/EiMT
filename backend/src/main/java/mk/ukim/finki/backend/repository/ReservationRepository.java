package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.model.enitites.Accommodation;
import mk.ukim.finki.backend.model.enitites.AppUser;
import mk.ukim.finki.backend.model.enitites.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    boolean existsByUser(AppUser user);
    boolean existsByAccommodation(Accommodation accommodation);


    @Query("SELECT COUNT(r) > 0 FROM Reservation r " +
            "WHERE r.accommodation.id = :accommodationId AND r.reservedAt < :releaseAt AND r.releaseAt > :reservedAt")
    boolean existsOverlappingReservation(@Param("accommodationId") Long accommodationId,
                                         @Param("reservedAt") LocalDateTime reservedAt,
                                         @Param("releaseAt") LocalDateTime releaseAt);
}
