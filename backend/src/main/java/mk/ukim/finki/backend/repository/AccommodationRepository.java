package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.model.enitites.Accommodation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long> {

    @Query("SELECT a FROM Accommodation a WHERE a.isRented = true")
    List<Accommodation> findRentedAccommodations();

    @Query("SELECT a FROM Accommodation a WHERE a.isRented = false")
    List<Accommodation> findAvailableAccommodations();


}
