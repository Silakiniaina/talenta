package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class DemandeDemission {
    
    int id;
    Candidat candidat;
    Date depot;
    String motif;
    String etat;

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
    public String getEtat(){
        return this.etat;
    }
    public void setEtat(){
        this.etat= etat;
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

    public static List<DemandeDemission> getAll() throws SQLException {
        List<DemandeDemission> demandes = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM v_listeDemandeDemission";

        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {

                int idDemande = rs.getInt("id_demande");
                Candidat candidat = Candidat.getById(c, rs.getInt("id_candidat"));
                java.sql.Date dateDepot = rs.getDate("date_depot");
                String motif = rs.getString("motif");

                DemandeDemission demande = new DemandeDemission(candidat, dateDepot, motif);
                demande.setId(idDemande);
                demandes.add(demande);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (prstm != null) prstm.close();
            if (c != null) c.close();
        }

        return demandes;
    }

    public static void updateEtat(int idDemande, String etat) throws SQLException {
        Connection c = null;
        PreparedStatement prstm = null;

        String query = "UPDATE demande_demission SET etat = ? WHERE id_demande = ?";

        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);

            prstm.setString(1, etat); 
            prstm.setInt(2, idDemande); 

            prstm.executeUpdate();
        } catch (SQLException e) {
            throw e;
        } finally {
            if (prstm != null) prstm.close();
            if (c != null) c.close();
        }
    }

}
