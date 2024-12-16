-- Insertion des données de présence avec différents scénarios
INSERT INTO presence_employe (id_employe, date_entree, date_sortie) VALUES
-- Employé 1 - Jean Dupont (Informatique)
(1, '2024-11-01 07:30:00', '2024-11-01 17:45:00'), -- Journée normale
(1, '2024-11-04 19:00:00', '2024-11-05 03:30:00'), -- Travail de nuit
(1, '2024-11-08 06:45:00', '2024-11-08 18:15:00'), -- Heures supplémentaires
(1, '2024-11-15 08:00:00', '2024-11-15 12:00:00'), -- Demi-journée
(1, '2024-11-22 07:15:00', '2024-11-22 19:45:00'), -- Longue journée

-- Employé 2 - Sophie Martin (Marketing)
(2, '2024-11-02 08:15:00', '2024-11-02 16:30:00'), -- Journée normale
(2, '2024-11-06 20:00:00', '2024-11-07 04:30:00'), -- Travail de nuit
(2, '2024-11-10 07:00:00', '2024-11-10 17:00:00'), -- Journée standard
(2, '2024-11-18 09:00:00', '2024-11-18 13:00:00'), -- Demi-journée
(2, '2024-11-25 06:45:00', '2024-11-25 20:15:00'), -- Longue journée avec heures sup

-- Employé 3 - Christophe Leroy (RH)
(3, '2024-11-03 07:45:00', '2024-11-03 16:45:00'), -- Journée normale
(3, '2024-11-07 18:30:00', '2024-11-08 02:45:00'), -- Travail de nuit
(3, '2024-11-11 08:00:00', '2024-11-11 17:30:00'), -- Journée standard
(3, '2024-11-16 07:15:00', '2024-11-16 19:45:00'), -- Longue journée

-- Employé 4 - Claire Moreau (Finance)
(4, '2024-11-05 08:00:00', '2024-11-05 17:00:00'), -- Journée normale
(4, '2024-11-09 19:30:00', '2024-11-10 03:45:00'), -- Travail de nuit
(4, '2024-11-14 07:30:00', '2024-11-14 18:00:00'), -- Heures supplémentaires
(4, '2024-11-21 09:00:00', '2024-11-21 13:00:00'), -- Demi-journée

-- Employé 5 - Nicolas Petit (Logistique)
(5, '2024-11-06 06:45:00', '2024-11-06 16:45:00'), -- Journée normale
(5, '2024-11-10 20:00:00', '2024-11-11 04:30:00'), -- Travail de nuit
(5, '2024-11-17 07:15:00', '2024-11-17 19:45:00'), -- Longue journée

-- Employé 6 - Émilie Girard (Commercial)
(6, '2024-11-07 08:15:00', '2024-11-07 16:30:00'), -- Journée normale
(6, '2024-11-11 18:30:00', '2024-11-12 02:45:00'), -- Travail de nuit
(6, '2024-11-19 07:45:00', '2024-11-19 17:45:00'), -- Journée standard
(6, '2024-11-26 06:30:00', '2024-11-26 20:00:00'), -- Très longue journée

-- Employé 7 - David Lopez (Production)
(7, '2024-11-08 07:00:00', '2024-11-08 17:00:00'), -- Journée normale
(7, '2024-11-12 19:45:00', '2024-11-13 04:15:00'), -- Travail de nuit
(7, '2024-11-20 08:15:00', '2024-11-20 18:45:00'), -- Heures supplémentaires

-- Employé 8 - Amélie Thomas (Informatique)
(8, '2024-11-09 08:30:00', '2024-11-09 16:45:00'), -- Journée normale
(8, '2024-11-13 20:15:00', '2024-11-14 04:45:00'), -- Travail de nuit
(8, '2024-11-22 07:00:00', '2024-11-22 19:00:00'), -- Longue journée

-- Employé 9 - Alexandre Rousseau (Marketing)
(9, '2024-11-10 07:45:00', '2024-11-10 16:15:00'), -- Journée normale
(9, '2024-11-14 18:45:00', '2024-11-15 03:15:00'), -- Travail de nuit
(9, '2024-11-23 08:00:00', '2024-11-23 17:30:00'), -- Journée standard

-- Employé 10 - Marie Lefebvre (Finance)
(10, '2024-11-11 08:00:00', '2024-11-11 17:00:00'), -- Journée normale
(10, '2024-11-15 19:30:00', '2024-11-16 04:00:00'), -- Travail de nuit
(10, '2024-11-25 07:15:00', '2024-11-25 18:45:00'); -- Heures supplémentaires