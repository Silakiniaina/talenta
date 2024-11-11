package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Poste {

    int id;
    String nom;
    int nbEmploye;
    List<Competence> requis;
    int ageMin;
    int ageMax;
    double experience;
    int totalPlace;
    int occupees;
    String etat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getNbEmploye() {
        return nbEmploye;
    }

    public void setNbEmploye(int nbEmploye) {
        this.nbEmploye = nbEmploye;
    }

    public List<Competence> getRequis() {
        return requis;
    }

    public void setRequis(List<Competence> requis) {
        this.requis = requis;
    }

    public int getAgeMin() {
        return ageMin;
    }

    public void setAgeMin(int ageMin) {
        this.ageMin = ageMin;
    }

    public int getAgeMax() {
        return ageMax;
    }

    public void setAgeMax(int ageMax) {
        this.ageMax = ageMax;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public int getTotalPlace() {
        return totalPlace;
    }

    public void setTotalPlace(int totalPlace) {
        this.totalPlace = totalPlace;
    }

    public int getOccupees() {
        return occupees;
    }

    public void setOccupees(int occupees) {
        this.occupees = occupees;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }
    
    public Poste (int id, String nom, int total, int occupees, String etat){
        this.setId(id);
        this.setNom(nom);
        this.setTotalPlace(total);
        this.setOccupees(occupees);
        this.setEtat(etat);
    }

    public Poste() {}

    public static Poste getById(int id) throws SQLException {
        Connection connex = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Poste poste = null;

        try {
            connex = Database.getConnection();

            String sql = "SELECT* FROM Poste WHERE id=?";
            st = connex.prepareStatement(sql);
            st.setInt(1, id); 
            rs = st.executeQuery();

            if (rs.next()) {
                poste = new Poste();
                poste.setId(rs.getInt("id"));
                poste.setNom(rs.getString("nom"));
                poste.setNbEmploye(rs.getInt("nb_employe"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (connex != null) connex.close();
        }

        return poste;
    }

    public List<Employe> getEmployeCapable() throws SQLException {
        List<Employe> employes = null;
        Connection connex = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            connex = Database.getConnection();
            String sql = "SELECT * v_employe_capable where id_poste=?";
            st = connex.prepareStatement(sql);
            st.setInt(1, this.getId());
            rs = st.executeQuery();

            while (rs.next()) {

                employes= new ArrayList<>();
                int id= rs.getInt("id_personne");
                String nom = rs.getString("nom_employe");
                String prenom = rs.getString("prenom_employe");
                int idPoste= rs.getInt("id_poste");
                double pourcentage = rs.getDouble("pourcentage");

                Employe emp= new Employe(id, nom, prenom, idPoste, pourcentage);
                employes.add(emp);
            }
        } catch (Exception e) {
            System.out.println("Probleme lors de la recuperation des employes capables");
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (connex != null) connex.close();
        }

        return employes;
    }

    public List<Candidat> getCandidatSelection() throws SQLException {
        List<Candidat> candidats = null;
        Connection connex = null;
        PreparedStatement st = null;
        ResultSet rs = null;
    
        try {
            connex = Database.getConnection();
            String sql = "SELECT * FROM v_selection WHERE id_poste = ?";
            st = connex.prepareStatement(sql);  
            st.setInt(1, this.getId());  
            rs = st.executeQuery();
    
            candidats = new ArrayList<>();
            while (rs.next()) {
                int id = rs.getInt("id_personne");
                String nom = rs.getString("nom_candidat");
                String prenom = rs.getString("prenom_candidat");
                int idPoste = rs.getInt("id_poste");
                double pourcentage = rs.getDouble("pourcentage");
                int rang = rs.getInt("rang");
    
                Candidat can = new Candidat(id, nom, prenom, idPoste, pourcentage, rang);
                candidats.add(can);
            }
        } catch (Exception e) {
            System.out.println("Probleme lors de la recuperation des candidats selectionnes");
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (connex != null) connex.close();
        }
    
        return candidats;
    }
    

    public List<Candidat> getCandidatEntretien() throws SQLException {
        List<Candidat> candidats = null;
        Connection connex = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            connex = Database.getConnection();
            String sql = "SELECT * v_entretien where id_poste=?";
            st = connex.prepareStatement(sql);
            st.setInt(1, this.getId());
            rs = st.executeQuery();

            while (rs.next()) {

                candidats= new ArrayList<>();
                int id= rs.getInt("id_personne");
                String nom = rs.getString("nom_candidat");
                String prenom = rs.getString("prenom_candidat");
                int idPoste= rs.getInt("id_poste");
                int nbSoftSkills= rs.getInt("nb_soft_skills");
                double score= rs.getDouble("score_entretien");

                Candidat can= new Candidat(id, nom, prenom, idPoste, nbSoftSkills, score);
                candidats.add(can);
            }
        } catch (Exception e) {
            System.out.println("Probleme lors de la recuperation des employes apres entretien");
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (connex != null) connex.close();
        }

        return candidats;
    }

    public static List<Poste> getAll() throws SQLException {
        List<Poste> postes = new ArrayList<>();
        Connection connex = null;
        PreparedStatement st = null;
        ResultSet rs = null;
    
        try {
            connex = Database.getConnection();
            String sql = "SELECT * FROM v_etatPoste"; 
            st = connex.prepareStatement(sql);
            rs = st.executeQuery();
    
            while (rs.next()) {
                int id = rs.getInt("poste_id");
                String name = rs.getString("poste_nom");
                int totalPlaces = rs.getInt("total_places");
                int occupees = rs.getInt("current_employes");
                String etat = rs.getString("etat");

                Poste poste = new Poste(id, name, totalPlaces, occupees, etat);
                postes.add(poste);
            }
    
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (connex != null) connex.close();
        }
    
        return postes;
    }
    
    public static void main(String[] args) throws SQLException {
        Poste poste= Poste.getById(1);
        List<Candidat> c= poste.getCandidatSelection();
        for (Candidat candidat : c){
            System.out.println(candidat.getNom()+" "+candidat.getPrenom());
        }
    }
    
    
}
