package model.paie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import model.Employe;
import model.utils.Database;

public class SalaireEmploye {
    
    private Employe employe;
    private double salaireBrut;
    private double primeRendement;
    private double primeAncienete;
    private int congePaye;
    private double montantConge;
    private double total;


    public static SalaireEmploye getByEmploye(Connection c, int id) throws SQLException{
        SalaireEmploye result = null; 
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        boolean isNewConnection  = false;
        String sql = "SELECT * FROM v_salaire_complet WHERE id_employe = ?";
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setInt(1, id);

            rs = prstm.executeQuery();
            if(rs.next()){
                result = new SalaireEmploye();
                result.setEmploye(c, rs.getInt(2));
                result.setSalaireBrut(rs.getDouble(3));
                result.setPrimeRendement(rs.getDouble(4));
                result.setPrimeAncienete(rs.getDouble(5));
                result.setCongePaye(rs.getInt(6));
                result.setMontantConge(rs.getDouble(7));
                result.setTotal(rs.getDouble(8));
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
    
    public Employe getEmploye() {
        return employe;
    }
    public double getSalaireBrut() {
        return salaireBrut;
    }
    public double getPrimeRendement() {
        return primeRendement;
    }
    public double getPrimeAncienete() {
        return primeAncienete;
    }
    public int getCongePaye() {
        return congePaye;
    }
    public double getMontantConge() {
        return montantConge;
    }

    public void setEmploye(Connection c, int employe)throws SQLException {
        this.employe = Employe.getById(c, employe);
    }
    public void setSalaireBrut(double salaireBrut) {
        this.salaireBrut = salaireBrut;
    }
    public void setPrimeRendement(double primeRendement) {
        this.primeRendement = primeRendement;
    }
    public void setPrimeAncienete(double primeAncienete) {
        this.primeAncienete = primeAncienete;
    }
    public void setCongePaye(int congePaye) {
        this.congePaye = congePaye;
    }
    public void setMontantConge(double montantConge) {
        this.montantConge = montantConge;
    }


    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public static void main(String[] args) {
        try {
            SalaireEmploye s = SalaireEmploye.getByEmploye(null, 1);
            System.out.println(new Gson().toJson(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
