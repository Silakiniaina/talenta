package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Questionaire {
    
    private int idQuestionaire;
    private String question;
    private int typeQuestion;

    // CONSTRUCTORS
    public Questionaire(){

    }

    public Questionaire(int id, String question){
        this.setIdQuestionaire(id);
        this.setQuestion(question);
    }

    // ACTIONS
    public static List<Questionaire> getAll() throws SQLException{
        List<Questionaire> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM questionaire";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Questionaire d = new Questionaire();
                d.setIdQuestionaire(rs.getInt(1));
                d.setQuestion(rs.getString(2));
                result.add(d);
            }
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    public static List<Questionaire> getQuestionairesTest() throws SQLException{
        List<Questionaire> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM questionaire WHERE id_type_questionaire = ?";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setInt(1, 1);
            
            rs = prstm.executeQuery();

            while (rs.next()) {
                Questionaire d = new Questionaire();
                d.setIdQuestionaire(rs.getInt(1));
                d.setQuestion(rs.getString(2));
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

    public static Questionaire getById(int id) throws SQLException{
        Questionaire result = null;
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM questionaire WHERE id_questionaire = ?";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();

            if(rs.next()) {
                result = new Questionaire();
                result.setIdQuestionaire(rs.getInt(1));
                result.setQuestion(rs.getString(2));
                result.setTypeQuestion(rs.getInt(3));
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
    public void setIdQuestionaire(int idQuestionaire) {
        this.idQuestionaire = idQuestionaire;
    }
    public void setQuestion(String question) {
        this.question = question;
    }
    public void setTypeQuestion(int t){
        this.typeQuestion = t;
    }
    public int getIdQuestionaire() {
        return idQuestionaire;
    }
    public String getQuestion() {
        return question;
    }
    public int getTypeQuestion(){
        return this.typeQuestion;
    }
}
