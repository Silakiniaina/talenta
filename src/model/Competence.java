package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Competence {
    
    int id;
    TypeCompetence type;
    String details; 
    String nom;

    public int getId() {
        return id;
    }

    public TypeCompetence getType() {
        return type;
    }

    public String getDetails() {
        return details;
    }

    public String getNom(){
        return this.nom;
    }

    public void setNom (String nom){
        this.nom= nom;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(TypeCompetence type) {
        this.type = type;
    }

    public void setType(int idType) throws SQLException {
        this.type = TypeCompetence.getById(idType);
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public Competence (int id, String nom, String details, int idType) throws SQLException{
        this.setId(id);
        this.setNom(nom);
        this.setDetails(details);
        this.setType(idType);
    }

    public static Competence getById(int id) throws SQLException {
        Competence c = null; 
        Connection con = null; 
        PreparedStatement prstm = null; 
        ResultSet rs = null;

        try {
            con = Database.getConnection();
            prstm = con.prepareStatement("SELECT * FROM competence WHERE id= ? ");
            prstm.setInt(1, id);
            rs = prstm.executeQuery();
            if(rs.next()){
                c = new Competence(rs.getInt(1), rs.getString(2), rs.getString(4), rs.getInt(3));
            }
            return c;
        } catch (Exception e) {
            throw e;
        }finally{
            if(rs != null)rs.close();
            if(prstm != null)prstm.close();
            if(con !=  null) con.close();
        }
    }
    
    public static List<Competence> getAll() throws SQLException {
        List<Competence> competences = new ArrayList<>();
        Connection connex = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            connex = Database.getConnection();  
            String sql = "SELECT * FROM Competence";
            st = connex.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nom= rs.getString("nom");
                String details = rs.getString("details");
                int typeId = rs.getInt("id_typeCompetence");
                Competence competence = new Competence(id, nom, details, typeId);
                competences.add(competence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (connex != null) connex.close();
        }

        return competences;
    }

    public static List<Competence> getAllTechniqueProg() throws SQLException {
        List<Competence> competences = new ArrayList<>();
        Connection connex = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            connex = Database.getConnection();  
            String sql = "select* from Competence where id_typeCompetence=1 AND details='Programmation'";
            st = connex.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String details = rs.getString("details");
                String nom= rs.getString("nom");
                int typeId = rs.getInt("id_typeCompetence");
                Competence competence = new Competence(id, nom, details, typeId);
                competences.add(competence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (connex != null) connex.close();
        }

        return competences;
    }

    public static List<Competence> getAllTechniqueBase() throws SQLException {
        List<Competence> competences = new ArrayList<>();
        Connection connex = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            connex = Database.getConnection();  
            String sql = "select* from Competence where id_typeCompetence=1 AND details='Base de donnees'";
            st = connex.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String details = rs.getString("details");
                String nom= rs.getString("nom");
                int typeId = rs.getInt("id_typeCompetence");
                Competence competence = new Competence(id, nom, details, typeId);
                competences.add(competence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (connex != null) connex.close();
        }

        return competences;
    }

    public static List<Competence> getAllTechniqueOutil() throws SQLException {
        List<Competence> competences = new ArrayList<>();
        Connection connex = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            connex = Database.getConnection();  
            String sql = "select* from Competence where id_typeCompetence=1 AND details='Outil'";
            st = connex.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String details = rs.getString("details");
                String nom= rs.getString("nom");
                int typeId = rs.getInt("id_typeCompetence");
                Competence competence = new Competence(id, nom, details, typeId);
                competences.add(competence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (connex != null) connex.close();
        }

        return competences;
    }

    public static List<Competence> getAllDiplome() throws SQLException {
        List<Competence> competences = new ArrayList<>();
        Connection connex = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            connex = Database.getConnection();  
            String sql = "select* from Competence where id_typeCompetence=2";
            st = connex.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String details = rs.getString("details");
                String nom= rs.getString("nom");
                int typeId = rs.getInt("id_typeCompetence");
                Competence competence = new Competence(id, nom, details, typeId);
                competences.add(competence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (connex != null) connex.close();
        }

        return competences;
    }

    public static List<Competence> getAllLangue() throws SQLException {
        List<Competence> competences = new ArrayList<>();
        Connection connex = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            connex = Database.getConnection();  
            String sql = "select* from Competence where id_typeCompetence=3";
            st = connex.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String details = rs.getString("details");
                String nom= rs.getString("nom");
                int typeId = rs.getInt("id_typeCompetence");
                Competence competence = new Competence(id, nom, details, typeId);
                competences.add(competence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (connex != null) connex.close();
        }

        return competences;
    }

    public static List<Competence> getAllAutre() throws SQLException {
        List<Competence> competences = new ArrayList<>();
        Connection connex = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            connex = Database.getConnection();  
            String sql = "select* from Competence where id_typeCompetence=4";
            st = connex.prepareStatement(sql);
            rs = st.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String details = rs.getString("details");
                String nom= rs.getString("nom");
                int typeId = rs.getInt("id_typeCompetence");
                Competence competence = new Competence(id, nom, details, typeId);
                competences.add(competence);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (connex != null) connex.close();
        }

        return competences;
    }

}
