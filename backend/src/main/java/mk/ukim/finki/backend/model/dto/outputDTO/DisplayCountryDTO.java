package mk.ukim.finki.backend.model.dto.outputDTO;

import mk.ukim.finki.backend.model.enitites.Country;

import java.util.List;

public record DisplayCountryDTO(Long id, String name, String continent) {

    public static DisplayCountryDTO from(Country country){
        return new DisplayCountryDTO(
                country.getId(),
                country.getName(),
                country.getContinent()
        );
    }

    public static List<DisplayCountryDTO> from(List<Country> countries){
        return countries.stream()
                .map(DisplayCountryDTO::from)
                .toList();
    }

}
