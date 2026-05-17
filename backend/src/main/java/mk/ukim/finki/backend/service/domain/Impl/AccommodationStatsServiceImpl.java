package mk.ukim.finki.backend.service.domain.Impl;

import lombok.AllArgsConstructor;
import mk.ukim.finki.backend.model.enitites.AccommodationStatsMV;
import mk.ukim.finki.backend.repository.AccommodationStatsMVRepository;
import mk.ukim.finki.backend.service.domain.AccommodationStatsService;
import org.springframework.stereotype.Service;

import jakarta.persistence.EntityManager;
import java.util.List;

@Service
@AllArgsConstructor
public class AccommodationStatsServiceImpl implements AccommodationStatsService {

    private final AccommodationStatsMVRepository statsRepository;
    private final EntityManager entityManager;

    @Override
    public List<AccommodationStatsMV> findAll() {
        return statsRepository.findAll();
    }

    @Override
    public void refreshMaterializedView() {
        entityManager.createNativeQuery("REFRESH MATERIALIZED VIEW CONCURRENTLY accommodation_stats_mv")
                .executeUpdate();
    }
}
