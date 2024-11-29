package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class Employe {
    
    private int idEmploye;
    private Candidat candidat;
    private Poste poste;
    private Date dateEmbauche;

    // CONSTRUCTORS
    public Employe(){

    }


    // GETTERS AND SETTERS
    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }
    public void setCandidat(Connection con, int candidat) throws SQLException{
        Candidat c = new Candidat();
        this.candidat = c.getById(con,candidat);
    }
    public void setPoste(int poste)throws SQLException {
        this.poste = Poste.getById(poste);
    }
    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }
    public int getIdEmploye() {
        return idEmploye;
    }
    public Candidat getCandidat() {
        return candidat;
    }
    public Poste getPoste() {
        return poste;
    }
    public Date getDateEmbauche() {
        return dateEmbauche;
    }

}