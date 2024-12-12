package model.employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employe;
import model.utils.Database;

public class Indemnite {
    
    private int idIndemnite;
    private Employe employe;
    private TypeIndemnite typeIndemnite;
    private double montantIndemnite;

 public List<Indemnite> getAll(Connection c) throws SQLException{
        List<Indemnite> result = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM indemnite_employe";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Indemnite d = new Indemnite();
                d.setIdIndemnite(rs.getInt(1));
                d.setEmploye(c, rs.getInt(2));
                d.setTypeIndemnite(c, rs.getInt(3));
                d.setMontantIndemnite(rs.getDouble(4));
                result.add(d);
            }
        } catch (SQLException e) {
            throw e;
        }finally{
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
        return result;
    }

    public List<Indemnite> getByEmploye(Connection c, int idEmp) throws SQLException{
        List<Indemnite> result = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM indemnite_employe WHERE id_employe = ?";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, idEmp);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Indemnite d = new Indemnite();
                d.setIdIndemnite(rs.getInt(1));
                d.setEmploye(c, rs.getInt(2));
                d.setTypeIndemnite(c, rs.getInt(3));
                d.setMontantIndemnite(rs.getDouble(4));
                result.add(d);
            }
        } catch (SQLException e) {
            throw e;
        }finally{
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
        return result;
    }

    public void insert(Connection c) throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "INSERT INTO indemnite_employe(id_employe,id_type_indemnite,montant_indemnite) VALUES (?, ?, ?)";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getEmploye().getIdEmploye());
            prstm.setInt(2, this.getTypeIndemnite().getIdTypeIndemnite());
            prstm.setDouble(3, this.getMontantIndemnite());
            prstm.executeUpdate();

            c.commit();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        }finally{
            if(prstm != null){
                prstm.close();
            }
            if(c != null && isNewConnection){
                c.close();
            }
        }
    }


    public int getIdIndemnite() {
        return idIndemnite;
    }
    public Employe getEmploye() {
        return employe;
    }
    public double getMontantIndemnite() {
        return montantIndemnite;
    }
    public void setIdIndemnite(int idIndemnite) {
        this.idIndemnite = idIndemnite;
    }
    public void setEmploye(Connection c, int employe)throws SQLException {
        this.employe = Employe.getById(c, employe);
    }
    public void setMontantIndemnite(double montantIndemnite) {
        this.montantIndemnite = montantIndemnite;
    }
    public TypeIndemnite getTypeIndemnite() {
        return typeIndemnite;
    }

    public void setTypeIndemnite(Connection c, int typeIndemnite) throws SQLException{
        this.typeIndemnite = TypeIndemnite.getById(c, typeIndemnite);
    }
    
}
