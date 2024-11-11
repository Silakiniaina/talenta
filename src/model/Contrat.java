package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Contrat {
    
    int id;
    Personne personne;
    Poste poste;
    Date dateDebut;
    Date dateFin;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public Personne getPersonne() {
        return personne;
    }
    public void setPersonne(int id) throws SQLException {
        this.personne = Personne.getById(id);
    }

    public Poste getPoste() {
        return poste;
    }
    public void setPoste(int id) throws SQLException {
        this.poste = Poste.getById(id);
    }

    public Date getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(Date DateDebut) {
        this.dateDebut = DateDebut;
    }

    public Date getDateFin() {
        return dateFin;
    }
    public void setDateFin(Date DateFin) {
        this.dateFin = DateFin;
    }

    public Contrat (int id, int idPersonne, int idPoste, Date debut, Date fin) throws SQLException{
        this.setId(id);
        this.setPersonne(idPersonne);
        this.setPoste(idPoste);
        this.setDateDebut(debut);
        this.setDateFin(fin);
    }
    
    public void insert() throws SQLException {
        Connection connex = null;
        PreparedStatement st = null;

        try {
            connex = Database.getConnection();

            String sql = "INSERT INTO Contrat (id_personne, id_poste, date_debut, date_fin) VALUES (?, ?, ?, ?)";
            st = connex.prepareStatement(sql);
            st.setInt(1, this.personne.getId());
            st.setInt(2, this.poste.getId());
            st.setDate(3, this.dateDebut);
            st.setDate(4, this.dateFin);

            st.executeUpdate();
            System.out.println("Insertion réussie du contrat");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (st != null) st.close();
            if (connex != null) connex.close();
        }
    }

    public static List<Contrat> getAll() throws SQLException {
        List<Contrat> contrats = new ArrayList<>();
        Connection connex = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            connex = Database.getConnection();
            String sql = "SELECT * FROM Contrat c";
            st = connex.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int idPersonne = rs.getInt("id_personne");
                int idPoste = rs.getInt("id_poste");
                Date dateDebut = rs.getDate("date_debut");
                Date dateFin = rs.getDate("date_fin");

                Contrat contrat = new Contrat(id, idPersonne, idPoste, dateDebut, dateFin);
                contrats.add(contrat);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (connex != null) connex.close();
        }

        return contrats;
    }
}
