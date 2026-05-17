package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.model.enitites.AccommodationView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccommodationViewRepository extends JpaRepository<AccommodationView, Long> {
}
