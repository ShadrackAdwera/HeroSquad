
CREATE DATABASE herosquad;
\c herosquad;
CREATE TABLE hero (id SERIAL PRIMARY KEY, name VARCHAR, age INTEGER, superpowers VARCHAR, weakness VARCHAR, squad BOOLEAN, squadid INTEGER);
CREATE TABLE squads (id SERIAL PRIMARY KEY, name VARCHAR, maxsize INTEGER, cause VARCHAR);
CREATE DATABASE herosquad_test WITH TEMPLATE herosquad;