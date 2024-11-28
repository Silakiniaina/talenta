package statut_conge;

import java.sql.Date;

public class Conge {

    private int idConge;
    private int idEmploye;
    private int idContrat;
    private int idTypeConge;
    private Date dateDebut;
    private Date dateFin;

    // Constructors
    public Conge() {
    }

    public Conge(int idConge) {
        this.setIdConge(idConge);
    }

    public Conge(int idConge, int idEmploye, int idContrat, int idTypeConge, Date dateDebut, Date dateFin) {
        this(idConge);
        this.setIdEmploye(idEmploye);
        this.setIdContrat(idContrat);
        this.setIdTypeConge(idTypeConge);
        this.setDateDebut(dateDebut);
        this.setDateFin(dateFin);
    }

    // Getters and setters
    public int getIdConge() {
        return idConge;
    }

    public void setIdConge(int idConge) {
        this.idConge = idConge;
    }

    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public int getIdContrat() {
        return idContrat;
    }

    public void setIdContrat(int idContrat) {
        this.idContrat = idContrat;
    }

    public int getIdTypeConge() {
        return idTypeConge;
    }

    public void setIdTypeConge(int idTypeConge) {
        this.idTypeConge = idTypeConge;
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

}
