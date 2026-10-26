-- Organizations (companies and schools), headquarters looked up by city
INSERT INTO organizations (type, name, slug, industry, size_range, headquarters_location_id, is_verified)
SELECT v.type, v.name, v.slug, v.industry, v.size_range, l.id, true
FROM (VALUES
 ('COMPANY', 'Grab','grab', 'Technology', '10001+', 'Singapore'),
 ('COMPANY','Shopee','shopee','E-Commerce','10001+','Singapore'),
 ('COMPANY','Maybank','maybank','Banking','10001+','Kuala Lumpur'),
 ('SCHOOL','Universiti Malaya', 'universiti-malaya','Education','5001-10000','Kuala Lumpur'),
 ('SCHOOL','NUS', 'nus', 'Eductation','10001+','Singapore')
) AS v(type, name, slug, industry, size_range, city)
JOIN locations l ON l.city = v.city;

-- Page admins: Alice runs Shopee's page, Bob helps with Grab's content
INSERT INTO organizations_admins (organization_id, user_id, role)
SELECT o.id, u.id, v.role
FROM (VALUES
  ('shopee','alice@example.com','SUPER_ADMIN'),
  ('grab','bob@example.com','CONTENT_ADMIN')
) AS v(org_slug, email, role)
JOIN organizations o ON o.slug = v.org_slug
JOIN users u ON u.email = v.email;

-- Profiles (Eve deliberately gets none)
INSERT INTO profiles (user_id, slug, headline, about, location_id)
SELECT u.id, v.slug, v.headline, v.about, l.id
FROM (VALUES
   ('alice@example.com', 'alice-tan', 'Backend Engineer | Java & Spring', 'I build scalable backend systems.', 'Kuala Lumpur'),
   ('bob@example.com',   'bob-lee',   'Data Scientist',                   'Turning data into decisions.',      'Singapore'),
   ('chen@example.com',  'chen-wei',  'Engineering Manager',              NULL,                                'Bengaluru'),
   ('dina@example.com',  'dina-kumar', NULL,                              NULL,                                NULL)
) AS v(email, slug, headline, about, city)
JOIN users u ON u.email = v.email
LEFT JOIN locations l ON l.city = v.city;

-- Experiences: linked to a page when one exists, plain text when not
INSERT INTO experiences (user_id, organization_id, company_name, title, employment_type,
                         location_id, workplace_type, start_date, end_date)
SELECT u.id, o.id, v.company_name, v.title, v.employment_type,
       l.id, v.workplace_type, v.start_date::date, v.end_date::date
FROM (VALUES
    ('alice@example.com', 'Grab',         'Software Engineer',        'FULL_TIME', 'Singapore',    'HYBRID',  '2021-03-01', '2023-06-01'),
    ('alice@example.com', 'Shopee',       'Senior Software Engineer', 'FULL_TIME', 'Kuala Lumpur', 'ON_SITE', '2023-07-01', NULL),
    ('bob@example.com',   'Maybank',      'Data Analyst',             'FULL_TIME', 'Kuala Lumpur', 'ON_SITE', '2020-01-01', '2022-12-01'),
    ('bob@example.com',   'Grab',         'Data Scientist',           'FULL_TIME', 'Singapore',    'REMOTE',  '2023-01-01', NULL),
    ('chen@example.com',  'Tiny Startup', 'Engineering Manager',      'CONTRACT',  'Bengaluru',    'HYBRID',  '2019-05-01', NULL)
) AS v(email, company_name, title, employment_type, city, workplace_type, start_date, end_date)
JOIN users u              ON u.email = v.email
LEFT JOIN organizations o ON o.name = v.company_name AND o.type = 'COMPANY'
LEFT JOIN locations l     ON l.city = v.city;

-- Educations: IIT Bombay has no page, so organization_id stays NULL
INSERT INTO educations (user_id, organization_id, school_name, degree, field_of_study, start_date, end_date)
SELECT u.id, o.id, v.school_name, v.degree, v.field, v.start_date::date, v.end_date::date
FROM (VALUES
   ('alice@example.com', 'Universiti Malaya', 'BSc',   'Computer Science',       '2016-09-01', '2020-07-01'),
   ('bob@example.com',   'NUS',               'MSc',   'Statistics',             '2017-08-01', '2019-06-01'),
   ('chen@example.com',  'IIT Bombay',        'BTech', 'Electrical Engineering', NULL,         NULL)
) AS v(email, school_name, degree, field, start_date, end_date)
JOIN users u              ON u.email = v.email
LEFT JOIN organizations o ON o.name = v.school_name AND o.type = 'SCHOOL';

-- Skills catalogue
INSERT INTO skills (name) VALUES
('Java'), ('Spring Boot'), ('PostgreSQL'), ('Python'), ('Machine Learning'), ('Leadership');

-- Who has which skill (Dina and Eve have none)
INSERT INTO profiles_skills (user_id, skill_id)
SELECT u.id, s.id
FROM (VALUES
   ('alice@example.com', 'Java'), ('alice@example.com', 'Spring Boot'), ('alice@example.com', 'PostgreSQL'),
   ('bob@example.com',   'Python'), ('bob@example.com', 'Machine Learning'), ('bob@example.com', 'PostgreSQL'),
   ('chen@example.com',  'Java'), ('chen@example.com', 'Leadership')
) AS v(email, skill_name)
JOIN users u  ON u.email = v.email
JOIN skills s ON s.name = v.skill_name;

