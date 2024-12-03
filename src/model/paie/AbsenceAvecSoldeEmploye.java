package model.paie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import model.Employe;
import model.utils.Database;

public class AbsenceAvecSoldeEmploye {
    
    private Employe employe;
    private int reposMedical;
    private int assistanceMat;
    private int hospitalisationConv;
    private int evenementFamilial;
    private int total;


    public static AbsenceAvecSoldeEmploye getByEmploye(Connection c, int id) throws SQLException{
        AbsenceAvecSoldeEmploye result = null; 
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
                result = new AbsenceAvecSoldeEmploye();
                result.setEmploye(c, rs.getInt(1));
                result.setReposMedical(rs.getInt(2));
                result.setAssistanceMat(rs.getInt(3));
                result.setHospitalisationConv(rs.getInt(4));
                result.setEvenementFamilial(rs.getInt(5));
                result.setTotal(rs.getInt(6));
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
    public int getReposMedical() {
        return reposMedical;
    }
    public int getAssistanceMat() {
        return assistanceMat;
    }
    public int getHospitalisationConv() {
        return hospitalisationConv;
    }
    public int getEvenementFamilial() {
        return evenementFamilial;
    }
    public int getTotal() {
        return total;
    }


    public void setEmploye(Connection c,int employe) throws SQLException{
        this.employe = Employe.getById(c, employe);
    }
    public void setReposMedical(int reposMedical) {
        this.reposMedical = reposMedical;
    }
    public void setAssistanceMat(int assistanceMat) {
        this.assistanceMat = assistanceMat;
    }
    public void setHospitalisationConv(int hospitalisationConv) {
        this.hospitalisationConv = hospitalisationConv;
    }
    public void setEvenementFamilial(int evenementFamilial) {
        this.evenementFamilial = evenementFamilial;
    }
    public void setTotal(int total) {
        this.total = total;
    }

    public static void main(String[] args) {
        try {
            AbsenceAvecSoldeEmploye s = AbsenceAvecSoldeEmploye.getByEmploye(null, 1);
            System.out.println(new Gson().toJson(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
