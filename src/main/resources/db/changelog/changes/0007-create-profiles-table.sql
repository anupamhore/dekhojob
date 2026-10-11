--liquibase formatted SQL

--changeset anupam:0007-create-profiles-table
CREATE TABLE profiles(
    user_id BIGINT PRIMARY KEY REFERENCES users (id) ON DELETE CASCADE ,
    slug CITEXT NOT NULL ,
    headline VARCHAR(220),
    about VARCHAR(2600),
    location_id BIGINT REFERENCES locations (id),
    photo_key VARCHAR(500),
    banner_key VARCHAR(500),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT uk_profiles_slug UNIQUE (slug)
);
CREATE INDEX idx_profiles_location_id ON profiles (location_id);
--rollback DROP TABLE profiles