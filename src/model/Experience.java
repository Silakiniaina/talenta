package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Experience {
    
    private int idExperience;
    private Date dateDebut;
    private Date dateFin;
    private String description;

    // CONSTRUCTOR
    public Experience(){

    }


    public static List<Experience> getAllByCandidat(Connection conn, int idCandidat)throws SQLException{
        List<Experience> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        boolean isNewConnection = false;
        String query = "SELECT id_experience_candidat FROM experience_candidat WHERE id_candidat = ?";
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

            Experience d = null;
            while (rs.next()) {
                d = new Experience();
                d.setIdExperience(rs.getInt(1));
                d.setDateDebut(rs.getDate(3));
                d.setDateFin(rs.getDate(4));
                d.setDescription(rs.getString(5));
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

    // public static Experience getById(Connection conn ,int id) throws SQLException{
    //     Experience result = null;
    //      Connection c = null;
    //      PreparedStatement prstm = null; 
    //      ResultSet rs = null;
    //      boolean isNewConnection = false;
    //      String query = "SELECT * FROM competence WHERE id_competence = ?";
    //      try {
    //          if(conn == null){
    //              c = Database.getConnection();
    //              isNewConnection = true;
    //          }else{
    //              c = conn;
    //          }
    //          prstm = c.prepareStatement(query);
    //          prstm.setInt(1, id);
    //          rs = prstm.executeQuery();
 
    //          if(rs.next()) {
    //             result = new Experience();
    //             result.setIdExperience(rs.getInt(1));
    //             result.setDateDebut(rs.getDate(3));
    //             result.setDateFin(rs.getDate(4));
    //             result.setDescription(rs.getString(5));
    //         }
    //      } catch (SQLException e) {
    //          throw e;
    //      }finally{
    //          if(rs != null){
    //              rs.close();
    //          }
    //          if(prstm != null){
    //              prstm.close();
    //          }
    //          if( c != null && isNewConnection){
    //              c.close();
    //          }
    //      }
    //      return result;
    //  }
    
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
    public String getDescription() {
        return description;
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
    public void setDescription(String description) {
        this.description = description;
    }
}
