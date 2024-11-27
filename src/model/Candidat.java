package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import model.utils.Database;

public class Candidat {
    
    private int idCandidat;
    private String nomCandidat;
    private String prenomCandidat;
    private Date dateNaissance;
    private String adresse;
    private Genre genre;
    private List<Competence> listCompetence;
    private List<Experience> listExperience;
    private List<Education> listEducation;

    // CONSTRUCTORS
    public Candidat(){
        this.setListCompetence(new ArrayList<>());
        this.setListExperience(new ArrayList<>());
        this.setListEducation(new ArrayList<>());
    }

    public Candidat(String nom, String prenom, String dtn, int genre, String adresse) throws SQLException{
        this.setListCompetence(new ArrayList<>());
        this.setNomCandidat(nom);
        this.setPrenomCandidat(prenom);
        this.setDateNaissance(dtn);
        this.setGenre(genre);
        this.setAdresse(adresse);
    }

    // ACTIONS
    public static List<Candidat> getAll() throws SQLException{
        List<Candidat> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM v_candidat";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Candidat d = new Candidat();
                d.setIdCandidat(rs.getInt(1));
                d.setNomCandidat(rs.getString(2));
                d.setPrenomCandidat(rs.getString(3));
                d.setDateNaissance(rs.getDate(4));
                d.setAdresse(rs.getString(5));
                d.setGenre(rs.getInt(6));
                d.getCompetences(c);
                d.getExperiences(c);
                d.getEducations(c);
                result.add(d);
            }
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    public Candidat getById(Connection c) throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM v_candidat WHERE id_candidat = ?";
        try {
            if( c == null){
                c = Database.getConnection();
                isNewConnection = true;
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getIdCandidat());
            rs = prstm.executeQuery();

            if (rs.next()) {
                this.setIdCandidat(rs.getInt(1));
                this.setNomCandidat(rs.getString(2));
                this.setPrenomCandidat(rs.getString(3));
                this.setDateNaissance(rs.getDate(4));
                this.setAdresse(rs.getString(5));
                this.setGenre(rs.getInt(6));
                this.getCompetences(c);
                this.getExperiences(c);
                this.getEducations(c);
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

    public Candidat insert() throws SQLException{
        Connection c = null; 
        PreparedStatement prstm = null;
        String query = "INSERT INTO candidat(nom, prenom, date_naissance, id_genre,adresse) VALUES (?, ?, ?, ?, ?)";
        try {
            c = Database.getConnection();
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            prstm.setString(1, this.getNomCandidat());
            prstm.setString(2, this.getPrenomCandidat());
            prstm.setDate(3, this.getDateNaissance());
            prstm.setInt(4, this.getGenre().getIdGenre());
            prstm.setString(5, this.getAdresse());

            int affectedRows = prstm.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = prstm.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int id = generatedKeys.getInt(1);
                        this.setIdCandidat(id);
                    }
                }
            }
            c.commit();
            return this;
        } catch (SQLException e) {
            c.rollback();
            throw e;
        } finally{
            if(prstm != null){
                prstm.close();
            }
            if(c != null){
                c.close(); 
            }
        }
    }

    public void insertIntoEmploye(Recrutement r) throws SQLException{
        Connection c = null; 
        PreparedStatement prstm = null;
        String query = "INSERT INTO employe(id_candidat, date_embauche, id_poste) VALUES (?, ?, ?)";
        try {
            c = Database.getConnection();
            c.setAutoCommit(false);

            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getIdCandidat());
            prstm.setDate(2, Date.valueOf(LocalDate.now()));
            prstm.setInt(3,r.getPoste().getIdPoste());

            prstm.executeUpdate();

            c.commit();
        } catch (SQLException e) {
            c.rollback();
            throw e;
        } finally{
            if(prstm != null){
                prstm.close();
            }
            if(c != null){
                c.close(); 
            }
        }
    }


    public void insertCompetence(Competence comp) throws SQLException {
        Connection c = null;
        PreparedStatement st = null;

        try {
            c = Database.getConnection();
            c.setAutoCommit(false);
            if(comp != null){
                String sql = "INSERT INTO competence_candidat (id_candidat, id_competence) VALUES (?, ?)";
                st = c.prepareStatement(sql);
                st.setInt(1, this.getIdCandidat());
                st.setInt(2, comp.getIdCompetence());
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

    public static Candidat login(String email, String password) throws SQLException{
        Candidat result = null; 
        Connection c = null; 
        PreparedStatement prstm = null;
        ResultSet rs = null; 
        String query = "SELECT * FROM v_candidat WHERE email = ? AND mdp = ? ";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setString(1, email);
            prstm.setString(2, password);

            rs = prstm.executeQuery();

            if(rs.next()){
                result = new Candidat();
                result.setIdCandidat(rs.getInt(1));
                result.setNomCandidat(rs.getString(2));
                result.setPrenomCandidat(rs.getString(3));
                result.setDateNaissance(rs.getDate(4));
                result.setAdresse(rs.getString(5));
                result.setGenre(rs.getInt(6));
                result.getCompetences(c);
                result.getExperiences(c);
                result.getEducations(c);
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

    public void getCompetences(Connection conn)throws SQLException{
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        boolean isNewConnection = false;
        String query = "SELECT id_competence FROM competence_candidat WHERE id_candidat = ?";
        try {

            if(conn == null){
                c = Database.getConnection();
                isNewConnection = true;
            }else{
                c = conn;
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, this.getIdCandidat());
            rs = prstm.executeQuery();

            Competence d = null;
            this.setListCompetence(new ArrayList<>());

            while (rs.next()) {
                d = Competence.getById(c, rs.getInt(1));
                this.getListCompetence().add(d);
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
            if(c != null && isNewConnection){
                c.close();
            }
        }
    }

    public void getExperiences(Connection c)throws SQLException{
        this.setListExperience(Experience.getAllByCandidat(c,this.getIdCandidat()));
    }

    public void getEducations(Connection c)throws SQLException{
        this.setListEducation(Education.getAllByCandidat(c,this.getIdCandidat()));
    }



    // GETTERS AND SETTERS
    public void setGenre(int idGenre) throws SQLException{
        this.genre = Genre.getById(idGenre);
    }
    public Genre getGenre() {
        return genre;
    }
    public int getIdCandidat() {
        return idCandidat;
    }
    public String getNomCandidat() {
        return nomCandidat;
    }
    public String getPrenomCandidat() {
        return prenomCandidat;
    }
    public Date getDateNaissance() {
        return dateNaissance;
    }
    public String getAdresse() {
        return adresse;
    
    }
    public List<Experience> getListExperience(){
        return this.listExperience;
    }

    public List<Education> getListEducation(){
        return this.listEducation;
    }

    public void setIdCandidat(int idCandidat) {
        this.idCandidat = idCandidat;
    }
    public void setNomCandidat(String nomCandidat) {
        this.nomCandidat = nomCandidat;
    }
    public void setPrenomCandidat(String prenomCandidat) {
        this.prenomCandidat = prenomCandidat;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }
    public void setDateNaissance(String dt){
        this.dateNaissance = Date.valueOf(dt);
    }
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setListCompetence(List<Competence> listCompetence) {
        this.listCompetence = listCompetence;
    }

    public List<Competence> getListCompetence() {
        return listCompetence;
    }   

    public void setListExperience(List<Experience> ls){
        this.listExperience = ls;
    }

    public void setListEducation(List<Education> ls){
        this.listEducation = ls;
    }

    public static void main(String[] args) {
        try {
           
            Recrutement r = new Recrutement();
            r.setIdRecrutement(1);

            r = r.getById(null);
            System.out.println(new Gson().toJson(r.getListCandidats()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
