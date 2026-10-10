--liquibase formatted sql

--changeset anupam:0002-create-users-table
CREATE TABLE users (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    email CITEXT NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    first_name VARCHAR(100) NOT NULL ,
    last_name VARCHAR(100) NOT NULL,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT uk_user_email         UNIQUE (email),
    CONSTRAINT chk_users_first_name  CHECK ( btrim(first_name) <> ''),
    CONSTRAINT chk_users_last_name   CHECK ( btrim(last_name) <> '')
);
--rollback DROP TABLE users;