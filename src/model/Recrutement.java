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
    private List<Candidat> listCandidats;

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
                d.fetchListCandidat(c);

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

    public Recrutement getById(Connection c) throws SQLException{
        PreparedStatement prstm = null;
        boolean isNewConnection = false; 
        ResultSet rs = null;
        String query = "SELECT * FROM recrutement WHERE id_recrutement = ?";
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getIdRecrutement());
            rs = prstm.executeQuery();

            if(rs.next()) {
                this.setIdRecrutement(rs.getInt(1));
                this.setDateDebut(rs.getDate(2));
                this.setDateFin(rs.getDate(3));
                this.setNombre(rs.getInt(4));
                this.setPoste(rs.getInt(5));
                this.fetchListCandidat(c);
            }
            return this;
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

    public void fetchListCandidat(Connection c) throws SQLException{
        List<Candidat> result = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT id_candidat FROM v_recrutement_candidat WHERE id_recrutement = ?";
        try {
            if(c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getIdRecrutement());
            rs = prstm.executeQuery();

            while (rs.next()) {
                Candidat cd = new Candidat();
                result.add(cd.getById(c, rs.getInt(1)));
            }
            this.setListCandidats(result);
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

    public int getNombreCandidature(Connection c) throws SQLException{
        int result = 0;
        PreparedStatement prstm = null; 
        ResultSet rs = null; 
        boolean isNewConnection = false;
        String sql = "SELECT DISTINCT COUNT(id_candidat) FROM recrutement_candidat WHERE id_recrutement = ?";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(sql);
            prstm.setInt( 1, this.getIdRecrutement());

            rs = prstm.executeQuery();
            if(rs.next()){
                result = rs.getInt(1);
            }

            return result;
        } catch (SQLException e) {
            throw e;
        } finally{
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
    public List<Candidat> getListCandidats() {
        return listCandidats;
    }
    
    public void setListCandidats(List<Candidat> listCandidats) {
        this.listCandidats = listCandidats;
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
