package model.paie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.google.gson.Gson;

import model.Employe;
import model.utils.Database;

public class TauxEmploye {
    
    private Employe employe;
    private double tauxHoraire;
    private double tauxJournalier;

    public static TauxEmploye getByEmploye(Connection c, int id) throws SQLException{
        TauxEmploye result = null; 
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        boolean isNewConnection  = false;
        String sql = "SELECT * FROM v_taux_employe WHERE id_employe = ?";
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }

            prstm = c.prepareStatement(sql);
            prstm.setInt(1, id);

            rs = prstm.executeQuery();
            if(rs.next()){
                result = new TauxEmploye();
                result.setEmploye(c, rs.getInt(1));
                result.setTauxHoraire(rs.getDouble(2));
                result.setTauxJournalier(rs.getDouble(3));
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
    public double getTauxHoraire() {
        return tauxHoraire;
    }
    public double getTauxJournalier() {
        return tauxJournalier;
    }
    public void setEmploye(Connection c, int employe) throws SQLException{
        this.employe = Employe.getById(c, employe);
    }
    public void setTauxHoraire(double tauxHoraire) {
        this.tauxHoraire = tauxHoraire;
    }
    public void setTauxJournalier(double tauxJournalier) {
        this.tauxJournalier = tauxJournalier;
    }

    public static void main(String[] args) {
        try {
            TauxEmploye s = TauxEmploye.getByEmploye(null, 1);
            System.out.println(new Gson().toJson(s));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
