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
    Date preavis;
    Date dateFin;

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
    public Date getPreavis() {
        return preavis;
    }
    public void setPreavis(Date preavis) {
        this.preavis = preavis;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public FinContrat (Connection con, int idEmploye, int idType, String motif, Date preavis, Date fin) throws SQLException{
        this.setEmploye(con, idEmploye);
        this.setType(idType);
        this.setMotif(motif);
        this.setPreavis(preavis);
        this.setDateFin(fin);
    }

    public void insert(Connection conn) throws SQLException {
        String query = "INSERT INTO fin_contrat (id_employe, id_type_fin_contrat, motif, date_preavis, date_fin) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement prstm = conn.prepareStatement(query)) {
            prstm.setInt(1, this.employe.getIdEmploye());
            prstm.setInt(2, this.type.getId());
            prstm.setString(3, this.motif);
            prstm.setDate(4, this.preavis);
            prstm.setDate(5, this.dateFin);

            prstm.executeUpdate();
        } catch (SQLException e) {
            throw e;
        }
    }
    

}
