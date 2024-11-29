package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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
    
    public void save(Connection conn) throws SQLException {
        if (this.idQuestionTest == 0) {
            String query = "INSERT INTO question_test (id_test, texte_question) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, this.getIdTest());
                pstmt.setString(2, this.getTexteQuestion());
                pstmt.executeUpdate();
                
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    this.idQuestionTest = rs.getInt(1);
                }
            }
        } else {
            String query = "UPDATE question_test SET texte_question = ? WHERE id_question_test = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, this.getTexteQuestion());
                pstmt.setInt(2, this.getIdQuestionTest());
                pstmt.executeUpdate();
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