package model.classification;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.utils.Database;

public class Hierarchie {
    
    private int idHierarchie;
    private String nomHierarchie;
    
    public static List<Hierarchie> getAll(Connection c) throws SQLException{
        List<Hierarchie> result = new ArrayList<>();
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
                Hierarchie d = new Hierarchie();
                d.setIdHierarchie(rs.getInt(1));
                d.setNomHierarchie(rs.getString(2));
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

    // GETTERS
    public int getIdHierarchie() {
        return idHierarchie;
    }
    public String getNomHierarchie() {
        return nomHierarchie;
    }

    public void setIdHierarchie(int idHierarchie) {
        this.idHierarchie = idHierarchie;
    }
    public void setNomHierarchie(String nomHierarchie) {
        this.nomHierarchie = nomHierarchie;
    }

    public static void main(String[] args) {
        try {
            Connection c = Database.getConnection();
            List<Hierarchie> ls = Hierarchie.getAll(null);
            System.out.println(new Gson().toJson(ls));
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
