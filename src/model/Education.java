package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Education {
    
    private int idEducation;
    private Date dateDebut; 
    private Date dateFin;
    private TypeDiplome typeDiplome;
    private String nomEcole;

    // CONSTRUCTOR
    public Education(){

    }

    //ACTION
    public static List<Education> getAllByCandidat(Connection conn, int idCandidat)throws SQLException{
        List<Education> result = new ArrayList();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        boolean isNewConnection = false;
        String query = "SELECT id_education_candidat FROM education_candidat WHERE id_candidat = ?";
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

            Education d = null;
            while (rs.next()) {
                d = new Education();
                d.setIdEducation(rs.getInt(1));
                d.setDateDebut(rs.getDate(3));
                d.setDateFin(rs.getDate(4));
                d.setTypeDiplome(rs.getInt(5));
                d.setNomEcole(rs.getString(6));
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
    public int getIdEducation() {
        return idEducation;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public TypeDiplome getTypeDiplome() {
        return typeDiplome;
    }
    public String getNomEcole() {
        return nomEcole;
    }

    public void setIdEducation(int idEducation) {
        this.idEducation = idEducation;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public void setTypeDiplome(int typeDiplome) throws SQLException{
        this.typeDiplome = TypeDiplome.getById(typeDiplome);
    }
    public void setNomEcole(String nomEcole) {
        this.nomEcole = nomEcole;
    }
    
}
