CREATE TABLE
	branche_education (
		id_branche_education SERIAL NOT NULL,
		nom_branche VARCHAR(200) NOT NULL,
		description_branche TEXT,
		CONSTRAINT branche_education_pkey PRIMARY KEY (id_branche_education)
	);

CREATE TABLE
	categorie_competence (
		id_categorie_competence SERIAL NOT NULL,
		label VARCHAR(150) NOT NULL,
		CONSTRAINT categorie_competence_pkey PRIMARY KEY (id_categorie_competence)
	);

CREATE TABLE
	categorie_experience (
		id_categorie_experience SERIAL NOT NULL,
		libelle_categorie VARCHAR(100) NOT NULL,
		duree_min_annees INTEGER,
		duree_max_annees INTEGER,
		coefficient NUMERIC(5, 2) DEFAULT 1.0,
		CONSTRAINT categorie_experience_pkey PRIMARY KEY (id_categorie_experience)
	);

CREATE TABLE
	categorie_professionnelle (
		id_categorie_professionnelle SERIAL NOT NULL,
		nom_categorie_professionnelle VARCHAR(250) NOT NULL,
		CONSTRAINT categorie_professionnelle_pkey PRIMARY KEY (id_categorie_professionnelle)
	);

CREATE TABLE
	competence (
		id_competence SERIAL NOT NULL,
		label VARCHAR(250) NOT NULL,
		id_categorie_competence INTEGER NOT NULL,
		CONSTRAINT competence_pkey PRIMARY KEY (id_competence),
		CONSTRAINT fk_categorie_competence FOREIGN KEY (id_categorie_competence) REFERENCES categorie_competence (id_categorie_competence)
	);

CREATE TABLE
	csp (
		id_csp SERIAL NOT NULL,
		code_csp VARCHAR(20) NOT NULL,
		description TEXT NULL,
		CONSTRAINT csp_pkey PRIMARY KEY (id_csp),
		CONSTRAINT csp_code_csp_key UNIQUE (code_csp)
	);

CREATE TABLE
	departement (
		id_departement SERIAL NOT NULL,
		nom VARCHAR(150) NOT NULL,
		CONSTRAINT departement_pkey PRIMARY KEY (id_departement)
	);

CREATE TABLE
	genre (
		id_genre SERIAL NOT NULL,
		label VARCHAR(150) NOT NULL,
		CONSTRAINT genre_pkey PRIMARY KEY (id_genre)
	);

CREATE TABLE
	hierarchie (
		id_hierarchie SERIAL NOT NULL,
		nom_hierarchie VARCHAR(100) NOT NULL,
		CONSTRAINT hierarchie_pkey PRIMARY KEY (id_hierarchie),
		CONSTRAINT hierarchie_nom_hierarchie_key UNIQUE (nom_hierarchie)
	);

CREATE TABLE
	jours_feries (
		"date" DATE NOT NULL,
		description VARCHAR(255),
		CONSTRAINT jours_feries_pkey PRIMARY KEY ("date")
	);

CREATE TABLE
	niveau_competence (
		id_niveau_competence SERIAL NOT NULL,
		libelle_niveau VARCHAR(50) NOT NULL,
		niveau_competence VARCHAR(20) NOT NULL,
		coefficient NUMERIC(5, 2) DEFAULT 1.0,
		CONSTRAINT niveau_competence_pkey PRIMARY KEY (id_niveau_competence)
	);

CREATE TABLE
	niveau_diplome (
		id_niveau_diplome SERIAL NOT NULL,
		libelle_niveau VARCHAR(100) NOT NULL,
		niveau_diplome VARCHAR(50) NOT NULL,
		coefficient NUMERIC(5, 2) DEFAULT 1.0,
		CONSTRAINT niveau_diplome_pkey PRIMARY KEY (id_niveau_diplome)
	);

CREATE TABLE
	notification_admin (
		id_notification SERIAL NOT NULL,
		contenu_notification VARCHAR(256) NOT NULL,
		date_notification TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
		date_vue_notification TIMESTAMP,
		target_link VARCHAR(256),
		CONSTRAINT notification_admin_pkey PRIMARY KEY (id_notification)
	);

CREATE TABLE
	poste (
		id_poste SERIAL NOT NULL,
		nom VARCHAR(150) NOT NULL,
		id_departement INTEGER NOT NULL,
		annees_experience_requises INTEGER DEFAULT 0,
		id_hierarchie INTEGER NOT NULL,
		CONSTRAINT poste_pkey PRIMARY KEY (id_poste),
		CONSTRAINT poste_id_departement_fkey FOREIGN KEY (id_departement) REFERENCES departement (id_departement),
		CONSTRAINT poste_id_hierarchie_pkey FOREIGN KEY (id_hierarchie) REFERENCES hierarchie (id_hierarchie)
	);

CREATE TABLE
	role_talenta (
		id_role SERIAL NOT NULL,
		nom_role VARCHAR(150) NOT NULL,
		CONSTRAINT role_talenta_pkey PRIMARY KEY (id_role)
	);

CREATE TABLE
	specialite (
		id_specialite SERIAL NOT NULL,
		nom_specialite VARCHAR(200) NOT NULL,
		description_specialite TEXT,
		id_branche_education INTEGER NOT NULL,
		CONSTRAINT specialite_pkey PRIMARY KEY (id_specialite),
		CONSTRAINT specialite_id_branche_education_fkey FOREIGN KEY (id_branche_education) REFERENCES branche_education (id_branche_education)
	);

CREATE TABLE
	status (
		id_status SERIAL NOT NULL,
		label VARCHAR(150) NOT NULL,
		corresponding_color VARCHAR(250) NOT NULL,
		CONSTRAINT status_pkey PRIMARY KEY (id_status)
	);

CREATE TABLE
	type_absence (
		id_type_absence SERIAL NOT NULL,
		nom_type VARCHAR(50) NOT NULL,
		est_avec_solde boolean DEFAULT false,
		CONSTRAINT type_absence_pkey PRIMARY KEY (id_type_absence)
	);

CREATE TABLE
	type_conge (
		id_type_conge INTEGER NOT NULL,
		nom_type VARCHAR(50) NOT NULL,
		est_conge_paye boolean DEFAULT true,
		CONSTRAINT type_conge_pkey PRIMARY KEY (id_type_conge)
	);

CREATE TABLE
	type_contrat (
		id_type_contrat SERIAL NOT NULL,
		label VARCHAR(150) NOT NULL,
		CONSTRAINT type_contrat_pkey PRIMARY KEY (id_type_contrat)
	);

CREATE TABLE
	type_diplome (
		id_type_diplome SERIAL NOT NULL,
		label VARCHAR(100) NOT NULL,
		CONSTRAINT type_diplome_pkey PRIMARY KEY (id_type_diplome)
	);

CREATE TABLE
	type_fin_contrat (
		id_type_fin_contrat SERIAL NOT NULL,
		label VARCHAR(20)  NOT NULL,
		CONSTRAINT type_fin_contrat_pkey PRIMARY KEY (id_type_fin_contrat)
	);

CREATE TABLE
	type_indemnite (
		id_type_indemnite SERIAL NOT NULL,
		nom_type_indemnite VARCHAR(50) NOT NULL,
		CONSTRAINT type_indemnite_pkey PRIMARY KEY (id_type_indemnite)
	);

CREATE TABLE
	type_prime (
		id_type_prime SERIAL NOT NULL,
		nom_type_prime VARCHAR(50) NOT NULL,
		CONSTRAINT type_prime_pkey PRIMARY KEY (id_type_prime)
	);

CREATE TABLE
	candidat (
		id_candidat SERIAL NOT NULL,
		nom VARCHAR(150) NOT NULL,
		prenom VARCHAR(250) NOT NULL,
		date_naissance DATE NOT NULL,
		adresse VARCHAR(250) NOT NULL,
		id_genre INTEGER NOT NULL,
		email VARCHAR NOT NULL,
		mdp VARCHAR NOT NULL,
		CONSTRAINT candidat_pkey PRIMARY KEY (id_candidat),
		CONSTRAINT candidat_email_key UNIQUE (email),
		CONSTRAINT uq_candidat UNIQUE (id_candidat, email),
		CONSTRAINT candidat_id_genre_fkey FOREIGN KEY (id_genre) REFERENCES genre (id_genre)
	);

CREATE TABLE
	categorie_professionnelle_poste (
		id_poste INTEGER NOT NULL,
		id_categorie_professionnelle INTEGER NOT NULL,
		CONSTRAINT categorie_professionnelle_poste_id_poste_fkey FOREIGN KEY (id_poste) REFERENCES poste (id_poste),
		CONSTRAINT categorie_professionnelle_pos_id_categorie_professionnelle_fkey FOREIGN KEY (id_categorie_professionnelle) REFERENCES categorie_professionnelle (id_categorie_professionnelle)
	);

CREATE TABLE
	competence_candidat (
		id_candidat INTEGER NOT NULL,
		id_competence INTEGER NOT NULL,
		id_niveau_competence INTEGER NOT NULL,
		CONSTRAINT competence_candidat_id_competence_fkey FOREIGN KEY (id_competence) REFERENCES competence (id_competence),
		CONSTRAINT competence_candidat_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat),
		CONSTRAINT fk_niveau_competence FOREIGN KEY (id_niveau_competence) REFERENCES niveau_competence (id_niveau_competence)
	);

CREATE TABLE
	competence_requise_poste (
		id_poste INTEGER NOT NULL,
		id_competence INTEGER NOT NULL,
		obligatoire boolean DEFAULT true,
		CONSTRAINT competence_requise_poste_pkey PRIMARY KEY (id_poste, id_competence),
		CONSTRAINT competence_requise_poste_id_competence_fkey FOREIGN KEY (id_competence) REFERENCES competence (id_competence),
		CONSTRAINT competence_requise_poste_id_poste_fkey FOREIGN KEY (id_poste) REFERENCES poste (id_poste)
	);

CREATE TABLE
	contrat (
		id_contrat SERIAL NOT NULL,
		date_debut_contrat DATE DEFAULT now (),
		salaire_base NUMERIC(18, 2) NOT NULL,
		date_fin_contrat DATE,
		id_candidat INTEGER NOT NULL,
		id_type_contrat INTEGER NOT NULL,
		CONSTRAINT contrat_pkey PRIMARY KEY (id_contrat),
		CONSTRAINT contrat_id_type_contrat_fkey FOREIGN KEY (id_type_contrat) REFERENCES type_contrat (id_type_contrat),
		CONSTRAINT contrat_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat)
	);

CREATE TABLE
	demande_demission (
		id_demande SERIAL NOT NULL,
		id_candidat INTEGER,
		date_depot DATE,
		motif VARCHAR(100),
		etat VARCHAR(10),
		CONSTRAINT demande_demission_pkey PRIMARY KEY (id_demande),
		CONSTRAINT demande_demission_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat)
	);

CREATE TABLE
	diplome_requis_poste (
		id_poste INTEGER NOT NULL,
		id_type_diplome INTEGER NOT NULL,
		obligatoire boolean DEFAULT true,
		id_branche_education INTEGER NOT NULL,
		CONSTRAINT diplome_requis_poste_pkey PRIMARY KEY (id_poste, id_type_diplome),
		CONSTRAINT fkey_id_branche_education_diplome_poste FOREIGN KEY (id_branche_education) REFERENCES branche_education (id_branche_education),
		CONSTRAINT diplome_requis_poste_id_type_diplome_fkey FOREIGN KEY (id_type_diplome) REFERENCES type_diplome (id_type_diplome),
		CONSTRAINT diplome_requis_poste_id_poste_fkey FOREIGN KEY (id_poste) REFERENCES poste (id_poste)
	);

CREATE TABLE
	education_candidat (
		id_education_candidat SERIAL NOT NULL,
		id_candidat INTEGER NOT NULL,
		date_debut DATE NOT NULL,
		date_fin DATE NOT NULL,
		id_type_diplome INTEGER NOT NULL,
		nom_ecole VARCHAR(256) NOT NULL,
		id_branche_education INTEGER NOT NULL,
		id_niveau_diplome INTEGER NOT NULL,
		CONSTRAINT education_candidat_pkey PRIMARY KEY (id_education_candidat),
		CONSTRAINT education_candidat_id_type_diplome_nom_ecole_key UNIQUE (id_type_diplome, nom_ecole),
		CONSTRAINT fkey_id_branche_education FOREIGN KEY (id_branche_education) REFERENCES branche_education (id_branche_education),
		CONSTRAINT education_candidat_id_type_diplome_fkey FOREIGN KEY (id_type_diplome) REFERENCES type_diplome (id_type_diplome),
		CONSTRAINT education_candidat_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat),
		CONSTRAINT fk_niveau_diplome FOREIGN KEY (id_niveau_diplome) REFERENCES niveau_diplome (id_niveau_diplome)
	);

CREATE TABLE
	employe (
		id_employe SERIAL NOT NULL,
		date_embauche DATE DEFAULT now (),
		id_candidat INTEGER NOT NULL,
		id_poste INTEGER NOT NULL,
		salaire_base NUMERIC(18, 2) DEFAULT 0,
		numero_cnaps INTEGER NOT NULL,
		CONSTRAINT uq_numero_cnaps UNIQUE(numero_cnaps),
		CONSTRAINT employe_pkey PRIMARY KEY (id_employe),
		CONSTRAINT employe_id_poste_fkey FOREIGN KEY (id_poste) REFERENCES poste (id_poste),
		CONSTRAINT employe_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat)
	);

CREATE TABLE
	experience_candidat (
		id_experience_candidat SERIAL NOT NULL,
		id_candidat INTEGER NOT NULL,
		date_debut DATE NOT NULL,
		date_fin DATE NOT NULL,
		id_specialite INTEGER NOT NULL,
		entreprise VARCHAR(200) NOT NULL,
		id_categorie_experience INTEGER NOT NULL,
		CONSTRAINT experience_candidat_pkey PRIMARY KEY (id_experience_candidat),
		CONSTRAINT fkey_id_specialite FOREIGN KEY (id_specialite) REFERENCES specialite (id_specialite),
		CONSTRAINT experience_candidat_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat),
		CONSTRAINT fk_categorie_experience FOREIGN KEY (id_categorie_experience) REFERENCES categorie_experience (id_categorie_experience)
	);

CREATE TABLE
	experience_requise_poste (
		id_poste INTEGER NOT NULL,
		id_specialite INTEGER NOT NULL,
		duree INTEGER DEFAULT 0,
		obligatoire boolean DEFAULT true,
		CONSTRAINT experience_requise_poste_id_specialite_fkey FOREIGN KEY (id_specialite) REFERENCES specialite (id_specialite),
		CONSTRAINT experience_requise_poste_id_poste_fkey FOREIGN KEY (id_poste) REFERENCES poste (id_poste)
	);

ALTER TABLE experience_requise_poste ADD CONSTRAINT experience_requise_poste_duree_chk CHECK ((duree >= 0));

CREATE TABLE
	fin_contrat (
		id_fin_contrat SERIAL NOT NULL,
		id_employe INTEGER NOT NULL,
		id_type_fin_contrat INTEGER,
		motif VARCHAR(100),
		date_depot DATE,
		CONSTRAINT fin_contrat_pkey PRIMARY KEY (id_fin_contrat),
		CONSTRAINT fin_contrat_id_employe_fkey FOREIGN KEY (id_employe) REFERENCES employe (id_employe),
		CONSTRAINT fin_contrat_id_type_fin_contrat_fkey FOREIGN KEY (id_type_fin_contrat) REFERENCES type_fin_contrat (id_type_fin_contrat)
	);

CREATE TABLE
	indemnite_employe (
		id_indemnite SERIAL NOT NULL,
		id_employe INTEGER NOT NULL,
		id_type_indemnite INTEGER NOT NULL,
		montant_indemnite NUMERIC(10, 2) NOT NULL,
		CONSTRAINT indemnite_employe_pkey PRIMARY KEY (id_indemnite),
		CONSTRAINT indemnite_employe_id_employe_fkey FOREIGN KEY (id_employe) REFERENCES employe (id_employe),
		CONSTRAINT indemnite_employe_id_type_indemnite_fkey FOREIGN KEY (id_type_indemnite) REFERENCES type_indemnite (id_type_indemnite)
	);

CREATE TABLE
	notification_candidat (
		id_notification SERIAL NOT NULL,
		id_candidat INTEGER NOT NULL,
		contenu_notification VARCHAR(256) NOT NULL,
		date_notification TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
		date_vue_notification TIMESTAMP,
		target_link VARCHAR(256) NOT NULL,
		CONSTRAINT notification_candidat_pkey PRIMARY KEY (id_notification),
		CONSTRAINT notification_candidat_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat)
	);

CREATE TABLE
	presence_employe (
		id_presence SERIAL NOT NULL,
		id_employe INTEGER NOT NULL,
		date_entree TIMESTAMP NOT NULL,
		date_sortie TIMESTAMP NOT NULL,
		CONSTRAINT chk_date_presence CHECK(date_sortie > date_entree),
		CONSTRAINT presence_employe_pkey PRIMARY KEY (id_presence),
		CONSTRAINT presence_employe_id_employe_fkey FOREIGN KEY (id_employe) REFERENCES employe (id_employe)
	);

CREATE TABLE
	prime_employe (
		id_prime SERIAL NOT NULL,
		id_employe INTEGER NOT NULL,
		id_type_prime INTEGER,
		montant_prime NUMERIC(10, 2) NOT NULL,
		CONSTRAINT prime_employe_pkey PRIMARY KEY (id_prime),
		CONSTRAINT prime_employe_id_employe_fkey FOREIGN KEY (id_employe) REFERENCES employe (id_employe),
		CONSTRAINT prime_employe_id_type_prime_fkey FOREIGN KEY (id_type_prime) REFERENCES type_prime (id_type_prime)
	);

CREATE TABLE
	recrutement (
		id_recrutement SERIAL NOT NULL,
		date_debut_recrutement DATE NOT NULL,
		date_fin_recrutement DATE NOT NULL,
		nombre INTEGER DEFAULT 0,
		id_poste INTEGER NOT NULL,
		id_status INTEGER DEFAULT 1,
		description_recrutement TEXT,
		CONSTRAINT chck_date_recrutement CHECK(date_debut_recrutement < date_fin_recrutement),
		CONSTRAINT recrutement_pkey PRIMARY KEY (id_recrutement),
		CONSTRAINT fk_status_recrutement FOREIGN KEY (id_status) REFERENCES status (id_status),
		CONSTRAINT recrutement_id_poste_fkey FOREIGN KEY (id_poste) REFERENCES poste (id_poste)
	);

CREATE TABLE
	recrutement_candidat (
		id_candidat INTEGER NOT NULL,
		id_recrutement INTEGER NOT NULL,
		date_postule DATE DEFAULT now (),
		is_prechosen boolean DEFAULT false,
		CONSTRAINT recrutement_candidat_pkey PRIMARY KEY (id_candidat, id_recrutement),
		CONSTRAINT recrutement_candidat_id_recrutement_fkey FOREIGN KEY (id_recrutement) REFERENCES recrutement (id_recrutement),
		CONSTRAINT recrutement_candidat_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat)
	);

CREATE TABLE
	responsable (
		id_responsable SERIAL NOT NULL,
		nom VARCHAR(150) NOT NULL,
		email VARCHAR(256) NOT NULL,
		mdp VARCHAR(256) NOT NULL,
		id_role INTEGER NOT NULL,
		CONSTRAINT responsable_pkey PRIMARY KEY (id_responsable),
		CONSTRAINT responsable_id_responsable_email_key UNIQUE (id_responsable, email),
		CONSTRAINT responsable_id_role_fkey FOREIGN KEY (id_role) REFERENCES role_talenta (id_role)
	);

CREATE TABLE
	test (
		id_test SERIAL NOT NULL,
		titre VARCHAR(255) NOT NULL,
		description TEXT NOT NULL,
		date_creation TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
		id_responsable INTEGER NOT NULL,
		CONSTRAINT test_pkey PRIMARY KEY (id_test),
		CONSTRAINT test_id_responsable_fkey FOREIGN KEY (id_responsable) REFERENCES responsable (id_responsable)
	);

CREATE TABLE
	test_candidat (
		id_attribution SERIAL NOT NULL,
		id_test INTEGER NOT NULL,
		id_recrutement INTEGER NOT NULL,
		id_candidat INTEGER NOT NULL,
		id_status INTEGER NOT NULL,
		CONSTRAINT test_candidat_pkey PRIMARY KEY (id_attribution),
		CONSTRAINT test_candidat_id_status_fkey FOREIGN KEY (id_status) REFERENCES status (id_status),
		CONSTRAINT test_candidat_id_recrutement_fkey FOREIGN KEY (id_recrutement) REFERENCES recrutement (id_recrutement),
		CONSTRAINT test_candidat_id_candidat_fkey FOREIGN KEY (id_candidat) REFERENCES candidat (id_candidat),
		CONSTRAINT test_candidat_id_test_fkey FOREIGN KEY (id_test) REFERENCES test (id_test) ON DELETE CASCADE
	);

CREATE TABLE
	absence (
		id_absence SERIAL NOT NULL,
		id_employe INTEGER NOT NULL,
		id_type_absence INTEGER,
		date_debut DATE NOT NULL,
		date_fin DATE NOT NULL,
		CONSTRAINT absence_pkey PRIMARY KEY (id_absence),
		CONSTRAINT absence_id_employe_fkey FOREIGN KEY (id_employe) REFERENCES employe (id_employe),
		CONSTRAINT absence_id_type_absence_fkey FOREIGN KEY (id_type_absence) REFERENCES type_absence (id_type_absence)
	);

CREATE TABLE
	conge (
		id_conge INTEGER NOT NULL,
		id_employe INTEGER NOT NULL,
		id_type_conge INTEGER,
		date_debut DATE NOT NULL,
		date_fin DATE NOT NULL,
		id_contrat INTEGER NOT NULL,
		CONSTRAINT conge_pkey PRIMARY KEY (id_conge),
		CONSTRAINT conge_id_employe_fkey FOREIGN KEY (id_employe) REFERENCES employe (id_employe),
		CONSTRAINT conge_id_type_conge_fkey FOREIGN KEY (id_type_conge) REFERENCES type_conge (id_type_conge),
		CONSTRAINT conge_id_contrat_fkey FOREIGN KEY (id_contrat) REFERENCES contrat (id_contrat)
	);

CREATE TABLE
	question_test (
		id_question_test SERIAL NOT NULL,
		id_test INTEGER NOT NULL,
		texte_question TEXT NOT NULL,
		CONSTRAINT question_test_pkey PRIMARY KEY (id_question_test),
		CONSTRAINT question_test_id_test_fkey FOREIGN KEY (id_test) REFERENCES test (id_test) ON DELETE CASCADE
	);

CREATE TABLE
	reponse_test_possibles (
		id_reponse_test_possibles SERIAL NOT NULL,
		id_question_test INTEGER NOT NULL,
		texte_reponse TEXT NOT NULL,
		est_reponse_attendue boolean DEFAULT false,
		CONSTRAINT reponse_test_possibles_pkey PRIMARY KEY (id_reponse_test_possibles),
		CONSTRAINT reponse_test_possibles_id_question_test_fkey FOREIGN KEY (id_question_test) REFERENCES question_test (id_question_test) ON DELETE CASCADE
	);

CREATE TABLE
	reponse_test_candidat (
		id_reponse SERIAL NOT NULL,
		id_attribution INTEGER NOT NULL,
		id_question INTEGER NOT NULL,
		id_reponse_candidat INTEGER NOT NULL,
		date_soumission TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
		CONSTRAINT reponse_test_candidat_pkey PRIMARY KEY (id_reponse),
		CONSTRAINT reponse_test_candidat_id_reponse_candidat_fkey FOREIGN KEY (id_reponse_candidat) REFERENCES reponse_test_possibles (id_reponse_test_possibles),
		CONSTRAINT reponse_test_candidat_id_attribution_fkey1 FOREIGN KEY (id_attribution) REFERENCES test_candidat (id_attribution),
		CONSTRAINT reponse_test_candidat_id_question_fkey FOREIGN KEY (id_question) REFERENCES question_test (id_question_test),
		CONSTRAINT reponse_test_candidat_id_attribution_fkey FOREIGN KEY (id_attribution) REFERENCES test_candidat (id_attribution) ON DELETE CASCADE
	);