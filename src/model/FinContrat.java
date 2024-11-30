package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FinContrat {
    
    int idFinContrat;
    Employe employe;
    TypeFinContrat type;
    String motif;
    Date dateDepot;

    public int getIdFinContrat() {
        return idFinContrat;
    }
    public void setIdFinContrat(int idFinContrat) {
        this.idFinContrat = idFinContrat;
    }
    public Employe getEmploye() {
        return employe;
    }
    public void setEmploye(Connection conn, int idEmploye) throws SQLException {
        this.employe= Employe.getById(conn, idEmploye);
    }
    public TypeFinContrat getType() {
        return type;
    }
    public void setType(int idType) throws SQLException {
        this.type = TypeFinContrat.getById(idType);
    }
    public String getMotif() {
        return motif;
    }
    public void setMotif(String motif) {
        this.motif = motif;
    }
    public Date getDateDepot() {
        return dateDepot;
    }
    public void setDateDepot(Date DateDepot) {
        this.dateDepot = DateDepot;
    }

    public FinContrat (Connection con, int idEmploye, int idType, String motif, Date depot) throws SQLException{
        this.setEmploye(con, idEmploye);
        this.setType(idType);
        this.setMotif(motif);
        this.setDateDepot(depot);
    }

    public void insert(Connection conn) throws SQLException {
        String query = "INSERT INTO fin_contrat (id_employe, id_type_fin_contrat, motif, date_depot) VALUES (?, ?, ?, ?)";

        try (PreparedStatement prstm = conn.prepareStatement(query)) {
            prstm.setInt(1, this.employe.getIdEmploye());
            prstm.setInt(2, this.type.getId());
            prstm.setString(3, this.motif);
            prstm.setDate(4, this.dateDepot);

            prstm.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    

}
