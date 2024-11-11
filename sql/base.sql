CREATE DATABASE Talenta;
\c talenta 

CREATE TABLE Genre(
   id SERIAL PRIMARY KEY,
   genre VARCHAR(50) NOT NULL
);

CREATE TABLE Personne(
   id SERIAL PRIMARY KEY,
   nom VARCHAR(50) NOT NULL,
   prenom VARCHAR(50) NOT NULL,
   date_naissance DATE NOT NULL,
   adresse VARCHAR(50),
   id_genre INT REFERENCES Genre(id)
);

CREATE TABLE Experience(
    id SERIAL PRIMARY KEY,
    id_personne INT REFERENCES Personne(id),
    annee DECIMAL (3,1)
);

CREATE TABLE Poste(
   id SERIAL PRIMARY KEY,
   nom VARCHAR(50) NOT NULL,
   nb_employe INT NOT NULL
);

CREATE TABLE TypeCompetence(
   id SERIAL PRIMARY KEY,
   type VARCHAR(50) NOT NULL
);

CREATE TABLE Competence(
   id SERIAL PRIMARY KEY,
   nom VARCHAR(50) NOT NULL,
   id_typeCompetence INT NOT NULL REFERENCES TypeCompetence(id),
   details VARCHAR(100)
);

CREATE TABLE PosteCompetence(
    id SERIAL PRIMARY KEY,
    id_poste INT REFERENCES Poste(id),
    id_competence INT REFERENCES Competence(id)
);

CREATE TABLE PersonneCompetence(
    id SERIAL PRIMARY KEY,
    id_personne INT REFERENCES Personne(id),
    id_competence INT REFERENCES Competence(id)
);

CREATE TABLE PosteExigence(
   id INT,
   id_poste INT NOT NULL REFERENCES Poste(id),
   age_min INT,
   age_max INT,
   id_genre INT REFERENCES Genre(id),
   experience DECIMAL (3,1)
);

CREATE TABLE Employe(
   id SERIAL PRIMARY KEY,
   id_personne INT REFERENCES Personne(id),
   date_embauche DATE NOT NULL,
   date_fin DATE,
   id_poste INT REFERENCES Poste(id)
);

CREATE TABLE Candidat(
    id SERIAL PRIMARY KEY,
    id_personne INT REFERENCES Personne(id),
    id_poste INT REFERENCES Poste(id)
);

CREATE TABLE SoftSkill(
    id SERIAL PRIMARY KEY,
    nom VARCHAR(30)
);

CREATE TABLE PersonneSoftSkill(
    id SERIAL PRIMARY KEY,
    id_personne INT REFERENCES Personne(id),
    id_softSkill INT REFERENCES SoftSkill(id)
);

CREATE TABLE Contrat(
    id SERIAL PRIMARY KEY,
    id_personne INT REFERENCES Personne(id),
    id_poste INT REFERENCES Poste(id),
    date_debut DATE,
    date_fin DATE
);




