package model;

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

    public Employe(int idEmploye,int idCandidat, int idPoste, Date dtb) throws SQLException{
        this.setIdEmploye(idEmploye);
        this.setCandidat(idCandidat);
        this.setPoste(idPoste);
        this.setDateEmbauche(dtb);
    }


    // GETTERS AND SETTERS
    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }
    public void setCandidat(int candidat) throws SQLException{
        this.candidat = Candidat.getById(candidat);
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