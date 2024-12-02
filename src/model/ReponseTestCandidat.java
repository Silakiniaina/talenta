package model;

import java.sql.Timestamp;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.utils.Database;

public class ReponseTestCandidat {
    private int idReponse;
    private AttributionTest attributionTest;
    private QuestionTest question; 
    private ReponseTestPossible reponse;
    private Timestamp dateSoumission;

    // CONSTRUCTORS
    public ReponseTestCandidat(){

    }

    // ACTION
    public void insert(Connection c) throws SQLException{
        PreparedStatement prstm = null;
        boolean isNewConnection = false;
        String query = "INSERT INTO reponse_test_candidat(id_attribution,id_question, id_reponse_candidat) VALUES (?, ?, ?)";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getAttributionTest().getIdAttribution());
            prstm.setInt(2, this.getQuestion().getIdQuestionTest());
            prstm.setInt(3, this.getReponse().getIdReponseTestPossible());
            prstm.executeUpdate();
            
            c.commit();
        } catch (SQLException e) {
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

    public int getIdReponse() {
        return idReponse;
    }

    public AttributionTest getAttributionTest() {
        return attributionTest;
    }

    public QuestionTest getQuestion() {
        return question;
    }

    public ReponseTestPossible getReponse() {
        return reponse;
    }

    public Timestamp getDateSoumission() {
        return dateSoumission;
    }


    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public void setAttributionTest(Connection c,int attributionTest) throws SQLException{
        AttributionTest a = new AttributionTest();
        this.attributionTest = a.getById(c, attributionTest);
    }

    public void setQuestion(Connection c, int question) throws SQLException{
        QuestionTest q = new QuestionTest();
        this.question = q.getById(c, question);
    }

    public void setReponse(Connection c, int reponse) throws SQLException{
        ReponseTestPossible r  = new ReponseTestPossible();
        this.reponse = r.getById(c, reponse);
    }

    public void setDateSoumission(Timestamp dateSoumission) {
        this.dateSoumission = dateSoumission;
    }

    // GETTERS AND SETTERS
}
