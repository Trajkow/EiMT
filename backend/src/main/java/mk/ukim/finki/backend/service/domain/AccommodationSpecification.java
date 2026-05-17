package mk.ukim.finki.backend.service.domain;

import mk.ukim.finki.backend.model.enitites.Accommodation;
import mk.ukim.finki.backend.model.enumeration.Category;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class AccommodationSpecification {

    public static Specification<Accommodation> buildSpecification(
            Category category,
            Long hostId,
            String country,
            Integer numRooms,
            Boolean hasAvailableRooms) {

        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (category != null) {
                predicates.add(cb.equal(root.get("category"), category));
            }
            if (hostId != null) {
                predicates.add(cb.equal(root.get("host").get("id"), hostId));
            }
            if (country != null && !country.isBlank()) {
                predicates.add(cb.equal(
                        cb.lower(root.get("host").get("country").get("name")),
                        country.toLowerCase()
                ));
            }
            if (numRooms != null) {
                predicates.add(cb.equal(root.get("numRooms"), numRooms));
            }
            if (Boolean.TRUE.equals(hasAvailableRooms)) {
                predicates.add(cb.isFalse(root.get("isRented")));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
