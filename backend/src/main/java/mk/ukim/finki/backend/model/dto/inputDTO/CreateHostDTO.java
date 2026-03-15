package mk.ukim.finki.backend.model.dto.inputDTO;

import mk.ukim.finki.backend.model.enitites.Country;
import mk.ukim.finki.backend.model.enitites.Host;

public record CreateHostDTO(
    String name,
    String surname,
    Long countryID
) {

    public Host toHost(Country country){
        return new Host(name, surname, country);
    }

}
