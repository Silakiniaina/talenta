package model.employe;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employe;
import model.utils.Database;

public class Absence {
    
    private int idAbsence;
    private Employe employe;
    private TypeAbsence typeAbsence;
    private Date dateDebut; 
    private Date dateFin;


    public List<Absence> getAll(Connection c) throws SQLException{
        List<Absence> result = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM absence";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Absence d = new Absence();
                d.setIdAbsence(rs.getInt(1));
                d.setEmploye(c, rs.getInt(2));
                d.setTypeAbsence(c, rs.getInt(3));
                d.setDateDebut(rs.getDate(4));
                d.setDateFin(rs.getDate(5));
                result.add(d);
            }
        } catch (SQLException e) {
            throw e;
        }finally{
            if(rs != null){
                rs.close();
            }
            if(prstm != null){
                prstm.close();
            }
            if(c != null && isNewConnection){
                c.close();
            }
        }
        return result;
    }

    public List<Absence> getByEmploye(Connection c, int idEmp) throws SQLException{
        List<Absence> result = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM absence WHERE id_employe = ?";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, idEmp);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Absence d = new Absence();
                d.setIdAbsence(rs.getInt(1));
                d.setEmploye(c, rs.getInt(2));
                d.setTypeAbsence(c, rs.getInt(3));
                d.setDateDebut(rs.getDate(4));
                d.setDateFin(rs.getDate(5));
                result.add(d);
            }
        } catch (SQLException e) {
            throw e;
        }finally{
            if(rs != null){
                rs.close();
            }
            if(prstm != null){
                prstm.close();
            }
            if(c != null && isNewConnection){
                c.close();
            }
        }
        return result;
    }

    public void insert(Connection c) throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "INSERT INTO absence(id_employe,id_type_absence,date_debut,date_fin) VALUES (?, ?, ?, ?)";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getEmploye().getIdEmploye());
            prstm.setInt(2, this.getTypeAbsence().getIdTypeAbsence());
            prstm.setDate(3, this.getDateDebut());
            prstm.setDate(4, this.getDateFin());
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

    public int getIdAbsence() {
        return idAbsence;
    }
    public Employe getEmploye() {
        return employe;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public void setIdAbsence(int idAbsence) {
        this.idAbsence = idAbsence;
    }
    public void setEmploye(Connection c , int employe) throws SQLException{

        this.employe = Employe.getById(c, employe);
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public TypeAbsence getTypeAbsence() {
        return typeAbsence;
    }

    public void setTypeAbsence(Connection c, int typeAbsence) throws SQLException{
        this.typeAbsence = TypeAbsence.getById(c,typeAbsence);
    }
}
