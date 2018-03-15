CREATE SEQUENCE seq_visits;
CREATE TABLE visits
(
  id      BIGINT PRIMARY KEY,
  user_id BIGINT REFERENCES users,
  city_id BIGINT REFERENCES cities,
  UNIQUE (user_id, city_id)
);