package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Status {
    
    private int idStatus;
    private String label;
    private String correspondingColor;

    // CONSTRUCTORS
    public Status(){

    }

    public Status(int id, String label){
        this.setIdStatus(id);
        this.setLabel(label);
    }

    // CRUD
    public List<Status> getAll(Connection c) throws SQLException{
        List<Status> result = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM status";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Status d = new Status();
                d.setIdStatus(rs.getInt(1));
                d.setLabel(rs.getString(2));
                d.setCorrespondingColor(rs.getString(3));
                result.add(d);
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
            if(c != null && isNewConnection){
                c.close();
            }
        }
    }

    public Status getById(Connection c,int id) throws SQLException{
         PreparedStatement prstm = null; 
         ResultSet rs = null;
         boolean isNewConnection = false;
         String query = "SELECT * FROM status WHERE id_status = ?";
         try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
             prstm = c.prepareStatement(query);
             prstm.setInt(1, id);
             rs = prstm.executeQuery();
 
             if(rs.next()) {
                 this.setIdStatus(rs.getInt(1));
                 this.setLabel(rs.getString(2));
                 this.setCorrespondingColor(rs.getString(3));
             }
             return this;
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
     }

    // GETTERS AND SETTERS
    public int getIdStatus() {
        return idStatus;
    }
    public String getLabel() {
        return label;
    }

    public String getCorrespondingColor() {
        return correspondingColor;
    }
    public void setIdStatus(int idStatus) {
        this.idStatus = idStatus;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public void setCorrespondingColor(String correspondingColor) {
        this.correspondingColor = correspondingColor;
    }

    
}
