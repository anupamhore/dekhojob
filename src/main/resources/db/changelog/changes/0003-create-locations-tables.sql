--liquibase formatted SQL

--changeset anupam:0003-create-locations-table
CREATE TABLE countries(
    code CHAR(2) PRIMARY KEY ,
    name VARCHAR(100) NOT NULL ,

    CONSTRAINT uk_countries_name UNIQUE (name)
);

-- rollback DROP TABLE countries;

CREATE TABLE locations(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    city VARCHAR(100) NOT NULL ,
    region VARCHAR(100),
    country_code CHAR(2) NOT NULL REFERENCES countries (code) ,
    latitude NUMERIC(9,6),
    longitude NUMERIC(9,6),

    CONSTRAINT uk_locations_place UNIQUE NULLS NOT DISTINCT (city, region, country_code),
    CONSTRAINT chk_locations_latitude CHECK ( latitude IS NULL OR latitude BETWEEN -90 AND 90),
    CONSTRAINT chk_locations_longitude CHECK ( longitude IS NULL OR longitude BETWEEN -180 AND 180)
);
CREATE INDEX idx_locations_country_code ON locations (country_code);
-- rollback DROP TABLE locations;