CREATE SCHEMA IF NOT EXISTS application;
CREATE TABLE IF NOT EXISTS application.aircraft
(
    callsign CHARACTER VARYING(15) NOT NULL,
    model CHARACTER VARYING(50) NOT NULL,
    modeltype CHARACTER VARYING(50)NOT NULL,
    modelyear CHARACTER VARYING(4),
    CONSTRAINT aircraft_pkey PRIMARY KEY (callsign)
);

CREATE TABLE IF NOT EXISTS application.pilot(
	id integer PRIMARY KEY NOT NULL GENERATED BY DEFAULT AS IDENTITY,
	first_name varchar(50) NOT NULL,
	last_name varchar(50) NOT NULL
);

