-- liquibase formatted sql

-- changeset team_unit:4

CREATE TABLE IF NOT EXISTS users
(
    id         SERIAL PRIMARY KEY,
    email      varchar NOT NULL,
    password   varchar NOT NULL,
    first_name varchar NOT NULL,
    last_name  varchar NOT NULL,
    phone      varchar NOT NULL,
    role       varchar NOT NULL
);
