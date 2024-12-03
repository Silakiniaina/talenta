package classification_personnelle;

public class ClassificationPersonnelle {

    private String firstName;
    private String lastName;
    private String categorie;

    /* ------------------------------ Constructors ------------------------------ */
    public ClassificationPersonnelle() {
    }

    public ClassificationPersonnelle(String firstName, String lastName, String categorie) {
        this.setFirstName(firstName);
        this.setLastName(lastName);
        this.setCategorie(categorie);
    }

    /* --------------------------- Getters and setters -------------------------- */
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

}
