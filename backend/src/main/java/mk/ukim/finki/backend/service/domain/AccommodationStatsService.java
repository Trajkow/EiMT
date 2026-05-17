package mk.ukim.finki.backend.service.domain;

import mk.ukim.finki.backend.model.enitites.AccommodationStatsMV;

import java.util.List;

public interface AccommodationStatsService {
    List<AccommodationStatsMV> findAll();
    void refreshMaterializedView();
}
