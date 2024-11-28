package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Employe {
    
    private int idEmploye;
    private Candidat candidat;
    private Poste poste;
    private Date dateEmbauche;

    // CONSTRUCTORS
    public Employe(){

    }


    // GETTERS AND SETTERS
    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }
    public void setCandidat(Connection con, int candidat) throws SQLException{
        Candidat c = new Candidat();
        c.setIdCandidat(candidat);
        this.candidat = c.getById(con);
    }
    public void setPoste(int poste)throws SQLException {
        this.poste = Poste.getById(poste);
    }
    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }
    public int getIdEmploye() {
        return idEmploye;
    }
    public Candidat getCandidat() {
        return candidat;
    }
    public Poste getPoste() {
        return poste;
    }
    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public static Employe getById(Connection con, int idEmploye) throws SQLException {
        Employe employe = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        String query = "SELECT * FROM employe WHERE id_employe = ?";
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, idEmploye);
            rs = ps.executeQuery();

            if (rs.next()) {
                employe = new Employe();
                employe.setIdEmploye(rs.getInt("id_employe"));
                employe.setCandidat(con, rs.getInt("id_candidat"));
                employe.setPoste(rs.getInt("id_poste"));
                employe.setDateEmbauche(rs.getDate("date_embauche"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
        return employe;
    }

    public static List<Employe> getAllReadyForRetraite(Connection con) throws SQLException {
        List<Employe> result = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM v_employes_age_retraite";
        
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Employe employe = new Employe();
                employe.setIdEmploye(rs.getInt("id_employe"));
                employe.setCandidat(con, rs.getInt("id_employe"));
                employe.setPoste(rs.getInt("id_poste"));
                employe.setDateEmbauche(rs.getDate("date_embauche"));

                result.add(employe);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }

        return result;
    }

}