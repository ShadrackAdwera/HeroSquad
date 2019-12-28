SET MODE PostgreSQL;
CREATE TABLE IF NOT EXISTS heroes (
  id int PRIMARY KEY auto_increment,
  Name VARCHAR,
  Age int,
  SuperPower VARCHAR,
  Weakness VARCHAR,
  Squad BOOLEAN
);