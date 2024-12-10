package model.classification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class CategorieProfessionnelle {
    
    private int idCategorieProfessionnelle;
    private String nomCategorieProfessionnelle;

    public List<CategorieProfessionnelle> getAll(Connection c) throws SQLException{
        List<CategorieProfessionnelle> result = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT id_hierarchie, nom_hierarchie FROM hierarchie";
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
}
