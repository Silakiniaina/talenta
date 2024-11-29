package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Education {

    private TypeDiplome typeDiplome;
    private BrancheEducation brancheEducation;

    //ACTION
    public static List<Education> getAllByCandidat(Connection conn, int idCandidat)throws SQLException{
        List<Education> result = new ArrayList<>();
        Connection c = null;
    // ACTION
    public List<Education> getAllByPoste(Connection c, int idPoste) throws SQLException{
        List<Education> result = new ArrayList<>();
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT id_type_diplome, id_branche_education FROM diplome_requis_poste WHERE id_poste = ?";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, idPoste);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Education d = new Education();
                d.setTypeDiplome(c, rs.getInt(1));
                d.setBrancheEducation(c, rs.getInt(2));
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
            if(c != null && isNewConnection){
                c.close();
            }
        }
        return result;
    }

    // GETTERS AND SETTERS
    public TypeDiplome getTypeDiplome() {
        return typeDiplome;
    }
    public BrancheEducation getBrancheEducation() {
        return brancheEducation;
    }

    public void setTypeDiplome(Connection c, int typeDiplome) throws SQLException{
        TypeDiplome d = new TypeDiplome();
        this.typeDiplome = d.getById(c, typeDiplome);
    }
    public void setBrancheEducation(Connection c,int brancheEducation) throws SQLException{
        BrancheEducation b = new BrancheEducation();
        this.brancheEducation = b.getById(c, brancheEducation);
    }
    
}