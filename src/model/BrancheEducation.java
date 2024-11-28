package model;

public class BrancheEducation {
    
    private int idBrancheEducation;
    private String nomBranche;
    private String descriptionBranche;

    // GETTERS AND SETTERS
    public int getIdBrancheEducation() {
        return idBrancheEducation;
    }
    public String getNomBranche() {
        return nomBranche;
    }
    public String getDescriptionBranche() {
        return descriptionBranche;
    }
    public void setIdBrancheEducation(int idBrancheEducation) {
        this.idBrancheEducation = idBrancheEducation;
    }
    public void setNomBranche(String nomBranche) {
        this.nomBranche = nomBranche;
    }
    public void setDescriptionBranche(String descriptionBranche) {
        this.descriptionBranche = descriptionBranche;
    }
}
