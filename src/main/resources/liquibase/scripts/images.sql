-- liquibase formatted sql

-- changeset unit:3

CREATE TABLE IF NOT EXISTS image
(
    id         BIGSERIAL PRIMARY KEY,
    image      bytea   NOT NULL,
    file_size  BIGINT NOT NULL,
    media_type text
);

-- changeset unit:2
ALTER TABLE ads
    ADD COLUMN image_id BIGINT REFERENCES image (id);

-- changeset unit:5
ALTER TABLE users
    ADD COLUMN image_id BIGINT REFERENCES image (id);

--changeset unit:1
ALTER TABLE image
ADD COLUMN ads_id BIGINT REFERENCES ads (id);

--changeset unit:6
ALTER TABLE image
ADD COLUMN user_id BIGINT REFERENCES users (id);


--changeset unit:4
ALTER TABLE image
ADD COLUMN file_path VARCHAR NOT NULL;