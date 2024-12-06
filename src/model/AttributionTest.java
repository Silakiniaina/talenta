package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import model.utils.Database;

public class AttributionTest {

    private int idAttribution;
    private Test test;
    private Recrutement recrutement;
    private Candidat candidat;
    private Status status;

    // CONSTRUCTORS
    public AttributionTest() {

    }

    public AttributionTest getById(Connection c, int id) throws SQLException {
        PreparedStatement prstm = null;
        ResultSet rs = null;
        boolean isNewConnection = false;
        String sql = "SELECT * FROM test_candidat WHERE id_attribution = ?";
        try {
            if (c == null) {
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setInt(1, id);

            rs = prstm.executeQuery();
            if (rs.next()) {
                this.setIdAttribution(rs.getInt(1));
                this.setTest(c, rs.getInt(2));
                this.setRecrutement(c, rs.getInt(3));
                this.setCandidat(c, rs.getInt(4));
                this.setStatus(c, rs.getInt(5));
            }

            return this;
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (prstm != null) {
                prstm.close();
            }
            if (c != null && isNewConnection) {
                c.close();
            }
        }
    }

    public AttributionTest getByCandidatAndTest(Connection c, Candidat candidat, Test t) throws SQLException {
        PreparedStatement prstm = null;
        ResultSet rs = null;
        boolean isNewConnection = false;
        String sql = "SELECT * FROM test_candidat WHERE id_test = ? AND id_candidat = ?";
        try {
            if (c == null) {
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setInt(1, t.getIdTest());
            prstm.setInt(2, candidat.getIdCandidat());

            rs = prstm.executeQuery();
            if (rs.next()) {
                this.setIdAttribution(rs.getInt(1));
                this.setTest(c, rs.getInt(2));
                this.setRecrutement(c, rs.getInt(3));
                this.setCandidat(c, rs.getInt(4));
                this.setStatus(c, rs.getInt(5));
            }

            return this;
        } catch (Exception e) {
            throw e;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (prstm != null) {
                prstm.close();
            }
            if (c != null && isNewConnection) {
                c.close();
            }
        }
    }

    public void insert(Connection c) throws SQLException {
        PreparedStatement prstm = null;
        boolean isNewConnection = false;
        String query = "INSERT INTO test_candidat(id_test,id_candidat,id_recrutement, id_status) VALUES (?, ?, ?, ?)";
        try {
            if (c == null) {
                c = Database.getConnection();
                isNewConnection = true;
            }

            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getTest().getIdTest());
            prstm.setInt(2, this.getCandidat().getIdCandidat());
            prstm.setInt(3,this.getRecrutement().getIdRecrutement());
            prstm.setInt(4, this.getStatus().getIdStatus());
            prstm.executeUpdate();

            c.commit();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        } finally {
            if (prstm != null) {
                prstm.close();
            }
            if (c != null && isNewConnection) {
                c.close();
            }
        }
    }

    // GETTERS AND SETTERS
    public int getIdAttribution() {
        return idAttribution;
    }

    public Test getTest() {
        return test;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public Status getStatus() {
        return status;
    }

    public Recrutement getRecrutement(){
        return this.recrutement;
    }

    public void setIdAttribution(int idAttribution) {
        this.idAttribution = idAttribution;
    }

    public void setTest(Connection c, int test) throws SQLException {
        Test t = new Test();
        this.test = t.getById(c, test);
    }

    public void setCandidat(Connection c, int candidat) throws SQLException {
        Candidat ca = new Candidat();
        this.candidat = ca.getById(c, candidat);
    }

    public void setStatus(Connection c, int status) throws SQLException {
        Status s = new Status();
        this.status = s.getById(c, status);
    }

    public void setRecrutement(Connection c, int recrutement) throws SQLException{
        Recrutement r = new Recrutement();
        r.setIdRecrutement(recrutement);
        this.recrutement = r.getById(c);
    }

    public void setRecrutement(Recrutement r){
        this.recrutement = r;
    }

    public static void main(String[] args) {
        try {
            Connection c = Database.getConnection();

            Candidat candidat = new Candidat();
            Test t = new Test();

            candidat = candidat.getById(c, 1);
            t = t.getById(c, 2);

            AttributionTest a = new AttributionTest();

            a = a.getByCandidatAndTest(c, candidat, t);

            System.out.println(new Gson().toJson(a));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
