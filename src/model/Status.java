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
    public static List<Status> getAll() throws SQLException{
        List<Status> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM status";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Status d = new Status();
                d.setIdStatus(rs.getInt(1));
                d.setLabel(rs.getString(2));
                result.add(d);
            }
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    public static Status getById(int id) throws SQLException{
         Status result = null;
         Connection c = null;
         PreparedStatement prstm = null; 
         ResultSet rs = null;
         String query = "SELECT * FROM status WHERE id_status = ?";
         try {
             c = Database.getConnection();
             prstm = c.prepareStatement(query);
             prstm.setInt(1, id);
             rs = prstm.executeQuery();
 
             if(rs.next()) {
                 result = new Status();
                 result.setIdStatus(rs.getInt(1));
                 result.setLabel(rs.getString(2));
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
            if(c != null){
                c.close();
            }
        }
         return result;
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
