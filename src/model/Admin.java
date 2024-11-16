package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.utils.Database;

public class Admin {
    
    int idAdmin;
    String nomAdmin;
    String emailAdmin;
    Role roleAdmin;

    // CONSTRUCTOR
    public Admin(){

    }

    // OPERATIONS
    public static Admin login(String email, String mdp)throws SQLException{
        Admin result = null; 
        Connection c = null; 
        PreparedStatement prstm = null;
        ResultSet rs = null; 
        String query = "SELECT * FROM responsable WHERE email = ? AND mdp = ? ";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setString(1, email);
            prstm.setString(2, mdp);

            rs = prstm.executeQuery();

            if(rs.next()){
                result = new Admin();
                result.setIdAdmin(rs.getInt(1));
                result.setNomAdmin(rs.getString(2));
                result.setEmailAdmin(rs.getString(3));
                result.setRoleAdmin(rs.getInt(5));
            }
            return result;
        } catch (SQLException e) {
            throw e;
        }finally{
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
    public String getNomAdmin() {
        return nomAdmin;
    }
    public String getEmailAdmin() {
        return emailAdmin;
    }
    public Role getRoleAdmin() {
        return roleAdmin;
    }
    public void setNomAdmin(String nomAdmin) {
        this.nomAdmin = nomAdmin;
    }
    public void setEmailAdmin(String emailAdmin) {
        this.emailAdmin = emailAdmin;
    }
    public void setRoleAdmin(int idRole) throws SQLException{
        this.roleAdmin = Role.getById(idRole);
    }
    public int getIdAdmin() {
        return idAdmin;
    }

    public void setIdAdmin(int idAdmin) {
        this.idAdmin = idAdmin;
    }
}
