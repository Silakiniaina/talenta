package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.classification.CategorieProfessionnelle;
import model.classification.Hierarchie;
import model.utils.Database;

public class Poste {

    private int idPoste;
    private String nomPoste;
    private Departement departement;
    private int anneeExperienceRequise;
    private Hierarchie hierarchie;
    private List<Competence> listCompetence;
    private List<Education> listEducation;
    private List<Experience> listExperience;
    private List<CategorieProfessionnelle> listCategorieProfessionnelles;

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
    public List<Poste> getAll(Connection c) throws SQLException {
        List<Poste> result = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM poste";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            Competence comp = new Competence();
            Education edu = new Education();
            Experience exp = new Experience();
            CategorieProfessionnelle csp = new CategorieProfessionnelle();
            while (rs.next()) {
                Poste d = new Poste();
                d.setIdPoste(rs.getInt(1));
                d.setNomPoste(rs.getString(2));
                d.setDepartement(rs.getInt(3));
                d.setAnneeExperienceRequise(rs.getInt(4));
                d.setHierarchie(c, rs.getInt(5));
                d.setListCompetence(comp.getAllByPoste(c,d.getIdPoste()));
                d.setListEducation(edu.getAllByPoste(c, d.getIdPoste()));
                d.setListExperience(exp.getAllByPoste(c, d.getIdPoste()));
                d.setListCategorieProfessionnelles(csp.getAllByPoste(c, d.getIdPoste()));
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
            if(c != null && isNewConnection){
                c.close();
            }
        }
        return result;
    }

    public Poste getById(Connection c, int id) throws SQLException {
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM poste WHERE id_poste = ?";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();

            
            if (rs.next()) {
                Competence comp = new Competence();
                Education edu = new Education();
                Experience exp = new Experience();
                CategorieProfessionnelle csp = new CategorieProfessionnelle();

                this.setIdPoste(rs.getInt(1));
                this.setNomPoste(rs.getString(2));
                this.setDepartement(rs.getInt(3));
                this.setAnneeExperienceRequise(rs.getInt(4));
                this.setHierarchie(c, rs.getInt(5));
                this.setListCompetence(comp.getAllByPoste(c,this.getIdPoste()));
                this.setListEducation(edu.getAllByPoste(c, this.getIdPoste()));
                this.setListExperience(exp.getAllByPoste(c,this.getIdPoste()));
                this.setListCategorieProfessionnelles(csp.getAllByPoste(c, this.getIdPoste()));

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

    public Poste insert(Connection c) throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement prstm = null;
        String query = "INSERT INTO poste(nom,id_departement,annees_experience_requises,id_hierarchie) VALUES (?, ?, ?, ?)";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prstm.setString(1, this.getNomPoste());
            prstm.setInt(2, this.getDepartement().getIdDepartement());
            prstm.setInt(3, this.getAnneeExperienceRequise());
            prstm.setInt(4, this.getHierarchie().getIdHierarchie());
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
            if(c != null && isNewConnection){
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
            for (Competence competence : this.getListCompetence()) {
                String sql = "INSERT INTO competence_requise_poste (id_poste, id_competence) VALUES (?, ?)";
                st = c.prepareStatement(sql);

                st.setInt(1, this.getIdPoste());
                st.setInt(2, competence.getIdCompetence());
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

    public List<CategorieProfessionnelle> getListCategorieProfessionnelles() {
        return listCategorieProfessionnelles;
    }

    public void setListCategorieProfessionnelles(List<CategorieProfessionnelle> listCategorieProfessionnelles) {
        this.listCategorieProfessionnelles = listCategorieProfessionnelles;
    }

    public String getNomPoste() {
        return nomPoste;
    }

    public Departement getDepartement() {
        return departement;
    }
    public List<Competence> getListCompetence(){
        return this.listCompetence;
    }
    public List<Education> getListEducation(){
        return this.listEducation;
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

    public void setListCompetence(List<Competence> ls){
        this.listCompetence = ls;
    }

    public void setListEducation(List<Education> ls){
        this.listEducation = ls;
    }

    public List<Experience> getListExperience() {
        return listExperience;
    }

    public void setListExperience(List<Experience> listExperience) {
        this.listExperience = listExperience;
    }

    public Hierarchie getHierarchie() {
        return hierarchie;
    }

    public void setHierarchie(Connection c, int hierarchie) throws SQLException{
        this.hierarchie = new Hierarchie().getById(c, hierarchie);
    }

    public int getAnneeExperienceRequise() {
        return anneeExperienceRequise;
    }

    public void setAnneeExperienceRequise(int anneeExperienceRequise) {
        this.anneeExperienceRequise = anneeExperienceRequise;
    }


    public static void main(String[] args) {
        try {
            Connection c = Database.getConnection();
            List<Poste> ls = new Poste().getAll(c);
            System.out.println(new Gson().toJson(ls));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
