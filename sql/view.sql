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
WHERE
    current_date BETWEEN r.date_debut_recrutement
    AND r.date_fin_recrutement;


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



