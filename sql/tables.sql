CREATE TABLE
	branche_education (
		id_branche_education serial NOT NULL,
		nom_branche varchar(200) NOT NULL,
		description_branche text,
		CONSTRAINT branche_education_pkey PRIMARY KEY (id_branche_education)
	);

CREATE TABLE
	categorie_competence (
		id_categorie_competence serial NOT NULL,
		label varchar(150) NOT NULL,
		CONSTRAINT categorie_competence_pkey PRIMARY KEY (id_categorie_competence)
	);

CREATE TABLE
	competence (
		id_competence serial NOT NULL,
		label varchar(250) NOT NULL,
		id_categorie_competence integer NOT NULL,
		CONSTRAINT competence_pkey PRIMARY KEY (id_competence),
		CONSTRAINT fk_categorie_competence FOREIGN KEY (id_categorie_competence) REFERENCES categorie_competence (id_categorie_competence)
	);

CREATE TABLE
	departement (
		id_departement serial NOT NULL,
		nom varchar(150) NOT NULL,
		CONSTRAINT departement_pkey PRIMARY KEY (id_departement)
	);

CREATE TABLE
	genre (
		id_genre serial NOT NULL,
		label varchar(150) NOT NULL,
		CONSTRAINT genre_pkey PRIMARY KEY (id_genre)
	);

CREATE TABLE
	poste (
		id_poste serial NOT NULL,
		nom varchar(150) NOT NULL,
		id_departement integer NOT NULL,
		annees_experience_requises integer DEFAULT 0,
		CONSTRAINT poste_pkey PRIMARY KEY (id_poste),
		CONSTRAINT poste_id_departement_fkey FOREIGN KEY (id_departement) REFERENCES departement (id_departement)
	);

CREATE TABLE
	role_talenta (
		id_role serial NOT NULL,
		nom_role varchar(150) NOT NULL,
		CONSTRAINT role_talenta_pkey PRIMARY KEY (id_role)
	);

CREATE TABLE
	specialite (
		id_specialite serial NOT NULL,
		nom_specialite varchar(200) NOT NULL,
		description_specialite text,
		id_branche_education integer NOT NULL,
		CONSTRAINT specialite_pkey PRIMARY KEY (id_specialite),
		CONSTRAINT specialite_id_branche_education_fkey FOREIGN KEY (id_branche_education) REFERENCES branche_education (id_branche_education)
	);

CREATE TABLE
	status (
		id_status serial NOT NULL,
		label varchar(150) NOT NULL,
		corresponding_color varchar(250) NOT NULL,
		CONSTRAINT status_pkey PRIMARY KEY (id_status)
	);

CREATE TABLE
	type_contrat (
		id_type_contrat serial NOT NULL,
		label varchar(150) NOT NULL,
		CONSTRAINT type_contrat_pkey PRIMARY KEY (id_type_contrat)
	);

CREATE TABLE
	type_diplome (
		id_type_diplome serial NOT NULL,
		label varchar(100) NOT NULL,
		CONSTRAINT type_diplome_pkey PRIMARY KEY (id_type_diplome)
	);

CREATE TABLE
	type_questionaire (
		id_type_questionaire serial NOT NULL,
		label varchar(150) NOT NULL,
		CONSTRAINT type_questionaire_pkey PRIMARY KEY (id_type_questionaire)
	);

CREATE TABLE
	candidat (
		id_candidat serial NOT NULL,
		nom varchar(150) NOT NULL,
		prenom varchar(250) NOT NULL,
		date_naissance date NOT NULL,
		adresse varchar(250) NOT NULL,
		id_genre integer NOT NULL,
		email varchar NOT NULL UNIQUE,
		mdp varchar NOT NULL,
		CONSTRAINT candidat_pkey PRIMARY KEY (id_candidat),
		CONSTRAINT uq_candidat UNIQUE (id_candidat, email),
		CONSTRAINT candidat_id_genre_fkey FOREIGN KEY (id_genre) REFERENCES genre (id_genre)
	);

CREATE TABLE
	competence_candidat (
		id_candidat integer NOT NULL,
		id_competence integer NOT NULL,
		CONSTRAINT competence_candidat_id_competence_fkey FOREIGN KEY (id_competence) REFERENCES competence (id_competence),
		CONSTRAINT competence_candidat_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat)
	);

CREATE TABLE
	competence_requise_poste (
		id_poste integer NOT NULL,
		id_competence integer NOT NULL,
		obligatoire boolean DEFAULT true,
		CONSTRAINT competence_requise_poste_pkey PRIMARY KEY (id_poste, id_competence),
		CONSTRAINT competence_requise_poste_id_competence_fkey FOREIGN KEY (id_competence) REFERENCES competence (id_competence),
		CONSTRAINT competence_requise_poste_id_poste_fkey FOREIGN KEY (id_poste) REFERENCES poste (id_poste)
	);

CREATE TABLE
	contrat (
		id_contrat serial NOT NULL,
		date_debut_contrat date DEFAULT now (),
		salaire_base numeric(18, 2) NOT NULL,
		date_fin_contrat date,
		id_candidat integer NOT NULL,
		id_type_contrat integer NOT NULL,
		CONSTRAINT contrat_pkey PRIMARY KEY (id_contrat),
		CONSTRAINT contrat_id_type_contrat_fkey FOREIGN KEY (id_type_contrat) REFERENCES type_contrat (id_type_contrat),
		CONSTRAINT contrat_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat)
	);

CREATE TABLE
	diplome_requis_poste (
		id_poste integer NOT NULL,
		id_type_diplome integer NOT NULL,
		obligatoire boolean DEFAULT true,
		id_branche_education integer NOT NULL,
		CONSTRAINT diplome_requis_poste_pkey PRIMARY KEY (id_poste, id_type_diplome),
		CONSTRAINT fkey_id_branche_education_diplome_poste FOREIGN KEY (id_branche_education) REFERENCES branche_education (id_branche_education),
		CONSTRAINT diplome_requis_poste_id_type_diplome_fkey FOREIGN KEY (id_type_diplome) REFERENCES type_diplome (id_type_diplome),
		CONSTRAINT diplome_requis_poste_id_poste_fkey FOREIGN KEY (id_poste) REFERENCES poste (id_poste)
	);

CREATE TABLE
	education_candidat (
		id_education_candidat serial NOT NULL,
		id_candidat integer NOT NULL,
		date_debut date NOT NULL,
		date_fin date NOT NULL,
		id_type_diplome integer NOT NULL,
		nom_ecole varchar(256) NOT NULL,
		id_branche_education integer NOT NULL,
		CONSTRAINT education_candidat_pkey PRIMARY KEY (id_education_candidat),
		CONSTRAINT education_candidat_id_type_diplome_nom_ecole_key UNIQUE (id_type_diplome, nom_ecole),
		CONSTRAINT fkey_id_branche_education FOREIGN KEY (id_branche_education) REFERENCES branche_education (id_branche_education),
		CONSTRAINT education_candidat_id_type_diplome_fkey FOREIGN KEY (id_type_diplome) REFERENCES type_diplome (id_type_diplome),
		CONSTRAINT education_candidat_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat)
	);

CREATE TABLE
	employe(
		id_employe serial NOT NULL,
		date_embauche date DEFAULT NOW (),
		salaire_base DECIMAL(18,2) NOT NULL,
		id_candidat integer NOT NULL,
		id_poste integer NOT NULL,
		CONSTRAINT employe_pkey PRIMARY KEY (id_employe),
		CONSTRAINT employe_id_poste_fkey FOREIGN KEY (id_poste) REFERENCES poste (id_poste),
		CONSTRAINT employe_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat)
	);

CREATE TABLE
	experience_candidat (
		id_experience_candidat serial NOT NULL,
		id_candidat integer NOT NULL,
		date_debut date NOT NULL,
		date_fin date NOT NULL,
		id_specialite integer NOT NULL,
		entreprise varchar(200) NOT NULL,
		CONSTRAINT experience_candidat_pkey PRIMARY KEY (id_experience_candidat),
		CONSTRAINT fkey_id_specialite FOREIGN KEY (id_specialite) REFERENCES specialite (id_specialite),
		CONSTRAINT experience_candidat_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat)
	);

CREATE TABLE
	experience_requise_poste (
		id_poste integer NOT NULL,
		id_specialite integer NOT NULL,
		duree integer DEFAULT 0,
		obligatoire boolean DEFAULT true,
		CONSTRAINT experience_requise_poste_id_specialite_fkey FOREIGN KEY (id_specialite) REFERENCES specialite (id_specialite),
		CONSTRAINT experience_requise_poste_id_poste_fkey FOREIGN KEY (id_poste) REFERENCES poste (id_poste)
	);

ALTER TABLE experience_requise_poste ADD CONSTRAINT experience_requise_poste_duree_chk CHECK ((duree >= 0));

CREATE TABLE
	notification_candidat (
		id_notification serial NOT NULL,
		id_candidat integer NOT NULL,
		contenu_notification varchar(256) NOT NULL,
		date_notification timestamp DEFAULT CURRENT_TIMESTAMP,
		date_vue_notification timestamp,
		target_link varchar(256) NOT NULL,
		CONSTRAINT notification_candidat_pkey PRIMARY KEY (id_notification),
		CONSTRAINT notification_candidat_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat)
	);

CREATE TABLE
	questionaire (
		id_questionaire serial NOT NULL,
		question text NOT NULL,
		id_type_questionaire integer NOT NULL,
		CONSTRAINT questionaire_pkey PRIMARY KEY (id_questionaire),
		CONSTRAINT questionaire_id_type_questionaire_fkey FOREIGN KEY (id_type_questionaire) REFERENCES type_questionaire (id_type_questionaire)
	);

CREATE TABLE
	recrutement (
		id_recrutement serial NOT NULL,
		date_debut_recrutement date NOT NULL,
		date_fin_recrutement date NOT NULL,
		nombre integer DEFAULT 0,
		id_poste integer NOT NULL,
		id_status integer DEFAULT 1,
		description_recrutement text,
		CONSTRAINT recrutement_pkey PRIMARY KEY (id_recrutement),
		CONSTRAINT fk_status_recrutement FOREIGN KEY (id_status) REFERENCES status (id_status),
		CONSTRAINT recrutement_id_poste_fkey FOREIGN KEY (id_poste) REFERENCES poste (id_poste)
	);

CREATE TABLE
	recrutement_candidat (
		id_candidat integer NOT NULL,
		id_recrutement integer NOT NULL,
		date_postule date DEFAULT now (),
		is_prechosen boolean DEFAULT false,
		CONSTRAINT recrutement_candidat_pkey PRIMARY KEY (id_candidat, id_recrutement),
		CONSTRAINT recrutement_candidat_id_recrutement_fkey FOREIGN KEY (id_recrutement) REFERENCES recrutement (id_recrutement),
		CONSTRAINT recrutement_candidat_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat)
	);

CREATE TABLE
	reponse_entretient (
		id_candidat integer NOT NULL,
		id_questionaire integer NOT NULL,
		reponse smallint NOT NULL,
		CONSTRAINT reponse_entretient_pkey PRIMARY KEY (id_candidat, id_questionaire),
		CONSTRAINT reponse_entretient_id_questionaire_fkey FOREIGN KEY (id_questionaire) REFERENCES questionaire (id_questionaire),
		CONSTRAINT reponse_entretient_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat)
	);

CREATE TABLE
	reponse_test (
		id_candidat integer NOT NULL,
		id_questionaire integer NOT NULL,
		reponse smallint NOT NULL,
		CONSTRAINT reponse_test_pkey PRIMARY KEY (id_candidat, id_questionaire),
		CONSTRAINT reponse_test_id_questionaire_fkey FOREIGN KEY (id_questionaire) REFERENCES questionaire (id_questionaire),
		CONSTRAINT reponse_test_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat)
	);

CREATE TABLE
	responsable (
		id_responsable serial NOT NULL,
		nom varchar(150) NOT NULL,
		email varchar(256) NOT NULL,
		mdp varchar(256) NOT NULL,
		id_role integer NOT NULL,
		CONSTRAINT responsable_pkey PRIMARY KEY (id_responsable),
		CONSTRAINT responsable_id_responsable_email_key UNIQUE (id_responsable, email),
		CONSTRAINT responsable_id_role_fkey FOREIGN KEY (id_role) REFERENCES role_talenta (id_role)
	);

CREATE TABLE
	test (
		id_test serial NOT NULL,
		titre varchar(255) NOT NULL,
		description text NOT NULL,
		date_creation timestamp DEFAULT CURRENT_TIMESTAMP,
		id_responsable integer NOT NULL,
		CONSTRAINT test_pkey PRIMARY KEY (id_test),
		CONSTRAINT test_id_responsable_fkey FOREIGN KEY (id_responsable) REFERENCES responsable (id_responsable)
	);

CREATE TABLE
	test_candidat (
		id_attribution serial NOT NULL,
		id_test integer NOT NULL,
		id_recrutement integer NOT NULL,
		id_candidat integer NOT NULL,
		id_status integer NOT NULL,
		CONSTRAINT test_candidat_pkey PRIMARY KEY (id_attribution),
		CONSTRAINT test_candidat_id_status_fkey FOREIGN KEY (id_status) REFERENCES status (id_status),
		CONSTRAINT test_candidat_id_recrutement_fkey FOREIGN KEY (id_recrutement) REFERENCES recrutement (id_recrutement),
		CONSTRAINT test_candidat_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat),
		CONSTRAINT test_candidat_id_test_fkey FOREIGN KEY (id_test) REFERENCES test (id_test) ON DELETE CASCADE
	);

CREATE TABLE
	question_test (
		id_question_test serial NOT NULL,
		id_test integer,
		texte_question text NOT NULL,
		CONSTRAINT question_test_pkey PRIMARY KEY (id_question_test),
		CONSTRAINT question_test_id_test_fkey FOREIGN KEY (id_test) REFERENCES test (id_test) ON DELETE CASCADE
	);

CREATE TABLE
	reponse_test_possibles (
		id_reponse_test_possibles serial NOT NULL,
		id_question_test integer NOT NULL,
		texte_reponse text NOT NULL,
		est_reponse_attendue boolean DEFAULT false,
		CONSTRAINT reponse_test_possibles_pkey PRIMARY KEY (id_reponse_test_possibles),
		CONSTRAINT reponse_test_possibles_id_question_test_fkey FOREIGN KEY (id_question_test) REFERENCES question_test (id_question_test) ON DELETE CASCADE
	);

CREATE TABLE reponse_test_candidat (
    id_reponse serial NOT NULL,
    id_attribution integer,
    id_question integer,
    id_reponse_candidat integer NOT NULL,
    date_soumission timestamp DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT reponse_test_candidat_pkey PRIMARY KEY (id_reponse),
    CONSTRAINT reponse_test_candidat_id_reponse_candidat_fkey FOREIGN KEY (id_reponse_candidat) REFERENCES reponse_test_possibles(id_reponse_test_possibles),
    CONSTRAINT reponse_test_candidat_id_attribution_fkey1 FOREIGN KEY (id_attribution) REFERENCES test_candidat(id_attribution),
    CONSTRAINT reponse_test_candidat_id_question_fkey FOREIGN KEY (id_question) REFERENCES question_test(id_question_test),
    CONSTRAINT reponse_test_candidat_id_attribution_fkey FOREIGN KEY (id_attribution) REFERENCES test_candidat(id_attribution) ON DELETE CASCADE
);

CREATE TABLE type_fin_contrat(
    id_type_fin_contrat SERIAL PRIMARY KEY,
    label VARCHAR(20)
);

CREATE TABLE fin_contrat(
    id_fin_contrat SERIAL PRIMARY KEY,
    id_employe INT REFERENCES employe(id_employe),
    id_type_fin_contrat INT REFERENCES type_fin_contrat(id_type_fin_contrat),
    motif VARCHAR(100),
    date_depot DATE
);

CREATE TABLE notification_admin (
    id_notification serial NOT NULL,
    contenu_notification varchar(256) NOT NULL,
    date_notification timestamp DEFAULT CURRENT_TIMESTAMP,
    date_vue_notification timestamp,
    target_link varchar(256),
    CONSTRAINT notification_admin_pkey PRIMARY KEY (id_notification)
);

CREATE TABLE demande_demission(
    id_demande SERIAL PRIMARY KEY,
    id_candidat INT REFERENCES candidat(id_candidat),
    date_depot DATE,
    motif VARCHAR(100),
    etat VARCHAR(10));
    
CREATE TABLE type_conge(
   id_type_conge INT,
   nom_type VARCHAR(50) NOT NULL,
   est_conge_paye BOOLEAN DEFAULT true,
   PRIMARY KEY(id_type_conge)
);

CREATE TABLE conge(
id_conge INT,
id_employe INT REFERENCES employe(id_employe),
id_type_conge INT REFERENCES type_conge(id_type_conge),
date_debut DATE NOT NULL,
date_fin DATE,
id_contrat INT REFERENCES contrat(id_contrat),
PRIMARY KEY(id_conge)
);

CREATE TABLE presence_employe(
	id_presence SERIAL PRIMARY KEY, 
	id_employe INTEGER REFERENCES employe(id_employe),
	date_entree TIMESTAMP NOT NULL, 
	date_sortie TIMESTAMP NOT NULL
);

-- prime et indemnite : 
CREATE TABLE type_prime (
    id_type_prime SERIAL PRIMARY KEY,
    nom_type_prime VARCHAR(50) NOT NULL
);

CREATE TABLE prime_employe (
    id_prime SERIAL PRIMARY KEY,
    id_employe INT REFERENCES employe(id_employe),
    id_type_prime INT REFERENCES type_prime(id_type_prime),
    montant_prime NUMERIC(10, 2) NOT NULL
);

CREATE TABLE type_indemnite (
    id_type_indemnite SERIAL PRIMARY KEY,
    nom_type_indemnite VARCHAR(50) NOT NULL
);

CREATE TABLE indemnite_employe (
    id_indemnite SERIAL PRIMARY KEY,
    id_employe INT REFERENCES employe(id_employe),
    id_type_indemnite INT REFERENCES type_indemnite(id_type_indemnite),
    montant_indemnite NUMERIC(10, 2) NOT NULL
);


-- fisc
CREATE TABLE deduction_fiscale (
    id_deduction SERIAL PRIMARY KEY,
    nom_deduction VARCHAR(50) NOT NULL, -- OSTIE, CNAPS, IRSA
    taux NUMERIC(5, 2) NOT NULL         -- Pourcentage de la déduction
);

CREATE TABLE deduction_employe (
    id_deduction_employe SERIAL PRIMARY KEY,
    id_employe INT REFERENCES employe(id_employe),
    id_deduction INT REFERENCES deduction_fiscale(id_deduction),
    base_salaire NUMERIC(10, 2) NOT NULL, -- Salaire brut utilisé comme base
    montant NUMERIC(10, 2) NOT NULL      -- Montant de la déduction
);

--absence :
CREATE TABLE type_absence (
    id_type_absence SERIAL PRIMARY KEY,
    nom_type VARCHAR(50) NOT NULL,
    est_avec_solde BOOLEAN DEFAULT false -- Indique si l'absence est avec solde ou sans solde
);

CREATE TABLE absence (
    id_absence SERIAL PRIMARY KEY,
    id_employe INT REFERENCES employe(id_employe),
    id_type_absence INT REFERENCES type_absence(id_type_absence),
    date_debut DATE NOT NULL,
    date_fin DATE NOT NULL -- Si l'absence dure un seul jour, `date_debut` = `date_fin`
);

-- heure supp
CREATE TABLE regle_majoration(
    id_regle SERIAL PRIMARY KEY,
    pourcentage_majoration INTEGER NOT NULL, -- (30%, 40%, etc.)
    debut_heure_supp INTEGER NOT NULL, -- Nombre d'heures après lesquelles la majoration commence
    description TEXT
);

CREATE TABLE jours_feries (
    date DATE PRIMARY KEY,       -- Date du jour férié
    description VARCHAR(255)     -- Description du jour férié
);


 
--paie
CREATE TABLE avantage_nature (
    id_avantage SERIAL PRIMARY KEY,
    id_employe INT REFERENCES employe(id_employe),
    type_avantage VARCHAR(50) NOT NULL,
    valeur_avantage DECIMAL(10,2) NOT NULL,
    date_application DATE NOT NULL
);

CREATE TABLE entite (
    id_entite SERIAL PRIMARY KEY,
    nom_entite VARCHAR(100) NOT NULL,
    adresse VARCHAR(200) NOT NULL,
    code_postal VARCHAR(10) NOT NULL,
    ville VARCHAR(100) NOT NULL,
    pays VARCHAR(100) NOT NULL
);

CREATE TABLE taux_cotisation (
    id_taux_cotisation SERIAL PRIMARY KEY,
    type_cotisation VARCHAR(50) NOT NULL,
    taux_employeur DECIMAL(5,2) NOT NULL,
    taux_employe DECIMAL(5,2) NOT NULL,
    date_application DATE NOT NULL
);
