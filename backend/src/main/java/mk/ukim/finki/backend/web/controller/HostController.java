package mk.ukim.finki.backend.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.dto.inputDTO.CreateHostDTO;
import mk.ukim.finki.backend.model.dto.outputDTO.DisplayHostDTO;
import mk.ukim.finki.backend.service.application.HostApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Hosts", description = "CRUD operations for hosts")
@RestController
@RequestMapping("/api/hosts")
@RequiredArgsConstructor
public class HostController {

    private final HostApplicationService hostService;

    @Operation(summary = "List all hosts")
    @GetMapping
    public ResponseEntity<List<DisplayHostDTO>> findAll() {
        return ResponseEntity.ok(hostService.findAll());
    }

    @Operation(summary = "Get host by ID")
    @GetMapping("/{id}")
    public ResponseEntity<DisplayHostDTO> findById(@PathVariable Long id) {
        return hostService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Create a new host")
    @PostMapping("/add")
    public ResponseEntity<DisplayHostDTO> create(@RequestBody CreateHostDTO hostDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(hostService.create(hostDTO));
    }

    @Operation(summary = "Update an existing host")
    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayHostDTO> update(@PathVariable Long id, @RequestBody CreateHostDTO hostDTO) {
        return hostService.update(id, hostDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a host")
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<DisplayHostDTO> delete(@PathVariable Long id) {
        return hostService.deleteById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}

