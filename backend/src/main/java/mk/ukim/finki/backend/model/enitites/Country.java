package mk.ukim.finki.backend.model.enitites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Country extends BaseEntity {

    private String name;

    private String continent;
}
