-- liquibase formatted SQL

--changeset anupam:0009-create-educations-table
CREATE TABLE educations (

    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    user_id BIGINT NOT NULL REFERENCES profiles (user_id) ON DELETE CASCADE ,
    organization_id BIGINT REFERENCES organizations (id) ON DELETE SET NULL ,
    school_name VARCHAR(150) NOT NULL,
    degree VARCHAR(100),
    field_of_study VARCHAR(100),
    start_date DATE,
    end_date DATE,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),
    updated_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT chk_educations_date CHECK ( end_date IS NULL OR start_date IS NULL  OR end_date >= start_date)
);
CREATE INDEX idx_educations_user_id ON educations (user_id);
CREATE INDEX idx_educations_organization_id ON educations (organization_id);

--rollback DROP TABLE educations;