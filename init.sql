-- init.sql

-- Utiliser la base de données par défaut pour créer la base de données si elle n'existe pas encore
DO $$
BEGIN
   IF NOT EXISTS (
      SELECT FROM pg_catalog.pg_database
      WHERE datname = 'mydatabase'
   ) THEN
      PERFORM dblink_exec('dbname=' || current_database(), 'CREATE DATABASE mydatabase');
END IF;
END
$$;

-- Utiliser la base de données
\c mydatabase;

-- Créer une table avec un ID auto-incrémenté si elle n'existe pas encore
CREATE TABLE IF NOT EXISTS my_table (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);


