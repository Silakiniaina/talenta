CREATE OR REPLACE FUNCTION reset_tables() 
RETURNS void AS $$
DECLARE
    r RECORD;
BEGIN
    FOR r IN 
        SELECT tablename
        FROM pg_tables
        WHERE schemaname = 'public' 
        AND tablename NOT LIKE 'pg_%'
    LOOP
        EXECUTE 'TRUNCATE TABLE public.' || r.tablename || ' CASCADE';
    END LOOP;

    FOR r IN 
        SELECT c.relname 
        FROM pg_class c 
        JOIN pg_namespace n ON (n.oid = c.relnamespace) 
        WHERE c.relkind = 'S' 
        AND n.nspname = 'public'
    LOOP
        EXECUTE 'ALTER SEQUENCE public.' || r.relname || ' RESTART WITH 1';
    END LOOP;
    
    RAISE NOTICE 'Tables and sequences have been reset.';
END;
$$ LANGUAGE plpgsql;

SELECT reset_tables();


CREATE OR REPLACE FUNCTION reset_competence() 
RETURNS void AS $$
BEGIN
    TRUNCATE TABLE public.Competence CASCADE;
    ALTER SEQUENCE public.competence_id_seq RESTART WITH 1;
    RAISE NOTICE 'Table Competence and sequence have been reset.';
END;
$$ LANGUAGE plpgsql;

SELECT reset_competence();

CREATE OR REPLACE FUNCTION reset_employe() 
RETURNS void AS $$
BEGIN
    TRUNCATE TABLE public.Employe CASCADE;
    ALTER SEQUENCE public.employe_id_seq RESTART WITH 1;
    RAISE NOTICE 'Table Employe and sequence have been reset.';
END;
$$ LANGUAGE plpgsql;

SELECT reset_employe();

