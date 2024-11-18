package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Genre {
    
    private int idGenre;
    private String label;

    // CONSTRUCTORS
    public Genre(){

    }

    public Genre(int id, String label){
        this.setIdGenre(id);
        this.setLabel(label);
    }

    // CRUD
    public static List<Genre> getAll() throws SQLException{
        List<Genre> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM genre";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Genre d = new Genre();
                d.setIdGenre(rs.getInt(1));
                d.setLabel(rs.getString(2));
                result.add(d);
            }
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    public static Genre getById(int id) throws SQLException{
         Genre result = null;
         Connection c = null;
         PreparedStatement prstm = null; 
         ResultSet rs = null;
         String query = "SELECT * FROM genre WHERE id_genre = ?";
         try {
             c = Database.getConnection();
             prstm = c.prepareStatement(query);
             prstm.setInt(1, id);
             rs = prstm.executeQuery();
 
             if(rs.next()) {
                 result = new Genre();
                 result.setIdGenre(rs.getInt(1));
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
    public int getIdGenre() {
        return idGenre;
    }
    public String getLabel() {
        return label;
    }
    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    
}
