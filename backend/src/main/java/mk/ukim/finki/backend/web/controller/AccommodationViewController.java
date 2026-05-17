package mk.ukim.finki.backend.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mk.ukim.finki.backend.model.enitites.AccommodationView;
import mk.ukim.finki.backend.service.domain.AccommodationViewService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Accommodation View", description = "Read-only data from accommodation_view (SQL VIEW)")
@RestController
@RequestMapping("/api/view/accommodations")
@AllArgsConstructor
public class AccommodationViewController {

    private final AccommodationViewService viewService;

    @Operation(summary = "Get all accommodations from the DB view",
            description = "Reads from the accommodation_view SQL VIEW which joins accommodation, host, and country.")
    @GetMapping
    public ResponseEntity<List<AccommodationView>> findAll() {
        return ResponseEntity.ok(viewService.findAll());
    }
}
