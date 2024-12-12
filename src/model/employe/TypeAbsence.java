package model.employe;

public class TypeAbsence {
    
    private int idTypeAbsence;
    private String nomTypeAbsence; 
    private boolean avecSolde;
    
    // GETTERS AND SETTERS
    public int getIdTypeAbsence() {
        return idTypeAbsence;
    }
    public String getNomTypeAbsence() {
        return nomTypeAbsence;
    }
    public boolean isAvecSolde() {
        return avecSolde;
    }

    public void setIdTypeAbsence(int idTypeAbsence) {
        this.idTypeAbsence = idTypeAbsence;
    }
    public void setNomTypeAbsence(String nomTypeAbsence) {
        this.nomTypeAbsence = nomTypeAbsence;
    }
    public void setAvecSolde(boolean avecSolde) {
        this.avecSolde = avecSolde;
    }
}
