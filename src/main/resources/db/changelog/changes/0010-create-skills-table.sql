--liquibase formatted SQL

--changeset anupam:0010-create-skills-table
CREATE TABLE skills(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    name CITEXT NOT NULL ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    CONSTRAINT uk_skills_name UNIQUE (name)
);

CREATE TABLE profiles_skills(
    user_id BIGINT NOT NULL REFERENCES profiles (user_id) ON DELETE CASCADE ,
    skill_id BIGINT NOT NULL REFERENCES skills (id) ON DELETE CASCADE ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    PRIMARY KEY (user_id, skill_id)
);

CREATE INDEX idx_profile_skills_skill_id ON profiles_skills (skill_id);
--rollback DROP TABLE profile_skills;
--rollback DROP TABLE skills;