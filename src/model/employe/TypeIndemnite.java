package model.employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class TypeIndemnite{

    private int idTypeIndemnite;
    private String nomTypeIndemnite;

    public static List<TypeIndemnite> getAll() throws SQLException{
        List<TypeIndemnite> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM type_indemnite";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                TypeIndemnite d = new TypeIndemnite();
                d.setIdTypeIndemnite(rs.getInt(1));
                d.setNomTypeIndemnite(rs.getString(2));
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
    
    public static TypeIndemnite getById(Connection c, int id) throws SQLException{
        TypeIndemnite result = null;
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM type_indemnite WHERE id_type_indemnite = ?";
        try {
           if( c == null ){
               c = Database.getConnection();
               isNewConnection = true;
           }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();

            if(rs.next()) {
                result = new TypeIndemnite();
                result.setIdTypeIndemnite(rs.getInt(1));
                result.setNomTypeIndemnite(rs.getString(2));
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

    public int getIdTypeIndemnite() {
        return idTypeIndemnite;
    }
    public String getNomTypeIndemnite() {
        return nomTypeIndemnite;
    }
    public void setIdTypeIndemnite(int idTypeIndemnite) {
        this.idTypeIndemnite = idTypeIndemnite;
    }
    public void setNomTypeIndemnite(String nomTypeIndemnite) {
        this.nomTypeIndemnite = nomTypeIndemnite;
    }
}