package mk.ukim.finki.backend.model.projection;

import mk.ukim.finki.backend.model.enumeration.Category;
import org.springframework.beans.factory.annotation.Value;

public interface AccommodationDetailedProjection {
    Long getId();
    String getName();
    Category getCategory();
    Integer getNumRooms();

    @Value("#{target.host.name}")
    String getHostName();

    @Value("#{target.host.surname}")
    String getHostSurname();

    @Value("#{target.host.country.name}")
    String getCountryName();
}
