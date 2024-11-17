package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class TypeCompetence {
     private int idTypeCompetence;
    private String label;

    // CONSTRUCTORS
    public TypeCompetence(){

    }

    public TypeCompetence(int id, String label){
        this.setIdTypeCompetence(id);
        this.setLabel(label);
    }

    // CRUD
    public static List<TypeCompetence> getAll() throws SQLException{
        List<TypeCompetence> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM categorie_competence";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                TypeCompetence d = new TypeCompetence();
                d.setIdTypeCompetence(rs.getInt(1));
                d.setLabel(rs.getString(2));
                result.add(d);
            }
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    public static TypeCompetence getById(int id) throws SQLException{
         TypeCompetence result = null;
         Connection c = null;
         PreparedStatement prstm = null; 
         ResultSet rs = null;
         String query = "SELECT * FROM categorie_competence WHERE id_categorie_competence = ?";
         try {
             c = Database.getConnection();
             prstm = c.prepareStatement(query);
             prstm.setInt(1, id);
             rs = prstm.executeQuery();
 
             if(rs.next()) {
                 result = new TypeCompetence();
                 result.setIdTypeCompetence(rs.getInt(1));
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
    public int getIdTypeCompetence() {
        return idTypeCompetence;
    }
    public String getLabel() {
        return label;
    }
    public void setIdTypeCompetence(int idTypeCompetence) {
        this.idTypeCompetence = idTypeCompetence;
    }
    public void setLabel(String label) {
        this.label = label;
    }
}
