package mk.ukim.finki.backend.service.application.Impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.backend.model.enitites.Country;
import mk.ukim.finki.backend.model.dto.inputDTO.CreateHostDTO;
import mk.ukim.finki.backend.model.dto.outputDTO.DisplayHostDTO;
import mk.ukim.finki.backend.model.exception.CountryNotFound;
import mk.ukim.finki.backend.service.application.HostApplicationService;
import mk.ukim.finki.backend.service.domain.CountryService;
import mk.ukim.finki.backend.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class HostApplicationServiceImpl implements HostApplicationService {

    private final HostService hostService;
    private final CountryService countryService;

    @Override
    public Optional<DisplayHostDTO> findById(Long id) {
        return this.hostService.findById(id).map(DisplayHostDTO::from);
    }

    @Override
    public List<DisplayHostDTO> findAll() {
        return DisplayHostDTO.from(this.hostService.findAll());
//        return this.hostService.findAll().stream().map(DisplayHostDTO::from).toList();
    }

    @Override
    public DisplayHostDTO create(CreateHostDTO hostDTI) {
        Country country = this.countryService.findById(hostDTI.countryID())
                .orElseThrow(()-> new CountryNotFound("Country not found"));

        return DisplayHostDTO.from(this.hostService.create(hostDTI.toHost(country)));
    }

    @Override
    public Optional<DisplayHostDTO> update(Long id, CreateHostDTO hostDTO) {
        Country country = this.countryService.findById(hostDTO.countryID())
                .orElseThrow(()-> new CountryNotFound("Country not found"));
        return this.hostService.update(id, hostDTO.toHost(country)).map(DisplayHostDTO::from);
    }

    @Override
    public Optional<DisplayHostDTO> deleteById(Long id) {
        return this.hostService.deleteById(id).map(DisplayHostDTO::from);
    }
}
