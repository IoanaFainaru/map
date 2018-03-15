CREATE SEQUENCE seq_cities;
CREATE TABLE cities
(
  id          BIGINT PRIMARY KEY,
  name        VARCHAR(100) NOT NULL,
  description VARCHAR(2000)
);
