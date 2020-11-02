
DROP TABLE IF EXISTS position;
DROP TABLE IF EXISTS shape;



CREATE TABLE shape(
shape_id SERIAL PRIMARY KEY,
name varchar(100) NOT NULL UNIQUE
);

CREATE TABLE position(
  position_id SERIAL ,
  x integer NOT NULL ,
  y integer NOT NULL ,
  shape_id integer NOT NULL REFERENCES shape(shape_id)
);