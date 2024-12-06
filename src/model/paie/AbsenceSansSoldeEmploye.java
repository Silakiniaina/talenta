package model.paie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Employe;
import model.utils.Database;

public class AbsenceSansSoldeEmploye {
    
    private Employe employe;
    private int retard;
    private int absenceSansSolde;
    private int absenceNonAutorise;
    private int miseAPied;
    private int total;

    public static AbsenceSansSoldeEmploye getByEmploye(Connection c, int id) throws SQLException{
            AbsenceSansSoldeEmploye result = null; 
            PreparedStatement prstm = null; 
            ResultSet rs = null; 
            boolean isNewConnection  = false;
            String sql = "SELECT * FROM v_absence_sans_solde WHERE id_employe = ?";
            try {
                if(c == null){
                    c = Database.getConnection();
                    isNewConnection = true;
                }

                prstm = c.prepareStatement(sql);
                prstm.setInt(1, id);

                rs = prstm.executeQuery();
                if(rs.next()){
                    result = new AbsenceSansSoldeEmploye();
                    result.setEmploye(c, rs.getInt(1));
                    result.setRetard(rs.getInt(2));
                    result.setAbsenceSansSolde(rs.getInt(3));
                    result.setAbsenceNonAutorise(rs.getInt(4));
                    result.setMiseAPied(rs.getInt(5));
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

    public void setEmploye(Connection c, int employe)throws SQLException {
        this.employe = Employe.getById(c, employe);
    }
    public void setRetard(int retard) {
        this.retard = retard;
    }
    public void setAbsenceSansSolde(int absenceSansSolde) {
        this.absenceSansSolde = absenceSansSolde;
    }
    public void setAbsenceNonAutorise(int absenceNonAutorise) {
        this.absenceNonAutorise = absenceNonAutorise;
    }
    public void setMiseAPied(int miseAPied) {
        this.miseAPied = miseAPied;
    }
    public void setTotal(int total) {
        this.total = total;
    }
    public Employe getEmploye() {
        return employe;
    }
    public int getRetard() {
        return retard;
    }
    public int getAbsenceSansSolde() {
        return absenceSansSolde;
    }
    public int getAbsenceNonAutorise() {
        return absenceNonAutorise;
    }
    public int getMiseAPied() {
        return miseAPied;
    }
    public int getTotal() {
        return total;
    }
}
