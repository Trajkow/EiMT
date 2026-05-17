package mk.ukim.finki.backend.service.application;

import mk.ukim.finki.backend.model.dto.inputDTO.CreateAccommodationDTO;
import mk.ukim.finki.backend.model.dto.outputDTO.DisplayAccommodationDTO;
import mk.ukim.finki.backend.model.enumeration.Category;
import mk.ukim.finki.backend.model.projection.AccommodationDetailedProjection;
import mk.ukim.finki.backend.model.projection.AccommodationShortProjection;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AccommodationApplicationService {

    DisplayAccommodationDTO create(CreateAccommodationDTO accommodationDTO);

    Optional<DisplayAccommodationDTO> findById(Long id);

    List<DisplayAccommodationDTO> findAll();

    Optional<DisplayAccommodationDTO> update(Long id, CreateAccommodationDTO accommodationDTO);

    Optional<DisplayAccommodationDTO> deleteById(Long id);

    Optional<DisplayAccommodationDTO> setRented(Long id);

    List<DisplayAccommodationDTO> findRented();

    List<DisplayAccommodationDTO> findAvailable();

    Page<AccommodationShortProjection> findFiltered(int page, int size, String sortBy, String sortDir,
                                                    Category category, Long hostId, String country,
                                                    Integer numRooms, Boolean hasAvailableRooms);

    List<AccommodationDetailedProjection> findAllDetailed();

    List<DisplayAccommodationDTO> findMostPopular();
}
