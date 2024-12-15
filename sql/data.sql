INSERT INTO
    genre(label)
VALUES
    ('Homme'),
    ('Femme');


INSERT INTO
    departement(nom)
VALUES
    ('DSI'),
    ('DIGITAL'),
    ('MARKETING'),
    ('FINANCE');


INSERT INTO
    poste(nom, id_departement,annees_experience_requises)
VALUES
    ('Developpeur backend', 1,3),
    ('Developpeur frontent', 1,3),
    ('Integrateur', 1,4),
    ('Designer', 1, 0);

INSERT INTO 
    categorie_competence(label)
VALUES
    ('Soft Skill'),
    ('Hard Skill')
;

INSERT INTO
    competence (label,id_categorie_competence)
VALUES
    ('Communication',1),
    ('Gestion de projet',2),
    ('Analyse de données',2),
    ('Développement web',2),
    ('Gestion des relations clients',1),
    ('Marketing digital',2),
    ('Résolution de problèmes',1),
    ('Leadership',1),
    ('Travail en équipe',1),
    ('Créativité',1);

INSERT INTO
    type_contrat(label)
VALUES
    ('CDD'),
    ('CDI');

INSERT INTO
    status(label,corresponding_color)
VALUES
    ('Sheduled','info'),
    ('In progress','warning'),
    ('Finished','success'),
    ('Blocked','danger');

INSERT INTO
    role_talenta(nom_role)
VALUES
    ('Admin'),
    ('Responsable RH');

INSERT INTO
    type_diplome(label)
VALUES
    ('Licence'),
    ('Master'),
    ('Doctorat');

INSERT INTO 
    branche_education(nom_branche)
VALUES  
    ('Developpement'),
    ('Web'),
    ('Finance'),
    ('Agricole')
;

INSERT INTO
    competence_requise_poste (id_poste, id_competence, obligatoire)
VALUES
    (1, 6, true),
    (1, 5, true),
    (1, 4, false);

INSERT INTO
    diplome_requis_poste(id_poste, id_type_diplome, obligatoire, id_branche_education)
VALUES
    (1, 1, true,2),
    (1, 2, false,1);

--admin
INSERT INTO
    responsable(nom, email, mdp, id_role)
VALUES
    ('Sanda', 'sanda@admin.com', 'admin', 1);

--candidat
INSERT INTO candidat (nom, prenom, date_naissance, adresse, id_genre, email, mdp) VALUES
('Randrianarisoa', 'Arisoa', '1999-04-15', 'Lot II J 15 Ankadifotsy, Antananarivo', 1, 'arisoa.randrianarisoa@gmail.com', '123'),
('Rakoto', 'Tiana', '2001-07-22', 'Lot III M 45 Ambanidia, Antananarivo', 2, 'tiana.rakoto@yahoo.com', '123'),
('Ravelo', 'Hery', '1995-12-03', 'Lot IV K 32 Isotry, Antananarivo', 1, 'hery.ravelo@outlook.com', '123'),
('Andrianina', 'Miora', '1998-11-08', 'Lot V B 12 Ambohimena, Antsirabe', 2, 'miora.andrianina@gmail.com', '123'),
('Rasoanaivo', 'Faly', '2000-03-25', 'Lot VI T 10 Mahamasina, Antananarivo', 1, 'faly.rasoanaivo@hotmail.com', '123'),
('Rajaonarison', 'Lalao', '1997-06-12', 'Lot VII Z 8 Tsimbazaza, Antananarivo', 2, 'lalao.rajaonarison@gmail.com', '123'),
('Andriamihaja', 'Tojo', '1996-02-14', 'Lot VIII P 15 Itaosy, Antananarivo', 1, 'tojo.andriamihaja@gmail.com', '123'),
('Randriamalala', 'Sitraka', '1994-09-10', 'Lot IX F 22 Soavimasoandro, Antananarivo', 1, 'sitraka.randriamalala@gmail.com', '123'),
('Rasoamampionona', 'Tahina', '2002-05-05', 'Lot X G 20 Talatamaty, Antananarivo', 1, 'tahina.rasoamampionona@gmail.com', '123'),
('Raharimalala', 'Malala', '1993-12-18', 'Lot XI K 45 Alasora, Antananarivo', 2, 'malala.raharimalala@yahoo.com', '123');


-- Création des employés
INSERT INTO employe (date_embauche, id_candidat, id_poste) VALUES
('2024-01-15', 1, 1), 
('2024-02-10', 2, 2), 
('2024-03-20', 3, 3); 

-- Types de congé
INSERT INTO type_conge (id_type_conge, nom_type, est_conge_paye) VALUES
(1, 'Congé payé', true),       -- Congé rémunéré
(2, 'Congé sans solde', false), -- Congé non rémunéré
(3, 'Congé maladie', true);    -- Congé maladie rémunéré

INSERT INTO type_absence (nom_type, est_avec_solde)
VALUES
    ('Repos médical', true),
    ('Assistance maternité', true),
    ('Hospitalisation et convalescence', true),
    ('Événement familial', true), -- Ajustez selon votre politique d'entreprise
    ('Retard', false),
    ('Absence sans solde', false),
    ('Absence non autorisée', false),
    ('Mise à pied', false);

-- prime et indemnite
INSERT INTO type_prime (nom_type_prime) VALUES ('prime_rendement');
INSERT INTO type_prime (nom_type_prime) VALUES ('prime_ancienete');
INSERT INTO type_indemnite (nom_type_indemnite) VALUES ('indemnite_licenciement');



-- Création des contrats pour les employés
INSERT INTO contrat (date_debut_contrat, salaire_base, date_fin_contrat, id_candidat, id_type_contrat) VALUES
('2024-01-15', 1200000.00, '2025-01-14', 1, 1), 
('2024-02-10', 950000.00, '2024-08-10', 2, 2), 
('2024-03-20', 1100000.00, NULL, 3, 1); 


 -- Présence pour Arisoa (ID employé : 1)
INSERT INTO presence_employe (id_employe, date_entree, date_sortie) VALUES
(1, '2024-10-01 08:00:00', '2024-10-01 17:00:00'), -- Journée normale (9h)
(1, '2024-10-02 20:00:00', '2024-10-03 04:00:00'), -- Travail de nuit
(1, '2024-10-04 08:00:00', '2024-10-04 18:30:00'), -- Heure supplémentaire (10h30)
(1, '2024-10-05 08:30:00', '2024-10-05 16:00:00'), -- Journée normale (7h30)
(1, '2024-10-08 22:00:00', '2024-10-09 05:00:00'); -- Travail de nuit

-- Présence pour Tiana (ID employé : 2)
INSERT INTO presence_employe (id_employe, date_entree, date_sortie) VALUES
(2, '2024-10-01 07:30:00', '2024-10-01 16:30:00'), -- Journée normale (9h)
(2, '2024-10-03 20:00:00', '2024-10-04 03:00:00'), -- Travail de nuit
(2, '2024-10-06 09:00:00', '2024-10-06 19:00:00'), -- Heure supplémentaire (10h)
(2, '2024-10-07 08:00:00', '2024-10-07 15:30:00'), -- Journée normale (7h30)
(2, '2024-10-10 23:00:00', '2024-10-11 04:00:00'); -- Travail de nuit

-- Présence pour Hery (ID employé : 3)
INSERT INTO presence_employe (id_employe, date_entree, date_sortie) VALUES
(3, '2024-10-02 08:00:00', '2024-10-02 17:00:00'), -- Journée normale (9h)
(3, '2024-10-05 21:00:00', '2024-10-06 02:30:00'), -- Travail de nuit
(3, '2024-10-09 08:30:00', '2024-10-09 18:00:00'), -- Heure supplémentaire (9h30)
(3, '2024-10-11 08:00:00', '2024-10-11 16:30:00'), -- Journée normale (8h30)
(3, '2024-10-13 20:30:00', '2024-10-14 04:00:00'); -- Travail de nuit

-- Ajout d'un congé pour l'employé 1
INSERT INTO conge (id_conge, id_employe, id_type_conge, date_debut, date_fin, id_contrat) VALUES
(1, 1, 1, '2024-10-10', '2024-10-20', 1); -- Congé payé du 10 au 20 octobre 2024


INSERT INTO hierarchie(nom_hierarchie) VALUES 
    ('Junior'),
    ('Senior')
;

INSERT INTO csp (code_csp, description)
VALUES
    ('OP-2A', 'Technicien de niveau intermédiaire'),
    ('OP-1B', 'Manager junior'),
    ('EX-3C', 'Expert senior'),
    ('OP-2B', 'Technicien avancé'),
    ('EX-2A', 'Expert débutant');
