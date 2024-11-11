CREATE OR REPLACE FUNCTION verifier_pourcentage_competences(id_candidat integer, id_poste_param integer)
RETURNS float AS $$
DECLARE
    nb_competences_requises integer;
    nb_competences_acquises integer;
    pourcentage float;
BEGIN
    SELECT COUNT(*) INTO nb_competences_requises
    FROM PosteCompetence pc
    WHERE pc.id_poste = id_poste_param;  

    SELECT COUNT(*) INTO nb_competences_acquises
    FROM PersonneCompetence pC
    WHERE pC.id_personne = id_candidat
    AND pC.id_competence IN (
        SELECT pc.id_competence
        FROM PosteCompetence pc
        WHERE pc.id_poste = id_poste_param  
    );

    IF nb_competences_requises > 0 THEN
        pourcentage := (nb_competences_acquises::float / nb_competences_requises) * 100;
    ELSE
        pourcentage := 0;
    END IF;

    RETURN pourcentage;
END;
$$ LANGUAGE plpgsql;




select verifier_pourcentage_competences(1,1);
