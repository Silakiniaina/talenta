package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Experience {
    
    private Specialite specialite;
    private int dureeMois;
    private boolean obligatoire;

    public List<Experience> getAllByPoste(Connection c, int idPoste) throws SQLException{
        List<Experience> result = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT id_specialite, duree, obligatoire FROM experience_requise_poste WHERE id_poste = ?";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, idPoste);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Experience d = new Experience();
                d.setSpecialite(c, rs.getInt(1));
                d.setDureeMois(rs.getInt(2));
                d.setObligatoire(rs.getBoolean(3));
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
    public void setSpecialite(Connection c, int specialite) throws SQLException{
        Specialite s = new Specialite();
        this.specialite = s.getById(c, specialite);
    }
    public void setDureeMois(int dureeMois) {
        this.dureeMois = dureeMois;
    }
    public void setObligatoire(boolean obligatoire) {
        this.obligatoire = obligatoire;
    }
    public boolean isObligatoire() {
        return obligatoire;
    }
    public Specialite getSpecialite() {
        return specialite;
    }
    public int getDureeMois() {
        return dureeMois;
    } 
}
