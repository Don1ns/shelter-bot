-- liquibase formatted sql

CREATE TABLE dog
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name          VARCHAR,
    breed         VARCHAR,
    year_of_birth INTEGER,
    description   VARCHAR
);

CREATE TABLE cat
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name          VARCHAR,
    breed         VARCHAR,
    year_of_birth INTEGER,
    description   VARCHAR
);

CREATE TABLE cat_owner
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name          VARCHAR,
    year_of_birth INTEGER,
    phone         VARCHAR,
    mail          VARCHAR,
    address       VARCHAR,
    chat_id       BIGINT,
    cat_id        BIGINT REFERENCES cat (id),
    status        VARCHAR
);

CREATE TABLE dog_owner
(
    id            BIGINT GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    name          VARCHAR,
    year_of_birth INTEGER,
    phone         VARCHAR,
    mail          VARCHAR,
    address       VARCHAR,
    chat_id       BIGINT,
    dog_id        BIGINT REFERENCES gog (id),
    status        VARCHAR
);

CREATE TABLE contexts
(
    chat_id BIGSERIAL PRIMARY KEY,
    shelter    VARCHAR,
    cat_owner_id   BIGINT REFERENCES cat_owner (id),
    dog_owner_id   BIGINT REFERENCES dog_owner (id)
);