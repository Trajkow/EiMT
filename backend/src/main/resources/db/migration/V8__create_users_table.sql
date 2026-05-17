CREATE TABLE users
(
    id       BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role     VARCHAR(30)  NOT NULL DEFAULT 'ROLE_USER'
);

-- Seed admin user: username=admin, password=admin (BCrypt of "admin")
INSERT INTO users (username, password, role)
VALUES ('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iAt6Bud6', 'ROLE_ADMINISTRATOR');
