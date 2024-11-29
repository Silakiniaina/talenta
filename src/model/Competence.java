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
    private TypeCompetence typeCompetence;

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
                d.setTypeCompetence(rs.getInt(3));
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

    public Competence getById(Connection c ,int id) throws SQLException{
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        boolean isNewConnection = false;
        String query = "SELECT * FROM competence WHERE id_competence = ?";
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();

            if(rs.next()) {
                this.setIdCompetence(rs.getInt(1));
                this.setNomCompetence(rs.getString(2));
                this.setTypeCompetence(rs.getInt(3));
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
            if( c != null && isNewConnection){
                c.close();
            }
        }
    }

    public List<Competence> getAllByPoste(Connection c, int idPoste) throws SQLException{
        List<Competence> result = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT id_competence FROM competence_requise_poste WHERE id_poste = ?";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, idPoste);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Competence d = new Competence();
                d.setIdCompetence(rs.getInt(1));

                d = d.getById(c, d.getIdCompetence());
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
            if(c != null && isNewConnection){
                c.close();
            }
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
    public TypeCompetence getTypeCompetence(){
        return this.typeCompetence;
    }

    public void setIdCompetence(int idComptence) {
        this.idCompetence = idComptence;
    }
    public void setNomCompetence(String nomCompetence) {
        this.nomCompetence = nomCompetence;
    }
    public void setTypeCompetence(int id) throws SQLException{
        this.typeCompetence = TypeCompetence.getById(id);
    }
}
