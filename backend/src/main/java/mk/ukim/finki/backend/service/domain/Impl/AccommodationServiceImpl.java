package mk.ukim.finki.backend.service.domain.Impl;

import mk.ukim.finki.backend.model.enitites.Accommodation;
import mk.ukim.finki.backend.repository.AccommodationRepository;
import mk.ukim.finki.backend.service.domain.AccommodationService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository){
        this.accommodationRepository = accommodationRepository;
    }

    @Override
    public Accommodation create(Accommodation accommodation) {
        return this.accommodationRepository.save(accommodation);
    }

    @Override
    public Optional<Accommodation> findById(Long id) {

        return this.accommodationRepository.findById(id);
    }

    @Override
    public List<Accommodation> findAll() {
        return this.accommodationRepository.findAll();
    }

    @Override
    public Optional<Accommodation> update(Long id, Accommodation accommodation) {
        return this.accommodationRepository.findById(id)
                .map((current) -> {
                    current.setName(accommodation.getName());
                    current.setCategory(accommodation.getCategory());
                    current.setNumRooms(accommodation.getNumRooms());
                    current.setHost(accommodation.getHost());
                    current.setUpdatedAt(LocalDateTime.now());
                    return accommodationRepository.save(current);
                });
    }

    @Override
    public Optional<Accommodation> deleteById(Long id) {
        Optional<Accommodation> current = this.accommodationRepository.findById(id);
        current.ifPresent(this.accommodationRepository::delete);
        return current;
    }

    @Override
    public Optional<Accommodation> setRented(Long id) {
        return this.accommodationRepository.findById(id)
                .map((current) -> {
                    current.setIsRented(true);
                    return this.accommodationRepository.save(current);
                });
    }

    @Override
    public List<Accommodation> findRented() {
        return this.accommodationRepository.findRentedAccommodations();
    }

    @Override
    public List<Accommodation> findAvailable() {
        return this.accommodationRepository.findAvailableAccommodations();
    }
}
