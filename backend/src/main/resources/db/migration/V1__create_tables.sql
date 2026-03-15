create table Country(
    id BIGSERIAL primary key,
    name varchar(100),
    continent varchar(100)
);

create table Host(
    id BIGSERIAL primary key,
    created_at DATE,
    updated_at DATE,
    name varchar(100),
    surname varchar(100),
    country_id BIGINT,
    constraint fk_host_country foreign key (country_id) references country(id)
);

create type accommodation_category as enum(
    'ROOM',
    'HOUSE',
    'FLAT',
    'APARTMENT',
    'HOTEL',
    'MOTEL'
);

create table Accommodation(
    id BIGSERIAL primary key,
    created_at DATE,
    updated_at DATE,
    name varchar(100),
    category varchar(100),
    host_id BIGINT,
    num_rooms INT,
    constraint fk_accommodation_host foreign key (host_id) references Host(id)
)