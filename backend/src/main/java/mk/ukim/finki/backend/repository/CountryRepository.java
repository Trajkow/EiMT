package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.model.enitites.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {
}
