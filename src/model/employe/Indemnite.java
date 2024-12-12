package model.employe;

import java.sql.Connection;
import java.sql.SQLException;

import model.Employe;

public class Indemnite {
    
    private int idIndemnite;
    private Employe employe;
    private double montantIndemnite;




    public int getIdIndemnite() {
        return idIndemnite;
    }
    public Employe getEmploye() {
        return employe;
    }
    public double getMontantIndemnite() {
        return montantIndemnite;
    }
    public void setIdIndemnite(int idIndemnite) {
        this.idIndemnite = idIndemnite;
    }
    public void setEmploye(Connection c, int employe)throws SQLException {
        this.employe = Employe.getById(c, employe);
    }
    public void setMontantIndemnite(double montantIndemnite) {
        this.montantIndemnite = montantIndemnite;
    }

    
}
