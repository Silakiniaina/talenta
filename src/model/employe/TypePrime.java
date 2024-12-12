package model.employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class TypePrime {
    
    private int idTypePrime; 
    private String nomTypePrime;

    public static List<TypePrime> getAll() throws SQLException{
        List<TypePrime> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM type_prime";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                TypePrime d = new TypePrime();
                d.setIdTypePrime(rs.getInt(1));
                d.setNomTypePrime(rs.getString(2));
                result.add(d);
            }
        } catch (SQLException e) {
            throw e;
        }
        finally{
            if(rs != null){
                rs.close();
            }
            if(prstm != null){
                prstm.close();
            }
            if(c != null){
                c.close();
            }
        }
        return result;
    }   

    // GETTERS AND SETTERS
    public int getIdTypePrime() {
        return idTypePrime;
    }
    public String getNomTypePrime() {
        return nomTypePrime;
    }
    public void setIdTypePrime(int idTypePrime) {
        this.idTypePrime = idTypePrime;
    }
    public void setNomTypePrime(String nomTypePrime) {
        this.nomTypePrime = nomTypePrime;
    }
}
