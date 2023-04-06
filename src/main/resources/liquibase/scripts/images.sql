-- liquibase formatted sql

-- changeset team_unit:2

CREATE TABLE IF NOT EXISTS image
(
    id         BIGSERIAL PRIMARY KEY,
    image      bytea   NOT NULL,
    file_size  integer NOT NULL,
    media_type text,
    ads_id      integer REFERENCES ads (id),
    user_id     integer REFERENCES users (id),
    file_path VARCHAR NOT NULL
);
