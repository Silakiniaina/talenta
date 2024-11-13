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
    private int nombreCompetenceRequise;
    private int nombreCompetenceCandidat;
    private double pourcentageCompetence;
    private double pourcentageExperience;
    private double scoreGlobale;

    // CONSTRUCTORS
    public PreselectionCandidat(){

    }

    public PreselectionCandidat(int idCandidat, int idRecrutement, int requise, int got, double pourcentageCompetence, double pourcentageExperience, double score)throws SQLException{
        this.setCandidat(idCandidat);
        this.setRecrutement(idRecrutement);
        this.setNombreCompetenceRequise(requise);
        this.setNombreCompetenceCandidat(idCandidat);
        this.setPourcentageCompetence(pourcentageCompetence);
        this.setPourcentageExperience(pourcentageExperience);
        this.setScoreGlobale(score);
    }

    // ACTIONS 
    public static List<PreselectionCandidat> getPreselectionByRecrutement(int idRecrutement) throws SQLException{
        List<PreselectionCandidat> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM v_preselection_candidat WHERE id_recrutement = ?";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setInt(1, idRecrutement);
            rs = prstm.executeQuery();

            while (rs.next()) {
                PreselectionCandidat d = new PreselectionCandidat(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getInt(3),
                    rs.getInt(4),
                    rs.getDouble(5),
                    rs.getDouble(6),
                    rs.getDouble(7)
                );
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
    public int getNombreCompetenceRequise() {
        return nombreCompetenceRequise;
    }
    public int getNombreCompetenceCandidat() {
        return nombreCompetenceCandidat;
    }
    public double getPourcentageCompetence() {
        return pourcentageCompetence;
    }
    public double getPourcentageExperience() {
        return pourcentageExperience;
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
    public void setNombreCompetenceRequise(int nombreCompetenceRequise) {
        this.nombreCompetenceRequise = nombreCompetenceRequise;
    }
    public void setNombreCompetenceCandidat(int nombreCompetenceCandidat) {
        this.nombreCompetenceCandidat = nombreCompetenceCandidat;
    }
    public void setPourcentageCompetence(double pourcentageCompetence) {
        this.pourcentageCompetence = pourcentageCompetence;
    }
    public void setPourcentageExperience(double pourcentageExperience) {
        this.pourcentageExperience = pourcentageExperience;
    }
    public void setScoreGlobale(double scoreGlobale) {
        this.scoreGlobale = scoreGlobale;
    }
}
