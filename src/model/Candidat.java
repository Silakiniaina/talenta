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

import model.utils.Database;

public class Candidat {
    
    private int idCandidat;
    private String nomCandidat;
    private String prenomCandidat;
    private Date dateNaissance;
    private String adresse;
    private Genre genre;
    private List<CompetenceCandidat> listCompetence;

    // CONSTRUCTORS
    public Candidat(){
        this.setListCompetence(new ArrayList<>());
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
                d.setListCompetence(CompetenceCandidat.getAllByCandidat(d.getIdCandidat()));
                result.add(d);
            }
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    public static Candidat getById(int id) throws SQLException{
        Candidat result = null;
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM v_candidat WHERE id_candidat = ?";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();

            if (rs.next()) {
                result = new Candidat();
                result.setIdCandidat(rs.getInt(1));
                result.setNomCandidat(rs.getString(2));
                result.setPrenomCandidat(rs.getString(3));
                result.setDateNaissance(rs.getDate(4));
                result.setAdresse(rs.getString(5));
                result.setGenre(rs.getInt(6));
                result.setListCompetence(CompetenceCandidat.getAllByCandidat(result.getIdCandidat()));
            }
        } catch (SQLException e) {
            throw e;
        }
        return result;
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
            this.insertCompetence();
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


    public void insertCompetence() throws SQLException {
        Connection c = null;
        PreparedStatement st = null;

        try {
            c = Database.getConnection();
            c.setAutoCommit(false);
            for (CompetenceCandidat competence : this.getListCompetence()) {
                String sql = "INSERT INTO competence_candidat (id_candidat, id_competence, experience) VALUES (?, ?, ?)";
                st = c.prepareStatement(sql);
                st.setInt(1, this.getIdCandidat());
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

    public void setListCompetence(List<CompetenceCandidat> listCompetence) {
        this.listCompetence = listCompetence;
    }

    public List<CompetenceCandidat> getListCompetence() {
        return listCompetence;
    }   
}
