package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.utils.Database;

public class RecrutementCandidat {
    
    private Recrutement recrutement;
    private Candidat candidat;
    private Date datePostule;

    // CONSTRUCTORS

    public RecrutementCandidat(){

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
    public void setRecrutement(Connection c,int idrecrutement) throws SQLException{
        Recrutement r = new Recrutement();
        r.setIdRecrutement(idrecrutement);

        this.recrutement = r.getById(c);
    }
    public void setCandidat(Connection con, int candidat) throws SQLException{
        Candidat c = new Candidat();
        this.candidat = c.getById(con,candidat);
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
}
