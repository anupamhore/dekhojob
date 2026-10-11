--liquibase formatted SQL

--changeset anupam:0006-create-organizaiton-admins-table
CREATE TABLE organizations_admins(
    organization_id BIGINT NOT NULL REFERENCES organizations (id) ON DELETE CASCADE ,
    user_id BIGINT NOT NULL REFERENCES users (id) ON DELETE CASCADE ,
    role VARCHAR(20) NOT NULL ,
    created_at TIMESTAMPTZ NOT NULL DEFAULT now(),

    PRIMARY KEY (organization_id, user_id),
    CONSTRAINT chk_organizations_admins_role CHECK ( role IN ('SUPER_ADMIN', 'CONTENT_ADMIN', 'ANALYST'))
);

CREATE INDEX idx_organization_admins_user_id ON organizations_admins (user_id);

--rollback DROP TABLE organizations_admins;