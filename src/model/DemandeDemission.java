package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import model.utils.Database;

public class DemandeDemission {
    
    int id;
    Candidat candidat;
    Date depot;
    String motif;

    public DemandeDemission(Candidat candidat, Date depot, String motif) {
        this.candidat = candidat;
        this.depot = depot;
        this.motif = motif;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public Candidat getCandidat() {
        return candidat;
    }
    public void setCandidat(Candidat candidat) {
        this.candidat = candidat;
    }
    public Date getDepot() {
        return depot;
    }
    public void setDepot(Date depot) {
        this.depot = depot;
    }
    public String getMotif() {
        return motif;
    }
    public void setMotif(String motif) {
        this.motif = motif;
    }

    public void insert() throws SQLException {
        Connection c = null;
        PreparedStatement prstm = null;

        String query = "INSERT INTO demande_demission (id_candidat, date_depot, motif) VALUES (?, ?, ?)";

        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);

            prstm.setInt(1, this.getCandidat().getIdCandidat()); 
            prstm.setDate(2, this.getDepot()); 
            prstm.setString(3, this.getMotif()); 

            prstm.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (prstm != null) prstm.close();
            if (c != null) c.close();
        }
    }

}
