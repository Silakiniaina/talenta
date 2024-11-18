package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class PreselectionCandidat {
    
    private Candidat candidat;
    private Recrutement recrutement;
    private double pourcentageCompetence;
    private double pourcentageExperience;
    private double pourcentageDiplome;
    private double scoreGlobale;

    // CONSTRUCTORS
    public PreselectionCandidat(){

    }


    // ACTIONS 
    public static List<PreselectionCandidat> getPreselectionByRecrutement(int idRecrutement) throws SQLException{
        List<PreselectionCandidat> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM v_filtre_cv WHERE id_recrutement = ?";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setInt(1, idRecrutement);
            rs = prstm.executeQuery();

            while (rs.next()) {
                PreselectionCandidat d = new PreselectionCandidat();
                d.setCandidat(rs.getInt(1));
                d.setRecrutement(rs.getInt(2));
                d.setPourcentageCompetence(rs.getDouble(4));
                d.setPourcentageDiplome(rs.getDouble(5));
                d.setPourcentageExperience(rs.getDouble(6));
                d.setScoreGlobale(rs.getDouble(7));
                result.add(d);
            }
        } catch (SQLException e) {
            throw e;
        }finally{
            if(rs != null){
                rs.close();
            }
            if(prstm != null){
                prstm.close();
            }
            if(c != null){
                c.close();
            }
        }
        return result;
    }


    // GETTERS
    public Candidat getCandidat() {
        return candidat;
    }
    public Recrutement getRecrutement() {
        return recrutement;
    }

    public double getPourcentageCompetence() {
        return pourcentageCompetence;
    }
    public double getPourcentageExperience() {
        return pourcentageExperience;
    }
    public double getPourcentageDiplome(){
        return pourcentageDiplome;
    }
    public double getScoreGlobale() {
        return scoreGlobale;
    }
    public void setCandidat(int idcandidat) throws SQLException{
        this.candidat = Candidat.getById(idcandidat);
    }
    public void setRecrutement(int idrecrutement) throws SQLException{
        this.recrutement = Recrutement.getById(idrecrutement);
    }
    public void setPourcentageCompetence(double pourcentageCompetence) {
        this.pourcentageCompetence = pourcentageCompetence;
    }
    public void setPourcentageExperience(double pourcentageExperience) {
        this.pourcentageExperience = pourcentageExperience;
    }
    public void setPourcentageDiplome(double d){
        this.pourcentageDiplome = d;
    }
    public void setScoreGlobale(double scoreGlobale) {
        this.scoreGlobale = scoreGlobale;
    }
}
