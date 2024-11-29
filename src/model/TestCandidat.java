package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestCandidat {
    
    private int idAttribution;
    private Test test;
    private Candidat candidat;
    private Status status;
    
    // Constructeurs
    public TestCandidat() {}

    
    // Méthodes DAO
    // public static List<SimulationCandidat> getByCandidat(Connection conn, int candidatId) throws SQLException {
    //     List<SimulationCandidat> tests = new ArrayList<>();
    //     String query = "SELECT * FROM test_candidat WHERE id_candidat = ?";
    //     try (PreparedStatement pstmt = conn.prepareStatement(query)) {
    //         pstmt.setInt(1, candidatId);
    //         ResultSet rs = pstmt.executeQuery();
    //         while (rs.next()) {
    //             SimulationCandidat s = new SimulationCandidat();
    //             s.setIdAttribution(rs.getInt("id_attribution"));
    //             s.setSimulation(rs.getInt("id_test"));
    //             s.setCandidat(conn, candidatId);
    //             tests.add(new SimulationCandidat(
    //                 ,
    //                 ,
    //                 rs.getInt("id_candidat"),
    //                 rs.getInt("id_status")
    //             ));
    //         }
    //     }
    //     return tests;
    // }
    
    public void save(Connection conn) throws SQLException {
        if (this.idAttribution == 0) {
            String query = "INSERT INTO test_candidat (id_test, id_candidat, id_status) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                pstmt.setInt(1, this.getSimulation().getIdSimulation());
                pstmt.setInt(2, this.getCandidat().getIdCandidat());
                pstmt.setInt(3, this.getStatus().getIdStatus());
                pstmt.executeUpdate();
                
                ResultSet rs = pstmt.getGeneratedKeys();
                if (rs.next()) {
                    this.idAttribution = rs.getInt(1);
                }
            }
        } else {
            String query = "UPDATE test_candidat SET id_status = ? WHERE id_attribution = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, this.getStatus().getIdStatus());
                pstmt.setInt(2, this.getIdAttribution());
                pstmt.executeUpdate();
            }
        }
    }

    public int getIdAttribution() {
        return idAttribution;
    }

    public Test getSimulation() {
        return test;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public Status getStatus() {
        return status;
    }

    public void setIdAttribution(int idAttribution) {
        this.idAttribution = idAttribution;
    }

    public void setSimulation(int test) throws SQLException{
        this.test = Test.getById(null, test);
    }

    public void setCandidat(Connection con, int candidat) throws SQLException{
        Candidat c = new Candidat();
        this.candidat = c.getById(con, candidat);
    }

    public void setStatus(Connection c, int status) throws SQLException{
        Status s = new Status();
        this.status = s.getById(c,status);
    }
}
