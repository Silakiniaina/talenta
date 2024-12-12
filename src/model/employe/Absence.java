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
                d.setDateDebut(rs.getDate(3));
                d.setDateFin(rs.getDate(4));
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
}
