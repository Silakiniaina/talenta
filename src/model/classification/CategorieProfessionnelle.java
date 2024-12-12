package model.classification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.utils.Database;

public class CategorieProfessionnelle {
    
    private int idCategorieProfessionnelle;
    private String nomCategorieProfessionnelle;

    public List<CategorieProfessionnelle> getAll(Connection c) throws SQLException{
        List<CategorieProfessionnelle> result = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT id_categorie_professionnelle, nom_categorie_professionnelle FROM categorie_professionnelle";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                CategorieProfessionnelle d = new CategorieProfessionnelle();
                d.setIdCategorieProfessionnelle(rs.getInt(1));
                d.setNomCategorieProfessionnelle(rs.getString(2));
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

    public List<CategorieProfessionnelle> getAllByPoste(Connection c, int idPoste) throws SQLException{
        List<CategorieProfessionnelle> result = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT id_categorie_professionnelle FROM categorie_professionnelle_poste WHERE id_poste = ?";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, idPoste);
            rs = prstm.executeQuery();
            
            while (rs.next()) {
                CategorieProfessionnelle d = new CategorieProfessionnelle();
                d = d.getById(c, rs.getInt(1));
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

    public CategorieProfessionnelle getById(Connection c, int id) throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM categorie_professionnelle WHERE id_categorie_professionnelle = ?";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();

            if (rs.next()) {
                this.setIdCategorieProfessionnelle(rs.getInt(1));
                this.setNomCategorieProfessionnelle(rs.getString(2));
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
    public int getIdCategorieProfessionnelle() {
        return idCategorieProfessionnelle;
    }
    public String getNomCategorieProfessionnelle() {
        return nomCategorieProfessionnelle;
    }
    public void setIdCategorieProfessionnelle(int idCategorieProfessionnelle) {
        this.idCategorieProfessionnelle = idCategorieProfessionnelle;
    }
    public void setNomCategorieProfessionnelle(String nomCategorieProfessionnelle) {
        this.nomCategorieProfessionnelle = nomCategorieProfessionnelle;
    }

    public static void main(String[] args) {
        try {
            List<CategorieProfessionnelle> ls = new CategorieProfessionnelle().getAllByPoste(null,1);
            System.out.println(new Gson().toJson(ls));
        } catch (Exception e) {
            
        }
    }
}
