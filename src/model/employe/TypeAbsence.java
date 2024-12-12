package model.employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class TypeAbsence {
    
    private int idTypeAbsence;
    private String nomTypeAbsence; 
    private boolean avecSolde;
    
    
    public static List<TypeAbsence> getAll() throws SQLException{
        List<TypeAbsence> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM genre";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                TypeAbsence d = new TypeAbsence();
                d.setIdTypeAbsence(rs.getInt(1));
                d.setNomTypeAbsence(rs.getString(2));
                d.setAvecSolde(rs.getBoolean(3));
                result.add(d);
            }
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    // GETTERS AND SETTERS
    public int getIdTypeAbsence() {
        return idTypeAbsence;
    }
    public String getNomTypeAbsence() {
        return nomTypeAbsence;
    }
    public boolean isAvecSolde() {
        return avecSolde;
    }

    public void setIdTypeAbsence(int idTypeAbsence) {
        this.idTypeAbsence = idTypeAbsence;
    }
    public void setNomTypeAbsence(String nomTypeAbsence) {
        this.nomTypeAbsence = nomTypeAbsence;
    }
    public void setAvecSolde(boolean avecSolde) {
        this.avecSolde = avecSolde;
    }
}
