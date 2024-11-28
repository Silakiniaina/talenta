CREATE TABLE genre(
    id_genre SERIAL,
    label VARCHAR(150) NOT NULL,
    PRIMARY KEY(id_genre)
);

CREATE TABLE departement(
    id_departement SERIAL,
    nom VARCHAR(150) NOT NULL,
    PRIMARY KEY(id_departement)
);

CREATE TABLE poste(
    id_poste SERIAL,
    nom VARCHAR(150) NOT NULL,
    id_departement INTEGER NOT NULL,
    PRIMARY KEY(id_poste),
    FOREIGN KEY(id_departement) REFERENCES departement(id_departement)
);

CREATE TABLE status(
    id_status SERIAL,
    label VARCHAR(150) NOT NULL,
    PRIMARY KEY(id_status)
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
    label VARCHAR(250) NOT NULL,
    PRIMARY KEY(id_competence)
);

CREATE TABLE type_questionaire(
    id_type_questionaire SERIAL,
    label VARCHAR(150) NOT NULL,
    PRIMARY KEY(id_type_questionaire)
);

CREATE TABLE type_contrat(
    id_type_contrat SERIAL,
    label VARCHAR(150) NOT NULL,
    PRIMARY KEY(id_type_contrat)
);

CREATE TABLE candidat(
    id_candidat SERIAL,
    nom VARCHAR(150) NOT NULL,
    prenom VARCHAR(250) NOT NULL,
    date_naissance DATE NOT NULL,
    adresse VARCHAR(250) NOT NULL,
    id_genre INTEGER NOT NULL,
    PRIMARY KEY(id_candidat),
    FOREIGN KEY(id_genre) REFERENCES genre(id_genre)
);

CREATE TABLE employe(
    id_employe SERIAL,
    date_embauche DATE DEFAULT NOW(),
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
    date_debut_contrat DATE DEFAULT NOW(),
    salaire_base NUMERIC(18, 2) NOT NULL,
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
    date_postule DATE DEFAULT NOW(),
    PRIMARY KEY(id_candidat, id_recrutement),
    FOREIGN KEY(id_candidat) REFERENCES candidat(id_candidat),
    FOREIGN KEY(id_recrutement) REFERENCES recrutement(id_recrutement)
);

CREATE TABLE competence_candidat(
    id_candidat INTEGER,
    id_competence INTEGER,
    experience VARCHAR(50) NOT NULL,
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

CREATE TABLE competence_requise(
    id_poste INTEGER,
    id_competence INTEGER,
    experience INTEGER NOT NULL,
    PRIMARY KEY(id_poste, id_competence),
    FOREIGN KEY(id_poste) REFERENCES poste(id_poste),
    FOREIGN KEY(id_competence) REFERENCES competence(id_competence)
);

ALTER TABLE
    recrutement
ADD
    COLUMN id_status INT DEFAULT 1;

ALTER TABLE
    recrutement
ADD
    CONSTRAINT fk_status_recrutement FOREIGN KEY(id_status) REFERENCES status(id_status);

ALTER TABLE
    candidat
SET
    COLUMN email VARCHAR(150) NOT NULL;

ALTER TABLE
    candidat
SET
    COLUMN mdp VARCHAR(256) NOT NULL;

ALTER TABLE
    candidat
ADD
    CONSTRAINT uq_candidat UNIQUE(id_candidat, email);

CREATE TABLE role_talenta(
    id_role SERIAL,
    nom_role VARCHAR(150) NOT NULL,
    PRIMARY KEY(id_role)
);

CREATE TABLE responsable(
    id_responsable SERIAL,
    nom VARCHAR(150) NOT NULL,
    email VARCHAR(256) NOT NULL,
    mdp VARCHAR(256) NOT NULL,
    id_role INT NOT NULL,
    PRIMARY KEY(id_responsable),
    UNIQUE(id_responsable, email),
    FOREIGN KEY(id_role) REFERENCES role_talenta(id_role)
);

DROP TABLE competence_candidat CASCADE;

CREATE TABLE categorie_competence (
    id_categorie_competence SERIAL PRIMARY KEY,
    label VARCHAR(150) NOT NULL
);

CREATE TABLE type_diplome(
    id_type_diplome SERIAL PRIMARY KEY,
    label VARCHAR(100) NOT NULL
);

CREATE TABLE competence_candidat (
    id_candidat INT NOT NULL,
    id_competence INT NOT NULL,
    FOREIGN KEY(id_candidat) REFERENCES candidat(id_candidat),
    FOREIGN KEY(id_competence) REFERENCES competence(id_competence)
);

CREATE TABLE education_candidat (
    id_education_candidat SERIAL PRIMARY KEY,
    id_candidat INT NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    id_type_diplome INT NOT NULL,
    nom_ecole VARCHAR(256) NOT NULL,
    UNIQUE(id_type_diplome, nom_ecole),
    FOREIGN KEY (id_candidat) REFERENCES candidat(id_candidat),
    FOREIGN KEY (id_type_diplome) REFERENCES type_diplome(id_type_diplome)
);

CREATE TABLE experience_candidat (
    id_experience_candidat SERIAL PRIMARY KEY,
    id_candidat INT NOT NULL,
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL,
    description_experience TEXT NOT NULL,
    FOREIGN KEY (id_candidat) REFERENCES candidat(id_candidat)
);

ALTER TABLE
    competence
ADD
    COLUMN id_categorie_competence INT;

ALTER TABLE
    competence
ADD
    CONSTRAINT fk_categorie_competence FOREIGN KEY(id_categorie_competence) REFERENCES categorie_competence(id_categorie_competence);

CREATE TABLE notification_candidat(
    id_notification SERIAL PRIMARY KEY,
    id_candidat INT NOT NULL,
    contenu_notification VARCHAR(256) NOT NULL,
    date_notification TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    date_vue_notification TIMESTAMP,
    FOREIGN KEY(id_candidat) REFERENCES candidat(id_candidat)
);

ALTER TABLE
    notification_candidat
ADD
    COLUMN target_link VARCHAR(256);

ALTER TABLE
    poste
ADD
    COLUMN annees_experience_requises INT DEFAULT 0;

CREATE TABLE competence_requise_poste (
    id_poste INT NOT NULL,
    id_competence INT NOT NULL,
    obligatoire BOOLEAN DEFAULT true,
    FOREIGN KEY (id_poste) REFERENCES poste(id_poste),
    FOREIGN KEY (id_competence) REFERENCES competence(id_competence),
    PRIMARY KEY (id_poste, id_competence)
);

CREATE TABLE diplome_requis_poste (
    id_poste INT NOT NULL,
    id_type_diplome INT NOT NULL,
    obligatoire BOOLEAN DEFAULT true,
    FOREIGN KEY (id_poste) REFERENCES poste(id_poste),
    FOREIGN KEY (id_type_diplome) REFERENCES type_diplome(id_type_diplome),
    PRIMARY KEY (id_poste, id_type_diplome)
);

CREATE TABLE simulation (
    id_simulation SERIAL PRIMARY KEY,
    titre VARCHAR(255) NOT NULL,
    description TEXT NOT NULL,
    date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    id_responsable INT NOT NULL,
    FOREIGN KEY(id_responsable) REFERENCES responsable(id_responsable)
);

CREATE TABLE question_simulation (
    id_question_simulation SERIAL PRIMARY KEY,
    id_simulation INT REFERENCES simulation(id_simulation) ON DELETE CASCADE,
    texte_question TEXT NOT NULL
);

CREATE TABLE reponse_simulation_possibles (
    id_reponse_simulation_possibles SERIAL PRIMARY KEY,
    id_question_simulation INT REFERENCES question_simulation(id_question_simulation) ON DELETE CASCADE,
    texte_reponse TEXT NOT NULL
);

CREATE TABLE simulation_candidat (
    id_attribution SERIAL PRIMARY KEY,
    id_simulation INT REFERENCES simulation(id_simulation) ON DELETE CASCADE,
    id_candidat INT NOT NULL,
    id_status INT NOT NULL,
    FOREIGN KEY(id_candidat) REFERENCES candidat(id_candidat),
    FOREIGN KEY(id_status) REFERENCES status(id_status)
);

CREATE TABLE reponse_attendue(
    id_simulation INT NOT NULL,
    id_question_simulation INT NOT NULL,
    id_reponse_attendue INT NOT NULL,
    FOREIGN KEY(id_simulation) REFERENCES simulation(id_simulation),
    FOREIGN KEY(id_question_simulation) REFERENCES question_simulation(id_question_simulation),
    FOREIGN KEY(id_reponse_attendue) REFERENCES reponse_simulation_possibles(id_reponse_simulation_possibles)
);

CREATE TABLE reponse_simulation_candidat (
    id_reponse SERIAL PRIMARY KEY,
    id_attribution INT REFERENCES simulation_candidat(id_attribution) ON DELETE CASCADE,
    id_question INT REFERENCES question_simulation(id_question_simulation),
    id_reponse_candidat INT NOT NULL,
    date_soumission TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY(id_attribution) REFERENCES simulation_candidat(id_attribution),
    FOREIGN KEY(id_reponse_candidat) REFERENCES reponse_simulation_possibles(id_reponse_simulation_possibles)
);

ALTER TABLE
    status
ADD
    COLUMN corresponding_color VARCHAR(250);

ALTER TABLE
    recrutement_candidat
ADD
    COLUMN is_prechosen BOOLEAN DEFAULT FALSE;

DROP TABLE competence_recrutement;