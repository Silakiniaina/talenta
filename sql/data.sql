INSERT INTO departement(nom) VALUES
    ('DSI'),
    ('DIGITAL'),
    ('MARKETING'),
    ('FINANCE')
;

INSERT INTO poste(nom,id_departement) VALUES
    ('Developpeur backend',1),
    ('Developpeur frontent',1),
    ('Integrateur',1),
    ('Designer',1)
;

INSERT INTO competence (label) VALUES 
('Communication'),
('Gestion de projet'),
('Analyse de données'),
('Développement web'),
('Gestion des relations clients'),
('Marketing digital'),
('Résolution de problèmes'),
('Leadership'),
('Travail en équipe'),
('Créativité');

INSERT INTO genre(label) VALUES
    ('Homme'),
    ('Femme')
;

INSERT INTO type_questionaire (label) VALUES ('Compétences Techniques');
INSERT INTO type_questionaire (label) VALUES ('Aptitudes Interpersonnelles');

INSERT INTO questionaire (question, id_type_questionaire) VALUES ('Maîtrisez-vous Java ?', 1);
INSERT INTO questionaire (question, id_type_questionaire) VALUES ('Avez-vous de l expérience en gestion de projet ?', 1);
INSERT INTO questionaire (question, id_type_questionaire) VALUES ('Êtes-vous à l aise pour travailler en équipe ?', 2);
INSERT INTO questionaire (question, id_type_questionaire) VALUES ('Comment évaluez-vous votre capacité à résoudre des conflits ?', 2);


INSERT INTO type_contrat(label) VALUES
    ('CDD'),
    ('CDI')
;

INSERT INTO status(label) VALUES 
    ('Sheduled'),
    ('In progress'),
    ('Finished'),
    ('Blocked')
;

INSERT INTO competence_requise(id_poste, id_competence,experience) VALUES 
    (3,1,0),
    (3,2,3),
    (3,3,1)
;