-- Countries
INSERT INTO Country
(
    name,
    continent
) VALUES
      ('Macedonia','Europe'),
      ( 'Germany', 'Europe'),
      ( 'France', 'Europe'),
      ( 'USA', 'North America'),
      ( 'Japan', 'Asia');

-- Hosts
INSERT INTO Host
(
    created_at,
    updated_at,
    name,
    surname,
    country_id
) VALUES
    (now(), now(), 'Marko', 'Markovski', 1),
    (now(), now(), 'Hans', 'Müller', 2),
    (now(), now(), 'Pierre', 'Dupont', 3),
    (now(), now(), 'John', 'Smith', 4),
    (now(), now(), 'Yuki', 'Tanaka', 5);

-- Accommodations
INSERT INTO Accommodation
(
     created_at,
     updated_at,
     name,
     category,
     host_id,
     num_rooms
) VALUES
    (now(), now(), 'Ohrid Lake View', 'APARTMENT', 1, 3),
    (now(), now(), 'Berlin Central', 'HOTEL', 2, 20),
    (now(), now(), 'Paris Flat', 'FLAT', 3, 2),
    (now(), now(), 'New York Room', 'ROOM', 4, 1),
    (now(), now(), 'Tokyo House', 'HOUSE', 5, 5),
    (now(), now(), 'Skopje Motel', 'MOTEL', 1, 10),
    (now(), now(), 'Munich Apartment', 'APARTMENT', 2, 4),
    (now(), now(), 'Nice Hotel', 'HOTEL', 3, 15);
