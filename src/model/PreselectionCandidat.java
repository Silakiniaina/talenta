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

    public String getTypeNiveau(int a){
        String result = "success";
        switch (a) {
            case 1:
                if(this.getPourcentageCompetence() >= 80){
                    result = "success";
                }else if(this.getPourcentageCompetence() >= 50 && this.getPourcentageCompetence() <= 80){
                    result = "primary";
                }else if(this.getPourcentageCompetence() >= 25 && this.getPourcentageCompetence() <= 50){
                    result = "warning";
                }else{
                    result = "danger";
                }
                break;

            case 2:
                if(this.getPourcentageDiplome() >= 80){
                    result = "success";
                }else if(this.getPourcentageDiplome() >= 50 && this.getPourcentageDiplome() <= 80){
                    result = "primary";
                }else if(this.getPourcentageDiplome() >= 25 && this.getPourcentageDiplome() <= 50){
                    result = "warning";
                }else{
                    result = "danger";
                }
                break;

            case 3:
                if(this.getPourcentageExperience() >= 80){
                    result = "success";
                }else if(this.getPourcentageExperience() >= 50 && this.getPourcentageExperience() <= 80){
                    result = "primary";
                }else if(this.getPourcentageExperience() >= 25 && this.getPourcentageExperience() <= 50){
                    result = "warning";
                }else{
                    result = "danger";
                }
                break;

            case 4:
                if(this.getScoreGlobale() >= 80){
                    result = "success";
                }else if(this.getScoreGlobale() >= 50 && this.getScoreGlobale() <= 80){
                    result = "primary";
                }else if(this.getScoreGlobale() >= 25 && this.getScoreGlobale() <= 50){
                    result = "warning";
                }else{
                    result = "danger";
                }
                break;
        
            default:
                break;
        }
        return result;
    }

    public String getTypeArrow(int a){
        String result = "up";
        switch (a) {
            case 1:
                result = this.getPourcentageCompetence() >= 50 ? "up" : "down";
                break;

            case 2:
                result = this.getPourcentageDiplome() >= 50 ? "up" : "down";
                break;

            case 3:
                result = this.getPourcentageExperience() >= 50 ? "up" : "down";
                break;

            case 4:
                result = this.getScoreGlobale() >= 50 ? "up" : "down"; 
                break;
        
            default:
                break;
        }
        return result;
    }
}
