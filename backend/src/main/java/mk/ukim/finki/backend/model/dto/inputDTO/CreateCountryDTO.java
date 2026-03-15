package mk.ukim.finki.backend.model.dto.inputDTO;

import mk.ukim.finki.backend.model.enitites.Country;

public record CreateCountryDTO(
    String name,
    String continent
) {

    public Country toCountry(){
        return new Country(name, continent);
    }

}
