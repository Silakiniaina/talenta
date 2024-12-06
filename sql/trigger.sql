CREATE OR REPLACE FUNCTION calculer_deductions()
RETURNS TRIGGER AS $$
BEGIN
    INSERT INTO deduction_employe (id_employe, id_deduction, base_salaire, montant)
    SELECT 
        NEW.id_employe, 
        df.id_deduction, 
        NEW.salaire_base,
        (NEW.salaire_base * df.taux / 100)
    FROM 
        deduction_fiscale df;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER trigger_calcul_deductions
AFTER INSERT OR UPDATE ON employe
FOR EACH ROW
EXECUTE FUNCTION calculer_deductions();
