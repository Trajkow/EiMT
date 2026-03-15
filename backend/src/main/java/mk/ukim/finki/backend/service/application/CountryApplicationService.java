package mk.ukim.finki.backend.service.application;

import mk.ukim.finki.backend.model.dto.inputDTO.CreateCountryDTO;
import mk.ukim.finki.backend.model.dto.outputDTO.DisplayCountryDTO;

import java.util.List;
import java.util.Optional;

public interface CountryApplicationService {

    Optional<DisplayCountryDTO> findById(Long id);

    List<DisplayCountryDTO> findAll();

    DisplayCountryDTO create(CreateCountryDTO countryDTO);

    Optional<DisplayCountryDTO> update(Long id, CreateCountryDTO countryDTO);

    Optional<DisplayCountryDTO> deleteById(Long id);
}
