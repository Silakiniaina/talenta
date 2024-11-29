package model;

import java.sql.Connection;
import java.sql.SQLException;

public class Experience {
    
    private Specialite specialite;
    private int dureeMois;

    // GETTERS AND SETTERS
    public void setSpecialite(Connection c, int specialite) throws SQLException{
        Specialite s = new Specialite();
        this.specialite = s.getById(c, specialite);
    }
    public void setDureeMois(int dureeMois) {
        this.dureeMois = dureeMois;
    }
    public Specialite getSpecialite() {
        return specialite;
    }
    public int getDureeMois() {
        return dureeMois;
    } 
}
