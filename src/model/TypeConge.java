package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class TypeConge {
    
    int idTypeConge;
    String nomType;

    public int getIdTypeConge() {
        return idTypeConge;
    }
    public void setIdTypeConge(int idTypeConge) {
        this.idTypeConge = idTypeConge;
    }
    public String getNomType() {
        return nomType;
    }
    public void setNomType(String nomType) {
        this.nomType = nomType;
    }

    public TypeConge(){}

    public TypeConge(int id, String nom){
        this.setIdTypeConge(id);
        this.setNomType(nom);
    }

    public static List<TypeConge> getAll() throws SQLException {
        List<TypeConge> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM type_conge";

        try {
            c = Database.getConnection();  // Connexion à la base de données
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                TypeConge typeConge = new TypeConge();
                typeConge.setIdTypeConge(rs.getInt("id_type_conge"));
                typeConge.setNomType(rs.getString("nom_type"));
                result.add(typeConge);  // Ajouter à la liste
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (prstm != null) prstm.close();
            if (c != null) c.close();
        }
        return result;
    }

        // Méthode pour récupérer un type de congé par son ID
    public static TypeConge getById(Connection c,int id) throws SQLException {
        TypeConge typeConge = null;
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM type_conge WHERE id_type_conge = ?";
    
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();
    
            if (rs.next()) {
                typeConge = new TypeConge();
                typeConge.setIdTypeConge(rs.getInt("id_type_conge"));
                typeConge.setNomType(rs.getString("nom_type"));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (prstm != null) prstm.close();
            if (c != null && isNewConnection) c.close();
        }
        return typeConge;
    }
    
    
}
