package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class TypeDiplome {

    private int idTypeDiplome;
    private String label;

    // CONSTRUCTORS
    public TypeDiplome(){

    }

    public TypeDiplome(int id, String label){
        this.setIdTypeDiplome(id);
        this.setLabel(label);
    }

    // CRUD
    public static List<TypeDiplome> getAll() throws SQLException{
        List<TypeDiplome> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM type_diplome";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                TypeDiplome d = new TypeDiplome();
                d.setIdTypeDiplome(rs.getInt(1));
                d.setLabel(rs.getString(2));
                result.add(d);
            }
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    public static TypeDiplome getById(int id) throws SQLException{
         TypeDiplome result = null;
         Connection c = null;
         PreparedStatement prstm = null; 
         ResultSet rs = null;
         String query = "SELECT * FROM type_diplome WHERE id_type_diplome = ?";
         try {
             c = Database.getConnection();
             prstm = c.prepareStatement(query);
             prstm.setInt(1, id);
             rs = prstm.executeQuery();
 
             if(rs.next()) {
                 result = new TypeDiplome();
                 result.setIdTypeDiplome(rs.getInt(1));
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
    public int getIdTypeDiplome() {
        return idTypeDiplome;
    }
    public String getLabel() {
        return label;
    }
    public void setIdTypeDiplome(int idTypeDiplome) {
        this.idTypeDiplome = idTypeDiplome;
    }
    public void setLabel(String label) {
        this.label = label;
    }
}
