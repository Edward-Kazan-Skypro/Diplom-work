-- liquibase formatted sql

-- changeset team_unit:3

CREATE TABLE IF NOT EXISTS image
(
    id         SERIAL PRIMARY KEY,
    byte_Data  bytea   NOT NULL,
    file_size  BIGINT NOT NULL,
    media_type text,
    ads_id     integer REFERENCES ads (id),
    user_id    integer REFERENCES users (id),
    file_path  VARCHAR NOT NULL
);