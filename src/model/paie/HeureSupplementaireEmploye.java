package model.paie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import model.Employe;
import model.utils.Database;

public class HeureSupplementaireEmploye {
    
    private Employe employe; 
    private double sup30;
    private double sup40;
    private double sup50;
    private double sup100;
    private double total;

    public static HeureSupplementaireEmploye getByEmploye(Connection c, int id) throws SQLException{
        HeureSupplementaireEmploye result = null; 
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        boolean isNewConnection  = false;
        String sql = "SELECT * FROM v_heure_sup_details WHERE id_employe = ?";
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setInt(1, id);

            rs = prstm.executeQuery();
            if(rs.next()){
                result = new HeureSupplementaireEmploye();
                result.setEmploye(c, rs.getInt(1));
                result.setSup30(rs.getDouble(2));
                result.setSup40(rs.getDouble(3));
                result.setSup50(rs.getDouble(4));
                result.setSup100(rs.getInt(5));
                result.setTotal(rs.getDouble(6));
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
    public double getSup30() {
        return sup30;
    }
    public double getSup40() {
        return sup40;
    }
    public double getSup50() {
        return sup50;
    }
    public double getSup100() {
        return sup100;
    }
    
    public void setEmploye(Connection c,int employe) throws SQLException{
        this.employe = Employe.getById(c, employe);
    }
    public void setSup30(double sup30) {
        this.sup30 = sup30;
    }
    public void setSup40(double sup40) {
        this.sup40 = sup40;
    }
    public void setSup50(double sup50) {
        this.sup50 = sup50;
    }
    public void setSup100(double sup100) {
        this.sup100 = sup100;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public static void main(String[] args) {
        try {
            HeureSupplementaireEmploye s = HeureSupplementaireEmploye.getByEmploye(null, 1);
            System.out.println(new Gson().toJson(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
