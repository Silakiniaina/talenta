package model.paie;

import java.sql.Connection;
import java.sql.SQLException;

import model.Employe;

public class FichePaie {
    
    private Employe employe;
    private InformationPaieEmploye information;
    private HeureSupplementaireEmploye heureSupplementaire;
    private AbsenceAvecSoldeEmploye absenceAvecSolde;
    private AbsenceSansSoldeEmploye absenceSansSolde;
    private SalaireEmploye salaireEmploye;
    private TauxEmploye tauxEmploye;
    private DeductionSocialEtFiscalEmploye deductionFiscalEtSocial;

    public FichePaie(Connection c, int id_employe) throws SQLException{
        this.setEmploye(Employe.getById(c, id_employe));
        this.setInformation(InformationPaieEmploye.getByEmploye(c, id_employe));
        this.setHeureSupplementaire(HeureSupplementaireEmploye.getByEmploye(c, id_employe));
        this.setAbsenceAvecSolde(AbsenceAvecSoldeEmploye.getByEmploye(c, id_employe));
        this.setAbsenceSansSolde(AbsenceSansSoldeEmploye.getByEmploye(c, id_employe));
        this.setSalaireEmploye(SalaireEmploye.getByEmploye(c, id_employe));
        this.setDeductionFiscalEtSocial(DeductionSocialEtFiscalEmploye.getByEmploye(c, id_employe));
        this.setTauxEmploye(TauxEmploye.getByEmploye(c, id_employe));
    }

    public Employe getEmploye() {
        return employe;
    }
    public InformationPaieEmploye getInformation() {
        return information;
    }
    public HeureSupplementaireEmploye getHeureSupplementaire() {
        return heureSupplementaire;
    }
    public AbsenceAvecSoldeEmploye getAbsenceAvecSolde() {
        return absenceAvecSolde;
    }
    public AbsenceSansSoldeEmploye getAbsenceSansSolde() {
        return absenceSansSolde;
    }
    public SalaireEmploye getSalaireEmploye() {
        return salaireEmploye;
    }
    public DeductionSocialEtFiscalEmploye getDeductionFiscalEtSocial() {
        return deductionFiscalEtSocial;
    }
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    public void setInformation(InformationPaieEmploye information) {
        this.information = information;
    }
    public void setHeureSupplementaire(HeureSupplementaireEmploye heureSupplementaire) {
        this.heureSupplementaire = heureSupplementaire;
    }
    public void setAbsenceAvecSolde(AbsenceAvecSoldeEmploye absenceAvecSolde) {
        this.absenceAvecSolde = absenceAvecSolde;
    }
    public void setAbsenceSansSolde(AbsenceSansSoldeEmploye absenceSansSolde) {
        this.absenceSansSolde = absenceSansSolde;
    }
    public void setSalaireEmploye(SalaireEmploye salaireEmploye) {
        this.salaireEmploye = salaireEmploye;
    }
    public void setDeductionFiscalEtSocial(DeductionSocialEtFiscalEmploye deductionFiscalEtSocial) {
        this.deductionFiscalEtSocial = deductionFiscalEtSocial;
    }
    public TauxEmploye getTauxEmploye() {
        return tauxEmploye;
    }

    public void setTauxEmploye(TauxEmploye tauxEmploye) {
        this.tauxEmploye = tauxEmploye;
    }
}
