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
    competence_recrutement cr ON r.id_recrutement = cr.id_recrutement
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

