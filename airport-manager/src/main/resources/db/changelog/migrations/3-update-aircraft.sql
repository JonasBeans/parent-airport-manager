alter table application.aircraft
add column if not exists maximum_occupation int NOT NULL default 0