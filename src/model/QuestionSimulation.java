package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.utils.Database;

public class QuestionSimulation {

    private int idQuestionSimulation;
    private int idSimulation;
    private String texteQuestion;
    private List<ReponseSimulationPossible> reponsePossible;
    
    // Constructeurs
    public QuestionSimulation() {}
    
    public QuestionSimulation(int idQuestionSimulation, int idSimulation, String texteQuestion) throws SQLException{
        this.setIdQuestionSimulation(idQuestionSimulation); 
        this.setIdSimulation(idSimulation);
        this.setTexteQuestion(texteQuestion);
    }

    public void getReponsesPossibles(Connection conn) throws SQLException{
        this.setReponsePossible(new ArrayList<>());
        String query = "SELECT * FROM reponse_simulation_possibles WHERE id_question_simulation = ?"; 
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, this.getIdQuestionSimulation());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                this.getReponsePossible().add(new ReponseSimulationPossible(
                    rs.getInt(1),
                    rs.getInt(2),
                    rs.getString(3)
                ));
            }
        }
    }
    
    public void save(Connection conn) throws SQLException {
        if (this.idQuestionSimulation == 0) {
            String query = "INSERT INTO question_simulation (id_simulation, texte_question) VALUES (?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, this.getIdSimulation());
                pstmt.setString(2, this.getTexteQuestion());
                pstmt.executeUpdate();
                
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    this.idQuestionSimulation = rs.getInt(1);
                }
            }
        } else {
            String query = "UPDATE question_simulation SET texte_question = ? WHERE id_question_simulation = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, this.getTexteQuestion());
                pstmt.setInt(2, this.getIdQuestionSimulation());
                pstmt.executeUpdate();
            }
        }
    }

    public int getIdQuestionSimulation() {
        return idQuestionSimulation;
    }

    public int getIdSimulation() {
        return idSimulation;
    }

    public String getTexteQuestion() {
        return texteQuestion;
    }

    public void setIdQuestionSimulation(int idQuestionSimulation) {
        this.idQuestionSimulation = idQuestionSimulation;
    }

    public void setIdSimulation(int idSimulation) throws SQLException{
        this.idSimulation = idSimulation;
    }

    public void setTexteQuestion(String texteQuestion) {
        this.texteQuestion = texteQuestion;
    }

    public void setReponsePossible(List<ReponseSimulationPossible> ls){
        this.reponsePossible = ls;
    }

    public List<ReponseSimulationPossible> getReponsePossible(){
        return this.reponsePossible;
    }
}