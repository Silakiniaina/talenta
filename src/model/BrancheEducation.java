package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class BrancheEducation {
    
    private int idBrancheEducation;
    private String nomBranche;
    private String descriptionBranche;


    public List<BrancheEducation> getAll(Connection c) throws SQLException{
        List<BrancheEducation> result = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM competence";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                BrancheEducation d = new BrancheEducation();
                d.setIdBrancheEducation(rs.getInt(1));
                d.setNomBranche(rs.getString(2));
                d.setDescriptionBranche(rs.getString(3));
                result.add(d);
            }
        } catch (SQLException e) {
            throw e;
        } finally{
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
    // GETTERS AND SETTERS
    public int getIdBrancheEducation() {
        return idBrancheEducation;
    }
    public String getNomBranche() {
        return nomBranche;
    }
    public String getDescriptionBranche() {
        return descriptionBranche;
    }
    public void setIdBrancheEducation(int idBrancheEducation) {
        this.idBrancheEducation = idBrancheEducation;
    }
    public void setNomBranche(String nomBranche) {
        this.nomBranche = nomBranche;
    }
    public void setDescriptionBranche(String descriptionBranche) {
        this.descriptionBranche = descriptionBranche;
    }
}
