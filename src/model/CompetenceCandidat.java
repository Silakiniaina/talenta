package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class CompetenceCandidat {

    private Competence competence;
    private int experience;

    // CONSTRUCTORS
    public CompetenceCandidat(){

    }

    public CompetenceCandidat(int competence, int experience) throws SQLException{
        this.setCompetence(competence);
        this.setExperience(experience);
    }


    // CRUD
    public static List<CompetenceCandidat> getAllByCandidat(int idCandidat) throws SQLException{
        List<CompetenceCandidat> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT id_competence, experience FROM competence_candidat WHERE id_candidat = ?";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setInt(1, idCandidat);
            rs = prstm.executeQuery();

            while (rs.next()) {
                CompetenceCandidat d = new CompetenceCandidat();
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