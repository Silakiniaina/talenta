package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Test {
    private int idSimulation;
    private String titre;
    private String description;
    private Timestamp dateCreation;
    private Admin responsable;
    private List<QuestionTest> questions;
    
    // Constructeurs
    public Test() {}
    
    public Test(int idSimulation, String titre, String description, Timestamp dateCreation, int idResponsable) throws SQLException{
        this.setIdSimulation(idSimulation); 
        this.setTitre(titre); 
        this.setDescription(description); 
        this.setDateCreation(dateCreation);
        this.setResponsable(idResponsable);
    }
    

    public static Test getById(Connection conn, int id) throws SQLException {
        boolean isNewConnection = false;
        if(conn == null){
            isNewConnection = true;
            conn = Database.getConnection();
        }
        String query = "SELECT * FROM test WHERE id_test = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Test s =  new Test(
                    rs.getInt("id_test"),
                    rs.getString("titre"),
                    rs.getString("description"),
                    rs.getTimestamp("date_creation"),
                    rs.getInt("id_responsable")
                );
                s.getQuestionSimulation(conn);
                return s;
            }
        }finally{
            if(isNewConnection && conn != null){
                conn.close();
            }
        }
        return null;
    }
    
    public static List<Test> getAll(Connection conn) throws SQLException {
        List<Test> tests = new ArrayList<>();
        String query = "SELECT * FROM test";
        try (Statement stmt = conn.createStatement()) {
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                Test s = new Test(
                    rs.getInt("id_test"),
                    rs.getString("titre"),
                    rs.getString("description"),
                    rs.getTimestamp("date_creation"),
                    rs.getInt("id_responsable")
                );
                s.getQuestionSimulation(conn);
                tests.add(s);
            }
        }
        return tests;
    }
    
    public void save(Connection conn) throws SQLException {
        if (this.idSimulation == 0) {
            // Insert
            String query = "INSERT INTO test (titre, description, id_responsable) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setString(1, this.getTitre());
                pstmt.setString(2, this.getDescription());
                pstmt.setInt(3, this.getResponsable().getIdAdmin());
                pstmt.executeUpdate();
                
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    this.idSimulation = rs.getInt(1);
                }
            }
        } else {
            // Update
            String query = "UPDATE test SET titre = ?, description = ?, id_responsable = ? WHERE id_test = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, this.getTitre());
                pstmt.setString(2, this.getDescription());
                pstmt.setInt(3, this.getResponsable().getIdAdmin());
                pstmt.setInt(4, this.getIdSimulation());
                pstmt.executeUpdate();
            }
        }
    }
    
    public void delete(Connection conn) throws SQLException {
        String query = "DELETE FROM test WHERE id_test = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, this.idSimulation);
            pstmt.executeUpdate();
        }
    }

    // Méthodes DAO
    public void getQuestionSimulation(Connection conn) throws SQLException {
        this.setQuestions(new ArrayList<>());
        String query = "SELECT * FROM question_test WHERE id_test = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, this.getIdSimulation());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                this.getQuestions().add(new QuestionTest(
                    rs.getInt("id_question_test"),
                    this.getIdSimulation(),
                    rs.getString("texte_question")
                ));
            }
        }
    }
    
    // GETTERS AND SETTERS
    public int getIdSimulation() {
        return idSimulation;
    }
    
    public String getTitre() {
        return titre;
    }
    
    public String getDescription() {
        return description;
    }
    
    public Timestamp getDateCreation() {
        return dateCreation;
    }
    
    public Admin getResponsable() {
        return responsable;
    }
    
    public void setIdSimulation(int idSimulation) {
        this.idSimulation = idSimulation;
    }
    
    public void setTitre(String titre) {
        this.titre = titre;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void setDateCreation(Timestamp dateCreation) {
        this.dateCreation = dateCreation;
    }
    
    public void setResponsable(int idResponsable) throws SQLException{
        this.responsable = Admin.getById(idResponsable);
    }

    public List<QuestionTest> getQuestions(){
        return this.questions;
    }

    public void setQuestions(List<QuestionTest> ls){
        this.questions = ls;
    }
}