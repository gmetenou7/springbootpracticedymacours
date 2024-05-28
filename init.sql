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

   -- Table: public.player

-- DROP TABLE IF EXISTS public.player;

CREATE TABLE IF NOT EXISTS public.player
(
    id bigint NOT NULL DEFAULT nextval('player_id_seq'::regclass),
    birth_date date NOT NULL,
    first_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    last_name character varying(50) COLLATE pg_catalog."default" NOT NULL,
    points integer NOT NULL,
    rank integer NOT NULL,
    CONSTRAINT player_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.player
    OWNER to postgres;

-- =======================================

CREATE SEQUENCE user_id_seq;
CREATE TABLE IF NOT EXISTS dyma_user (
    id integer NOT NULL DEFAULT nextval('user_id_seq'),
    login character varying(50) NOT NULL,
    password character varying(60) NOT NULL,
    last_name character varying(50) NOT NULL,
    first_name character varying(50) NOT NULL,
    PRIMARY KEY (id)
);
ALTER SEQUENCE user_id_seq OWNED BY player.id;
ALTER TABLE IF EXISTS public.dyma_user OWNER to postgres;

--=====================================================

CREATE TABLE IF NOT EXISTS dyma_role
(
    name character varying(50) NOT NULL,
    PRIMARY KEY (name)
);
ALTER TABLE IF EXISTS public.dyma_role OWNER to postgres;

-- ==========================================================

CREATE TABLE IF NOT EXISTS dyma_user_role
(
    user_id bigint NOT NULL,
    role_name character varying(50) NOT NULL,
    CONSTRAINT dyma_user_role_pkey PRIMARY KEY (user_id, role_name),
    CONSTRAINT fk_role_name FOREIGN KEY (role_name)
        REFERENCES public.dyma_role (name),
    CONSTRAINT fk_user_id FOREIGN KEY (user_id)
        REFERENCES public.dyma_user (id)
);
ALTER TABLE IF EXISTS public.dyma_user_role OWNER to postgres;





