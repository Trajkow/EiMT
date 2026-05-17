package mk.ukim.finki.backend.model.enitites;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Getter
@NoArgsConstructor
@Table(name = "accommodation_stats_mv", schema = "public")
public class AccommodationStatsMV {

    @Id
    private String category;

    @Column(name = "total_accommodations")
    private Long totalAccommodations;

    @Column(name = "total_rooms")
    private Long totalRooms;

    @Column(name = "avg_rooms")
    private Double avgRooms;
}
