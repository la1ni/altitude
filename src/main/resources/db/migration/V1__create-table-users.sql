CREATE TABLE users (
   id UUID DEFAULT RANDOM_UUID() PRIMARY KEY,
   username VARCHAR(50) UNIQUE NOT NULL,
   password VARCHAR NOT NULL,
   first_name VARCHAR(50) NOT NULL,
   last_name VARCHAR(50) NOT NULL,
   email VARCHAR(70) NOT NULL,
   phone VARCHAR(50) NOT NULL,
   cpf VARCHAR NOT NULL);