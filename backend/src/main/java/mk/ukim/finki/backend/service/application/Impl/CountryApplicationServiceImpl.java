package mk.ukim.finki.backend.service.application.Impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.backend.model.dto.inputDTO.CreateCountryDTO;
import mk.ukim.finki.backend.model.dto.outputDTO.DisplayCountryDTO;
import mk.ukim.finki.backend.model.dto.outputDTO.DisplayHostDTO;
import mk.ukim.finki.backend.service.application.CountryApplicationService;
import mk.ukim.finki.backend.service.domain.CountryService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class CountryApplicationServiceImpl implements CountryApplicationService {

    private final CountryService countryService;

    @Override
    public Optional<DisplayCountryDTO> findById(Long id) {
        return this.countryService.findById(id)
                .map(DisplayCountryDTO::from);
    }

    @Override
    public List<DisplayCountryDTO> findAll() {
        return DisplayCountryDTO.from(this.countryService.findAll());
    }

    @Override
    public DisplayCountryDTO create(CreateCountryDTO countryDTO) {
        return DisplayCountryDTO.from(this.countryService.create(countryDTO.toCountry()));
    }

    @Override
    public Optional<DisplayCountryDTO> update(Long id, CreateCountryDTO countryDTO) {
        return this.countryService.update(id, countryDTO.toCountry()).map(DisplayCountryDTO::from);
    }

    @Override
    public Optional<DisplayCountryDTO> deleteById(Long id) {
        return this.countryService.deleteById(id).map(DisplayCountryDTO::from);
    }
}
