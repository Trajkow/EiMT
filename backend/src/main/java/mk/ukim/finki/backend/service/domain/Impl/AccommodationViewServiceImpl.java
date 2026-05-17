package mk.ukim.finki.backend.service.domain.Impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.backend.model.enitites.AccommodationView;
import mk.ukim.finki.backend.repository.AccommodationViewRepository;
import mk.ukim.finki.backend.service.domain.AccommodationViewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class AccommodationViewServiceImpl implements AccommodationViewService {

    private final AccommodationViewRepository viewRepository;

    @Override
    public List<AccommodationView> findAll() {
        return viewRepository.findAll();
    }
}
