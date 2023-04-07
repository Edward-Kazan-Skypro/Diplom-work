-- liquibase formatted sql

-- changeset team_unit:1

CREATE TABLE IF NOT EXISTS ads_comment
(
    id         BIGSERIAL PRIMARY KEY,
    author_id  integer REFERENCES users (id),
    created_at bigint,
    text       text,
    ads_id     integer REFERENCES ads (id)
);