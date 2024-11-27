package model;

import java.sql.Connection;
import java.sql.SQLException;

public class ResultatEvalution {
    
    private Candidat candidat; 
    private Recrutement recrutement;
    private double score;

    // CONSTRUCTORS
    public ResultatEvalution(){

    }

    // ACTIONS
    // public static List<ResultatEvalution> getResultatParRecrutement(int idRecrutement) throws Exception{
    //     List<ResultatEvalution> result = new ArrayList<>();
    //     Connection c = null;
    //     PreparedStatement prstm = null; 
    //     ResultSet rs = null;
    //     String query = "SELECT * FROM v_evaluation_test_recrutement WHERE id_recrutement = ?";
    //     try {
    //         c = Database.getConnection();
    //         prstm = c.prepareStatement(query);
    //         prstm.setInt(1, idRecrutement);
    //         rs = prstm.executeQuery();

    //         while (rs.next()) {
    //             ResultatEvalution d = new ResultatEvalution();
    //             d.setCandidat(c, idRecrutement);
    //             result.add(d);
    //         }
    //     } catch (SQLException e) {
    //         throw e;
    //     }finally{
    //         if(rs != null){
    //             rs.close();
    //         }
    //         if(prstm != null){
    //             prstm.close();
    //         }
    //         if(c != null){
    //             c.close();
    //         }
    //     }
    //     return result;
    // }

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


    public void setCandidat(Connection con, int candidat) throws SQLException{
        Candidat c = new Candidat();
        c.setIdCandidat(candidat);
        this.candidat = c.getById(con);
    }
    public void setScore(double score) {
        this.score = score;
    }
    public void setRecrutement(Connection c,int idrecrutement) throws SQLException{
        Recrutement r = new Recrutement();
        r.setIdRecrutement(idrecrutement);

        this.recrutement = r.getById(c);
    }
}
