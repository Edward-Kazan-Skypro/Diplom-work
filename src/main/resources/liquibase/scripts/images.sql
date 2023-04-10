-- liquibase formatted sql

-- changeset team_unit:3

CREATE TABLE IF NOT EXISTS image
(
    id         SERIAL PRIMARY KEY,
    image      bytea   NOT NULL,
    file_size  BIGINT NOT NULL,
    media_type text,
    ads_id     integer REFERENCES ads (id),
    file_path  VARCHAR NOT NULL
);