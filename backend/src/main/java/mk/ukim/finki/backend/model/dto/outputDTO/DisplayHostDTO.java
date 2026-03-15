package mk.ukim.finki.backend.model.dto.outputDTO;

import mk.ukim.finki.backend.model.enitites.Host;

import java.util.List;

public record DisplayHostDTO(
    Long id,
    String name,
    String surname,
    Long countryId
) {

    public static DisplayHostDTO from(Host host){
        return new DisplayHostDTO(
                host.getId(),
                host.getName(),
                host.getSurname(),
                host.getCountry().getId()
        );
    }

    public static List<DisplayHostDTO> from(List<Host> hosts){
        return hosts.stream()
                .map(DisplayHostDTO::from)
                .toList();
    }

}
