package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class RecrutementCandidat {
    
    private Recrutement recrutement;
    private Candidat candidat;
    private Date datePostule;

    // CONSTRUCTORS

    public RecrutementCandidat(){

    }

    public RecrutementCandidat(int recrutement, int candidat, Date dt)throws SQLException{
        this.setRecrutement(recrutement);
        this.setCandidat(candidat);
        this.setDatePostule(dt);
    }
    
    public void insert() throws SQLException{
        Connection c = null; 
        PreparedStatement prstm = null;
        String query = "INSERT INTO recrutement_candidat(id_recrutement, id_candidat, date_postule) VALUES (?, ?, ?)";
        try {
            c = Database.getConnection();
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getRecrutement().getIdRecrutement());
            prstm.setInt(2, this.getCandidat().getIdCandidat());
            prstm.setDate(3, this.getDatePostule());
            prstm.executeUpdate();
            c.commit();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        }finally{
            if(prstm != null){
                prstm.close();
            }
            if(c != null){
                c.close();
            }
        }
    }

    public static List<Candidat> getCandidatByRecrutement(int idRecrutement) throws SQLException{
        List<Candidat> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT id_candidat FROM v_recrutement_candidat WHERE id_recrutement = ?";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setInt(1, idRecrutement);
            rs = prstm.executeQuery();

            while (rs.next()) {
                System.out.println(rs.getInt(1));
                Candidat d = Candidat.getById(rs.getInt(1));
                result.add(d);
            }
            return result;
        } catch (SQLException e) {
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
    }

    // GETTERS AND SETTERS
    public Recrutement getRecrutement() {
        return recrutement;
    }
    public Candidat getCandidat() {
        return candidat;
    }
    public Date getDatePostule() {
        return datePostule;
    }
    public void setRecrutement(int recrutement) throws SQLException {
        this.recrutement = Recrutement.getById(recrutement);
    }
    public void setCandidat(int candidat) throws SQLException{
        this.candidat = Candidat.getById(candidat);
    }
    public void setCandidat(Candidat d){
        this.candidat = d;
    }
    public void setDatePostule(Date datePostule) {
        this.datePostule = datePostule;
    }
    public void setDatePostule(String dt){
        this.datePostule = Date.valueOf(dt);
    }

    public static void main(String[] args) {
        try {
            List<Candidat> c = RecrutementCandidat.getCandidatByRecrutement(1);
            for(Candidat cand : c){
                System.out.println(cand.getIdCandidat());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
