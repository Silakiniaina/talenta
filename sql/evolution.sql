ALTER TABLE education_candidat ADD COLUMN id_branche_education INTEGER;
ALTER TABLE education_candidat ADD CONSTRAINT fkey_id_branche_education FOREIGN KEY(id_branche_education) REFERENCES branche_education(id_branche_education);

ALTER TABLE diplome_requis_poste ADD COLUMN id_branche_education INTEGER;
ALTER TABLE diplome_requis_poste ADD CONSTRAINT fkey_id_branche_education_diplome_poste FOREIGN KEY(id_branche_education) REFERENCES branche_education(id_branche_education);

ALTER TABLE experience_candidat DROP COLUMN description_experience;
ALTER TABLE experience_candidat ADD COLUMN id_specialite INTEGER ;
ALTER TABLE experience_candidat ADD CONSTRAINT fkey_id_specialite FOREIGN KEY(id_specialite) REFERENCES specialite(id_specialite);
ALTER TABLE experience_candidat ADD COLUMN entreprise VARCHAR(200);

ALTER TABLE recrutement ADD COLUMN description_recrutement TEXT;

ALTER TABLE reponse_test_possibles ADD COLUMN est_reponse_attendue boolean DEFAULT false;

ALTER TABLE employe ADD COLUMN salaire_base DECIMAL(18,2) DEFAULT 0,



CREATE OR REPLACE FUNCTION calcul_salaire_mensuel(id_employe_param INT, mois DATE)
RETURNS NUMERIC AS $$
DECLARE
    salaire_base_v NUMERIC;
    salaire_journalier_v NUMERIC;
    total_heures_supplementaires_v NUMERIC := 0;
    total_heures_nuit_v NUMERIC := 0;
    total_salaire_v NUMERIC;
    date_debut_v DATE;
    date_fin_v DATE;
    presence_v RECORD;
    jours_du_mois_v INT;
BEGIN
    -- Récupérer le salaire de base de l'employé
    SELECT salaire_base INTO salaire_base_v
    FROM contrat
    WHERE id_candidat = (SELECT id_candidat FROM employe WHERE id_employe = id_employe_param)
    AND date_debut_contrat <= mois
    AND (date_fin_contrat IS NULL OR date_fin_contrat >= mois);

    -- Calculer les dates de début et fin du mois spécifié
    date_debut_v := date_trunc('month', mois);  -- 1er jour du mois
    date_fin_v := (date_trunc('month', mois) + INTERVAL '1 month' - INTERVAL '1 day'); -- dernier jour du mois
    
    -- Calculer le nombre de jours dans le mois
    jours_du_mois_v := EXTRACT(DAY FROM date_fin_v);

    -- Calcul du salaire journalier
    salaire_journalier_v := salaire_base_v / jours_du_mois_v;

    -- Calcul des heures supplémentaires et du travail de nuit
    FOR presence_v IN
        SELECT date_entree, date_sortie
        FROM presence_employe
        WHERE id_employe = id_employe_param
        AND date_entree >= date_debut_v
        AND date_sortie <= date_fin_v
    LOOP
        -- Calcul des heures supplémentaires (si plus de 8 heures de travail)
        IF EXTRACT(EPOCH FROM (presence_v.date_sortie - presence_v.date_entree)) / 3600 > 8 THEN
            total_heures_supplementaires_v := total_heures_supplementaires_v + (EXTRACT(EPOCH FROM (presence_v.date_sortie - presence_v.date_entree)) / 3600 - 8);
        END IF;

        -- Calcul des heures de travail de nuit (si travail entre 20h et 5h)
        IF (EXTRACT(HOUR FROM presence_v.date_entree) >= 20 OR EXTRACT(HOUR FROM presence_v.date_sortie) < 5) THEN
            total_heures_nuit_v := total_heures_nuit_v + (EXTRACT(EPOCH FROM (presence_v.date_sortie - presence_v.date_entree)) / 3600);
        END IF;
    END LOOP;

    -- Calcul du salaire total pour le mois
    total_salaire_v := salaire_journalier_v * jours_du_mois_v  -- salaire de base pour le mois
                     + total_heures_supplementaires_v * (salaire_journalier_v / 8 * 1.5)  -- heures supplémentaires
                     + total_heures_nuit_v * (salaire_journalier_v / 8 * 1.5);  -- travail de nuit

    RETURN total_salaire_v;
END;
$$ LANGUAGE plpgsql;




CREATE OR REPLACE FUNCTION calcul_salaire_mensuel(id_employe_param INT, mois DATE)
RETURNS TABLE(salaire_brut NUMERIC, heures_supplementaires_v NUMERIC, heures_nuit NUMERIC, salaire_total NUMERIC) AS $$
DECLARE
    salaire_base_v NUMERIC;
    salaire_journalier_v NUMERIC;
    total_heures_supplementaires_v NUMERIC := 0;
    total_heures_nuit_v NUMERIC := 0;
    total_salaire_v NUMERIC;
    date_debut_v DATE;
    date_fin_v DATE;
    presence_v RECORD;
    jours_du_mois_v INT;
BEGIN
    -- Récupérer le salaire de base de l'employé
    SELECT salaire_base INTO salaire_base_v
    FROM contrat
    WHERE id_candidat = (SELECT id_candidat FROM employe WHERE id_employe = id_employe_param)
    AND date_debut_contrat <= mois
    AND (date_fin_contrat IS NULL OR date_fin_contrat >= mois);

    -- Calculer les dates de début et fin du mois spécifié
    date_debut_v := date_trunc('month', mois);  -- 1er jour du mois
    date_fin_v := (date_trunc('month', mois) + INTERVAL '1 month' - INTERVAL '1 day'); -- dernier jour du mois
    
    -- Calculer le nombre de jours dans le mois
    jours_du_mois_v := EXTRACT(DAY FROM date_fin_v);

    -- Calcul du salaire journalier
    salaire_journalier_v := salaire_base_v / jours_du_mois_v;

    -- Calcul des heures supplémentaires et du travail de nuit
    FOR presence_v IN
        SELECT date_entree, date_sortie
        FROM presence_employe
        WHERE id_employe = id_employe_param
        AND date_entree >= date_debut_v
        AND date_sortie <= date_fin_v
    LOOP
        -- Calcul des heures supplémentaires (si plus de 8 heures de travail)
        IF EXTRACT(EPOCH FROM (presence_v.date_sortie - presence_v.date_entree)) / 3600 > 8 THEN
            total_heures_supplementaires_v := total_heures_supplementaires_v + (EXTRACT(EPOCH FROM (presence_v.date_sortie - presence_v.date_entree)) / 3600 - 8);
        END IF;

        -- Calcul des heures de travail de nuit (si travail entre 20h et 5h)
        IF (EXTRACT(HOUR FROM presence_v.date_entree) >= 20 OR EXTRACT(HOUR FROM presence_v.date_sortie) < 5) THEN
            total_heures_nuit_v := total_heures_nuit_v + (EXTRACT(EPOCH FROM (presence_v.date_sortie - presence_v.date_entree)) / 3600);
        END IF;
    END LOOP;

    -- Calcul du salaire total pour le mois
    total_salaire_v := salaire_journalier_v * jours_du_mois_v  -- salaire de base pour le mois
                     + total_heures_supplementaires_v * (salaire_journalier_v / 8 * 1.5)  -- heures supplémentaires
                     + total_heures_nuit_v * (salaire_journalier_v / 8 * 1.5);  -- travail de nuit

    -- Retourner les valeurs dans des colonnes séparées
    RETURN QUERY 
    SELECT salaire_base_v, total_heures_supplementaires_v, total_heures_nuit_v, total_salaire_v;
END;
$$ LANGUAGE plpgsql;





CREATE OR REPLACE FUNCTION calcul_salaire_mensuel(id_employe_param INT, mois DATE)
RETURNS TABLE (
    salaire_base_v NUMERIC,
    heures_supplementaires NUMERIC, 
    heures_nuit NUMERIC, 
    salaire_total NUMERIC
) AS $$
DECLARE
    salaire_base_v NUMERIC;
    salaire_journalier_v NUMERIC;
    total_heures_supplementaires_v NUMERIC := 0;
    total_heures_nuit_v NUMERIC := 0;
    total_salaire_v NUMERIC;
    date_debut_v DATE;
    date_fin_v DATE;
    presence_v RECORD;
    jours_du_mois_v INT;
BEGIN
    -- Récupérer le salaire de base de l'employé
    SELECT salaire_base INTO salaire_base_v
    FROM contrat
    WHERE id_candidat = (SELECT id_candidat FROM employe WHERE id_employe = id_employe_param)
    AND date_debut_contrat <= mois
    AND (date_fin_contrat IS NULL OR date_fin_contrat >= mois);

    date_debut_v := date_trunc('month', mois);
    date_fin_v := (date_trunc('month', mois) + INTERVAL '1 month' - INTERVAL '1 day');
    
    jours_du_mois_v := EXTRACT(DAY FROM date_fin_v);
    salaire_journalier_v := salaire_base_v / jours_du_mois_v;

    FOR presence_v IN
        SELECT date_entree, date_sortie
        FROM presence_employe
        WHERE id_employe = id_employe_param
        AND date_entree >= date_debut_v
        AND date_sortie <= date_fin_v
    LOOP
        IF EXTRACT(EPOCH FROM (presence_v.date_sortie - presence_v.date_entree)) / 3600 > 8 THEN
            total_heures_supplementaires_v := total_heures_supplementaires_v + (EXTRACT(EPOCH FROM (presence_v.date_sortie - presence_v.date_entree)) / 3600 - 8);
        END IF;

        IF (EXTRACT(HOUR FROM presence_v.date_entree) >= 20 OR EXTRACT(HOUR FROM presence_v.date_sortie) < 5) THEN
            total_heures_nuit_v := total_heures_nuit_v + (EXTRACT(EPOCH FROM (presence_v.date_sortie - presence_v.date_entree)) / 3600);
        END IF;
    END LOOP;

    total_salaire_v := salaire_journalier_v * jours_du_mois_v  
                     + total_heures_supplementaires_v * (salaire_journalier_v / 8 * 1.5)  
                     + total_heures_nuit_v * (salaire_journalier_v / 8 * 1.5);  

    RETURN QUERY 
    SELECT salaire_base_v, total_heures_supplementaires_v, total_heures_nuit_v, total_salaire_v;
END;
$$ LANGUAGE plpgsql;

-- Exemple d'utilisation
SELECT * FROM calcul_salaire_mensuel(votre_id_employe, 'année-mois-jour'::DATE);