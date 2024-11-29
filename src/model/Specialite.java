package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Specialite {
    
    private int idSpecialite;
    private String nomSpecialite;
    private String description;
    private BrancheEducation brancheEducation;

    public List<Specialite> getAll(Connection c) throws SQLException{
        List<Specialite> result = new ArrayList<>();
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        boolean isNewConnection = false;
        String query = "SELECT * FROM specialite";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Specialite d = new Specialite();
                d.setIdSpecialite(rs.getInt(1));
                d.setNomSpecialite(rs.getString(2));
                d.setDescription(rs.getString(3));
                d.setBrancheEducation(c, rs.getInt(4));
                result.add(d);
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
            if(c != null && isNewConnection){
                c.close();
            }
        }
    }

    public Specialite getById(Connection c, int id) throws SQLException{
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        boolean isNewConnection = false;
        String query = "SELECT * FROM specialite WHERE id_specialite = ? ";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();

            if (rs.next()) {
                this.setIdSpecialite(rs.getInt(1));
                this.setNomSpecialite(rs.getString(2));
                this.setDescription(rs.getString(3));
                this.setBrancheEducation(c, rs.getInt(4));
            }
            return this;
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
    }

    // GETTERS AND SETTERS
    public int getIdSpecialite() {
        return idSpecialite;
    }
    public String getNomSpecialite() {
        return nomSpecialite;
    }
    public String getDescription() {
        return description;
    }
    public BrancheEducation getBrancheEducation() {
        return brancheEducation;
    }

    public void setIdSpecialite(int idSpecialite) {
        this.idSpecialite = idSpecialite;
    }
    public void setNomSpecialite(String nomSpecialite) {
        this.nomSpecialite = nomSpecialite;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setBrancheEducation(Connection c, int brancheEducation)throws SQLException {
        BrancheEducation b = new BrancheEducation();

        this.brancheEducation = b.getById(c, brancheEducation);
    }
}
