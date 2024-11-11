-- Insertion des types de compétences
INSERT INTO TypeCompetence (type) VALUES 
('Technique'),
('Diplôme'),
('Langue'),
('Autre');


INSERT INTO Competence (nom, id_typeCompetence, details) VALUES 
('Java', 1, 'Programmation'),
('Python', 1, 'Programmation'),
('C++', 1, 'Programmation'),
('C#', 1, 'Programmation'),
('JavaScript', 1, 'Programmation'),
('PHP', 1, 'Programmation'),
('PostgreSQL', 1, 'Base de donnees'),
('MongoDB', 1, 'Base de donnees'),
('SQLite', 1, 'Base de donnees'),
('Mysql', 1, 'Base de donnees'),
('Oracle', 1, 'Base de donnees'),
('Spring Boot', 1, 'Programmation'),
('Django', 1, 'Programmation'),
('Node.js', 1, 'Programmation'),
('Git', 1, 'Outil');


INSERT INTO Competence (nom, id_typeCompetence, details) VALUES 
('Licence', 2, NULL),
('Master', 2, NULL),
('BTS', 2, NULL),
('Doctorat', 2, NULL),
('DUT', 2, NULL);

INSERT INTO Competence (nom, id_typeCompetence, details) VALUES 
('Français', 3, NULL),
('Anglais', 3, NULL),
('Espagnol', 3, NULL),
('Mandarin', 3, NULL);

INSERT INTO Competence (nom, id_typeCompetence, details) VALUES 
('Volontariat', 4, NULL),
('Mentorat', 4, NULL);



INSERT INTO Poste (nom, nb_employe) VALUES 
('Developpeur Full Stack', 3),
('Chef de projet', 3),
('Testeur QA', 3),
('Administrateur Systemes et Reseaux', 3);

INSERT INTO Genre (genre) VALUES ('homme'), ('femme');


INSERT INTO SoftSkill (nom) VALUES 
('Communication'),
('Travail en equipe'),
('Leadership'),
('Adaptabilite'),
('Resolution de problemes');

