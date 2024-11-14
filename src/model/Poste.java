package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                d.setListCompetence(CompetenceRequise.getAllByPoste(d.getIdPoste()));
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
                result.setListCompetence(CompetenceRequise.getAllByPoste(result.getIdPoste()));

            }
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    public Poste insert() throws SQLException{
        Connection c = null; 
        PreparedStatement prstm = null;
        String query = "INSERT INTO poste(nom,id_departement) VALUES (?, ?)";
        try {
            c = Database.getConnection();
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prstm.setString(1, this.getNomPoste());
            prstm.setInt(2, this.getDepartement().getIdDepartement());

            int affectedRows = prstm.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = prstm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        this.setIdPoste(id);
                    }
                }
            }

            c.commit();
            this.insertCompetence();

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

    public void insertCompetence() throws SQLException {
        Connection c = null;
        PreparedStatement st = null;

        try {
            c = Database.getConnection();
            c.setAutoCommit(false);
            for (CompetenceRequise competence : this.getListCompetence()) {
                String sql = "INSERT INTO competence_requise (id_poste, id_competence, experience) VALUES (?, ?, ?)";
                st = c.prepareStatement(sql);

                st.setInt(1, this.getIdPoste());
                st.setInt(2, competence.getCompetence().getIdCompetence());
                st.setInt(3, competence.getExperience());
                st.executeUpdate();
            }

            c.commit();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        } finally {
            if (st != null)
                st.close();
            if (c != null)
                c.close();
        }
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
