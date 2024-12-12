package model.employe;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import model.Employe;

public class Absence {
    
    private int idAbsence;
    private Employe employe;
    private Date dateDebut; 
    private Date dateFin;


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
