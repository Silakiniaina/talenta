CREATE TABLE genre(
   id_genre SERIAL,
   label VARCHAR(150)  NOT NULL,
   PRIMARY KEY(id_genre)
);

CREATE TABLE departement(
   id_departement SERIAL,
   nom VARCHAR(150)  NOT NULL,
   PRIMARY KEY(id_departement)
);

CREATE TABLE poste(
   id_poste SERIAL,
   nom VARCHAR(150)  NOT NULL,
   id_departement INTEGER NOT NULL,
   PRIMARY KEY(id_poste),
   FOREIGN KEY(id_departement) REFERENCES departement(id_departement)
);

CREATE TABLE recrutement(
   id_recrutement SERIAL,
   date_debut_recrutement DATE NOT NULL,
   date_fin_recrutement DATE NOT NULL,
   nombre INTEGER DEFAULT 1,
   id_poste INTEGER NOT NULL,
   PRIMARY KEY(id_recrutement),
   FOREIGN KEY(id_poste) REFERENCES poste(id_poste)
);

CREATE TABLE competence(
   id_competence SERIAL,
   label VARCHAR(250)  NOT NULL,
   PRIMARY KEY(id_competence)
);

CREATE TABLE type_questionaire(
   id_type_questionaire SERIAL,
   label VARCHAR(150)  NOT NULL,
   PRIMARY KEY(id_type_questionaire)
);

CREATE TABLE type_contrat(
   id_type_contrat SERIAL,
   label VARCHAR(150)  NOT NULL,
   PRIMARY KEY(id_type_contrat)
);

CREATE TABLE candidat(
   id_candidat SERIAL,
   nom VARCHAR(150)  NOT NULL,
   prenom VARCHAR(250)  NOT NULL,
   date_naissance DATE NOT NULL,
   adresse VARCHAR(250)  NOT NULL,
   id_genre INTEGER NOT NULL,
   PRIMARY KEY(id_candidat),
   FOREIGN KEY(id_genre) REFERENCES genre(id_genre)
);

CREATE TABLE employe(
   id_employe SERIAL ,
   date_embauche DATE DEFAULT NOW( ),
   id_candidat INTEGER NOT NULL,
   id_poste INTEGER NOT NULL,
   PRIMARY KEY(id_employe),
   FOREIGN KEY(id_candidat) REFERENCES candidat(id_candidat),
   FOREIGN KEY(id_poste) REFERENCES poste(id_poste)
);

CREATE TABLE questionaire(
   id_questionaire SERIAL,
   question TEXT NOT NULL,
   id_type_questionaire INTEGER NOT NULL,
   PRIMARY KEY(id_questionaire),
   FOREIGN KEY(id_type_questionaire) REFERENCES type_questionaire(id_type_questionaire)
);

CREATE TABLE contrat(
   id_contrat SERIAL,
   date_debut_contrat DATE DEFAULT NOW( ),
   salaire_base NUMERIC(18,2)   NOT NULL,
   date_fin_contrat DATE,
   id_candidat INTEGER NOT NULL,
   id_type_contrat INTEGER NOT NULL,
   PRIMARY KEY(id_contrat),
   FOREIGN KEY(id_candidat) REFERENCES candidat(id_candidat),
   FOREIGN KEY(id_type_contrat) REFERENCES type_contrat(id_type_contrat)
);

CREATE TABLE recrutement_candidat(
   id_candidat INTEGER,
   id_recrutement INTEGER,
   date_postule DATE DEFAULT NOW( ),
   PRIMARY KEY(id_candidat, id_recrutement),
   FOREIGN KEY(id_candidat) REFERENCES candidat(id_candidat),
   FOREIGN KEY(id_recrutement) REFERENCES recrutement(id_recrutement)
);

CREATE TABLE competence_recrutement(
   id_recrutement INTEGER,
   id_competence INTEGER,
   experience INTEGER NOT NULL,
   PRIMARY KEY(id_recrutement, id_competence),
   FOREIGN KEY(id_recrutement) REFERENCES recrutement(id_recrutement),
   FOREIGN KEY(id_competence) REFERENCES competence(id_competence)
);

CREATE TABLE competence_candidat(
   id_candidat INTEGER,
   id_competence INTEGER,
   experience VARCHAR(50)  NOT NULL,
   PRIMARY KEY(id_candidat, id_competence),
   FOREIGN KEY(id_candidat) REFERENCES candidat(id_candidat),
   FOREIGN KEY(id_competence) REFERENCES competence(id_competence)
);

CREATE TABLE reponse_test(
   id_candidat INTEGER,
   id_questionaire INTEGER,
   reponse SMALLINT NOT NULL,
   PRIMARY KEY(id_candidat, id_questionaire),
   FOREIGN KEY(id_candidat) REFERENCES candidat(id_candidat),
   FOREIGN KEY(id_questionaire) REFERENCES questionaire(id_questionaire)
);

CREATE TABLE reponse_entretient(
   id_candidat INTEGER,
   id_questionaire INTEGER,
   reponse SMALLINT NOT NULL,
   PRIMARY KEY(id_candidat, id_questionaire),
   FOREIGN KEY(id_candidat) REFERENCES candidat(id_candidat),
   FOREIGN KEY(id_questionaire) REFERENCES questionaire(id_questionaire)
);