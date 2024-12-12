package model.employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Employe;
import model.utils.Database;

public class Prime {
    
    private int idPrime; 
    private Employe employe;
    private TypePrime typePrime; 
    private double montantPrime;

    public List<Prime> getAll(Connection c) throws SQLException{
        List<Prime> result = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM prime_employe";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Prime d = new Prime();
                d.setIdPrime(rs.getInt(1));
                d.setEmploye(c, rs.getInt(2));
                d.setTypePrime(c, rs.getInt(3));
                d.setMontantPrime(rs.getDouble(4));
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

    public List<Prime> getByEmploye(Connection c, int idEmp) throws SQLException{
        List<Prime> result = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM prime_employe WHERE id_employe = ?";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, idEmp);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Prime d = new Prime();
                d.setIdPrime(rs.getInt(1));
                d.setEmploye(c, rs.getInt(2));
                d.setTypePrime(c, rs.getInt(3));
                d.setMontantPrime(rs.getDouble(4));
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
        String query = "INSERT INTO prime_employe(id_employe,id_type_prime,montant_prime) VALUES (?, ?, ?)";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getEmploye().getIdEmploye());
            prstm.setInt(2, this.getTypePrime().getIdTypePrime());
            prstm.setDouble(3, this.getMontantPrime());
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
    // GETTERS AND SETTERS
    public int getIdPrime() {
        return idPrime;
    }
    public Employe getEmploye() {
        return employe;
    }
    public TypePrime getTypePrime() {
        return typePrime;
    }
    public double getMontantPrime() {
        return montantPrime;
    }
    public void setIdPrime(int idPrime) {
        this.idPrime = idPrime;
    }
    public void setEmploye(Connection c, int employe) throws SQLException{
        this.employe = Employe.getById(c, employe);
    }
    public void setTypePrime(Connection c, int typePrime) throws SQLException{
        this.typePrime = TypePrime.getById(c, typePrime);
    }
    public void setMontantPrime(double montantPrime) {
        this.montantPrime = montantPrime;
    }
}
