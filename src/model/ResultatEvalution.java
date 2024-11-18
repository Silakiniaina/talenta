package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class ResultatEvalution {
    
    private Candidat candidat; 
    private Recrutement recrutement;
    private double score;

    // CONSTRUCTORS
    public ResultatEvalution(){

    }

    public ResultatEvalution(int idRecrutement, int idCandidat, double score) throws Exception{
        this.setRecrutement(idRecrutement);
        this.setCandidat(idCandidat);
        this.setScore(score);
    }

    // ACTIONS
    public static List<ResultatEvalution> getResultatParRecrutement(int idRecrutement) throws Exception{
        List<ResultatEvalution> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM v_evaluation_test_recrutement WHERE id_recrutement = ?";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setInt(1, idRecrutement);
            rs = prstm.executeQuery();

            while (rs.next()) {
                ResultatEvalution d = new ResultatEvalution(
                    rs.getInt(1), 
                    rs.getInt(2), 
                    rs.getDouble(3)
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

    // GETTERS AND SETTERS
    public Candidat getCandidat() {
        return candidat;
    }
    public double getScore() {
        return score;
    }
    public Recrutement getRecrutement(){
        return this.recrutement;
    }


    public void setCandidat(int candidat)throws Exception {
        this.candidat = Candidat.getById(candidat);
    }
    public void setScore(double score) {
        this.score = score;
    }
    public void setRecrutement(int recrutement) throws SQLException{
        this.recrutement = Recrutement.getById(recrutement);
    }
}
