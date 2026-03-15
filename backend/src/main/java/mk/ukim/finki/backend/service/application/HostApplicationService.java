package mk.ukim.finki.backend.service.application;

import mk.ukim.finki.backend.model.dto.inputDTO.CreateHostDTO;
import mk.ukim.finki.backend.model.dto.outputDTO.DisplayHostDTO;

import java.util.List;
import java.util.Optional;

public interface HostApplicationService {
    Optional<DisplayHostDTO> findById(Long id);

    List<DisplayHostDTO> findAll();

    DisplayHostDTO create(CreateHostDTO hostDTI);

    Optional<DisplayHostDTO> update(Long id, CreateHostDTO hostDTO);

    Optional<DisplayHostDTO> deleteById(Long id);
}
