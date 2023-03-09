INSERT INTO application.pilot(first_name, last_name) VALUES ('first_name_test', 'last_name_test') ON CONFLICT DO NOTHING;
INSERT INTO application.pilot(first_name, last_name) VALUES ('first_name_test', 'last_name_test') ON CONFLICT DO NOTHING;
INSERT INTO application.aircraft
(callsign, model, modeltype, modelyear) VALUES
('N5432', 'Cessna', 'Skyhawk 172', '2022'),
('N1234', 'Cessna', 'Skyhawk 172', '2023') on CONFLICT DO NOTHING;
INSERT INTO application.flight(id, pilot_id) VALUES ('QTA543', 1) ON CONFLICT DO NOTHING;
