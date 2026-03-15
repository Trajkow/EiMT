package mk.ukim.finki.backend.service.application.Impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.backend.model.enitites.Accommodation;
import mk.ukim.finki.backend.model.enitites.Host;
import mk.ukim.finki.backend.model.dto.inputDTO.CreateAccommodationDTO;
import mk.ukim.finki.backend.model.dto.outputDTO.DisplayAccommodationDTO;
import mk.ukim.finki.backend.model.exception.HostNotFound;
import mk.ukim.finki.backend.service.application.AccommodationApplicationService;
import mk.ukim.finki.backend.service.domain.AccommodationService;
import mk.ukim.finki.backend.service.domain.HostService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AccommodationApplicationServiceImpl implements AccommodationApplicationService {

    private final AccommodationService accommodationService;
    private final HostService hostService;

    @Override
    public DisplayAccommodationDTO create(CreateAccommodationDTO accommodationDTO) {
        Host host = this.hostService.findById(accommodationDTO.HostId())
                .orElseThrow(()-> new HostNotFound("Host not found"));
        return DisplayAccommodationDTO.from(this.accommodationService.create(accommodationDTO.toAccommodation(host)));
    }

    @Override
    public Optional<DisplayAccommodationDTO> findById(Long id) {
        return this.accommodationService.findById(id).map(DisplayAccommodationDTO::from);
    }

    @Override
    public List<DisplayAccommodationDTO> findAll() {
        return DisplayAccommodationDTO.from(this.accommodationService.findAll());
    }

    @Override
    public Optional<DisplayAccommodationDTO> update(Long id, CreateAccommodationDTO accommodationDTO) {
        Host host = this.hostService.findById(accommodationDTO.HostId())
                .orElseThrow(()->new HostNotFound("Host not found"));

        return this.accommodationService.update(id, accommodationDTO.toAccommodation(host)).map(DisplayAccommodationDTO::from);
    }

    @Override
    public Optional<DisplayAccommodationDTO> deleteById(Long id) {
        return this.accommodationService.deleteById(id).map(DisplayAccommodationDTO::from);
    }
}
