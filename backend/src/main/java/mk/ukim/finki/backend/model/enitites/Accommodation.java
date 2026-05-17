package mk.ukim.finki.backend.model.enitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import mk.ukim.finki.backend.model.enumeration.Category;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@NamedEntityGraph(
        name = "Accommodation.withHostAndCountry",
        attributeNodes = @NamedAttributeNode(value = "host", subgraph = "host-subgraph"),
        subgraphs = @NamedSubgraph(
                name = "host-subgraph",
                attributeNodes = @NamedAttributeNode("country")
        )
)
public class Accommodation extends withTimeEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Host host;

    private Integer numRooms;

    private Boolean isRented;

    private Integer rentCount = 0;
}
