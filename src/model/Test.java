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
    private int idTest;
    private String titre;
    private String description;
    private Timestamp dateCreation;
    private Admin responsable;
    private List<QuestionTest> questions;

    // Constructeurs
    public Test() {
    }

    public Test(int idTest, String titre, String description, Timestamp dateCreation, int idResponsable)
            throws SQLException {
        this.setIdTest(idTest);
        this.setTitre(titre);
        this.setDescription(description);
        this.setDateCreation(dateCreation);
        this.setResponsable(idResponsable);
    }

    public static Test getById(Connection conn, int id) throws SQLException {
        boolean isNewConnection = false;
        if (conn == null) {
            isNewConnection = true;
            conn = Database.getConnection();
        }
        String query = "SELECT * FROM test WHERE id_test = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Test s = new Test(
                        rs.getInt("id_test"),
                        rs.getString("titre"),
                        rs.getString("description"),
                        rs.getTimestamp("date_creation"),
                        rs.getInt("id_responsable"));
                s.getQuestionSimulation(conn);
                return s;
            }
        } finally {
            if (isNewConnection && conn != null) {
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
                        rs.getInt("id_responsable"));
                s.getQuestionSimulation(conn);
                tests.add(s);
            }
        }
        return tests;
    }

    public void insert(Connection conn) throws SQLException {
        PreparedStatement prstm = null;
        boolean isNewConnection = false;
        String query = "INSERT INTO test (titre, description, id_responsable, date_creation) VALUES (?, ?, ?, ?)";
        try {
            if (conn == null) {
                conn = Database.getConnection();
                isNewConnection = true;
            }
            conn.setAutoCommit(false);
            prstm = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prstm.setString(1, this.getTitre());
            prstm.setString(2, this.getDescription());
            prstm.setInt(3, this.getResponsable().getIdAdmin());
            prstm.setTimestamp(4, this.getDateCreation());
            prstm.executeUpdate();

            conn.commit();
        } catch (SQLException e) {
            conn.rollback();
            throw e;
        } finally {
            if (prstm != null) {
                prstm.close();
            }
            if (conn != null && isNewConnection) {
                conn.close();
            }
        }
    }

    public void delete(Connection conn) throws SQLException {
        String query = "DELETE FROM test WHERE id_test = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, this.idTest);
            pstmt.executeUpdate();
        }
    }

    // Méthodes DAO
    public void getQuestionSimulation(Connection conn) throws SQLException {
        this.setQuestions(new ArrayList<>());
        String query = "SELECT id_question_test FROM question_test WHERE id_test = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, this.getIdTest());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                QuestionTest qt = new QuestionTest();
                this.getQuestions().add(qt.getById(conn, rs.getInt(1)));
            }
        }
    }

    // GETTERS AND SETTERS
    public int getIdTest() {
        return idTest;
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

    public void setIdTest(int idTest) {
        this.idTest = idTest;
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

    public void setResponsable(int idResponsable) throws SQLException {
        this.responsable = Admin.getById(idResponsable);
    }

    public List<QuestionTest> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<QuestionTest> ls) {
        this.questions = ls;
    }
}