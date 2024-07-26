CREATE TABLE flights(
    id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
    origin VARCHAR NOT NULL,
    destination VARCHAR NOT NULL,
    departue_time TIMESTAMP NOT NULL,
    arrival_time TIMESTAMP NOT NULL,
    number_of_passengers INT NOT NULL
);