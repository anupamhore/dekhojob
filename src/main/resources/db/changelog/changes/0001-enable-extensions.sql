--liquibase formatted sql

--changeset anupam:0001-enable-citext
CREATE EXTENSION IF NOT EXISTS citext;
-- rollback DROP EXTENSION IF EXISTS citext;