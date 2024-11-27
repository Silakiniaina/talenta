package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import model.utils.Database;

public class PreselectionCandidat {
    
    private Candidat candidat;
    private Recrutement recrutement;
    private double pourcentageCompetence;
    private double pourcentageExperience;
    private double pourcentageDiplome;
    private double scoreGlobale;
    private boolean prechosen;

    // CONSTRUCTORS
    public PreselectionCandidat(){

    }


    // ACTIONS 
    public static HashMap<String,List<PreselectionCandidat>> getPreselectionByRecrutement(Connection c , int idRecrutement) throws SQLException{
        List<PreselectionCandidat> result = new ArrayList<>();
        HashMap<String,List<PreselectionCandidat>> res = new HashMap<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM v_filtre_cv WHERE id_recrutement = ?";
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, idRecrutement);
            rs = prstm.executeQuery();

            while (rs.next()) {
                PreselectionCandidat d = new PreselectionCandidat();
                d.setCandidat(c,rs.getInt(1));
                d.setRecrutement(c,rs.getInt(2));
                d.setPourcentageCompetence(rs.getDouble(4));
                d.setPourcentageDiplome(rs.getDouble(5));
                d.setPourcentageExperience(rs.getDouble(6));
                d.setScoreGlobale(rs.getDouble(7));
                d.setPrechosen(rs.getBoolean(8));
                result.add(d);
            }
            List<PreselectionCandidat> preselection = new ArrayList<>();
            for(PreselectionCandidat p : result){
                if(p.isPrechosen()){
                    preselection.add(p);
                }
            }

            result.removeAll(preselection);

            res.put("preselectionne", preselection);
            res.put("normal", result);

            return res;
        } catch (SQLException e) {
            throw e;
        }finally{
            if(rs != null){
                rs.close();
            }
            if(prstm != null){
                prstm.close();
            }
            if(c != null && isNewConnection){
                c.close();
            }
        }
    }

    public void preselectionner(Connection c) throws SQLException{
        PreparedStatement prstm = null; 
        String sql  = "UPDATE recrutement_candidat SET is_prechosen = true WHERE id_recrutement = ? AND id_candidat = ?";
        boolean isNewConnection = false;
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            
            prstm = c.prepareStatement(sql);
            prstm.setInt(1, this.getRecrutement().getIdRecrutement());
            prstm.setInt(2, this.getCandidat().getIdCandidat());
            prstm.executeUpdate();

            c.commit();

        } catch (Exception e) {
            c.rollback();
            throw e;
        }finally{
            if(prstm != null){
                prstm.close();
            }
            if(c != null && isNewConnection){
                c.close();
            }
        }
    }
    public void depreselectionner(Connection c) throws SQLException{
        PreparedStatement prstm = null; 
        String sql  = "UPDATE recrutement_candidat SET is_prechosen = false WHERE id_recrutement = ? AND id_candidat = ?";
        boolean isNewConnection = false;
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            
            prstm = c.prepareStatement(sql);
            prstm.setInt(1, this.getRecrutement().getIdRecrutement());
            prstm.setInt(2, this.getCandidat().getIdCandidat());
            prstm.executeUpdate();

            c.commit();

        } catch (Exception e) {
            c.rollback();
            throw e;
        }finally{
            if(prstm != null){
                prstm.close();
            }
            if(c != null && isNewConnection){
                c.close();
            }
        }
    }

    public void eliminer(Connection c)throws SQLException{
        PreparedStatement prstm = null; 
        String sql  = "DELETE FROM recrutement_candidat WHERE id_recrutement = ? AND id_candidat = ?";
        boolean isNewConnection = false;
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            c.setAutoCommit(false);
            
            prstm = c.prepareStatement(sql);
            prstm.setInt(1, this.getRecrutement().getIdRecrutement());
            prstm.setInt(2, this.getCandidat().getIdCandidat());
            prstm.executeUpdate();

            c.commit();

        } catch (Exception e) {
            c.rollback();
            throw e;
        }finally{
            if(prstm != null){
                prstm.close();
            }
            if(c != null && isNewConnection){
                c.close();
            }
        }
    }


    public static PreselectionCandidat getByCandidatAndRecrutement(Connection c, int idCandidat, int idRecrutement) throws SQLException{
        PreselectionCandidat result = null; 
        PreparedStatement prtm = null; 
        ResultSet rs = null; 
        String sql = "SELECT * FROM v_filtre_cv WHERE id_recrutement = ? AND id_candidat = ?";
        boolean isNewConnection = false; 
        
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection  = true;
            }

            prtm = c.prepareStatement(sql);
            prtm.setInt(1, idRecrutement);
            prtm.setInt(2 ,idCandidat);

            rs = prtm.executeQuery();

            if(rs.next()){
                result = new PreselectionCandidat();
                result.setCandidat(c,rs.getInt(1));
                result.setRecrutement(c,rs.getInt(2));
                result.setPourcentageCompetence(rs.getDouble(4));
                result.setPourcentageDiplome(rs.getDouble(5));
                result.setPourcentageExperience(rs.getDouble(6));
                result.setScoreGlobale(rs.getDouble(7));
                result.setPrechosen(rs.getBoolean(8));
            }
            return result;
        } catch (Exception e) {
            throw e;
        }finally{
            if(rs != null){
                rs.close();
            }
            if(prtm != null){
                prtm.close();
            }
            if(c != null && isNewConnection){
                c.close();
            }
        }

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
    public boolean isPrechosen() {
        return prechosen;
    }

    public void setCandidat(Connection con, int candidat) throws SQLException{
        Candidat c = new Candidat();
        c.setIdCandidat(candidat);
        this.candidat = c.getById(con);
    }
    public void setRecrutement(Connection c,int idrecrutement) throws SQLException{
        Recrutement r = new Recrutement();
        r.setIdRecrutement(idrecrutement);

        this.recrutement = r.getById(c);
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
    public void setPrechosen(boolean prechosen) {
        this.prechosen = prechosen;
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
