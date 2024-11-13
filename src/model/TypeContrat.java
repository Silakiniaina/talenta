package model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class TypeContrat {
    private int id_type_contrat;
    private String label;
    
    // Constructeurs
    public TypeContrat() {}
    
    public TypeContrat(int id_type_contrat, String label) {
        this.id_type_contrat = id_type_contrat;
        this.label = label;
    }
    
    // Getters et Setters
    public int getId_type_contrat() {
        return id_type_contrat;
    }
    
    public void setId_type_contrat(int id_type_contrat) {
        this.id_type_contrat = id_type_contrat;
    }
    
    public String getLabel() {
        return label;
    }
    
    public void setLabel(String label) {
        this.label = label;
    }
    
    public static List<TypeContrat> getAll() throws SQLException {
        List<TypeContrat> typeContrats = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement("SELECT * FROM type_contrat");
            rs = prstm.executeQuery();
            
            while (rs.next()) {
                TypeContrat type = new TypeContrat();
                type.setId_type_contrat(rs.getInt("id_type_contrat"));
                type.setLabel(rs.getString("label"));
                typeContrats.add(type);
            }
        }catch(SQLException e){
            throw e;
        }
        
        return typeContrats;
    }
    
    public static TypeContrat getById(int id) throws SQLException {
        String sql = "SELECT * FROM type_contrat WHERE id_type_contrat = ?";
        TypeContrat result = null;
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(sql);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();
            
            if (rs.next()) {
                result = new TypeContrat();
                result.setId_type_contrat(rs.getInt("id_type_contrat"));
                result.setLabel(rs.getString("label"));
            }
        }catch(SQLException e){
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
    
    // Exemple d'utilisation dans le toString()
    @Override
    public String toString() {
        return "TypeContrat{" +
               "id_type_contrat=" + id_type_contrat +
               ", label='" + label + '\'' +
               '}';
    }
}