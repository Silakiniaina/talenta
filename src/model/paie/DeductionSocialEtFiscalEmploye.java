package model.paie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import model.Employe;
import model.utils.Database;

public class DeductionSocialEtFiscalEmploye {
    
    private Employe employe; 
    private double salaireBrut;
    private double cotisationCnaps;
    private double cotisationOstie;
    private double irsa;
    private double salaireNet;

    public static DeductionSocialEtFiscalEmploye getByEmploye(Connection c, int id) throws SQLException{
        DeductionSocialEtFiscalEmploye result = null; 
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        boolean isNewConnection  = false;
        String sql = "SELECT * FROM v_bulletin_paie WHERE id_employe = ?";
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setInt(1, id);

            rs = prstm.executeQuery();
            if(rs.next()){
                result = new DeductionSocialEtFiscalEmploye();
                result.setEmploye(c, rs.getInt(1));
                result.setSalaireBrut(rs.getDouble(2));
                result.setCotisationCnaps(rs.getDouble(3));
                result.setCotisationOstie(rs.getDouble(4));
                result.setIrsa(rs.getDouble(5));
                result.setSalaireNet(rs.getInt(6));
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
    public double getCotisationCnaps() {
        return cotisationCnaps;
    }
    public double getCotisationOstie() {
        return cotisationOstie;
    }
    public double getIrsa() {
        return irsa;
    }
    public double getSalaireNet() {
        return salaireNet;
    }


    public void setEmploye(Connection c, int employe)throws SQLException {
        this.employe = Employe.getById(c, employe);
    }
    public void setSalaireBrut(double salaireBrut) {
        this.salaireBrut = salaireBrut;
    }
    public void setCotisationCnaps(double cotisationCnaps) {
        this.cotisationCnaps = cotisationCnaps;
    }
    public void setCotisationOstie(double cotisationOstie) {
        this.cotisationOstie = cotisationOstie;
    }
    public void setIrsa(double irsa) {
        this.irsa = irsa;
    }
    public void setSalaireNet(double salaireNet) {
        this.salaireNet = salaireNet;
    }

    public static void main(String[] args) {
        try {
            DeductionSocialEtFiscalEmploye s = DeductionSocialEtFiscalEmploye.getByEmploye(null, 1);
            System.out.println(new Gson().toJson(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
