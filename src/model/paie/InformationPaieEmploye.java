package model.paie;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import model.utils.Database;

public class InformationPaieEmploye {
    
    private int id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String email;
    private int numeroCnaps;
    private String adresse;
    private Date dateEmbauche;
    private int age;
    private String ancienete;
    private String poste;
    private String departement;


    public static InformationPaieEmploye getByEmploye(Connection c, int id) throws SQLException{
        InformationPaieEmploye result = null; 
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        boolean isNewConnection  = false;
        String sql = "SELECT * FROM v_information_employe_paie WHERE id_employe = ?";
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setInt(1, id);

            rs = prstm.executeQuery();
            if(rs.next()){
                result = new InformationPaieEmploye();
                result.setId(rs.getInt(1));
                result.setNom(rs.getString(2));
                result.setPrenom(rs.getString(3));
                result.setDateNaissance(rs.getDate(4));
                result.setEmail(rs.getString(5));
                result.setNumeroCnaps(rs.getInt(6));
                result.setAdresse(rs.getString(7));
                result.setDateEmbauche(rs.getDate(8));
                result.setAge(rs.getInt(9));
                result.setAncienete(rs.getString(10));
                result.setPoste(rs.getString(11));
                result.setDepartement(rs.getString(12));
            }
            return result;
        } catch (Exception e) {
            throw e;
        } finally{
            if(rs != null){
                rs.close();
            }
            if(prstm != null){
                prstm.close();
            }
            if(c != null && isNewConnection){
                c.close();
            }
        }
    }

    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getPrenom() {
        return prenom;
    }
    public Date getDateNaissance() {
        return dateNaissance;
    }
    public String getEmail() {
        return email;
    }
    public int getNumeroCnaps() {
        return numeroCnaps;
    }
    public String getAdresse() {
        return adresse;
    }
    public Date getDateEmbauche() {
        return dateEmbauche;
    }
    public int getAge() {
        return age;
    }
    public String getAncienete() {
        return ancienete;
    }
    public String getPoste() {
        return poste;
    }
    public String getDepartement() {
        return departement;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setNumeroCnaps(int numeroCnaps) {
        this.numeroCnaps = numeroCnaps;
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public void setAncienete(String ancienete) {
        this.ancienete = ancienete;
    }
    public void setPoste(String poste) {
        this.poste = poste;
    }
    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public static void main(String[] args) {
        try {
            InformationPaieEmploye s = InformationPaieEmploye.getByEmploye(null, 1);
            System.out.println(new Gson().toJson(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
