CREATE TABLE users_table
(    id         BIGSERIAL PRIMARY KEY,
    user_name  TEXT NOT NULL,
    user_email TEXT NOT NULL);
CREATE TABLE events(
    id         BIGSERIAL PRIMARY KEY,
    title      TEXT NOT NULL,
    event_date TEXT NOT NULL);
CREATE TABLE tickets(
    id       BIGSERIAL PRIMARY KEY,
    event_id BIGINT REFERENCES events (id),
    user_id  BIGINT REFERENCES users_table (id),
    category TEXT NOT NULL,
    place    TEXT NOT NULL);
