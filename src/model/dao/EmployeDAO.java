package model.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.InformationEmploye;
import model.utils.Database;

public class EmployeDAO {

    public List<InformationEmploye> getInformationsEmployeAvecConges() {
        List<InformationEmploye> employes = new ArrayList<>();
        String requete = "SELECT * FROM v_informations_employe";
        
        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(requete)) {

            while (rs.next()) {
                InformationEmploye employe = new InformationEmploye();
                employe.setIdEmploye(rs.getInt("id_candidat"));
                employe.setPrenom(rs.getString("prenom"));
                employe.setNom(rs.getString("nom"));
                employe.setEmail(rs.getString("email"));
                employe.setGenre(rs.getString("genre"));
                employe.setJoursAcquis(rs.getInt("jours_acquis"));
                employe.setJoursPris(rs.getInt("jours_pris"));
                employe.setJoursRestants(rs.getInt("jours_restants"));
                employes.add(employe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employes;
    }

        public InformationEmploye getEmployeById(int idEmploye) {
            InformationEmploye employe = null;
        String requete = "SELECT * FROM v_informations_employe WHERE id_candidat = ?";  // Ta vue SQL ici

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(requete)) {

            // Passer l'ID de l'employé à la requête préparée
            stmt.setInt(1, idEmploye);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                employe = new InformationEmploye();
                employe.setIdEmploye(rs.getInt("id_candidat"));
                employe.setPrenom(rs.getString("prenom"));
                employe.setNom(rs.getString("nom"));
                employe.setEmail(rs.getString("email"));
                employe.setGenre(rs.getString("genre"));
                employe.setJoursAcquis(rs.getInt("jours_acquis"));
                employe.setJoursPris(rs.getInt("jours_pris"));
                employe.setJoursRestants(rs.getInt("jours_restants"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employe;
    }
}

