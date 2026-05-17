package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.model.enitites.AccommodationStatsMV;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationStatsMVRepository extends JpaRepository<AccommodationStatsMV, String> {
}
