package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Role {
    
    int idRole;
    String nomRole;

    // CONSTRUCTOR
    public Role(){

    }
    

    // CRUD 
    public static List<Role> getAll()throws SQLException{
        List<Role> result = new ArrayList<Role>();
        Connection c = null; 
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT * FROM role_talenta";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(sql);
            rs = prstm.executeQuery();
            
            while(rs.next()){
                Role r = new Role();
                r.setIdRole(rs.getInt(1));
                r.setNomRole(rs.getString(2));

                result.add(r);
            }
            return result;
        } catch (SQLException e) {
            throw e;
        } finally{
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
    }

    public static Role getById(int id)throws SQLException{
        Role result = null;
        Connection c = null; 
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        String sql = "SELECT * FROM role_talenta WHERE id_role = ?";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(sql);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();
            
            if(rs.next()){
                result = new Role();
                result.setIdRole(rs.getInt(1));
                result.setNomRole(rs.getString(2));
            }
            return result;
        } catch (SQLException e) {
            throw e;
        } finally{
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
    }

    // GETTERS AND SETTERS
    public int getIdRole() {
        return idRole;
    }
    public String getNomRole() {
        return nomRole;
    }
    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
    public void setNomRole(String nomRole) {
        this.nomRole = nomRole;
    }
}
