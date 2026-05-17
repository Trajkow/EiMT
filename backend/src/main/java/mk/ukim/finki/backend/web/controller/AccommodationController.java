package mk.ukim.finki.backend.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mk.ukim.finki.backend.model.dto.inputDTO.CreateAccommodationDTO;
import mk.ukim.finki.backend.model.dto.outputDTO.DisplayAccommodationDTO;
import mk.ukim.finki.backend.model.enumeration.Category;
import mk.ukim.finki.backend.model.projection.AccommodationDetailedProjection;
import mk.ukim.finki.backend.model.projection.AccommodationShortProjection;
import mk.ukim.finki.backend.service.application.AccommodationApplicationService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Accommodations", description = "CRUD and search operations for accommodations")
@RestController
@RequestMapping("/api/a")
@AllArgsConstructor
public class AccommodationController {

    private final AccommodationApplicationService accService;

    @Operation(summary = "List all accommodations")
    @GetMapping
    public ResponseEntity<List<DisplayAccommodationDTO>> findAll() {
        return ResponseEntity.ok(accService.findAll());
    }

    @Operation(summary = "Search & filter accommodations with pagination",
            description = "Supports filters: category, hostId, country, numRooms, hasAvailableRooms. Sort by 'name' or 'createdAt'.")
    @GetMapping("/search")
    public ResponseEntity<Page<AccommodationShortProjection>> search(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDir,
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) Long hostId,
            @RequestParam(required = false) String country,
            @RequestParam(required = false) Integer numRooms,
            @RequestParam(required = false) Boolean hasAvailableRooms
    ) {
        return ResponseEntity.ok(
                accService.findFiltered(page, size, sortBy, sortDir,category, hostId, country, numRooms, hasAvailableRooms));
    }

    @Operation(summary = "Get detailed accommodation list (uses EntityGraph + projection)",
            description = "Returns extended info including host name/surname and country via EntityGraph to avoid N+1.")
    @GetMapping("/detailed")
    public ResponseEntity<List<AccommodationDetailedProjection>> findAllDetailed() {
        return ResponseEntity.ok(accService.findAllDetailed());
    }

    @Operation(summary = "Create a new accommodation")
    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDTO> save(@RequestBody CreateAccommodationDTO accommodationDTO) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.accService.create(accommodationDTO));
    }

    @Operation(summary = "Update an existing accommodation")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAccommodationDTO> edit(@PathVariable Long id,
                                                        @RequestBody CreateAccommodationDTO accommodationDTO) {
        return this.accService.update(id, accommodationDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete an accommodation")
    @PostMapping("/delete/{id}")
    public ResponseEntity<DisplayAccommodationDTO> delete(@PathVariable Long id) {
        return this.accService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Mark accommodation as rented (fires rental event, decrements numRooms)")
    @PutMapping("/rent/{id}")
    public ResponseEntity<DisplayAccommodationDTO> setRented(@PathVariable Long id) {
        return this.accService.setRented(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "List rented accommodations")
    @GetMapping("/rented")
    public ResponseEntity<List<DisplayAccommodationDTO>> findRented() {
        return ResponseEntity.ok(this.accService.findRented());
    }

    @Operation(summary = "List available accommodations")
    @GetMapping("/available")
    public ResponseEntity<List<DisplayAccommodationDTO>> findAvailable() {
        return ResponseEntity.ok(this.accService.findAvailable());
    }

    @Operation(summary = "List most popular accommodations sorted by rent count (descending)")
    @GetMapping("/popular")
    public ResponseEntity<List<DisplayAccommodationDTO>> findMostPopular() {
        return ResponseEntity.ok(this.accService.findMostPopular());
    }
}
