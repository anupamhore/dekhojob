--liquibase formatted sql

--changeset anupam:0012-backfill-missing-profiles
INSERT INTO profiles (user_id, slug)
SELECT u.id,
       trim(both '-' FROM regexp_replace(lower(u.first_name || '-' || u.last_name), '[^a-z0-9]+', '-','g'))
       || '-' || u.id
FROM users u
LEFT JOIN profiles p ON p.user_id = u.id
WHERE p.user_id IS NULL;
--rollback not required