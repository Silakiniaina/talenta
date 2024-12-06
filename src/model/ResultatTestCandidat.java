package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class ResultatTestCandidat {
    
    private Recrutement recrutement;
    private Candidat candidat; 
    private int nombreReponseFausse;
    private int nombreReponseCorrecte;
    private int nombreTotalQuestion;
    private double pourcentageReussite;
    private String status;

    // ACTION
    public static List<ResultatTestCandidat> getResultatByRecrutement(Connection c, int idRecrutement)throws SQLException{
        List<ResultatTestCandidat> result = new ArrayList<>();
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        boolean isNewConnection = false;
        String sql = "SELECT * FROM resultat_test_candidat WHERE id_recrutement = ?";
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setInt(1, idRecrutement);

            rs = prstm.executeQuery();

            while (rs.next()) {
                ResultatTestCandidat r = new ResultatTestCandidat();
                r.setRecrutement(c, rs.getInt(1));
                r.setCandidat(c, rs.getInt(2));
                r.setNombreReponseFausse(rs.getInt(3));
                r.setNombreReponseCorrecte(rs.getInt(4));
                r.setNombreTotalQuestion(rs.getInt(5));
                r.setPourcentageReussite(rs.getDouble(6));
                r.setStatus(rs.getString(7));

                result.add(r);
            }

            return result;
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

    // GETTERS AND SETTERS
    public Recrutement getRecrutement() {
        return recrutement;
    }
    public Candidat getCandidat() {
        return candidat;
    }
    public int getNombreReponseFausse() {
        return nombreReponseFausse;
    }
    public int getNombreReponseCorrecte() {
        return nombreReponseCorrecte;
    }
    public int getNombreTotalQuestion() {
        return nombreTotalQuestion;
    }
    public double getPourcentageReussite() {
        return pourcentageReussite;
    }
    public String getStatus() {
        return status;
    }

    public void setRecrutement(Connection c,int recrutement) throws SQLException{
        Recrutement r = new Recrutement();
        r.setIdRecrutement(recrutement);
        this.recrutement = r.getById(c);
    }
    public void setCandidat(Connection c, int candidat) throws SQLException{
        Candidat can = new Candidat();
        this.candidat = can.getById(c, candidat);
    }
    public void setNombreReponseFausse(int nombreReponseFausse) {
        this.nombreReponseFausse = nombreReponseFausse;
    }
    public void setNombreReponseCorrecte(int nombreReponseCorrecte) {
        this.nombreReponseCorrecte = nombreReponseCorrecte;
    }
    public void setNombreTotalQuestion(int nombreTotalQuestion) {
        this.nombreTotalQuestion = nombreTotalQuestion;
    }
    public void setPourcentageReussite(double pourcentageReussite) {
        this.pourcentageReussite = pourcentageReussite;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
