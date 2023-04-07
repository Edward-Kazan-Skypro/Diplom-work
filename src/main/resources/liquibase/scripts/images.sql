-- liquibase formatted sql

-- changeset team_unit:2

CREATE TABLE IF NOT EXISTS image
(
    id         BIGSERIAL PRIMARY KEY,
    image      bytea   NOT NULL,
    file_size  integer NOT NULL,
    media_type text,
    ads_id integer REFERENCES ads (id),
    user_id integer REFERENCES user (id),
    file_path VARCHAR NOT NULL
);

/*--changeset team_unit:2
ALTER TABLE image
    ADD COLUMN ads_id integer REFERENCES ads (id);

ALTER TABLE image
    ADD COLUMN user_id integer REFERENCES user (id);

--changeset team_unit:2
ALTER TABLE image
    ADD COLUMN file_path VARCHAR NOT NULL;*/

/*CREATE TABLE IF NOT EXISTS image
(
    id         BIGSERIAL PRIMARY KEY,
    image      bytea   NOT NULL,
    file_size  integer NOT NULL,
    media_type text,
    ads_id      integer REFERENCES ads (id),
    user_id     integer REFERENCES users (id),
    file_path VARCHAR NOT NULL
);*/
