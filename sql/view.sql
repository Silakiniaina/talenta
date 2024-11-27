CREATE OR REPLACE VIEW v_preselection_candidat AS
SELECT 
    c.id_candidat,
    r.id_recrutement,
    COUNT(cr.id_competence) AS nombre_competences_requises,
    COUNT(cc.id_competence) AS nombre_competences_candidat,
    (COUNT(cc.id_competence) * 100.0 / COUNT(cr.id_competence)) AS pourcentage_adéquation_competence,
    COALESCE(SUM(
        CASE 
            WHEN cc.experience::INTEGER >= cr.experience THEN 1 
            ELSE cc.experience::FLOAT / cr.experience 
        END
    ) * 100.0 / COUNT(cr.id_competence), 0) AS pourcentage_adéquation_experience,
    ((COUNT(cc.id_competence) * 100.0 / COUNT(cr.id_competence)) * 0.5 + 
     COALESCE(SUM(
        CASE 
            WHEN cc.experience::INTEGER >= cr.experience THEN 1 
            ELSE cc.experience::FLOAT / cr.experience 
        END
    ) * 100.0 / COUNT(cr.id_competence), 0) * 0.5) AS score_global
FROM 
    v_candidat c
JOIN 
    recrutement r ON r.id_recrutement = (SELECT id_recrutement FROM recrutement_candidat WHERE id_candidat = c.id_candidat)
JOIN 
    poste p ON r.id_poste = p.id_poste
JOIN
    competence_requise cr ON p.id_poste = cr.id_poste
LEFT JOIN 
    competence_candidat cc ON c.id_candidat = cc.id_candidat 
    AND cr.id_competence = cc.id_competence
GROUP BY 
    c.id_candidat, c.nom, c.prenom, r.id_recrutement
HAVING ((COUNT(cc.id_competence) * 100.0 / COUNT(cr.id_competence)) * 0.5 + 
     COALESCE(SUM(
        CASE 
            WHEN cc.experience::INTEGER >= cr.experience THEN 1 
            ELSE cc.experience::FLOAT / cr.experience 
        END
    ) * 100.0 / COUNT(cr.id_competence), 0) * 0.5) >= 50
ORDER BY 
    score_global DESC
;

CREATE OR REPLACE VIEW v_evaluation_test_recrutement AS
SELECT 
    pc.id_recrutement,
    c.id_candidat,
    COUNT(rt.id_questionaire) AS nombre_questions_repondues,
    AVG(rt.reponse) AS score_moyen
FROM 
    v_preselection_candidat pc
JOIN 
    v_candidat c ON pc.id_candidat = c.id_candidat
JOIN 
    recrutement r ON pc.id_recrutement = r.id_recrutement
JOIN 
    reponse_test rt ON c.id_candidat = rt.id_candidat
GROUP BY 
    pc.id_recrutement, c.id_candidat
ORDER BY 
    pc.id_recrutement, score_moyen DESC;

CREATE OR REPLACE view v_candidat AS
SELECT 
    * 
FROM candidat 
WHERE id_candidat 
NOT IN (
    SELECT 
        id_candidat
    FROM employe 
);

CREATE OR REPLACE VIEW v_filtre_cv AS
WITH experience_totale AS (
    SELECT 
        ec.id_candidat,
        SUM(
            EXTRACT(YEAR FROM ec.date_fin) - EXTRACT(YEAR FROM ec.date_debut) +
            (EXTRACT(MONTH FROM ec.date_fin) - EXTRACT(MONTH FROM ec.date_debut)) / 12.0
        ) as total_annees
    FROM experience_candidat ec
    GROUP BY ec.id_candidat
),
competences_match AS (
    SELECT 
        cc.id_candidat,
        r.id_recrutement,
        COUNT(*)::FLOAT / NULLIF(
            (SELECT COUNT(*) 
             FROM competence_requise_poste crp2 
             WHERE crp2.id_poste = r.id_poste), 
            0
        ) * 100 as pourcentage_competences
    FROM competence_candidat cc
    JOIN competence_requise_poste crp ON cc.id_competence = crp.id_competence
    JOIN recrutement r ON r.id_poste = crp.id_poste
    GROUP BY cc.id_candidat, r.id_recrutement
),
diplomes_match AS (
    SELECT 
        ec.id_candidat,
        r.id_recrutement,
        COUNT(DISTINCT ec.id_type_diplome)::FLOAT / NULLIF(
            (SELECT COUNT(*) 
             FROM diplome_requis_poste drp2 
             WHERE drp2.id_poste = r.id_poste), 
            0
        ) * 100 as pourcentage_diplomes
    FROM education_candidat ec
    JOIN diplome_requis_poste drp ON ec.id_type_diplome = drp.id_type_diplome
    JOIN recrutement r ON r.id_poste = drp.id_poste
    GROUP BY ec.id_candidat, r.id_recrutement
)
SELECT 
    c.id_candidat,
    r.id_recrutement,
    p.nom as titre_poste,
    ROUND(CAST(COALESCE(cm.pourcentage_competences, 0) AS numeric), 2) as pourcentage_competences,
    ROUND(CAST(COALESCE(dm.pourcentage_diplomes, 0) AS numeric), 2) as pourcentage_diplomes,
    ROUND(CAST(LEAST(COALESCE(et.total_annees / GREATEST(p.annees_experience_requises, 1), 0) * 100, 100) AS numeric), 2) as pourcentage_experience,
    ROUND(
        CAST(
            (
                COALESCE(cm.pourcentage_competences, 0) * 0.4 + 
                COALESCE(dm.pourcentage_diplomes, 0) * 0.4 + 
                LEAST(COALESCE(et.total_annees / GREATEST(p.annees_experience_requises, 1), 0) * 100, 100) * 0.2
            ) AS numeric
        ),
        2
    ) as pourcentage_matching_total
FROM v_candidat c
JOIN recrutement_candidat po ON c.id_candidat = po.id_candidat
JOIN recrutement r ON r.id_recrutement = po.id_recrutement
JOIN poste p ON p.id_poste = r.id_poste
LEFT JOIN competences_match cm ON c.id_candidat = cm.id_candidat AND r.id_recrutement = cm.id_recrutement
LEFT JOIN diplomes_match dm ON c.id_candidat = dm.id_candidat AND r.id_recrutement = dm.id_recrutement
LEFT JOIN experience_totale et ON c.id_candidat = et.id_candidat
WHERE current_date BETWEEN r.date_debut_recrutement AND r.date_fin_recrutement;

CREATE OR REPLACE VIEW v_recrutement_candidat AS
    SELECT 
        * 
    FROM recrutement_candidat
    WHERE id_candidat 
    NOT IN (
        SELECT 
            id_candidat
        FROM employe 
    );


