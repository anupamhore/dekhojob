--liquibase formatted SQL

--changeset anupam:0008-create-experiences-table
CREATE TABLE experiences(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    user_id BIGINT NOT NULL REFERENCES profiles (user_id) ON DELETE CASCADE ,
    organization_id BIGINT REFERENCES organizations (id) ON DELETE SET NULL ,
    company_name VARCHAR(150) NOT NULL ,
    title VARCHAR(100) NOT NULL ,
    employment_type VARCHAR(20) ,
    location_id BIGINT REFERENCES locations (id),
    workplace_type VARCHAR(10),
    start_date DATE NOT NULL ,
    end_date DATE,
    description VARCHAR(2000),
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT chk_experiences_dates CHECK ( end_date IS NULL OR end_date >= start_date),
    CONSTRAINT chk_experiences_employment_type CHECK ( employment_type IS NULL  OR employment_type IN
                        ('FULL_TIME','PART_TIME','SELF_EMPLOYED','FREELANCE','CONTRACT','INTERNSHIP','APPRENTICESHIP','SEASONAL')),
    CONSTRAINT chk_experiences_workplace_type CHECK ( workplace_type IS NULL OR workplace_type IN
       ('ON_SITE','HYBRID','REMOTE'))
);

CREATE INDEX idx_experiences_user_id ON experiences (user_id);
CREATE INDEX idx_experiences_organization_id ON experiences (organization_id);
CREATE INDEX  idx_experiences_location_id ON experiences (location_id);
-- rollback DROP TABLE experiences;

