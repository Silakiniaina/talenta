INSERT INTO genre(label) VALUES
('Homme'),
('Femme');

INSERT INTO departement (nom) VALUES
('Développement logiciel'),
('Infrastructures et systèmes'),
('Sécurité informatique'),
('Data Science'),
('Support technique'),
('Ressources humaines');

INSERT INTO hierarchie (nom_hierarchie) VALUES
('Stagiaire'),
('Junior'),
('Moyenne expérience'),
('Senior'),
('Lead'),
('Directeur');

INSERT INTO poste (nom, id_departement, annees_experience_requises, id_hierarchie) VALUES
('Développeur junior', 1, 1, 2),
('Administrateur système', 2, 3, 3),
('Expert en cybersécurité', 3, 5, 4),
('Data Scientist', 4, 3, 3),
('Technicien support', 5, 2, 2),
('Responsable des ressources humaines', 6, 7, 5);

INSERT INTO branche_education (nom_branche, description_branche) VALUES
('Informatique', 'Études liées au développement logiciel, à la gestion des systèmes informatiques et à la cybersécurité.'),
('Data Science', 'Études qui portent sur l analyse de données massives et la modélisation de données.'),
('Réseaux et systèmes', 'Formation spécialisée dans les réseaux informatiques, les serveurs et la gestion des infrastructures.'),
('Gestion des ressources humaines', 'Études axées sur la gestion des talents, le développement organisationnel et la stratégie RH.');

INSERT INTO categorie_competence (label) VALUES
('Développement logiciel'),
('Cybersécurité'),
('Administration des systèmes'),
('Gestion des données'),
('Support client'),
('Management');

INSERT INTO competence (label, id_categorie_competence) VALUES
('Java', 1),
('Python', 1),
('SQL', 1),
('Sécurité réseau', 2),
('Gestion des accès', 2),
('Virtualisation', 3),
('Machine learning', 4),
('Analyse de données', 4),
('Gestion des incidents', 5),
('Leadership', 6);

INSERT INTO csp (code_csp, description) VALUES 
('M1', 'Cadres supérieurs, professions libérales'),
('M2', 'Cadres moyens'),
('OS1', 'Ouvriers qualifiés'),
('OS2', 'Ouvriers spécialisés'),
('OS3', 'Ouvriers non qualifiés'),
('OP1A', 'Employés administratifs qualifiés'),
('OP1B', 'Employés administratifs non qualifiés'),
('OP2A', 'Techniciens et agents de maîtrise qualifiés'),
('OP2B', 'Techniciens et agents de maîtrise non qualifiés'),
('OP3', 'Agents dentretien et de service');

INSERT INTO type_contrat(label) VALUES
('CDD'),
('CDI');

INSERT INTO status(label,corresponding_color) VALUES
('Sheduled','info'),
('In progress','warning'),
('Finished','success'),
('Blocked','danger');

INSERT INTO role_talenta(nom_role) VALUES
('Admin'),
('Responsable RH');

INSERT INTO type_diplome(label) VALUES
('Licence'),
('Master'),
('Doctorat');

--admin
INSERT INTO responsable(nom, email, mdp, id_role) VALUES
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

INSERT INTO candidat (nom, prenom, date_naissance, adresse, id_genre, email, mdp) VALUES
('Rakotomamonjy', 'Hasina', '2000-02-15', 'Lot XII R 21 Ambohijanaka, Antananarivo', 1, 'hasina.rakotomamonjy@gmail.com', '123'),
('Ravoninjatovo', 'Sarobidy', '1998-10-30', 'Lot XIII D 14 Ambatolampy, Antananarivo', 2, 'sarobidy.ravoninjatovo@gmail.com', '123'),
('Andriantseheno', 'Zo', '1995-05-11', 'Lot XIV H 32 Ankadimbahoaka, Antananarivo', 1, 'zo.andriantseheno@hotmail.com', '123'),
('Ratsimbazafy', 'Fanja', '1999-08-27', 'Lot XV J 16 Ambohibao, Antananarivo', 2, 'fanja.ratsimbazafy@yahoo.com', '123'),
('Rabenjamina', 'Aina', '2001-01-20', 'Lot XVI Z 5 Ambatoroka, Antananarivo', 1, 'aina.rabenjamina@gmail.com', '123');


-- Types de congé
INSERT INTO type_conge (id_type_conge, nom_type, est_conge_paye) VALUES
(1, 'Congé payé', true),       -- Congé rémunéré
(2, 'Congé sans solde', false)
; 

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
INSERT INTO type_prime (nom_type_prime) VALUES 
('prime_rendement'),
('prime_ancienete');

INSERT INTO type_indemnite (nom_type_indemnite) VALUES 
('indemnite_licenciement');

INSERT INTO categorie_experience( id_categorie_experience, libelle_categorie, duree_min_annees, duree_max_annees, coefficient ) VALUES 
( 1, 'Stage ou expérience courte', 0, 1, 0.50),
( 2, 'Expérience junior', 1, 2, 1),
( 3, 'Expérience intermédiaire', 2, 5, 2),
( 4, 'Expérience senior', 5, 10, 3),
( 5, 'Expérience expert', 10, null, 4);

INSERT INTO niveau_competence( id_niveau_competence, libelle_niveau, niveau_competence, coefficient ) VALUES 
( 1, 'Débutant', 'Débutant', 0.50),
( 2, 'Intermédiaire', 'Intermédiaire', 1),
( 3, 'Avancé', 'Avancé', 2),
( 4, 'Expert', 'Expert', 3);

INSERT INTO niveau_diplome( id_niveau_diplome, libelle_niveau, niveau_diplome, coefficient ) VALUES 
( 1, 'Baccalauréat', 'Bac', 1),
( 2, 'Brevet de Technicien Supérieur', 'BTS', 2),
( 3, 'Diplôme Universitaire Technologique', 'DUT', 2),
( 4, 'Licence', 'Licence', 3),
( 5, 'Master', 'Master', 4),
( 6, 'Doctorat', 'Doctorat', 5);

INSERT INTO competence_candidat (id_candidat, id_competence, id_niveau_competence)
VALUES
  (1, 1, 2),
  (1, 3, 4),
  (1, 5, 3),
  (1, 7, 2),
  (1, 8, 1),
  (2, 2, 3),
  (2, 4, 1),
  (2, 6, 4),
  (2, 9, 2),
  (2, 10, 3),
  (3, 1, 1),
  (3, 4, 2),
  (3, 6, 3),
  (3, 7, 4),
  (3, 9, 1),
  (4, 2, 4),
  (4, 3, 1),
  (4, 5, 2),
  (4, 8, 3),
  (4, 10, 4),
  (5, 2, 2),
  (5, 4, 3),
  (5, 7, 1),
  (5, 9, 4),
  (5, 10, 2),
  (6, 1, 3),
  (6, 5, 4),
  (6, 8, 2),
  (6, 9, 3),
  (6, 10, 1),
  (7, 3, 4),
  (7, 6, 1),
  (7, 7, 3),
  (7, 9, 2),
  (7, 10, 4),
  (8, 1, 2),
  (8, 4, 3),
  (8, 5, 1),
  (8, 8, 4),
  (8, 10, 2),
  (9, 2, 1),
  (9, 6, 4),
  (9, 7, 2),
  (9, 8, 3),
  (9, 9, 1),
  (10, 3, 2),
  (10, 4, 1),
  (10, 5, 4),
  (10, 6, 3),
  (10, 7, 2);

INSERT INTO competence_candidat (id_candidat, id_competence, id_niveau_competence)
VALUES
  (11, 1, 2),
  (11, 3, 4),
  (11, 5, 3),
  (11, 7, 2),
  (11, 8, 1),
  (12, 2, 3),
  (12, 4, 1),
  (12, 6, 4),
  (12, 9, 2),
  (12, 10, 3),
  (13, 1, 1),
  (13, 4, 2),
  (13, 6, 3),
  (13, 7, 4),
  (13, 9, 1),
  (14, 2, 4),
  (14, 3, 1),
  (14, 5, 2),
  (14, 8, 3),
  (14, 10, 4),
  (15, 2, 2),
  (15, 4, 3),
  (15, 7, 1),
  (15, 9, 4),
  (15, 10, 2)
;
INSERT INTO education_candidat (id_candidat, date_debut, date_fin, id_type_diplome, nom_ecole, id_branche_education, id_niveau_diplome) VALUES
  (1, '2018-09-01', '2021-06-30', 1, 'Université de Madagascar', 1, 4),
  (1, '2022-01-15', '2023-12-15', 2, 'Ecole Supérieure de Technologie', 2, 3),
  (2, '2017-09-01', '2020-06-30', 3, 'Institut Polytechnique de Madagascar', 3, 5),
  (2, '2021-02-01', '2023-11-01', 1, 'Université de la Réunion', 4, 6),
  (3, '2016-10-01', '2019-07-31', 2, 'Ecole de Management', 1, 2),
  (3, '2020-01-01', '2022-12-31', 3, 'Université de Tamatave', 2, 4),
  (4, '2015-09-01', '2018-06-30', 1, 'Université d’Antananarivo', 3, 3),
  (4, '2019-03-01', '2022-12-31', 2, 'Institut de Technologie', 4, 5),
  (5, '2017-09-01', '2020-06-30', 3, 'Ecole Nationale d’Ingénieurs', 1, 6),
  (5, '2021-02-01', '2023-11-01', 2, 'Ecole de Gestion', 2, 1),
  (6, '2016-09-01', '2019-05-31', 1, 'Université de Fianarantsoa', 3, 2),
  (6, '2020-01-01', '2022-12-31', 3, 'Institut Supérieur des Sciences', 4, 4),
  (7, '2018-09-01', '2021-06-30', 2, 'Université de Mahajanga', 1, 3),
  (7, '2022-02-01', '2024-01-31', 3, 'Université de Nosy Be', 2, 6),
  (8, '2015-09-01', '2018-06-30', 1, 'Université de Toamasina', 4, 5),
  (8, '2019-01-01', '2022-12-31', 3, 'Institut Supérieur de Technologie', 1, 2),
  (9, '2017-10-01', '2020-07-31', 2, 'Université des Sciences et Techniques', 3, 1),
  (9, '2021-03-01', '2023-12-31', 1, 'Ecole Polytechnique de Madagascar', 2, 4),
  (10, '2016-09-01', '2019-05-31', 3, 'Université de Paris', 4, 6),
  (10, '2020-01-01', '2022-12-31', 2, 'Université de Mahajanga - Ecole de Gestion', 1, 5);

INSERT INTO education_candidat (id_candidat, date_debut, date_fin, id_type_diplome, nom_ecole, id_branche_education, id_niveau_diplome) VALUES
  (11, '2018-09-01', '2021-06-30', 2, 'Université de Madagascar', 1, 4),
  (11, '2022-01-15', '2023-12-15', 3, 'Ecole Supérieure de Technologie', 2, 3),
  (12, '2017-09-01', '2020-06-30', 1, 'Institut Polytechnique de Madagascar', 3, 5),
  (12, '2021-02-01', '2023-11-01', 2, 'Université de la Réunion', 4, 6),
  (13, '2016-10-01', '2019-07-31', 1, 'Ecole de Management', 1, 2),
  (13, '2020-01-01', '2022-12-31', 2, 'Université de Tamatave', 2, 4),
  (14, '2015-09-01', '2018-06-30', 3, 'Université d’Antananarivo', 3, 3),
  (14, '2019-03-01', '2022-12-31', 1, 'Institut de Technologie', 4, 5),
  (15, '2017-09-01', '2020-06-30', 2, 'Ecole Nationale d’Ingénieurs', 1, 6),
  (15, '2021-02-01', '2023-11-01', 3, 'Ecole de Gestion', 2, 1)
;

INSERT INTO specialite (nom_specialite, description_specialite, id_branche_education) VALUES
  ('Software Development', 'Specialization in designing, developing, and maintaining software applications.', 1),
  ('Cybersecurity', 'Focuses on protecting systems, networks, and programs from digital attacks.', 1),
  ('Cloud Computing', 'The use of remote servers hosted on the internet to store, manage, and process data.', 2),
  ('Data Science and Analytics', 'Using statistical and computational techniques to extract insights from data.', 2),
  ('Network Administration', 'Managing and maintaining computer networks to ensure optimal performance and security.', 3),
  ('Artificial Intelligence and Machine Learning', 'Specialization in the development of algorithms that allow systems to learn and make decisions.', 3),
  ('Database Management', 'The practice of using software to store, organize, and manage data effectively.', 1),
  ('Web Development', 'Specializing in the creation and maintenance of websites and web applications.', 4),
  ('Mobile Application Development', 'Designing and building software applications for mobile devices.', 4),
  ('Blockchain Technology', 'Focus on developing and managing decentralized digital ledgers and applications using blockchain.', 2);


INSERT INTO experience_candidat (id_candidat, date_debut, date_fin, id_specialite, entreprise, id_categorie_experience) VALUES
  (1, '2021-06-01', '2023-06-30', 1, 'Tech Solutions Inc.', 2),
  (1, '2020-03-01', '2021-05-31', 5, 'Innovate Corp.', 3),
  (2, '2019-01-01', '2021-12-31', 2, 'SecureTech', 1),
  (2, '2022-02-01', '2024-01-31', 8, 'WebDev Agency', 4),
  (3, '2020-07-01', '2022-06-30', 4, 'DataScience Labs', 3),
  (3, '2021-08-01', '2023-07-31', 3, 'AI Ventures', 2),
  (4, '2018-09-01', '2020-06-30', 6, 'MobileTech Solutions', 1),
  (4, '2021-03-01', '2023-02-28', 7, 'Blockchain Innovations', 5),
  (5, '2017-05-01', '2020-04-30', 1, 'DevCorp Ltd.', 4),
  (5, '2021-06-01', '2023-05-31', 10, 'CryptoWorld Technologies', 2),
  (6, '2016-01-01', '2018-12-31', 3, 'DataConnect', 3),
  (6, '2019-02-01', '2021-01-31', 9, 'AppCreators', 1),
  (7, '2018-03-01', '2020-02-29', 2, 'CloudXperts', 2),
  (7, '2021-07-01', '2023-06-30', 4, 'Tech Innovations', 5),
  (8, '2019-11-01', '2021-10-31', 5, 'WebBuilders Inc.', 4),
  (8, '2022-01-01', '2024-12-31', 6, 'CyberSec Solutions', 1),
  (9, '2017-08-01', '2019-07-31', 8, 'FutureTech Labs', 2),
  (9, '2020-01-01', '2022-12-31', 7, 'BlockChain Hub', 5),
  (10, '2016-04-01', '2018-03-31', 9, 'TechCrafters Ltd.', 3),
  (10, '2019-01-01', '2021-12-31', 6, 'AppVision', 4);

INSERT INTO experience_candidat (id_candidat, date_debut, date_fin, id_specialite, entreprise, id_categorie_experience) VALUES
  (11, '2021-06-01', '2023-06-30', 1, 'Tech Solutions Inc.', 2),
  (11, '2020-03-01', '2021-05-31', 5, 'Innovate Corp.', 3),
  (12, '2019-01-01', '2021-12-31', 2, 'SecureTech', 1),
  (12, '2022-02-01', '2024-01-31', 8, 'WebDev Agency', 4),
  (13, '2020-07-01', '2022-06-30', 4, 'DataScience Labs', 3),
  (13, '2021-08-01', '2023-07-31', 3, 'AI Ventures', 2),
  (14, '2018-09-01', '2020-06-30', 6, 'MobileTech Solutions', 1),
  (14, '2021-03-01', '2023-02-28', 7, 'Blockchain Innovations', 5),
  (15, '2017-05-01', '2020-04-30', 1, 'DevCorp Ltd.', 4),
  (15, '2021-06-01', '2023-05-31', 10, 'CryptoWorld Technologies', 2)
;

INSERT INTO contrat (id_candidat, salaire_base, id_type_contrat, date_debut_contrat, date_fin_contrat)
VALUES
  (1, 250000.00, 1, '2023-01-01', '2024-01-01'),
  (2, 280000.00, 1, '2023-01-02', '2024-01-02'),
  (3, 300000.00, 1, '2023-01-03', '2024-01-03'),
  (4, 350000.00, 1, '2023-01-04', '2024-01-04'),
  (5, 320000.00, 1, '2023-01-05', '2024-01-05'),
  (6, 4000000.00, 1, '2023-01-06', '2024-01-06'),
  (7, 330000.00, 1, '2023-01-07', '2024-01-07'),
  (8, 360000.00, 1, '2023-01-08', '2024-01-08'),
  (9, 380000.00, 1, '2023-01-09', '2024-01-09'),
  (10, 450000.00, 1, '2023-01-10', '2024-01-10');

INSERT INTO employe (id_candidat, id_poste, salaire_base, numero_cnaps, date_embauche) VALUES
  (1, 1, 250000.00, 1000001, '2023-01-01'),
  (2, 2, 280000.00, 1000002, '2023-01-02'),
  (3, 3, 300000.00, 1000003, '2023-01-03'),
  (4, 4, 350000.00, 1000004, '2023-01-04'),
  (5, 5, 320000.00, 1000005, '2023-01-05'),
  (6, 1, 4000000.00, 1000006, '2023-01-06'),
  (7, 2, 330000.00, 1000007, '2023-01-07'),
  (8, 3, 360000.00, 1000008, '2023-01-08'),
  (9, 4, 380000.00, 1000009, '2023-01-09'),
  (10, 5, 450000.00, 1000010, '2023-01-10');

