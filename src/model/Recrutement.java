package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Recrutement {
    
    private int idRecrutement;
    private Date dateDebut;
    private Date dateFin;
    private int nombre;
    private Poste poste;
    private Status status;

    // CONSTRUCTORS
    public Recrutement(){

    }

    // CRUD
    public static List<Recrutement> getAll() throws SQLException{
        List<Recrutement> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM recrutement";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Recrutement d = new Recrutement();
                d.setIdRecrutement(rs.getInt(1));
                d.setDateDebut(rs.getDate(2));
                d.setDateFin(rs.getDate(3));
                d.setNombre(rs.getInt(4));
                d.setPoste(rs.getInt(5));
                d.setStatus(rs.getInt(6));
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
            if(c != null){
                c.close();
            }
        }
        return result;
    }

    public static Recrutement getById(int id) throws SQLException{
        Recrutement result = null;
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM recrutement WHERE id_recrutement = ?";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();

            if(rs.next()) {
                result = new Recrutement();
                result.setIdRecrutement(rs.getInt(1));
                result.setDateDebut(rs.getDate(2));
                result.setDateFin(rs.getDate(3));
                result.setNombre(rs.getInt(4));
                result.setPoste(rs.getInt(5));
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
            if(c != null){
                c.close();
            }
        }
        return result;
    }

    public Recrutement insert() throws SQLException{
        Connection c = null; 
        PreparedStatement prstm = null;
        String query = "INSERT INTO recrutement(date_debut_recrutement, date_fin_recrutement, nombre, id_poste) VALUES (?, ?, ?, ?)";
        try {
            c = Database.getConnection();
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prstm.setDate(1, this.getDateDebut());
            prstm.setDate(2, this.getDateFin());
            prstm.setInt(3, this.getNombre());
            prstm.setInt(4, this.getPoste().getIdPoste());

            int affectedRows = prstm.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = prstm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        this.setIdRecrutement(id);
                    }
                }
            }
            c.commit();
            return this;
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
    public int getIdRecrutement() {
        return idRecrutement;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public int getNombre() {
        return nombre;
    }
    public Poste getPoste() {
        return poste;
    }
    public Status getStatus(){
        return this.status;
    }


    public void setIdRecrutement(int idRecrutement) {
        this.idRecrutement = idRecrutement;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public void setDateDebut(String dt){
        this.dateDebut = Date.valueOf(dt);
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public void setDateFin(String str){
        this.dateFin = Date.valueOf(str);
    }
    public void setNombre(int nombre) {
        this.nombre = nombre;
    }
    public void setPoste(int idposte)throws SQLException {
        this.poste = Poste.getById(idposte);
    }
    public void setStatus(int idstatus)throws SQLException{
        this.status = Status.getById(idstatus);
    }
}