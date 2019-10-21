create sequence "Passenger_id_seq"
    as integer
    maxvalue 2147483647;

alter sequence "Passenger_id_seq" owner to postgres;

create sequence "Roles_id_seq"
    as integer
    maxvalue 2147483647;

alter sequence "Roles_id_seq" owner to postgres;

create sequence "Stations_id_seq"
    as integer
    maxvalue 2147483647;

alter sequence "Stations_id_seq" owner to postgres;

create sequence "Tickets_id_seq"
    as integer
    maxvalue 2147483647;

alter sequence "Tickets_id_seq" owner to postgres;

create sequence "TrainTrip_id_seq"
    as integer
    maxvalue 2147483647;

alter sequence "TrainTrip_id_seq" owner to postgres;

create sequence "Users_id_seq"
    as integer
    maxvalue 2147483647;

alter sequence "Users_id_seq" owner to postgres;

create sequence "Wagon_Types_id_seq"
    as integer
    maxvalue 2147483647;

alter sequence "Wagon_Types_id_seq" owner to postgres;

create sequence hibernate_sequence;

alter sequence hibernate_sequence owner to postgres;

create sequence routes_id_seq
    as integer
    maxvalue 2147483647;

alter sequence routes_id_seq owner to postgres;

create sequence route_station_id_seq
    as integer
    maxvalue 2147483647;

alter sequence route_station_id_seq owner to postgres;

create table passengers
(
    id         integer     not null
        constraint passengers_pkey
            primary key,
    first_name varchar(15) not null,
    last_name  varchar(15),
    birth_date date        not null,
    gender     char        not null
);

alter table passengers
    owner to postgres;

create table roles
(
    id   integer     not null
        constraint roles_pkey
            primary key,
    role varchar(20) not null
        constraint role
            unique
);

alter table roles
    owner to postgres;

create table stations
(
    id   integer     not null
        constraint "Stations_pkey"
            primary key,
    name varchar(50) not null
);

alter table stations
    owner to postgres;

create table users
(
    id       integer              not null
        constraint "Users_pkey"
            primary key,
    username varchar(20)          not null,
    password varchar(20)          not null,
    mail     varchar(30)          not null,
    role_id  integer,
    active   boolean default true not null
);

alter table users
    owner to postgres;

create table wagons
(
    id           integer not null
        constraint "Wagon_Types_pkey"
            primary key,
    seats_number integer not null,
    name         varchar(50)
);

comment on table wagons is 'Store types of wagons.';

alter table wagons
    owner to postgres;

create table users_roles
(
    user_id integer not null
        constraint "FK_user_id"
            references users
            on delete cascade,
    role_id integer not null
        constraint "FK_role_id"
            references roles
            on delete cascade
);

alter table users_roles
    owner to postgres;

create table routes
(
    id               integer     not null
        constraint routes_pkey
            primary key,
    route_number     varchar(50),
    name             varchar(50) not null,
    schedule_pattern varchar(50) not null
);

alter table routes
    owner to postgres;

create table trips
(
    id         integer               not null
        constraint "TrainTrip_pkey"
            primary key,
    start_date date                  not null,
    canceled   boolean default false not null,
    route_id   integer
        constraint "FK_route_id"
            references routes
);

alter table trips
    owner to postgres;

create table tickets
(
    id               integer              not null
        constraint "Tickets_pkey"
            primary key,
    passenger_id     integer
        constraint "FK_passenger_id"
            references passengers,
    trip_id          integer              not null
        constraint "FK_trip_id"
            references trips,
    start_station_id integer              not null,
    end_station_id   integer              not null,
    wagon_number     integer              not null,
    seat_number      integer              not null,
    booked           boolean default true not null,
    price            double precision
);

alter table tickets
    owner to postgres;

create index "fki_FK_trip_id"
    on tickets (trip_id);

create table train_structure
(
    "TrainTrip_id" integer not null
        constraint "FK_TrainTrip_id"
            references trips
            on delete cascade,
    wagon_number   integer not null,
    wagon_id       integer not null
        constraint "FK_wagon_id"
            references wagons
            on delete cascade,
    create_date    date    not null
);

alter table train_structure
    owner to postgres;

create table route_station
(
    id             integer not null
        constraint route_station_pk
            primary key,
    route_id       integer
        constraint "FK_route_id"
            references routes
            deferrable,
    station_id     integer not null
        constraint "FK_station_id"
            references stations,
    arrival_time   integer,
    departure_time integer,
    index_in_route integer not null
);

alter table route_station
    owner to postgres;

