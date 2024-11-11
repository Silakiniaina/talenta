CREATE OR REPLACE FUNCTION reset_personne_related()
RETURNS void AS
$$
BEGIN
    DELETE FROM PersonneCompetence;
    ALTER SEQUENCE personnecompetence_id_seq RESTART WITH 1;
    DELETE FROM PersonneHobby;
    ALTER SEQUENCE personnehobby_id_seq RESTART WITH 1;
    DELETE FROM Candidat;
    ALTER SEQUENCE candidat_id_seq RESTART WITH 1;
    DELETE FROM Personne;
    ALTER SEQUENCE personne_id_seq RESTART WITH 1;
END;
$$ LANGUAGE plpgsql;

select reset_personne_related();


---------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------MIORA-------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------
-- Candidat 1: Miora
INSERT INTO Personne (nom, prenom, date_naissance, adresse, id_genre) 
VALUES ('Randriana', 'Miora', '1990-12-10', 'Antananarivo, Madagascar', 2);

INSERT INTO Experience (id_personne, annee) VALUES (1, 4.5);

INSERT INTO PersonneCompetence (id_personne, id_competence) VALUES 
(1, 1),  
(1, 2),  
(1, 5), 
(1, 6),  
(1, 7),  
(1, 10),
(1, 13), 
(1, 22); 

INSERT INTO Candidat (id_personne, id_poste) VALUES (1, 1);


---------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------TIANA-------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------
-- Candidat 2: Tiana
INSERT INTO Personne (nom, prenom, date_naissance, adresse, id_genre) 
VALUES ('Rakotoarivelo', 'Tiana', '1987-07-18', 'Toamasina, Madagascar', 1);

INSERT INTO Experience (id_personne, annee) VALUES (2, 5.0);

INSERT INTO PersonneCompetence (id_personne, id_competence) VALUES 
(2, 1),  
(2, 4), 
(2, 7),
(2, 10), 
(2, 13),
(2, 22),
(2, 9);  

INSERT INTO Candidat (id_personne, id_poste) VALUES (2, 1);

---------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------Haja-------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------
-- Candidat 3: Haja
INSERT INTO Personne (nom, prenom, date_naissance, adresse, id_genre) 
VALUES ('Rasolonandrasana', 'Haja', '1995-01-25', 'Antsirabe, Madagascar', 1);

INSERT INTO Experience (id_personne, annee) VALUES (3, 2.5);

INSERT INTO PersonneCompetence (id_personne, id_competence) VALUES 
(3, 1),  
(3, 5),  
(3, 8), 
(3, 12), 
(3, 13), 
(3, 22);

INSERT INTO Candidat (id_personne, id_poste) VALUES (3, 1);

---------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------Zo-------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------
-- Candidat 4: Zo
INSERT INTO Personne (nom, prenom, date_naissance, adresse, id_genre) 
VALUES ('Andriambelo', 'Zo', '1992-03-15', 'Mahajanga, Madagascar', 1);

INSERT INTO Experience (id_personne, annee) VALUES (4, 4.0);

INSERT INTO PersonneCompetence (id_personne, id_competence) VALUES 
(4, 1),  
(4, 5),  
(4, 6),  
(4, 8),  
(4, 13),
(4, 22), 
(4, 14), 
(4, 18); 

INSERT INTO Candidat (id_personne, id_poste) VALUES (4, 1);

---------------------------------------------------------------------------------------------------------------------------------------
----------------------------------------------------------Sarah-------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------
-- Candidat 5: Sarah
INSERT INTO Personne (nom, prenom, date_naissance, adresse, id_genre) 
VALUES ('Rakotonarivo', 'Sarah', '1993-06-05', 'Fianarantsoa, Madagascar', 2);

INSERT INTO Experience (id_personne, annee) VALUES (5, 3.5);

INSERT INTO PersonneCompetence (id_personne, id_competence) VALUES 
(5, 1),  
(5, 2), 
(5, 5),  
(5, 7),  
(5, 13), 
(5, 22), 
(5, 18); 

INSERT INTO Candidat (id_personne, id_poste) VALUES (5, 1);

---------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------------------------------Test/Entretien----------------------------------------------------------------
---------------------------------------------------------------------------------------------------------------------------------------------
-- Soft skills pour Miora (Candidat 1)
-- Miora a 3 soft skills
INSERT INTO PersonneSoftSkill (id_personne, id_softSkill) VALUES
(1, 1),  
(1, 2), 
(1, 3);  

-- Soft skills pour Tiana (Candidat 2)
-- Tiana a 4 soft skills
INSERT INTO PersonneSoftSkill (id_personne, id_softSkill) VALUES
(2, 1), 
(2, 2),  
(2, 4),  
(2, 5);  

-- Soft skills pour Haja (Candidat 3)
-- Haja a 3 soft skills
INSERT INTO PersonneSoftSkill (id_personne, id_softSkill) VALUES
(3, 3), 
(3, 4),  
(3, 5); 

-- Soft skills pour Zo (Candidat 4)
-- Zo a 3 soft skills
INSERT INTO PersonneSoftSkill (id_personne, id_softSkill) VALUES
(4, 2),  
(4, 3),  
(4, 5); 

-- Soft skills pour Sarah (Candidat 5)
-- Sarah a 2 soft skills
INSERT INTO PersonneSoftSkill (id_personne, id_softSkill) VALUES
(5, 1),  
(5, 4); 


