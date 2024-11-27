package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.utils.Database;

public class ReponseQuestionnaire {
    
    private Questionaire questionaire;
    private Candidat candidat; 
    private int reponse;

    // CONSTRUCTORS
    public ReponseQuestionnaire(){

    }

    // ACTION
    public void insert() throws SQLException{
        Connection c = null; 
        PreparedStatement prstm = null;
        String query = "INSERT INTO reponse_test(id_candidat, id_questionaire, reponse) VALUES (?, ?, ?)";
        try {
            c = Database.getConnection();
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getCandidat().getIdCandidat());
            prstm.setInt(2, this.getQuestionaire().getIdQuestionaire());
            prstm.setInt(3, this.getReponse());
            prstm.executeUpdate();
            
            c.commit();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        }finally{
            if(prstm != null){
                prstm.close();
            }
            if(c != null){
                c.close();
            }
        }
    }

    // GETTERS AND SETTERS
    public void setQuestionaire(int questionaire) throws SQLException{
        this.questionaire = Questionaire.getById(questionaire);
    }
    public void setCandidat(Connection con, int candidat) throws SQLException{
        Candidat c = new Candidat();
        c.setIdCandidat(candidat);
        this.candidat = c.getById(con);
    }
    public void setReponse(int reponse) {
        this.reponse = reponse;
    }
    public Questionaire getQuestionaire() {
        return questionaire;
    }
    public Candidat getCandidat() {
        return candidat;
    }
    public int getReponse() {
        return reponse;
    }
}
