package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.utils.Database;

public class ReponseTestPossible {
    
    private int idQuestionTest;
    private int idReponseTestPossible;
    private String texteReponse;

    // CONSTRUCTOR
    public ReponseTestPossible(int idReponseTestPossible, int idQuestionTest, String txt){
        this.setIdQuestionTest(idQuestionTest);
        this.setIdReponseTestPossible(idReponseTestPossible);
        this.setTexteReponse(txt);
    }
    public ReponseTestPossible(){

    }

    public void insert(Connection con) throws SQLException {
        PreparedStatement prst = null; 
        boolean isNewConnection = false;
        String query = "INSERT INTO reponse_test_possibles (id_question_test, texte_reponse) VALUES (?, ?)";

        try {
            if(con == null){
                isNewConnection = true;
                con = Database.getConnection();
            }

            con.setAutoCommit(false);

            prst = con.prepareStatement(query);
            prst.setInt(1,this.getIdQuestionTest());
            prst.setString(2, this.getTexteReponse());

            prst.executeUpdate();

            con.commit();
        } catch (SQLException e) {
            con.rollback();
            throw e;
        } finally{
            if(prst != null){
                prst.close();
            }
            if(con != null && isNewConnection){
                con.close();
            }
        }
    }

    public ReponseTestPossible getById(Connection c, int id) throws SQLException{
        PreparedStatement prstm = null;
        boolean isNewConnection = false; 
        ResultSet rs = null;
        String query = "SELECT * FROM reponse_test_possibles WHERE id_reponse_test_possibles = ?";
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();

            if(rs.next()) {
                this.setIdReponseTestPossible(rs.getInt(1));
                this.setIdQuestionTest(rs.getInt(2));
                this.setTexteReponse(rs.getString(3));
            }
            return this;
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
    public void setIdQuestionTest(int idQuestionTest) {
        this.idQuestionTest = idQuestionTest;
    }
    public void setIdReponseTestPossible(int idReponseTestPossible) {
        this.idReponseTestPossible = idReponseTestPossible;
    }
    public void setTexteReponse(String texteReponse) {
        this.texteReponse = texteReponse;
    }
    public int getIdQuestionTest() {
        return idQuestionTest;
    }
    public int getIdReponseTestPossible() {
        return idReponseTestPossible;
    }
    public String getTexteReponse() {
        return texteReponse;
    }

    
}
