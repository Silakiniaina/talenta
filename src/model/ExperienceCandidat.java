package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class ExperienceCandidat {
    
    private int idExperience;
    private Date dateDebut;
    private Date dateFin;
    private Specialite specialite;
    private String entreprise;

    // CONSTRUCTOR
    public ExperienceCandidat(){

    }


    public List<ExperienceCandidat> getAllByCandidat(Connection conn, int idCandidat)throws SQLException{
        @SuppressWarnings({ "unchecked", "rawtypes" })
        List<ExperienceCandidat> result = new ArrayList();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        boolean isNewConnection = false;
        String query = "SELECT * FROM experience_candidat WHERE id_candidat = ?";
        try {

            if(conn == null){
                c = Database.getConnection();
                isNewConnection = true;
            }else{
                c = conn;
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, idCandidat);
            rs = prstm.executeQuery();

            ExperienceCandidat d = null;
            while (rs.next()) {
                d = new ExperienceCandidat();
                d.setIdExperience(rs.getInt(1));
                d.setDateDebut(rs.getDate(3));
                d.setDateFin(rs.getDate(4));
                d.setSpecialite(c, rs.getInt(5));
                d.setEntreprise(rs.getString(6));
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
            if(c != null && isNewConnection){
                c.close();
            }
        }
    }
    
    // GETTERS AND SETTERS
    public int getIdExperience() {
        return idExperience;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public Specialite getSpecialite(){
        return this.specialite;
    }
    public String getEntreprise(){
        return this.entreprise;
    }

    public void setIdExperience(int idExperience) {
        this.idExperience = idExperience;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public void setSpecialite(Connection c , int id)throws SQLException{
        Specialite s = new Specialite();
        this.specialite = s.getById(c, id);
    }
    public void setEntreprise(String str){
        this.entreprise = str;
    }
}
