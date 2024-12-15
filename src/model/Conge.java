package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Conge {
    
    int idConge;
    Employe employe;
    Date dateDebut;
    Date dateFin;
    TypeConge typeConge;

    public int getIdConge() {
        return idConge;
    }
    public void setIdConge(int idConge) {
        this.idConge = idConge;
    }
    public Employe getEmploye() {
        return employe;
    }
    public void setEmploye(Connection c,int employe) throws SQLException{
        this.employe = Employe.getById(c, employe);
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public TypeConge getTypeConge() {
        return typeConge;
    }
    public void setTypeConge(Connection c, int typeConge) throws SQLException{
        this.typeConge = TypeConge.getById(c,typeConge);
    }

    public Conge(){}


    public static List<Conge> getPlanning() throws SQLException {
        List<Conge> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM conge WHERE date_debut IS NOT NULL AND date_fin IS NOT NULL";

        try {
            c = Database.getConnection(); 
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("id_conge"));
                conge.setEmploye(c,rs.getInt("id_employe"));
                conge.setDateDebut(rs.getDate("date_debut"));
                conge.setDateFin(rs.getDate("date_fin"));
                conge.setTypeConge(c, rs.getInt("id_type_conge"));

                result.add(conge);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (prstm != null) prstm.close();
            if (c != null) c.close();
        }
        return result;
    }

    public void insert(Connection c) throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "INSERT INTO conge(id_employe,id_type_conge,date_debut,date_fin,id_contrat) VALUES (?, ?, ?, ?, ?)";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getEmploye().getIdEmploye());
            prstm.setInt(2, this.getTypeConge().getIdTypeConge());
            prstm.setDate(3, this.getDateDebut());
            prstm.setDate(4, this.getDateFin());
            prstm.setInt(5, this.getEmploye().getContrat(c).getIdContrat());
            prstm.executeUpdate();

            c.commit();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        }finally{
            if(prstm != null){
                prstm.close();
            }
            if(c != null && isNewConnection){
                c.close();
            }
        }
    }
}
