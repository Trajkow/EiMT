CREATE TABLE reservation (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    accommodation_id BIGINT NOT NULL UNIQUE,
    reserved_at TIMESTAMP NOT NULL,
    release_at TIMESTAMP NOT NULL,
    CONSTRAINT fk_reservation_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_reservation_accommodation FOREIGN KEY (accommodation_id) REFERENCES accommodation(id)
);
