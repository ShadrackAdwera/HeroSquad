SET MODE PostgreSQL;
CREATE TABLE IF NOT EXISTS hero (
  id int PRIMARY KEY auto_increment,
  Name VARCHAR,
  Age int,
  SuperPowers VARCHAR,
  Weakness VARCHAR,
  Squad BOOLEAN,
  SquadId INTEGER
);

CREATE TABLE IF NOT EXISTS squads (
  id int PRIMARY KEY auto_increment,
  Name VARCHAR,
  MaxSize INTEGER,
  Cause VARCHAR
);