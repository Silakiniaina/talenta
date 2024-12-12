package model.employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.Employe;
import model.classification.Hierarchie;
import model.utils.Database;

public class Presence {
    
    private int idPresence;
    private Employe employe;
    private Timestamp dateHeureEntree;
    private Timestamp dateHeureSortie;

    public List<Presence> getAll(Connection c) throws SQLException{
        List<Presence> result = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM presence_employe";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Presence d = new Presence();
                d.setIdPresence(rs.getInt(1));
                d.setEmploye(c, rs.getInt(2));
                d.setDateHeureEntree(rs.getTimestamp(3));
                d.setDateHeureSortie(rs.getTimestamp(4));
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
    public int getIdPresence() {
        return idPresence;
    }
    public Employe getEmploye() {
        return employe;
    }
    public Timestamp getDateHeureEntree() {
        return dateHeureEntree;
    }
    public Timestamp getDateHeureSortie() {
        return dateHeureSortie;
    }
    public void setIdPresence(int idPresence) {
        this.idPresence = idPresence;
    }
    public void setEmploye(Connection c, int employe) throws SQLException{
        this.employe = Employe.getById(c, employe);
    }
    public void setDateHeureEntree(Timestamp dateHeureEntree) {
        this.dateHeureEntree = dateHeureEntree;
    }
    public void setDateHeureSortie(Timestamp dateHeureSortie) {
        this.dateHeureSortie = dateHeureSortie;
    }

    public static void main(String[] args) {
        try {
            Connection c = Database.getConnection();

            List<Presence> ls = new Presence().getAll(c);
            Gson g = new Gson();
            for(Presence p : ls){
                System.out.println(g.toJson(p));
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
