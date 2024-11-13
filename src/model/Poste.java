package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Poste {

    private int idPoste;
    private String nomPoste;
    private Departement departement;
    private List<CompetenceRequise> listCompetence;

    // CONSTRUCTOR
    public Poste() {
        this.setListCompetence(new ArrayList<>());
    }

    public Poste(String nom, int idDepartement) throws SQLException {
        this.setListCompetence(new ArrayList<>());
        this.setNomPoste(nom);
        this.setDepartement(idDepartement);
    }

    // CRUD
    public static List<Poste> getAll() throws SQLException {
        List<Poste> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM poste";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Poste d = new Poste();
                d.setIdPoste(rs.getInt(1));
                d.setNomPoste(rs.getString(2));
                d.setDepartement(rs.getInt(3));
                result.add(d);
            }
        }catch (SQLException e) {
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

    public static Poste getById(int id) throws SQLException {
        Poste result = null;
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM poste WHERE id_poste = ?";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();

            if (rs.next()) {
                result = new Poste();
                result.setIdPoste(rs.getInt(1));
                result.setNomPoste(rs.getString(2));
                result.setDepartement(rs.getInt(3));
            }
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    // GETTERS AND SETTERS
    public int getIdPoste() {
        return idPoste;
    }

    public String getNomPoste() {
        return nomPoste;
    }

    public Departement getDepartement() {
        return departement;
    }
    public List<CompetenceRequise> getListCompetence(){
        return this.listCompetence;
    }

    public void setIdPoste(int idPoste) {
        this.idPoste = idPoste;
    }

    public void setNomPoste(String nomPoste) {
        this.nomPoste = nomPoste;
    }

    public void setDepartement(int iddepartement) throws SQLException {
        this.departement = Departement.getById(iddepartement);
    }

    public void setListCompetence(List<CompetenceRequise> ls){
        this.listCompetence = ls;
    }
}
