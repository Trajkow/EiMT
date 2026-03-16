package mk.ukim.finki.backend.model.dto.outputDTO;

import mk.ukim.finki.backend.model.enitites.Accommodation;
import mk.ukim.finki.backend.model.enumeration.Category;

import java.util.List;

public record DisplayAccommodationDTO(
        Long id,
        String name,
        Category category,
        Long hostId,
        Integer numRooms,
        Boolean isRented
) {

    public static DisplayAccommodationDTO from(Accommodation accommodation){
        return new DisplayAccommodationDTO(
          accommodation.getId(),
          accommodation.getName(),
          accommodation.getCategory(),
          accommodation.getHost().getId(),
          accommodation.getNumRooms(),
            accommodation.getIsRented()
        );
    }

    public static List<DisplayAccommodationDTO> from(List<Accommodation>  accommodations){
        return accommodations.stream()
                .map(DisplayAccommodationDTO::from)
                .toList();
    }

}
