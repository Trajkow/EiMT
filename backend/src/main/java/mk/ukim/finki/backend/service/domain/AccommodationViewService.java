package mk.ukim.finki.backend.service.domain;

import mk.ukim.finki.backend.model.enitites.AccommodationView;

import java.util.List;

public interface AccommodationViewService {
    List<AccommodationView> findAll();
}
