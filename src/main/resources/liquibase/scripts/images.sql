-- liquibase formatted sql

-- changeset team_unit:3

CREATE TABLE IF NOT EXISTS image
(
    id         SERIAL PRIMARY KEY,
    image      bytea   NOT NULL,
    file_size  BIGINT NOT NULL,
    media_type text,
    file_path  VARCHAR NOT NULL
);


-- changeset team_unit:1
ALTER TABLE ads
    ADD COLUMN image_id integer REFERENCES image (id);

--changeset team_unit:2
ALTER TABLE image
    ADD COLUMN ads_id integer REFERENCES ads (id);
