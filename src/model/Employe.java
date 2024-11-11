package model;

import java.sql.Date;
import java.sql.SQLException;

public class Employe extends Personne {
    
    Poste poste;
    Date dateEmbauche;
    Date dateFin;

    public Poste getPoste() {
        return poste;
    }

    public void setPoste(Poste poste) {
        this.poste = poste;
    }

    public void setPoste(int idPoste) throws SQLException {
        this.poste = Poste.getById(idPoste);
    }

    public Date getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Date getDateFin() {
        return dateFin;
    }

    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }

    public Employe (int id, String nom, String prenom, int idPoste, double pourcentage) throws SQLException{
        this.setId(id);
        this.setNom(nom);
        this.setPrenom(prenom);
        this.setPoste(idPoste);
        this.setPourcentage(pourcentage);
    }
    
    public Employe() {}
    
}
