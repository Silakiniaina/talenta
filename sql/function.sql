CREATE OR REPLACE FUNCTION calculer_irsa(montant_brut NUMERIC)
RETURNS NUMERIC AS $$
BEGIN
    IF montant_brut <= 350000 THEN
        RETURN 0;
    ELSIF montant_brut BETWEEN 350001 AND 400000 THEN
        RETURN (montant_brut - 350000) * 0.05;
    ELSIF montant_brut BETWEEN 400001 AND 500000 THEN
        RETURN (montant_brut - 400000) * 0.10 + 2500; -- 2500 = (400000-350001)*0.05
    ELSIF montant_brut BETWEEN 500001 AND 600000 THEN
        RETURN (montant_brut - 500000) * 0.15 + 7500; -- 7500 = (400000-350001)*0.05 + (500000-400001)*0.10
    ELSE
        RETURN (montant_brut - 600000) * 0.20 + 12500; -- 12500 = (400000-350001)*0.05 + (500000-400001)*0.10 + (600000-500001)*0.15
    END IF;
END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION calculer_irsa(montant_brut NUMERIC) 
RETURNS NUMERIC AS $$
DECLARE
    irsa_total NUMERIC := 0;
BEGIN
    -- Tranche 1 : jusqu'à 350 000, taux de 0%
    IF montant_brut <= 350000 THEN
        irsa_total := 0;
    ELSE
        irsa_total := 0;
    END IF;

    -- Tranche 2 : de 350 001 à 400 000, taux de 5%
    IF montant_brut > 350000 AND montant_brut <= 400000 THEN
        irsa_total := irsa_total + (montant_brut - 350000) * 0.05;
    ELSIF montant_brut > 400000 THEN
        irsa_total := irsa_total + (400000 - 350000) * 0.05;
    END IF;

    -- Tranche 3 : de 400 001 à 500 000, taux de 10%
    IF montant_brut > 400000 AND montant_brut <= 500000 THEN
        irsa_total := irsa_total + (montant_brut - 400000) * 0.10;
    ELSIF montant_brut > 500000 THEN
        irsa_total := irsa_total + (500000 - 400000) * 0.10;
    END IF;

    -- Tranche 4 : de 500 001 à 600 000, taux de 15%
    IF montant_brut > 500000 AND montant_brut <= 600000 THEN
        irsa_total := irsa_total + (montant_brut - 500000) * 0.15;
    ELSIF montant_brut > 600000 THEN
        irsa_total := irsa_total + (600000 - 500000) * 0.15;
    END IF;

    -- Tranche 5 : au-delà de 600 000, taux de 20%
    IF montant_brut > 600000 THEN
        irsa_total := irsa_total + (montant_brut - 600000) * 0.20;
    END IF;

    RETURN irsa_total;
END;
$$ LANGUAGE plpgsql;
