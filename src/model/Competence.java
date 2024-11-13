package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Competence {
    
    private int idCompetence;
    private String nomCompetence;

    // CONSTRUCTORS
    public Competence(){

    }

    public Competence(String nom){
        this.setNomCompetence(nom);
    }

    // CRUD
    public static List<Competence> getAll() throws SQLException{
        List<Competence> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM competence";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Competence d = new Competence();
                d.setIdCompetence(rs.getInt(1));
                d.setNomCompetence(rs.getString(2));
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
            if(c != null){
                c.close();
            }
        }
        return result;
    }

    public static Competence getById(int id) throws SQLException{
       Competence result = null;
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM competence WHERE id_competence = ?";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();

            if(rs.next()) {
                result = new Competence();
                result.setIdCompetence(rs.getInt(1));
                result.setNomCompetence(rs.getString(2));
            }
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    // GETTERS AND SETTERS
    public int getIdCompetence() {
        return idCompetence;
    }
    public String getNomCompetence() {
        return nomCompetence;
    }

    public void setIdCompetence(int idComptence) {
        this.idCompetence = idComptence;
    }
    public void setNomCompetence(String nomCompetence) {
        this.nomCompetence = nomCompetence;
    }
}
