package mk.ukim.finki.backend.model.enitites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Host extends withTimeEntity {

    private String name;
    private String surname;

    @ManyToOne
    private Country country;

}
