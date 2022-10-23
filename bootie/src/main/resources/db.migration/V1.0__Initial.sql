
CREATE TABLE users_table
(
    id         BIGSERIAL PRIMARY KEY,
    user_name  TEXT NOT NULL,
    user_email TEXT NOT NULL
);
create table shoes
(
    id    BIGSERIAL PRIMARY KEY,
    size  BIGINT NOT NULL,
    model TEXT    NOT NULL,
    brand TEXT    NOT NULL
);
CREATE TABLE orders
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT REFERENCES users_table (id),
    shoes_id BIGINT REFERENCES shoes (id)
);