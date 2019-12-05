DROP TABLE IF EXISTS purchases;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS items;

DROP SEQUENCE IF EXISTS global_seq;

CREATE SEQUENCE global_seq START WITH 100000;

CREATE TABLE users
(
    id         INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name       VARCHAR                           NOT NULL,
    lastname   VARCHAR                           NOT NULL,
    age        INTEGER                           NOT NULL,
    email      VARCHAR                           NOT NULL,
    password   VARCHAR                           NOT NULL
);

CREATE UNIQUE INDEX users_unique_email_idx
    ON users (email);

CREATE TABLE items
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    name VARCHAR NOT NULL,
    cost INTEGER NOT NULL
);

CREATE UNIQUE INDEX items_unique_name_idx
    ON users (name);

CREATE TABLE purchases
(
    id INTEGER PRIMARY KEY DEFAULT nextval('global_seq'),
    user_id INTEGER NOT NULL,
    item_id INTEGER NOT NULL,
    count INTEGER NOT NULL,
    date TIMESTAMP NOT NULL,

    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE,
    FOREIGN KEY (item_id) REFERENCES items (id) ON DELETE CASCADE
);

CREATE INDEX purchases_date_idx
    ON purchases (date);

CREATE INDEX purchases_user_id_idx
    ON purchases (user_id);
