--liquibase formatted sql

--changeset anupam:0011-add-version-to-profiles
ALTER TABLE profiles ADD COLUMN version BIGINT NOT NULL DEFAULT 0;
-- rollback ALTER TABLE profiles DROP COLUMN version;