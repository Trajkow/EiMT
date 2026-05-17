package mk.ukim.finki.backend.service.domain.Impl;

import mk.ukim.finki.backend.model.enitites.Accommodation;
import mk.ukim.finki.backend.model.enumeration.Category;
import mk.ukim.finki.backend.model.event.AccommodationRentedEvent;
import mk.ukim.finki.backend.model.projection.AccommodationShortProjection;
import mk.ukim.finki.backend.repository.AccommodationRepository;
import mk.ukim.finki.backend.service.domain.AccommodationService;
import mk.ukim.finki.backend.service.domain.AccommodationSpecification;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AccommodationServiceImpl implements AccommodationService {

    private final AccommodationRepository accommodationRepository;
    private final ApplicationEventPublisher eventPublisher;

    public AccommodationServiceImpl(AccommodationRepository accommodationRepository,
                                    ApplicationEventPublisher eventPublisher) {
        this.accommodationRepository = accommodationRepository;
        this.eventPublisher = eventPublisher;
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
                    if (current.getNumRooms() != null && current.getNumRooms() > 0) {
                        current.setNumRooms(current.getNumRooms() - 1);
                    }
                    current.setIsRented(true);
                    current.setRentCount(current.getRentCount() == null ? 1 : current.getRentCount() + 1);
                    Accommodation saved = this.accommodationRepository.save(current);
                    eventPublisher.publishEvent(new AccommodationRentedEvent(this, saved));
                    return saved;
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

    @Override
    public Page<AccommodationShortProjection> findFiltered(int page, int size, String sortBy, String sortDir,
                                                           Category category, Long hostId, String country,
                                                           Integer numRooms, Boolean hasAvailableRooms) {
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir) ? Sort.Direction.DESC : Sort.Direction.ASC;
        String resolvedSortBy = (sortBy == null || sortBy.isBlank()) ? "name" : sortBy;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, resolvedSortBy));

        Specification<Accommodation> spec = AccommodationSpecification.buildSpecification(
                category, hostId, country, numRooms, hasAvailableRooms);

        return accommodationRepository.findBy(spec, q -> q.as(AccommodationShortProjection.class).page(pageable));
    }

    @Override
    public List<Accommodation> findMostPopular() {
        return accommodationRepository.findAllByOrderByRentCountDesc();
    }
}
