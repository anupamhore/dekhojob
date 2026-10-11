--liquibase formatted SQL

--changeset anupam:0004-seed-locations
INSERT INTO countries (code, name) VALUES
    ('IN','India'),
    ('SG','Singapore'),
    ('MY','Malaysia'),
    ('ID','Indonesia'),
    ('TH','Thailand'),
    ('AU','Australia'),
    ('GB','United Kingdom'),
    ('US','United States');

INSERT INTO locations (city, region, country_code, latitude, longitude) VALUES
    ('Mumbai', 'Maharashtra','IN',19.076090,72.877426),
    ('New Delhi','Delhi', 'IN', 28.613939,77.209023),
    ('Hyderabad','Telangana', 'IN', 17.385044,78.486671),
    ('Bengaluru','Karnataka','IN', 12.971599,77.594566),
    ('Singapore', NULL,'SG',   1.352083,  103.819836),
    ('Kuala Lumpur','Federal Territory of Kuala Lumpur', 'MY',3.139003,101.686852),
    ('George Town','Penang','MY',5.414130,100.328753),
    ('Johor Bahru','Johor','MY',1.492659,103.741359),
    ('Jakarta','Jakarta','ID',-6.208763,106.845599),
    ('Bangkok','Bangkok', 'TH',13.756331,100.501765),
    ('Sydney','New South Wales','AU', -33.868820,151.209296),
    ('London','England','GB',  51.507351,-0.127758),
    ('San Francisco','California', 'US',  37.774929,-122.419418),
    ('New York','New York','US',  40.712776,-74.005974);