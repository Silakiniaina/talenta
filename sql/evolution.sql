ALTER TABLE education_candidat ADD COLUMN id_branche_education INTEGER;
ALTER TABLE education_candidat ADD CONSTRAINT fkey_id_branche_education FOREIGN KEY(id_branche_education) REFERENCES branche_education(id_branche_education);

ALTER TABLE diplome_requis_poste ADD COLUMN id_branche_education INTEGER;
ALTER TABLE diplome_requis_poste ADD CONSTRAINT fkey_id_branche_education_diplome_poste FOREIGN KEY(id_branche_education) REFERENCES branche_education(id_branche_education);

ALTER TABLE experience_candidat DROP COLUMN description_experience;
ALTER TABLE experience_candidat ADD COLUMN id_specialite INTEGER ;
ALTER TABLE experience_candidat ADD CONSTRAINT fkey_id_specialite FOREIGN KEY(id_specialite) REFERENCES specialite(id_specialite);
ALTER TABLE experience_candidat ADD COLUMN entreprise VARCHAR(200);