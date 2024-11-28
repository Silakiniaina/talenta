CREATE  TABLE categorie_competence ( 
	id_categorie_competence serial  NOT NULL  ,
	label                varchar(150)  NOT NULL  ,
	CONSTRAINT categorie_competence_pkey PRIMARY KEY ( id_categorie_competence )
 );

CREATE  TABLE competence ( 
	id_competence        serial  NOT NULL  ,
	label                varchar(250)  NOT NULL  ,
	id_categorie_competence integer    ,
	CONSTRAINT competence_pkey PRIMARY KEY ( id_competence ),
	CONSTRAINT fk_categorie_competence FOREIGN KEY ( id_categorie_competence ) REFERENCES categorie_competence( id_categorie_competence )   
 );

CREATE  TABLE departement ( 
	id_departement       serial  NOT NULL  ,
	nom                  varchar(150)  NOT NULL  ,
	CONSTRAINT departement_pkey PRIMARY KEY ( id_departement )
 );

CREATE  TABLE genre ( 
	id_genre             serial  NOT NULL  ,
	label                varchar(150)  NOT NULL  ,
	CONSTRAINT genre_pkey PRIMARY KEY ( id_genre )
 );

CREATE  TABLE poste ( 
	id_poste             serial  NOT NULL  ,
	nom                  varchar(150)  NOT NULL  ,
	id_departement       integer  NOT NULL  ,
	annees_experience_requises integer DEFAULT 0   ,
	CONSTRAINT poste_pkey PRIMARY KEY ( id_poste ),
	CONSTRAINT poste_id_departement_fkey FOREIGN KEY ( id_departement ) REFERENCES departement( id_departement )   
 );

CREATE  TABLE role_talenta ( 
	id_role              serial  NOT NULL  ,
	nom_role             varchar(150)  NOT NULL  ,
	CONSTRAINT role_talenta_pkey PRIMARY KEY ( id_role )
 );

CREATE  TABLE status ( 
	id_status            serial  NOT NULL  ,
	label                varchar(150)  NOT NULL  ,
	corresponding_color  varchar(250)    ,
	CONSTRAINT status_pkey PRIMARY KEY ( id_status )
 );

CREATE  TABLE type_contrat ( 
	id_type_contrat      serial  NOT NULL  ,
	label                varchar(150)  NOT NULL  ,
	CONSTRAINT type_contrat_pkey PRIMARY KEY ( id_type_contrat )
 );

CREATE  TABLE type_diplome ( 
	id_type_diplome      serial  NOT NULL  ,
	label                varchar(100)  NOT NULL  ,
	CONSTRAINT type_diplome_pkey PRIMARY KEY ( id_type_diplome )
 );

CREATE  TABLE type_questionaire ( 
	id_type_questionaire serial  NOT NULL  ,
	label                varchar(150)  NOT NULL  ,
	CONSTRAINT type_questionaire_pkey PRIMARY KEY ( id_type_questionaire )
 );

CREATE  TABLE candidat ( 
	id_candidat          serial  NOT NULL  ,
	nom                  varchar(150)  NOT NULL  ,
	prenom               varchar(250)  NOT NULL  ,
	date_naissance       date  NOT NULL  ,
	adresse              varchar(250)  NOT NULL  ,
	id_genre             integer  NOT NULL  ,
	email                varchar DEFAULT 'abc@gmail.com'::character varying   ,
	mdp                  varchar DEFAULT 'admin'::character varying   ,
	CONSTRAINT candidat_pkey PRIMARY KEY ( id_candidat ),
	CONSTRAINT uq_candidat UNIQUE ( id_candidat, email ) ,
	CONSTRAINT candidat_id_genre_fkey FOREIGN KEY ( id_genre ) REFERENCES genre( id_genre )   
 );

CREATE  TABLE competence_candidat ( 
	id_candidat          integer  NOT NULL  ,
	id_competence        integer  NOT NULL  ,
	CONSTRAINT competence_candidat_id_competence_fkey FOREIGN KEY ( id_competence ) REFERENCES competence( id_competence )   ,
	CONSTRAINT competence_candidat_id_candidat_fkey FOREIGN KEY ( id_candidat ) REFERENCES candidat( id_candidat )   
 );

CREATE  TABLE competence_requise ( 
	id_poste             integer  NOT NULL  ,
	id_competence        integer  NOT NULL  ,
	experience           integer  NOT NULL  ,
	CONSTRAINT competence_requise_pkey PRIMARY KEY ( id_poste, id_competence ),
	CONSTRAINT competence_requise_id_competence_fkey FOREIGN KEY ( id_competence ) REFERENCES competence( id_competence )   ,
	CONSTRAINT competence_requise_id_poste_fkey FOREIGN KEY ( id_poste ) REFERENCES poste( id_poste )   
 );

CREATE  TABLE competence_requise_poste ( 
	id_poste             integer  NOT NULL  ,
	id_competence        integer  NOT NULL  ,
	obligatoire          boolean DEFAULT true   ,
	CONSTRAINT competence_requise_poste_pkey PRIMARY KEY ( id_poste, id_competence ),
	CONSTRAINT competence_requise_poste_id_competence_fkey FOREIGN KEY ( id_competence ) REFERENCES competence( id_competence )   ,
	CONSTRAINT competence_requise_poste_id_poste_fkey FOREIGN KEY ( id_poste ) REFERENCES poste( id_poste )   
 );

CREATE  TABLE contrat ( 
	id_contrat           serial  NOT NULL  ,
	date_debut_contrat   date DEFAULT now()   ,
	salaire_base         numeric(18,2)  NOT NULL  ,
	date_fin_contrat     date    ,
	id_candidat          integer  NOT NULL  ,
	id_type_contrat      integer  NOT NULL  ,
	CONSTRAINT contrat_pkey PRIMARY KEY ( id_contrat ),
	CONSTRAINT contrat_id_type_contrat_fkey FOREIGN KEY ( id_type_contrat ) REFERENCES type_contrat( id_type_contrat )   ,
	CONSTRAINT contrat_id_candidat_fkey FOREIGN KEY ( id_candidat ) REFERENCES candidat( id_candidat )   
 );

CREATE  TABLE diplome_requis_poste ( 
	id_poste             integer  NOT NULL  ,
	id_type_diplome      integer  NOT NULL  ,
	obligatoire          boolean DEFAULT true   ,
	CONSTRAINT diplome_requis_poste_pkey PRIMARY KEY ( id_poste, id_type_diplome ),
	CONSTRAINT diplome_requis_poste_id_type_diplome_fkey FOREIGN KEY ( id_type_diplome ) REFERENCES type_diplome( id_type_diplome )   ,
	CONSTRAINT diplome_requis_poste_id_poste_fkey FOREIGN KEY ( id_poste ) REFERENCES poste( id_poste )   
 );

CREATE  TABLE education_candidat ( 
	id_education_candidat serial  NOT NULL  ,
	id_candidat          integer  NOT NULL  ,
	date_debut           date  NOT NULL  ,
	date_fin             date  NOT NULL  ,
	id_type_diplome      integer  NOT NULL  ,
	nom_ecole            varchar(256)  NOT NULL  ,
	CONSTRAINT education_candidat_pkey PRIMARY KEY ( id_education_candidat ),
	CONSTRAINT education_candidat_id_type_diplome_nom_ecole_key UNIQUE ( id_type_diplome, nom_ecole ) ,
	CONSTRAINT education_candidat_id_type_diplome_fkey FOREIGN KEY ( id_type_diplome ) REFERENCES type_diplome( id_type_diplome )   ,
	CONSTRAINT education_candidat_id_candidat_fkey FOREIGN KEY ( id_candidat ) REFERENCES candidat( id_candidat )   
 );

CREATE  TABLE employe ( 
	id_employe           serial  NOT NULL  ,
	date_embauche        date DEFAULT now()   ,
	id_candidat          integer  NOT NULL  ,
	id_poste             integer  NOT NULL  ,
	CONSTRAINT employe_pkey PRIMARY KEY ( id_employe ),
	CONSTRAINT employe_id_poste_fkey FOREIGN KEY ( id_poste ) REFERENCES poste( id_poste )   ,
	CONSTRAINT employe_id_candidat_fkey FOREIGN KEY ( id_candidat ) REFERENCES candidat( id_candidat )   
 );

CREATE  TABLE experience_candidat ( 
	id_experience_candidat serial  NOT NULL  ,
	id_candidat          integer  NOT NULL  ,
	date_debut           date  NOT NULL  ,
	date_fin             date  NOT NULL  ,
	description_experience text  NOT NULL  ,
	CONSTRAINT experience_candidat_pkey PRIMARY KEY ( id_experience_candidat ),
	CONSTRAINT experience_candidat_id_candidat_fkey FOREIGN KEY ( id_candidat ) REFERENCES candidat( id_candidat )   
 );

CREATE  TABLE notification_candidat ( 
	id_notification      serial  NOT NULL  ,
	id_candidat          integer  NOT NULL  ,
	contenu_notification varchar(256)  NOT NULL  ,
	date_notification    timestamp DEFAULT CURRENT_TIMESTAMP   ,
	date_vue_notification timestamp    ,
	target_link          varchar(256)    ,
	CONSTRAINT notification_candidat_pkey PRIMARY KEY ( id_notification ),
	CONSTRAINT notification_candidat_id_candidat_fkey FOREIGN KEY ( id_candidat ) REFERENCES candidat( id_candidat )   
 );

CREATE  TABLE questionaire ( 
	id_questionaire      serial  NOT NULL  ,
	question             text  NOT NULL  ,
	id_type_questionaire integer  NOT NULL  ,
	CONSTRAINT questionaire_pkey PRIMARY KEY ( id_questionaire ),
	CONSTRAINT questionaire_id_type_questionaire_fkey FOREIGN KEY ( id_type_questionaire ) REFERENCES type_questionaire( id_type_questionaire )   
 );

CREATE  TABLE recrutement ( 
	id_recrutement       serial  NOT NULL  ,
	date_debut_recrutement date  NOT NULL  ,
	date_fin_recrutement date  NOT NULL  ,
	nombre               integer DEFAULT 1   ,
	id_poste             integer  NOT NULL  ,
	id_status            integer DEFAULT 1   ,
	CONSTRAINT recrutement_pkey PRIMARY KEY ( id_recrutement ),
	CONSTRAINT fk_status_recrutement FOREIGN KEY ( id_status ) REFERENCES status( id_status )   ,
	CONSTRAINT recrutement_id_poste_fkey FOREIGN KEY ( id_poste ) REFERENCES poste( id_poste )   
 );

CREATE  TABLE recrutement_candidat ( 
	id_candidat          integer  NOT NULL  ,
	id_recrutement       integer  NOT NULL  ,
	date_postule         date DEFAULT now()   ,
	is_prechosen         boolean DEFAULT false   ,
	CONSTRAINT recrutement_candidat_pkey PRIMARY KEY ( id_candidat, id_recrutement ),
	CONSTRAINT recrutement_candidat_id_recrutement_fkey FOREIGN KEY ( id_recrutement ) REFERENCES recrutement( id_recrutement )   ,
	CONSTRAINT recrutement_candidat_id_candidat_fkey FOREIGN KEY ( id_candidat ) REFERENCES candidat( id_candidat )   
 );

CREATE  TABLE reponse_entretient ( 
	id_candidat          integer  NOT NULL  ,
	id_questionaire      integer  NOT NULL  ,
	reponse              smallint  NOT NULL  ,
	CONSTRAINT reponse_entretient_pkey PRIMARY KEY ( id_candidat, id_questionaire ),
	CONSTRAINT reponse_entretient_id_questionaire_fkey FOREIGN KEY ( id_questionaire ) REFERENCES questionaire( id_questionaire )   ,
	CONSTRAINT reponse_entretient_id_candidat_fkey FOREIGN KEY ( id_candidat ) REFERENCES candidat( id_candidat )   
 );

CREATE  TABLE reponse_test ( 
	id_candidat          integer  NOT NULL  ,
	id_questionaire      integer  NOT NULL  ,
	reponse              smallint  NOT NULL  ,
	CONSTRAINT reponse_test_pkey PRIMARY KEY ( id_candidat, id_questionaire ),
	CONSTRAINT reponse_test_id_questionaire_fkey FOREIGN KEY ( id_questionaire ) REFERENCES questionaire( id_questionaire )   ,
	CONSTRAINT reponse_test_id_candidat_fkey FOREIGN KEY ( id_candidat ) REFERENCES candidat( id_candidat )   
 );

CREATE  TABLE responsable ( 
	id_responsable       serial  NOT NULL  ,
	nom                  varchar(150)  NOT NULL  ,
	email                varchar(256)  NOT NULL  ,
	mdp                  varchar(256)  NOT NULL  ,
	id_role              integer  NOT NULL  ,
	CONSTRAINT responsable_pkey PRIMARY KEY ( id_responsable ),
	CONSTRAINT responsable_id_responsable_email_key UNIQUE ( id_responsable, email ) ,
	CONSTRAINT responsable_id_role_fkey FOREIGN KEY ( id_role ) REFERENCES role_talenta( id_role )   
 );

CREATE  TABLE simulation ( 
	id_simulation        serial  NOT NULL  ,
	titre                varchar(255)  NOT NULL  ,
	description          text  NOT NULL  ,
	date_creation        timestamp DEFAULT CURRENT_TIMESTAMP   ,
	id_responsable       integer  NOT NULL  ,
	CONSTRAINT simulation_pkey PRIMARY KEY ( id_simulation ),
	CONSTRAINT simulation_id_responsable_fkey FOREIGN KEY ( id_responsable ) REFERENCES responsable( id_responsable )   
 );

CREATE  TABLE simulation_candidat ( 
	id_attribution       serial  NOT NULL  ,
	id_simulation        integer    ,
	id_candidat          integer  NOT NULL  ,
	id_status            integer  NOT NULL  ,
	CONSTRAINT simulation_candidat_pkey PRIMARY KEY ( id_attribution ),
	CONSTRAINT simulation_candidat_id_status_fkey FOREIGN KEY ( id_status ) REFERENCES status( id_status )   ,
	CONSTRAINT simulation_candidat_id_candidat_fkey FOREIGN KEY ( id_candidat ) REFERENCES candidat( id_candidat )   ,
	CONSTRAINT simulation_candidat_id_simulation_fkey FOREIGN KEY ( id_simulation ) REFERENCES simulation( id_simulation ) ON DELETE CASCADE  
 );

CREATE  TABLE question_simulation ( 
	id_question_simulation serial  NOT NULL  ,
	id_simulation        integer    ,
	texte_question       text  NOT NULL  ,
	CONSTRAINT question_simulation_pkey PRIMARY KEY ( id_question_simulation ),
	CONSTRAINT question_simulation_id_simulation_fkey FOREIGN KEY ( id_simulation ) REFERENCES simulation( id_simulation ) ON DELETE CASCADE  
 );

CREATE  TABLE reponse_simulation_possibles ( 
	id_reponse_simulation_possibles integer DEFAULT nextval('reponse_simulation_possibles_id_reponse_simulation_possible_seq'::regclass) NOT NULL  ,
	id_question_simulation integer    ,
	texte_reponse        text  NOT NULL  ,
	CONSTRAINT reponse_simulation_possibles_pkey PRIMARY KEY ( id_reponse_simulation_possibles ),
	CONSTRAINT reponse_simulation_possibles_id_question_simulation_fkey FOREIGN KEY ( id_question_simulation ) REFERENCES question_simulation( id_question_simulation ) ON DELETE CASCADE  
 );

CREATE  TABLE reponse_attendue ( 
	id_simulation        integer  NOT NULL  ,
	id_question_simulation integer  NOT NULL  ,
	id_reponse_attendue  integer  NOT NULL  ,
	CONSTRAINT reponse_attendue_id_reponse_attendue_fkey FOREIGN KEY ( id_reponse_attendue ) REFERENCES reponse_simulation_possibles( id_reponse_simulation_possibles )   ,
	CONSTRAINT reponse_attendue_id_question_simulation_fkey FOREIGN KEY ( id_question_simulation ) REFERENCES question_simulation( id_question_simulation )   ,
	CONSTRAINT reponse_attendue_id_simulation_fkey FOREIGN KEY ( id_simulation ) REFERENCES simulation( id_simulation )   
 );

CREATE  TABLE reponse_simulation_candidat ( 
	id_reponse           serial  NOT NULL  ,
	id_attribution       integer    ,
	id_question          integer    ,
	id_reponse_candidat  integer  NOT NULL  ,
	date_soumission      timestamp DEFAULT CURRENT_TIMESTAMP   ,
	CONSTRAINT reponse_simulation_candidat_pkey PRIMARY KEY ( id_reponse ),
	CONSTRAINT reponse_simulation_candidat_id_reponse_candidat_fkey FOREIGN KEY ( id_reponse_candidat ) REFERENCES reponse_simulation_possibles( id_reponse_simulation_possibles )   ,
	CONSTRAINT reponse_simulation_candidat_id_attribution_fkey1 FOREIGN KEY ( id_attribution ) REFERENCES simulation_candidat( id_attribution )   ,
	CONSTRAINT reponse_simulation_candidat_id_question_fkey FOREIGN KEY ( id_question ) REFERENCES question_simulation( id_question_simulation )   ,
	CONSTRAINT reponse_simulation_candidat_id_attribution_fkey FOREIGN KEY ( id_attribution ) REFERENCES simulation_candidat( id_attribution ) ON DELETE CASCADE  
 );