----------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------LISTE DES EMPLOYES CAPABLES POUR UN POSTE------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE VIEW v_employe_capable AS
SELECT 
    e.id_personne,  
    p.nom AS nom_employe, 
    p.prenom AS prenom_employe,  
    e.id_poste, 
    pos.nom AS poste_nom,  
    verifier_pourcentage_competences(e.id_personne, e.id_poste) AS pourcentage  
FROM 
    Employe e  
JOIN 
    Personne p ON e.id_personne = p.id  -
JOIN 
    Poste pos ON e.id_poste = pos.id  
WHERE 
    verifier_pourcentage_competences(e.id_personne, e.id_poste) >= 70  
ORDER BY 
    pourcentage DESC; 

----------------------------------------------------------------------------------------------------------------------------------------------
------------------------------------------------------LISTE DES CV SELECTIONNES----------------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE VIEW v_selection AS
SELECT 
    c.id_personne,
    p.nom AS nom_candidat,
    p.prenom AS prenom_candidat,
    c.id_poste,
    pos.nom,
    verifier_pourcentage_competences(c.id_personne, c.id_poste) AS pourcentage,
    ROW_NUMBER() OVER (ORDER BY verifier_pourcentage_competences(c.id_personne, c.id_poste) DESC) AS rang
FROM 
    Candidat c
JOIN 
    Personne p ON c.id_personne = p.id
JOIN 
    Poste pos ON c.id_poste = pos.id
ORDER BY 
    pourcentage DESC
LIMIT 3;

----------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------LISTE DES CANDIDATS APRES ENTRETIEN------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE VIEW v_entretien AS
SELECT 
    v.id_personne,
    v.id_poste,
    v.rang,
    p.nom AS nom_candidat,  
    p.prenom AS prenom_candidat, 
    COUNT(ss.id) AS nb_soft_skills,
    (100 - v.rang + COUNT(ss.id)) AS score_entretien 
FROM 
    v_selection v
JOIN 
    PersonneSoftSkill ps ON v.id_personne = ps.id_personne  
JOIN 
    SoftSkill ss ON ps.id_softSkill = ss.id 
JOIN 
    Personne p ON v.id_personne = p.id
GROUP BY 
    v.id_personne, v.id_poste, v.rang, p.nom, p.prenom  
ORDER BY 
    score_entretien DESC; 

----------------------------------------------------------------------------------------------------------------------------------------------
-----------------------------------ETAT DES POSTES (VACANTS OU OCCUPES)------------------------------------------------------------------------
----------------------------------------------------------------------------------------------------------------------------------------------

CREATE OR REPLACE VIEW v_etatPoste AS
SELECT 
    p.id AS poste_id,
    p.nom AS poste_nom,
    p.nb_employe AS total_places,
    COUNT(e.id) AS current_employes,
    CASE 
        WHEN COUNT(e.id) = p.nb_employe THEN 'complet'
        ELSE CONCAT(p.nb_employe - COUNT(e.id), ' places vacantes')
    END AS etat
FROM 
    Poste p
LEFT JOIN 
    Employe e ON p.id = e.id_poste AND e.date_fin IS NULL
GROUP BY 
    p.id, p.nom, p.nb_employe;



