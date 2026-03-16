package mk.ukim.finki.backend.model.dto.inputDTO;

import mk.ukim.finki.backend.model.enitites.Accommodation;
import mk.ukim.finki.backend.model.enitites.Host;
import mk.ukim.finki.backend.model.enumeration.Category;

public record CreateAccommodationDTO(
        String name,
        Category category,
        Long HostId,
        Integer numRooms,
        Boolean isRented
) {
    public Accommodation toAccommodation(Host host){
        return new Accommodation(name, category, host, numRooms, isRented);
    }
}
