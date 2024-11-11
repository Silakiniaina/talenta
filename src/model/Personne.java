package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Personne {

    int id;
    String nom;
    String prenom;
    Date dateNaissance;
    String adresse;
    String genre;
    double experience;
    List<Competence> acquis;
    List<SoftSkill> softSkills;
    double pourcentage;
    Poste poste;

    public Personne(){
        this.setAcquis(new ArrayList<Competence>());
        this.setSoftSkills(new ArrayList<SoftSkill>());
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public Poste getPoste(){
        return this.poste;
    }

    public void setPoste(int idPoste)throws SQLException{
        try {
            this.poste = Poste.getById(idPoste);   
        } catch (SQLException e) {
            throw e;
        }
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getGenre() {
        return genre;
    }

    public int getIdGenre() {
        if (this.genre == "homme") {
            return 1;
        }
        return 2;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public List<Competence> getAcquis() {
        return acquis;
    }

    public void setAcquis(List<Competence> acquis) {
        this.acquis = acquis;
    }

    public List<SoftSkill> getSoftSkills() {
        return softSkills;
    }

    public void setSoftSkills(List<SoftSkill> softSkills) {
        this.softSkills = softSkills;
    }

    public double getPourcentage() {
        return pourcentage;
    }

    public void setPourcentage(double pourcentage) {
        this.pourcentage = pourcentage;
    }

    public static Personne getById(int id) throws SQLException {
        Connection connex = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Personne personne = null;

        try {
            connex = Database.getConnection();

            String sql = "SELECT p.id, p.nom, p.prenom, p.adresse, g.genre FROM Personne as p JOIN Genre g ON p.id_genre = g.id WHERE p.id = ?";
            st = connex.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                personne = new Personne();
                personne.setId(rs.getInt("id"));
                personne.setNom(rs.getString("nom"));
                personne.setPrenom(rs.getString("prenom"));
                personne.setAdresse(rs.getString("adresse"));
                personne.setGenre(rs.getString("genre"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null)
                rs.close();
            if (st != null)
                st.close();
            if (connex != null)
                connex.close();
        }

        return personne;
    }

    public Personne insert() throws SQLException {
        Personne result = null;
        Connection connex = null;
        PreparedStatement st = null;

        try {
            connex = Database.getConnection();
            String insertPersonneSql = "INSERT INTO Personne (nom, prenom, date_naissance, adresse, id_genre) VALUES (?, ?, ?, ?, ?)";
            st = connex.prepareStatement(insertPersonneSql, Statement.RETURN_GENERATED_KEYS);

            st.setString(1, this.getNom());
            st.setString(2, this.getPrenom());
            st.setDate(3, this.getDateNaissance());
            st.setString(4, this.getAdresse());
            st.setInt(5, this.getIdGenre());

            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = st.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        System.out.println(id);
                        this.setId(id);
                    }
                }
            }

            this.insertExperience();
            this.insertPoste();
            
            return this;
        } catch (SQLException e) {
            throw e;
        } finally {
            if (st != null)
                st.close();
            if (connex != null)
                connex.close();
        }
    }

    public void insertCompetence() throws SQLException {
        Connection connex = null;
        PreparedStatement st = null;

        try {
            connex = Database.getConnection();
            for (Competence competence : this.getAcquis()) {
                String sql = "INSERT INTO PersonneCompetence (id_personne, id_competence) VALUES (?, ?)";
                st = connex.prepareStatement(sql);
                st.setInt(1, this.getId());
                st.setInt(2, competence.getId());
                st.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null)
                st.close();
            if (connex != null)
                connex.close();
        }

    }

    public void insertExperience() throws SQLException {
        Connection connex = null;
        PreparedStatement st = null;

        try {
            connex = Database.getConnection();
            String insertExperienceSql = "INSERT INTO Experience (id_personne, annee) VALUES (?, ?)";
            st = connex.prepareStatement(insertExperienceSql);
            st.setInt(1, this.getId());
            st.setDouble(2, this.getExperience());
            st.executeUpdate();
            System.out.println("Insertion réussie de l'expérience du candidat");

        } catch (SQLException e) {
            throw e;
        } finally {
            if (st != null)
                st.close();
            if (connex != null)
                connex.close();
        }
    }

    public void insertPoste() throws SQLException {
        Connection connex = null;
        PreparedStatement st = null;

        try {
            connex = Database.getConnection();
            String insertExperienceSql = "INSERT INTO candidat (id_personne, id_poste) VALUES (?, ?)";
            st = connex.prepareStatement(insertExperienceSql);
            st.setInt(1, this.getId());
            st.setInt(2, this.getPoste().getId());
            st.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (st != null)
                st.close();
            if (connex != null)
                connex.close();
        }
    }

    public static void main(String[] args) {
        try {
            Personne p = Personne.getById(38);
            if(p == null){
                System.out.println("personne null");
            }else{
                System.out.println("Personne not null" +p.getNom());
            }
            // List<Competence> ls = p.getAcquis();
            // for(Competence c : ls){
            //     System.out.println(c.getNom());
            // }
            
        } catch (SQLException  e) {
            e.printStackTrace();
        }
    }
}
