CREATE OR REPLACE FUNCTION reset_poste_exigence_competence()
RETURNS void AS
$$
BEGIN
    DELETE FROM PosteCompetence;
    ALTER SEQUENCE postecompetence_id_seq RESTART WITH 1;
    DELETE FROM PosteExigence;
    ALTER SEQUENCE posteexigence_id_seq RESTART WITH 1;
END;
$$ LANGUAGE plpgsql;

select reset_poste_exigence_competence();


INSERT INTO PosteCompetence (id_poste, id_competence) VALUES 
(1, 1),  
(1, 2),  
(1, 4),  
(1, 5),  
(1, 6),  
(1, 7), 
(1, 9), 
(1, 8),  
(1, 12), 
(1, 14), 
(1, 15), 
(1, 18); 



INSERT INTO PosteExigence (id_poste, age_min, age_max, id_genre, experience) 
VALUES 
(1, 25, 40, NULL, 3.0); 




