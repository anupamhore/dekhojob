--liquibase formatted sql

-- ci = case insensitive, normally we use it when we have
-- email property or username and customers enter as Anu@gmail.com or anu@gmail.com
--changeset anupam:0001-enable-citext
CREATE EXTENSION IF NOT EXISTS citext;
-- rollback DROP EXTENSION IF EXISTS citext;