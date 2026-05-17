package mk.ukim.finki.backend.model.enitites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Getter
@NoArgsConstructor
@Table(name = "accommodation_view")
public class AccommodationView {

    @Id
    private Long id;

    private String name;

    private String category;

    @Column(name = "num_rooms")
    private Integer numRooms;

    @Column(name = "host_full_name")
    private String hostFullName;

    @Column(name = "country_name")
    private String countryName;
}
