package statut_conge;

import java.sql.Date;

import jakarta.servlet.http.HttpServletRequest;

public class Conge {

    private int idConge;
    private int idEmploye;
    private int idContrat;
    private int idTypeConge;
    private Date dateDebut;
    private Date dateFin;

    /* ------------------------------ Constructors ------------------------------ */
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

    public Conge(HttpServletRequest req) throws NumberFormatException, IllegalArgumentException {
        this.createFromRequest(req);
    }

    /* --------------------------- Getters and setters -------------------------- */
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

    /* ---------------------------------- Utils --------------------------------- */
    public void createFromRequest(HttpServletRequest req) throws NumberFormatException, IllegalArgumentException {
        // NOTICE: Assumes the date is in "yyyy-MM-dd" format

        try {
            if (req.getParameter("idConge") != null) {
                this.idConge = Integer.parseInt(req.getParameter("idConge"));
            }

            if (req.getParameter("idEmploye") != null) {
                this.idEmploye = Integer.parseInt(req.getParameter("idEmploye"));
            }

            if (req.getParameter("idContrat") != null) {
                this.idContrat = Integer.parseInt(req.getParameter("idContrat"));
            }

            if (req.getParameter("idTypeConge") != null) {
                this.idTypeConge = Integer.parseInt(req.getParameter("idTypeConge"));
            }

            if (req.getParameter("dateDebut") != null) {
                this.dateDebut = Date.valueOf(req.getParameter("dateDebut"));
            }

            if (req.getParameter("dateFin") != null) {
                this.dateFin = Date.valueOf(req.getParameter("dateFin"));
            }
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid parameter format: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid parameter value: " + e.getMessage());
        }
    }

}
