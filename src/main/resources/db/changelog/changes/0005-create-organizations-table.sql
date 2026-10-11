--liquibase formatted SQL

--changeset anupam:0005-create-organizations-table
CREATE TABLE organizations(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    type VARCHAR(20) NOT NULL ,
    name VARCHAR(150) NOT NULL ,
    slug CITEXT NOT NULL ,
    tagline VARCHAR(120),
    description VARCHAR(2000),
    website VARCHAR(255),
    industry VARCHAR(100),
    size_range VARCHAR(20),
    headquarters_location_id BIGINT REFERENCES locations (id),
    founded_year SMALLINT,
    logo_key VARCHAR(500),
    banner_key VARCHAR(500),
    is_verified BOOLEAN NOT NULL DEFAULT false,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT uk_orgaizations_slug UNIQUE (slug),
    CONSTRAINT chk_organizations_type CHECK ( type IN ('COMPANY', 'SCHOOL')),
    CONSTRAINT chk_organizations_size CHECK ( size_range IS NULL OR size_range IN
        ('1-10','11-50','51-200','201-500','501-1000','1001-5000','5001-10000','10001+')),
    CONSTRAINT chk_organizations_founded CHECK ( founded_year IS NULL OR founded_year BETWEEN 1600 AND 2100)
);
CREATE INDEX idx_organizations_headquarters_location_id ON organizations (headquarters_location_id);
--rollback DROP TABLE organizations;