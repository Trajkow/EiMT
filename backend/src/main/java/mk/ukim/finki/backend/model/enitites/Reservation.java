package mk.ukim.finki.backend.model.enitites;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reservation extends BaseEntity {

    @ManyToOne
    private Accommodation accommodation;

    @ManyToOne
    private AppUser user;

    private LocalDateTime reservedAt;

    private LocalDateTime releaseAt;
}
