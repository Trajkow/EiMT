package mk.ukim.finki.backend.service.scheduled;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.backend.service.domain.AccommodationStatsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@AllArgsConstructor
public class MaterializedViewRefreshService {

    private final AccommodationStatsService accommodationStatsService;

    @Scheduled(fixedRateString = "${app.mv.refresh-rate-ms:60000}")
    @Transactional
    public void refreshAccommodationStatsMV() {
        log.info("Refreshing accommodation_stats_mv materialized view...");
        accommodationStatsService.refreshMaterializedView();
        log.info("accommodation_stats_mv refreshed successfully.");
    }
}
