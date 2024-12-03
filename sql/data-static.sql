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
    genre(label)
VALUES
    ('Homme'),
    ('Femme');

INSERT INTO
    type_questionaire (label)
VALUES
    ('Compétences Techniques'),
    ('Competences Theoriques'),
    ('Aptitudes Interpersonnelles')
;

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
    responsable(nom, email, mdp, id_role)
VALUES
    ('Sanda', 'sanda@admin.com', 'admin', 1);

INSERT INTO 
    candidat(nom,prenom,date_naissance,adresse,email,mdp,id_genre)
VALUES 
    ('Sanda','Silakiniaina','2005-07-12','Ambovokely','sanda@gmail.com','admin',1);

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


-- Création des employés
INSERT INTO employe (date_embauche, id_candidat, id_poste) VALUES
('2024-01-15', 1, 1), 
('2024-02-10', 2, 2), 
('2024-03-20', 3, 3); 

-- Création des contrats pour les employés
INSERT INTO contrat (date_debut_contrat, salaire_base, date_fin_contrat, id_candidat, id_type_contrat) VALUES
('2024-01-15', 1200000.00, '2025-01-14', 1, 1), 
('2024-02-10', 950000.00, '2024-08-10', 2, 2), 
('2024-03-20', 1100000.00, NULL, 3, 1);         

