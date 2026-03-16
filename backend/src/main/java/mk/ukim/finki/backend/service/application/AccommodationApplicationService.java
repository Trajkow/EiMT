package mk.ukim.finki.backend.service.application;

import mk.ukim.finki.backend.model.dto.inputDTO.CreateAccommodationDTO;
import mk.ukim.finki.backend.model.dto.outputDTO.DisplayAccommodationDTO;

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

}
