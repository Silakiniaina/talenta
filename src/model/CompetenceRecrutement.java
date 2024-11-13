package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class CompetenceRecrutement {
    
    private Competence competence;
    private int experience;

    // CONSTRUCTORS
    public CompetenceRecrutement(){

    }

    public CompetenceRecrutement(int competence, int experience) throws SQLException{
        this.setCompetence(competence);
        this.setExperience(experience);
    }


    // CRUD
    public static List<CompetenceRecrutement> getAllByRecrutement(int idRecrutement) throws SQLException{
        List<CompetenceRecrutement> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT id_competence, experience FROM competence_recrutement WHERE id_recrutement = ?";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setInt(1, idRecrutement);
            rs = prstm.executeQuery();

            while (rs.next()) {
                CompetenceRecrutement d = new CompetenceRecrutement();
                d.setCompetence(rs.getInt(1));
                d.setExperience(rs.getInt(2));
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

    // GETTERS AND SETTERS
    public Competence getCompetence() {
        return competence;
    }
    public int getExperience() {
        return experience;
    }
    public void setCompetence(int id_competence) throws SQLException{
        this.competence = Competence.getById(id_competence);
    }
    public void setExperience(int experience) {
        this.experience = experience;
    }
}
