CREATE TABLE IF NOT EXISTS application.flight
(
    id varchar(6) NOT NULL,
    pilot_id integer NOT NULL,
    CONSTRAINT flight_pkey PRIMARY KEY (id),
    CONSTRAINT fk_pilot_id FOREIGN KEY (pilot_id)
        REFERENCES application.pilot (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);