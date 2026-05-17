package mk.ukim.finki.backend.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mk.ukim.finki.backend.model.enitites.AccommodationStatsMV;
import mk.ukim.finki.backend.service.domain.AccommodationStatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Accommodation Stats", description = "Aggregated statistics from materialized view")
@RestController
@RequestMapping("/api/stats/accommodations")
@AllArgsConstructor
public class AccommodationStatsController {

    private final AccommodationStatsService statsService;

    @Operation(summary = "Get accommodation statistics per category",
            description = "Reads from the accommodation_stats_mv MATERIALIZED VIEW. Data is refreshed via scheduled job every 60s.")
    @GetMapping
    public ResponseEntity<List<AccommodationStatsMV>> findAll() {
        return ResponseEntity.ok(statsService.findAll());
    }
}
