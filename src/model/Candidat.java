package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Candidat extends Personne {
    int rang;
    int nbSoftSkills;
    double score;

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(int idPoste) throws SQLException {
        this.poste = Poste.getById(idPoste);
    }

    public int getRang() {
        return rang;
    }

    public void setRang(int rang) {
        this.rang = rang;
    }

    public int getNbSoftSkills() {
        return nbSoftSkills;
    }

    public void setNbSoftSkills(int nbSoftSkills) {
        this.nbSoftSkills = nbSoftSkills;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Candidat (int id, String nom, String prenom, int idPoste, double pourcentage, int rang) throws SQLException{
        this.setId(id);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setPoste(idPoste);
        this.setPourcentage(pourcentage);
        this.setRang(rang);
    }

    public Candidat (int id, String nom, String prenom, int idPoste, int nbSkills, double score) throws SQLException{
        this.setId(id);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setPoste(idPoste);
        this.setNbSoftSkills(nbSkills);
        this.setScore(score);
    }

    public Candidat (int id, int idPoste) throws SQLException{
        this.setId(idPoste);
        this.setPoste(idPoste);
    }

    public static Candidat getById(int id) throws SQLException {
        Candidat candidat = null;
        Connection connex = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            connex = Database.getConnection();

            String sql = "SELECT* FROM Candidat";
            st = connex.prepareStatement(sql);
            st.setInt(1, id);

            rs = st.executeQuery();

            if (rs.next()) {
                
                int idPersonne = rs.getInt("id_personne");
                int idPoste= rs.getInt("id_poste");
                candidat= new Candidat(idPersonne, idPoste);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (connex != null) connex.close();
        }
        return candidat;
    }

}
