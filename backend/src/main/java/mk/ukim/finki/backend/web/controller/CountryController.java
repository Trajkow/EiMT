package mk.ukim.finki.backend.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.dto.inputDTO.CreateCountryDTO;
import mk.ukim.finki.backend.model.dto.outputDTO.DisplayCountryDTO;
import mk.ukim.finki.backend.service.application.CountryApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Countries", description = "CRUD operations for countries")
@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryApplicationService countryService;

    @Operation(summary = "List all countries")
    @GetMapping
    public ResponseEntity<List<DisplayCountryDTO>> findAll() {
        return ResponseEntity.ok(countryService.findAll());
    }

    @Operation(summary = "Get country by ID")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayCountryDTO> findById(@PathVariable Long id) {
        return countryService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new country")
    @PostMapping("/add")
    public ResponseEntity<DisplayCountryDTO> create(@RequestBody CreateCountryDTO countryDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(countryService.create(countryDTO));
    }

    @Operation(summary = "Update an existing country")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayCountryDTO> update(@PathVariable Long id, @RequestBody CreateCountryDTO countryDTO) {
        return countryService.update(id, countryDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a country")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DisplayCountryDTO> delete(@PathVariable Long id) {
        return countryService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}

