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
    v_scr.jours_restants,
    e.id_employe
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







-- prime et indemnite:
CREATE VIEW v_prime AS
SELECT 
    e.id_employe,
    e.salaire_base,
    COALESCE(SUM(CASE WHEN tp.nom_type_prime = 'prime_rendement' THEN pe.montant_prime ELSE 0 END), 0) AS prime_rendement,
    COALESCE(SUM(CASE WHEN tp.nom_type_prime = 'prime_ancienete' THEN pe.montant_prime ELSE 0 END), 0) AS prime_ancienete
FROM 
    employe e
LEFT JOIN 
    prime_employe pe ON e.id_employe = pe.id_employe
LEFT JOIN 
    type_prime tp ON pe.id_type_prime = tp.id_type_prime
GROUP BY 
    e.id_employe, e.salaire_base;


CREATE VIEW v_indemnite AS
SELECT 
    e.id_employe,
    e.salaire_base,
    COALESCE(SUM(CASE WHEN ti.nom_type_indemnite = 'indemnite_licenciement' THEN ie.montant_indemnite ELSE 0 END), 0) AS indemnite_licenciement
FROM 
    employe e
LEFT JOIN 
    indemnite_employe ie ON e.id_employe = ie.id_employe
LEFT JOIN 
    type_indemnite ti ON ie.id_type_indemnite = ti.id_type_indemnite
GROUP BY 
    e.id_employe, e.salaire_base;








-- fisc
CREATE VIEW vue_deductions AS
SELECT 
    e.id_employe,
    COALESCE(SUM(CASE WHEN df.nom_deduction = 'OSTIE' THEN de.montant ELSE 0 END), 0) AS OSTIE,
    COALESCE(SUM(CASE WHEN df.nom_deduction = 'CNAPS' THEN de.montant ELSE 0 END), 0) AS CNAPS,
    COALESCE(SUM(CASE WHEN df.nom_deduction = 'IRSA' THEN de.montant ELSE 0 END), 0) AS IRSA
FROM
    employe e
LEFT JOIN deduction_employe de ON e.id_employe = de.id_employe
LEFT JOIN deduction_fiscale df ON de.id_deduction = df.id_deduction
GROUP BY 
    e.id_employe;


-- absence :
CREATE VIEW v_absence_avec_solde AS
SELECT 
    a.id_employe,
    -- Nombre de jours pour chaque type d'absence avec solde
    SUM(CASE WHEN ta.nom_type = 'Repos médical' THEN (a.date_fin - a.date_debut + 1) ELSE 0 END) AS repos_medical,
    SUM(CASE WHEN ta.nom_type = 'Assistance maternité' THEN (a.date_fin - a.date_debut + 1) ELSE 0 END) AS assistance_mat,
    SUM(CASE WHEN ta.nom_type = 'Hospitalisation et convalescence' THEN (a.date_fin - a.date_debut + 1) ELSE 0 END) AS hospitalisation_conv,
    SUM(CASE WHEN ta.nom_type = 'Événement familial' THEN (a.date_fin - a.date_debut + 1) ELSE 0 END) AS evenement_familial,
    -- Total des jours d'absence avec solde
    SUM(a.date_fin - a.date_debut + 1) AS total
FROM 
    absence a
JOIN 
    type_absence ta ON a.id_type_absence = ta.id_type_absence
WHERE 
    ta.nom_type IN ('Repos médical', 'Assistance maternité', 'Hospitalisation et convalescence', 'Événement familial')
GROUP BY 
    a.id_employe;

CREATE VIEW v_absence_sans_solde AS
SELECT 
    a.id_employe,
    -- Nombre de jours pour chaque type d'absence sans solde
    SUM(CASE WHEN ta.nom_type = 'Retard' THEN (a.date_fin - a.date_debut + 1) ELSE 0 END) AS retard,
    SUM(CASE WHEN ta.nom_type = 'Absence sans solde' THEN (a.date_fin - a.date_debut + 1) ELSE 0 END) AS absence_sans_solde,
    SUM(CASE WHEN ta.nom_type = 'Absence non autorisée' THEN (a.date_fin - a.date_debut + 1) ELSE 0 END) AS absence_non_autorise,
    SUM(CASE WHEN ta.nom_type = 'Mise à pied' THEN (a.date_fin - a.date_debut + 1) ELSE 0 END) AS mise_a_pied,
    -- Total des jours d'absence sans solde
    SUM(a.date_fin - a.date_debut + 1) AS total
FROM 
    absence a
JOIN 
    type_absence ta ON a.id_type_absence = ta.id_type_absence
WHERE 
    ta.nom_type IN ('Retard', 'Absence sans solde', 'Absence non autorisée', 'Mise à pied')
GROUP BY 
    a.id_employe;


-- heure supp
CREATE VIEW v_heure_sup_details AS
WITH calcul_durees AS (
    SELECT 
        pe.id_employe,
        pe.date_entree::DATE AS date_travail,
        EXTRACT(EPOCH FROM (pe.date_sortie - pe.date_entree)) / 3600 AS heures_travaillees, -- Convertit en heures
        -- Vérifie si c'est un dimanche
        CASE 
            WHEN EXTRACT(DOW FROM pe.date_entree) = 0 THEN TRUE 
            ELSE FALSE 
        END AS is_dimanche,
        -- Vérifie si c'est un jour férié
        CASE 
            WHEN EXISTS (
                SELECT 1 
                FROM jours_feries jf 
                WHERE jf.date = pe.date_entree::DATE
            ) THEN TRUE
            ELSE FALSE
        END AS is_jour_ferie
    FROM 
        presence_employe pe
),
heures_supp_tranche AS (
    SELECT 
        c.id_employe,
        c.date_travail,
        c.is_dimanche,
        c.is_jour_ferie,
        -- Heures supplémentaires 30% (de 8h à 10h)
        CASE 
            WHEN c.heures_travaillees > 8 THEN LEAST(c.heures_travaillees - 8, 2) 
            ELSE 0 
        END AS sup_30,
        -- Heures supplémentaires 40% (de 10h à 12h)
        CASE 
            WHEN c.heures_travaillees > 10 THEN LEAST(c.heures_travaillees - 10, 2) 
            ELSE 0 
        END AS sup_40,
        -- Heures supplémentaires 50% (de 12h à 14h)
        CASE 
            WHEN c.heures_travaillees > 12 THEN LEAST(c.heures_travaillees - 12, 2) 
            ELSE 0 
        END AS sup_50,
        -- Heures supplémentaires 100% (au-delà de 14h ou pour dimanches/jours fériés)
        CASE 
            WHEN c.heures_travaillees > 14 THEN c.heures_travaillees - 14
            WHEN c.is_dimanche OR c.is_jour_ferie THEN c.heures_travaillees -- Tout est majoré à 100%
            ELSE 0 
        END AS sup_100
    FROM 
        calcul_durees c
)
SELECT 
    t.id_employe,
    SUM(t.sup_30) AS sup_30,       -- Total heures supplémentaires à 30%
    SUM(t.sup_40) AS sup_40,       -- Total heures supplémentaires à 40%
    SUM(t.sup_50) AS sup_50,       -- Total heures supplémentaires à 50%
    SUM(t.sup_100) AS sup_100,     -- Total heures supplémentaires à 100%
    -- Total global des heures supplémentaires
    SUM(t.sup_30 + t.sup_40 + t.sup_50 + t.sup_100) AS total
FROM 
    heures_supp_tranche t
GROUP BY 
    t.id_employe;


CREATE VIEW v_taux_employe AS
SELECT
    e.id_employe,
    e.salaire_base,
    e.salaire_base / (30 * 8) AS taux_horaire, -- On suppose 30 jours travaillés et 8h par jour
    e.salaire_base / 30 AS taux_journalier
FROM
    employe e;


CREATE VIEW v_salaire_brut_employe AS
SELECT
    e.id_employe,
    e.salaire_base,
    (
        e.salaire_base +
        (
            SELECT SUM(
                (hs.sup_30 * t.taux_horaire * 1.3) + 
                (hs.sup_40 * t.taux_horaire * 1.4) + 
                (hs.sup_50 * t.taux_horaire * 1.5) + 
                (hs.sup_100 * t.taux_horaire * 2)
            )
            FROM v_heure_sup_details hs
            JOIN v_taux_employe t ON hs.id_employe = t.id_employe
            WHERE hs.id_employe = e.id_employe
        )
    ) AS salaire_brut
FROM
    employe e
;

-- conge
CREATE VIEW v_conge_paye AS
SELECT 
    e.id_employe,
    e.salaire_base,
    SUM(CASE WHEN tc.nom_type = 'Conge payé' THEN (ce.date_fin - ce.date_debut + 1) ELSE 0 END) AS conge_paye
FROM 
    employe e
LEFT JOIN 
    conge ce ON e.id_employe = ce.id_employe
LEFT JOIN 
    type_conge tc ON ce.id_type_conge = tc.id_type_conge
GROUP BY 
    e.id_employe, e.salaire_base;

CREATE VIEW v_conge_non_paye AS
SELECT 
    e.id_employe,
    e.salaire_base,
    SUM(CASE WHEN tc.nom_type = 'Conge non payé' THEN (ce.date_fin - ce.date_debut + 1) ELSE 0 END) AS conge_non_paye
FROM 
    employe e
LEFT JOIN 
    conge ce ON e.id_employe = ce.id_employe
LEFT JOIN 
    type_conge tc ON ce.id_type_conge = tc.id_type_conge
GROUP BY 
    e.id_employe, e.salaire_base;



CREATE OR REPLACE VIEW v_salaire_complet AS
SELECT
    e.id_employe,
    vsb.salaire_base,
    -- Salaire brut de l'employé
    vsb.salaire_brut,
    
    -- Prime de rendement
    COALESCE(vp.prime_rendement, 0) AS prime_rendement,
    
    -- Prime d'ancienneté
    COALESCE(vp.prime_ancienete, 0) AS prime_ancienete,
    
    -- Congé payé : Nombre total de jours de congé payé
    COALESCE(vc.conge_paye, 0) AS conge_paye,
    
    -- Montant correspondant au congé payé (nombre de jours * salaire journalier)
    COALESCE(vc.conge_paye, 0) * (vsb.salaire_brut / 30) AS montant_conge,

    -- Salaire brut avec primes, indemnités et congés payés
    vsb.salaire_brut +
    COALESCE(vp.prime_rendement, 0) +
    COALESCE(vp.prime_ancienete, 0) +
    COALESCE(vi.indemnite_licenciement, 0) +
    (COALESCE(vc.conge_paye, 0) * (vsb.salaire_brut / 30)) AS salaire_brut_avec_prime_indemnite
FROM
    employe e
JOIN 
    v_salaire_brut_employe vsb ON vsb.id_employe = e.id_employe
LEFT JOIN
    v_prime vp ON vp.id_employe = e.id_employe
LEFT JOIN
    v_indemnite vi ON vi.id_employe = e.id_employe
LEFT JOIN
    v_conge_paye vc ON vc.id_employe = e.id_employe;



CREATE VIEW v_bulletin_paie AS
SELECT
    e.id_employe,
    vsb.salaire_brut_avec_prime_indemnite as salaire_brut,
    -- Calcul de la CNAPS (exemple : 1% à la charge de l'employé, plafonné à 2 000 000 Ar)
    LEAST(vsb.salaire_brut_avec_prime_indemnite * 0.01, 20000) AS cotisation_cnaps,
    -- Calcul de l'OSTIE (exemple : 1% du salaire brut)
    vsb.salaire_brut_avec_prime_indemnite * 0.01 AS cotisation_ostie,
    calculer_irsa(vsb.salaire_brut_avec_prime_indemnite::NUMERIC) AS irsa,
    vsb.salaire_brut_avec_prime_indemnite - (LEAST(vsb.salaire_brut_avec_prime_indemnite * 0.01, 20000))  - (vsb.salaire_brut_avec_prime_indemnite * 0.01) - (calculer_irsa(vsb.salaire_brut_avec_prime_indemnite::NUMERIC)) AS salaire_net
FROM
    employe e
JOIN 
    v_salaire_complet vsb
ON 
    vsb.id_employe = e.id_employe
;

CREATE OR REPLACE VIEW v_information_employe_paie AS 
SELECT 
    e.id_employe,
    e.nom,
    e.prenom,
    e.date_naissance,
    e.email,
    emp.numero_cnaps,
    e.adresse,
    emp.date_embauche,
        
    -- Calcul de l'âge
    CAST(
        (EXTRACT(YEAR FROM CURRENT_DATE) - EXTRACT(YEAR FROM e.date_naissance)) -
        (CASE 
            WHEN EXTRACT(MONTH FROM CURRENT_DATE) < EXTRACT(MONTH FROM e.date_naissance) 
            OR (EXTRACT(MONTH FROM CURRENT_DATE) = EXTRACT(MONTH FROM e.date_naissance) 
                AND EXTRACT(DAY FROM CURRENT_DATE) < EXTRACT(DAY FROM e.date_naissance)) 
            THEN 1 
            ELSE 0 
        END)
    AS INTEGER) AS age,

        -- Calcul de l'ancienneté formaté en chaîne
    CONCAT(
        FLOOR(EXTRACT(YEAR FROM AGE(CURRENT_DATE, emp.date_embauche))), ' ans ',
        FLOOR(
            (EXTRACT(MONTH FROM AGE(CURRENT_DATE, emp.date_embauche)) * 1.0) 
        ), ' mois et ',
        FLOOR(
            (EXTRACT(DAY FROM AGE(CURRENT_DATE, emp.date_embauche)) * 1.0)
        ), ' jours'
    ) AS anciennete_formatee,
        
    p.nom as poste,
    d.nom as departement
        
    FROM 
        v_informations_employe e
    JOIN 
        employe emp ON e.id_employe = emp.id_employe
    JOIN 
        poste p ON p.id_poste = emp.id_poste
    JOIN 
        departement d ON p.id_departement = d.id_departement
    ;

-- Exemple de requête pour utiliser la vue
-- SELECT * FROM v_information_employe_paie;

-- Notes importantes :
-- 1. Adaptez les noms de tables et de colonnes à votre schéma de base de données
-- 2. Les calculs d'ancienneté utilisent des moyennes (30.44 jours par mois, 365.25 jours par an)
-- 3. Assurez-vous que les jointures correspondent à votre modèle de données