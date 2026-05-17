package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.model.enitites.Accommodation;
import mk.ukim.finki.backend.model.projection.AccommodationDetailedProjection;
import mk.ukim.finki.backend.model.projection.AccommodationShortProjection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccommodationRepository extends JpaRepository<Accommodation, Long>,
        JpaSpecificationExecutor<Accommodation> {

    @Query("SELECT a FROM Accommodation a WHERE a.isRented = true")
    List<Accommodation> findRentedAccommodations();

    @Query("SELECT a FROM Accommodation a WHERE a.isRented = false")
    List<Accommodation> findAvailableAccommodations();

    // Projection - short view with pagination
    Page<AccommodationShortProjection> findBy(Specification<Accommodation> spec, Pageable pageable, Class<AccommodationShortProjection> type);

    // Detailed projection using EntityGraph
    @EntityGraph(value = "Accommodation.withHostAndCountry")
    List<AccommodationDetailedProjection> findAllProjectedBy(Class<AccommodationDetailedProjection> type);

    // Most popular - sorted by rentCount descending
    List<Accommodation> findAllByOrderByRentCountDesc();
}
