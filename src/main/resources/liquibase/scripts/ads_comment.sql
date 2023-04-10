-- liquibase formatted sql

-- changeset unit:4

CREATE TABLE IF NOT EXISTS ads_comment
(
    id         BIGSERIAL PRIMARY KEY,
    author_id  BIGINT REFERENCES users (id),
    created_at timestamp,
    text       text,
    ads_id     BIGINT REFERENCES ads (id)
);

