package mk.ukim.finki.backend.web.controller;

import lombok.AllArgsConstructor;
import mk.ukim.finki.backend.model.dto.inputDTO.CreateAccommodationDTO;
import mk.ukim.finki.backend.model.dto.outputDTO.DisplayAccommodationDTO;
import mk.ukim.finki.backend.service.application.AccommodationApplicationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/a")
@AllArgsConstructor
public class AccommodationController {

//    todo
//    додавање ново сместување коешто може да се изнајми;
//    ажурирање на постоечки запис за одредено сместување;
//    бришење сместување кое веќе не е во добра состојба и не може да се изнајмува;
//    обележување сместување како изнајмено.

//    private final AccommodationService accommodationService;
    private final AccommodationApplicationService accService;

    @GetMapping
    public ResponseEntity<List<DisplayAccommodationDTO>> findAll(){
        return ResponseEntity.ok(accService.findAll());
    }

    @PostMapping("/add")
    public ResponseEntity<DisplayAccommodationDTO> save(@RequestBody CreateAccommodationDTO accommodationDTO){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(this.accService.create(accommodationDTO));
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<DisplayAccommodationDTO> edit(@PathVariable Long id, @RequestBody CreateAccommodationDTO accommodationDTO){
        return this.accService.update(id, accommodationDTO)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.notFound().build());
    }

    @PostMapping("/delete/{id}")
    public ResponseEntity<DisplayAccommodationDTO> delete(@PathVariable Long id){
        return this.accService.deleteById(id).map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @PutMapping("/rent/{id}")
    public ResponseEntity<DisplayAccommodationDTO> setRented(@PathVariable Long id){
        return this.accService.setRented(id)
                .map(ResponseEntity::ok)
                .orElseGet(()-> ResponseEntity.badRequest().build());
    }

    @GetMapping("/rented")
     public ResponseEntity<List<DisplayAccommodationDTO>> findRented(){
        return ResponseEntity.ok(this.accService.findRented());
    }

    @GetMapping("/available")
    public ResponseEntity<List<DisplayAccommodationDTO>> findAvailable(){
        return ResponseEntity.ok(this.accService.findAvailable());
    }

}
