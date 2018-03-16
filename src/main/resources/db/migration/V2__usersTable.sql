CREATE SEQUENCE seq_users;
CREATE TABLE users
(
  id       BIGINT PRIMARY KEY,
  name     VARCHAR(100) NOT NULL,
  username VARCHAR(100) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email    VARCHAR(100) NOT NULL,
  type     VARCHAR(10)  NOT NULL
);