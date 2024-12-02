CREATE
OR REPLACE view v_candidat AS
SELECT
    *
FROM
    candidat
WHERE
    id_candidat NOT IN (
        SELECT
            id_candidat
        FROM
            employe
    );

CREATE
OR REPLACE VIEW v_recrutement_candidat AS
SELECT
    *
FROM
    recrutement_candidat
WHERE
    id_candidat NOT IN (
        SELECT
            id_candidat
        FROM
            employe
    );

CREATE
OR REPLACE VIEW v_filtre_cv AS WITH experience_totale AS (
    SELECT
        ec.id_candidat,
        SUM(
            EXTRACT(
                YEAR
                FROM
                    ec.date_fin
            ) - EXTRACT(
                YEAR
                FROM
                    ec.date_debut
            ) + (
                EXTRACT(
                    MONTH
                    FROM
                        ec.date_fin
                ) - EXTRACT(
                    MONTH
                    FROM
                        ec.date_debut
                )
            ) / 12.0
        ) as total_annees
    FROM
        experience_candidat ec
    GROUP BY
        ec.id_candidat
),
competences_match AS (
    SELECT
        cc.id_candidat,
        r.id_recrutement,
        COUNT(*) :: FLOAT / NULLIF(
            (
                SELECT
                    COUNT(*)
                FROM
                    competence_requise_poste crp2
                WHERE
                    crp2.id_poste = r.id_poste
            ),
            0
        ) * 100 as pourcentage_competences
    FROM
        competence_candidat cc
        JOIN competence_requise_poste crp ON cc.id_competence = crp.id_competence
        JOIN recrutement r ON r.id_poste = crp.id_poste
    GROUP BY
        cc.id_candidat,
        r.id_recrutement
),
diplomes_match AS (
    SELECT
        ec.id_candidat,
        r.id_recrutement,
        COUNT(DISTINCT ec.id_type_diplome) :: FLOAT / NULLIF(
            (
                SELECT
                    COUNT(*)
                FROM
                    diplome_requis_poste drp2
                WHERE
                    drp2.id_poste = r.id_poste
            ),
            0
        ) * 100 as pourcentage_diplomes
    FROM
        education_candidat ec
        JOIN diplome_requis_poste drp ON ec.id_type_diplome = drp.id_type_diplome
        JOIN recrutement r ON r.id_poste = drp.id_poste
    GROUP BY
        ec.id_candidat,
        r.id_recrutement
)
SELECT
    c.id_candidat,
    r.id_recrutement,
    p.nom as titre_poste,
    ROUND(
        CAST(
            COALESCE(cm.pourcentage_competences, 0) AS numeric
        ),
        2
    ) as pourcentage_competences,
    ROUND(
        CAST(COALESCE(dm.pourcentage_diplomes, 0) AS numeric),
        2
    ) as pourcentage_diplomes,
    ROUND(
        CAST(
            LEAST(
                COALESCE(
                    et.total_annees / GREATEST(p.annees_experience_requises, 1),
                    0
                ) * 100,
                100
            ) AS numeric
        ),
        2
    ) as pourcentage_experience,
    ROUND(
        CAST(
            (
                COALESCE(cm.pourcentage_competences, 0) * 0.4 + COALESCE(dm.pourcentage_diplomes, 0) * 0.4 + LEAST(
                    COALESCE(
                        et.total_annees / GREATEST(p.annees_experience_requises, 1),
                        0
                    ) * 100,
                    100
                ) * 0.2
            ) AS numeric
        ),
        2
    ) as pourcentage_matching_total,
    po.is_prechosen
FROM
    v_candidat c
    JOIN v_recrutement_candidat po ON c.id_candidat = po.id_candidat
    JOIN recrutement r ON r.id_recrutement = po.id_recrutement
    JOIN poste p ON p.id_poste = r.id_poste
    LEFT JOIN competences_match cm ON c.id_candidat = cm.id_candidat
    AND r.id_recrutement = cm.id_recrutement
    LEFT JOIN diplomes_match dm ON c.id_candidat = dm.id_candidat
    AND r.id_recrutement = dm.id_recrutement
    LEFT JOIN experience_totale et ON c.id_candidat = et.id_candidat
;

-- Reste conge
CREATE
OR REPLACE VIEW v_solde_conge_employe AS
SELECT
    e.id_employe,
    e.date_embauche,
    -- Calculer le nombre total de mois depuis l'embauche
    EXTRACT(
        YEAR
        FROM
            AGE(NOW(), e.date_embauche)
    ) * 12 + EXTRACT(
        MONTH
        FROM
            AGE(NOW(), e.date_embauche)
    ) AS total_mois,
    -- Calculer le solde de congés
    (
        (
            EXTRACT(
                YEAR
                FROM
                    AGE(NOW(), e.date_embauche)
            ) * 12 + EXTRACT(
                MONTH
                FROM
                    AGE(NOW(), e.date_embauche)
            )
        ) * 2.5
    ) AS total_jours_conges
FROM
    employe e;

CREATE
OR REPLACE VIEW v_total_conges_paye_effectues AS
SELECT
    e.id_employe,
    SUM(
        CASE
            WHEN tc.est_conge_paye THEN c.date_fin - c.date_debut + 1
            ELSE 0
        END
    ) AS total_jours_conges
FROM
    employe e
    LEFT JOIN conge c ON e.id_employe = c.id_employe
    LEFT JOIN type_conge tc ON c.id_type_conge = tc.id_type_conge
GROUP BY
    e.id_employe;

CREATE
OR REPLACE VIEW v_solde_conge_restant AS
SELECT
    e.id_employe,
    e.total_jours_conges AS jours_acquis,
    COALESCE(t.total_jours_conges, 0) AS jours_pris,
    e.total_jours_conges - COALESCE(t.total_jours_conges, 0) AS jours_restants
FROM
    v_solde_conge_employe e
    LEFT JOIN v_total_conges_paye_effectues t ON e.id_employe = t.id_employe;

CREATE
OR REPLACE VIEW v_informations_employe AS
SELECT
    c.id_candidat,
    c.nom,
    c.prenom,
    c.date_naissance,
    c.adresse,
    c.email,
    g.label AS genre,
    -- Get the genre label from the 'genre' table
    e.jours_acquis,
    v_scr.jours_pris,
    v_scr.jours_restants
FROM
    candidat c
    LEFT JOIN employe e ON c.id_candidat = e.id_candidat
    LEFT JOIN v_solde_conge_restant v_scr ON e.id_employe = v_scr.id_employe
    LEFT JOIN genre g ON c.id_genre = g.id_genre;

CREATE OR REPLACE VIEW v_statistiques_reponses AS
SELECT 
    tc.id_attribution,
    tc.id_candidat,
    COUNT(CASE WHEN rtp.est_reponse_attendue = false THEN 1 END) AS nombre_reponse_fausse,
    COUNT(CASE WHEN rtp.est_reponse_attendue = true THEN 1 END) AS nombre_reponse_correcte,
    (
        SELECT COUNT(*) 
        FROM question_test qt 
        WHERE qt.id_test = t.id_test
    ) AS nombre_total_question
FROM 
    test_candidat tc
JOIN 
    test t ON tc.id_test = t.id_test
LEFT JOIN 
    reponse_test_candidat rtc ON tc.id_attribution = rtc.id_attribution
LEFT JOIN 
    reponse_test_possibles rtp ON rtc.id_reponse_candidat = rtp.id_reponse_test_possibles
GROUP BY 
    tc.id_attribution, 
    tc.id_candidat,
    t.id_test;

CREATE OR REPLACE VIEW resultat_test_candidat AS
WITH stats_test AS (
    SELECT 
        tc.id_attribution,
        tc.id_candidat,
        COUNT(CASE WHEN rtp.est_reponse_attendue = false THEN 1 END) AS nombre_reponse_fausse,
        COUNT(CASE WHEN rtp.est_reponse_attendue = true THEN 1 END) AS nombre_reponse_correcte,
        (
            SELECT COUNT(*) 
            FROM question_test qt 
            WHERE qt.id_test = t.id_test
        ) AS nombre_total_question
    FROM 
        test_candidat tc
    JOIN 
        test t ON tc.id_test = t.id_test
    LEFT JOIN 
        reponse_test_candidat rtc ON tc.id_attribution = rtc.id_attribution
    LEFT JOIN 
        reponse_test_possibles rtp ON rtc.id_reponse_candidat = rtp.id_reponse_test_possibles
    GROUP BY 
        tc.id_attribution, 
        tc.id_candidat,
        t.id_test
)
SELECT 
    id_attribution,
    id_candidat,
    nombre_reponse_fausse,
    nombre_reponse_correcte,
    nombre_total_question,
    ROUND(
        (nombre_reponse_correcte::float / nombre_total_question) * 100, 
        2
    ) AS pourcentage_reussite,
    CASE 
        WHEN (nombre_reponse_correcte::float / nombre_total_question) * 100 >= 70 THEN 'Embauche recommandée'
        WHEN (nombre_reponse_correcte::float / nombre_total_question) * 100 >= 50 THEN 'Potentiel à considérer'
        ELSE 'Non recommandé'
    END AS statut_embauche
FROM 
    stats_test;
