package mk.ukim.finki.backend.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import mk.ukim.finki.backend.model.dto.inputDTO.CreateReservationDTO;
import mk.ukim.finki.backend.model.dto.outputDTO.DisplayReservationDTO;
import mk.ukim.finki.backend.service.application.ReservationApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Tag(name = "Reservations", description = "Operations for reserving accommodations")
@RestController
@RequestMapping("/api/reservations")
@AllArgsConstructor
public class ReservationController {

    private final ReservationApplicationService reservationApplicationService;

    @Operation(summary = "Get all reservations")
    @GetMapping
    public ResponseEntity<List<DisplayReservationDTO>> findAll() {
        return ResponseEntity.ok(reservationApplicationService.findAll());
    }

    @Operation(summary = "Reserve an accommodation")
    @PostMapping("/reserve/{accommodationId}")
    public ResponseEntity<?> reserve(
            @PathVariable Long accommodationId,
            @RequestBody CreateReservationDTO createReservationDTO,
            Principal principal) {
        System.out.println("RESERVATIOON CONTROLLLERRRR");
        System.console().printf("RESERVATION CONTROLLER");
        try {
            if (principal == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not authenticated");
            }
            DisplayReservationDTO dto = reservationApplicationService.reserve(
                    accommodationId,
                    principal.getName(),
                    createReservationDTO.reservedAt(),
                    createReservationDTO.releaseAt()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(dto);
        } catch (RuntimeException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
