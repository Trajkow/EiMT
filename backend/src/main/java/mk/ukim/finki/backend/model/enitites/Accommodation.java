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
public class Accommodation extends withTimeEntity {

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne
    private Host host;

    private Integer numRooms;

}
