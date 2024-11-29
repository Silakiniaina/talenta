package model;

public class InformationEmploye {
    private int idEmploye;
    private String prenom;
    private String nom;
    private String email;
    private String genre;
    private int joursAcquis;
    private int joursPris;
    private int joursRestants;

    // Getters et Setters
    public int getIdEmploye() {
        return idEmploye;
    }

    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getJoursAcquis() {
        return joursAcquis;
    }

    public void setJoursAcquis(int joursAcquis) {
        this.joursAcquis = joursAcquis;
    }

    public int getJoursPris() {
        return joursPris;
    }

    public void setJoursPris(int joursPris) {
        this.joursPris = joursPris;
    }

    public int getJoursRestants() {
        return joursRestants;
    }

    public void setJoursRestants(int joursRestants) {
        this.joursRestants = joursRestants;
    }
}

