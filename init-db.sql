DO $$
BEGIN
    -- Vérifie si la base de données existe
    IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'springpracticecours') THEN
        -- Création de la base de données si elle n'existe pas
        CREATE DATABASE springpracticecours;
        USE springpracticecours;

END IF;
END $$;


