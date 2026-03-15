package mk.ukim.finki.backend.model.enitites;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public class withTimeEntity extends BaseEntity {

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
