package mk.ukim.finki.backend.service.domain;

import mk.ukim.finki.backend.model.enitites.Accommodation;

import java.util.List;
import java.util.Optional;

public interface AccommodationService {

    Accommodation create(Accommodation accommodation);

    Optional<Accommodation> findById(Long id);

    List<Accommodation> findAll();

    Optional<Accommodation> update(Long id, Accommodation accommodation);

    Optional<Accommodation> deleteById(Long id);

    Optional<Accommodation> setRented(Long id);

    List<Accommodation> findRented();

    List<Accommodation> findAvailable();

}
