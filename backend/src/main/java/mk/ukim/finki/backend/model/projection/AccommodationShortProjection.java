package mk.ukim.finki.backend.model.projection;

import mk.ukim.finki.backend.model.enumeration.Category;

public interface AccommodationShortProjection {
    Long getId();
    String getName();
    Category getCategory();
    Integer getNumRooms();
}
