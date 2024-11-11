------------------------------------------------------------------------------------------------------------------------------------------
---------------------------------------INSERTION DANS EMPLOYE APRES VALIDATION CONTRAT----------------------------------------------------
------------------------------------------------------------------------------------------------------------------------------------------
CREATE OR REPLACE FUNCTION insert_into_employe_after_contrat()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO Employe (id_personne, date_embauche, id_poste)
    VALUES (NEW.id_personne, NEW.date_debut, NEW.id_poste);
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_insert_employe_after_contrat
AFTER INSERT ON Contrat
FOR EACH ROW
EXECUTE FUNCTION insert_into_employe_after_contrat();
