package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class QuestionTest {

    private int idQuestionTest;
    private int idTest;
    private String texteQuestion;
    private List<ReponseTestPossible> reponsePossible;
    
    // Constructeurs
    public QuestionTest() {}
    
    public QuestionTest(int idQuestionTest, int idTest, String texteQuestion) throws SQLException{
        this.setIdQuestionTest(idQuestionTest); 
        this.setIdTest(idTest);
        this.setTexteQuestion(texteQuestion);
    }

    public void getReponsesPossibles(Connection conn) throws SQLException{
        this.setReponsePossible(new ArrayList<>());
        String query = "SELECT * FROM reponse_test_possibles WHERE id_question_test = ?"; 
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, this.getIdQuestionTest());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                this.getReponsePossible().add(new ReponseTestPossible(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3)
                ));
            }
        }
    }
    
    public void insert(Connection con) throws SQLException {
        PreparedStatement prst = null; 
        boolean isNewConnection = false;
        String query = "INSERT INTO question_test (id_test, texte_question) VALUES (?, ?)";

        try {
            if(con == null){
                isNewConnection = true;
                con = Database.getConnection();
            }

            con.setAutoCommit(false);

            prst = con.prepareStatement(query);
            prst.setInt(1,this.getIdTest());
            prst.setString(2, this.getTexteQuestion());

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

    public QuestionTest getById(Connection c, int id) throws SQLException{
        PreparedStatement prstm = null;
        boolean isNewConnection = false; 
        ResultSet rs = null;
        String query = "SELECT * FROM question_test WHERE id_question_test = ?";
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();

            if(rs.next()) {
                this.setIdQuestionTest(rs.getInt(1));
                this.setIdTest(rs.getInt(2));
                this.setTexteQuestion(rs.getString(3));
                this.fetchListReponse(c);
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

    public void fetchListReponse(Connection c) throws SQLException{
        List<ReponseTestPossible> result = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT id_reponse_test_possibles FROM reponse_test_possibles WHERE id_question_test = ?";
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getIdQuestionTest());
            rs = prstm.executeQuery();

            while (rs.next()) {
                ReponseTestPossible cd = new ReponseTestPossible();
                result.add(cd.getById(c, rs.getInt(1)));
            }
            this.setReponsePossible(result);
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

    public int getIdQuestionTest() {
        return idQuestionTest;
    }

    public int getIdTest() {
        return idTest;
    }

    public String getTexteQuestion() {
        return texteQuestion;
    }

    public void setIdQuestionTest(int idQuestionTest) {
        this.idQuestionTest = idQuestionTest;
    }

    public void setIdTest(int idTest) throws SQLException{
        this.idTest = idTest;
    }

    public void setTexteQuestion(String texteQuestion) {
        this.texteQuestion = texteQuestion;
    }

    public void setReponsePossible(List<ReponseTestPossible> ls){
        this.reponsePossible = ls;
    }

    public List<ReponseTestPossible> getReponsePossible(){
        return this.reponsePossible;
    }
}