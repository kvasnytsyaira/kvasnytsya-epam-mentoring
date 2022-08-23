CREATE TABLE user_account
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users_table (id),
    wallet  BIGINT NOT NULL
);

ALTER TABLE events
    ADD COLUMN price BIGINT;