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
OR REPLACE view v_candidat_employe AS
SELECT
    *
FROM
    candidat
WHERE
    id_candidat IN (
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
    v_scr.jours_acquis,
    v_scr.jours_pris,
    v_scr.jours_restants
FROM
    v_candidat_employe c
    LEFT JOIN employe e ON c.id_candidat = e.id_candidat
    LEFT JOIN v_solde_conge_restant v_scr ON e.id_employe = v_scr.id_employe
    LEFT JOIN genre g ON c.id_genre = g.id_genre;


-----------------------Liste des employes ayant pris un conge
CREATE OR REPLACE VIEW v_planning_conge AS
SELECT 
    e.id_employe,
    e.id_candidat,
    e.id_poste,
    c.id_conge,
    c.id_type_conge,
    c.date_debut,
    c.date_fin
FROM 
    conge c
JOIN 
    employe e ON c.id_employe = e.id_employe
WHERE 
    c.date_debut IS NOT NULL
    AND c.date_fin IS NOT NULL;

---------------------------Liste des employes qui ont l'age legal de retraite qui ne sont pas encore retraites
CREATE OR REPLACE VIEW v_employes_age_retraite AS
SELECT 
    e.id_employe,
    c.id_candidat as id_candidat,
    c.nom as candidat,
    c.prenom,
    c.date_naissance,
    e.date_embauche,
    p.id_poste as id_poste,
    EXTRACT(YEAR FROM AGE(NOW(), c.date_naissance)) AS age
FROM 
    employe e
JOIN 
    candidat c ON e.id_candidat = c.id_candidat
JOIN 
    poste p ON e.id_poste = p.id_poste
LEFT JOIN 
    fin_contrat f ON e.id_employe = f.id_employe
WHERE 
    EXTRACT(YEAR FROM AGE(NOW(), c.date_naissance)) >= 60
    AND f.id_employe IS NULL;

---------------------------Liste des candidats employes
CREATE
OR REPLACE view v_employe AS
SELECT
    *
FROM
    candidat
WHERE
    id_candidat IN (
        SELECT
            id_candidat
        FROM
            employe
);

-------------------------------Liste des demandes de demission pas encore approuves ou declines
CREATE OR REPLACE VIEW v_listeDemandeDemission AS
SELECT dd.id_demande, dd.id_candidat, dd.date_depot, dd.motif
FROM demande_demission dd
LEFT JOIN fin_contrat fc ON dd.id_candidat = fc.id_employe
WHERE fc.id_employe IS NULL
AND dd.etat IS NULL;





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
        (nombre_reponse_correcte::float / nombre_total_question) * 100
    ) AS pourcentage_reussite,
    CASE 
        WHEN (nombre_reponse_correcte::float / nombre_total_question) * 100 >= 70 THEN 'Embauche recommandée'
        WHEN (nombre_reponse_correcte::float / nombre_total_question) * 100 >= 50 THEN 'Potentiel à considérer'
        ELSE 'Non recommandé'
    END AS statut_embauche
FROM 
    stats_test;
