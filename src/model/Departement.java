package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Departement {
    
    private int idDepartement;
    private String nomDepartement;

    // CONSTRUCTORS
    public Departement(){

    }

    public Departement(String nom){
        this.setNomDepartement(nom);
    }

    // CRUD
    public static List<Departement> getAll() throws SQLException{
        List<Departement> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM departement";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Departement d = new Departement();
                d.setIdDepartement(rs.getInt(1));
                d.setNomDepartement(rs.getString(2));
                result.add(d);
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

    public static Departement getById(int id) throws SQLException{
       Departement result = null;
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM departement WHERE id_departement = ?";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();

            if(rs.next()) {
                result = new Departement();
                result.setIdDepartement(rs.getInt(1));
                result.setNomDepartement(rs.getString(2));
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
    public int getIdDepartement() {
        return idDepartement;
    }
    public void setIdDepartement(int idDepartement) {
        this.idDepartement = idDepartement;
    }
    public String getNomDepartement() {
        return nomDepartement;
    }
    public void setNomDepartement(String nomDepartement) {
        this.nomDepartement = nomDepartement;
    }

    
}
