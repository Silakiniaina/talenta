package model.employe;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import model.Employe;

public class Presence {
    
    private int idPresence;
    private Employe employe;
    private Timestamp dateHeureEntree;
    private Timestamp dateHeureSortie;


    // GETTERS AND SETTERS
    public int getIdPresence() {
        return idPresence;
    }
    public Employe getEmploye() {
        return employe;
    }
    public Timestamp getDateHeureEntree() {
        return dateHeureEntree;
    }
    public Timestamp getDateHeureSortie() {
        return dateHeureSortie;
    }
    public void setIdPresence(int idPresence) {
        this.idPresence = idPresence;
    }
    public void setEmploye(Connection c, int employe) throws SQLException{
        this.employe = Employe.getById(c, employe);
    }
    public void setDateHeureEntree(Timestamp dateHeureEntree) {
        this.dateHeureEntree = dateHeureEntree;
    }
    public void setDateHeureSortie(Timestamp dateHeureSortie) {
        this.dateHeureSortie = dateHeureSortie;
    }

}
