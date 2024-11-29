package model;

import java.sql.Connection;
import java.sql.SQLException;

public class Education {

    private TypeDiplome typeDiplome;
    private BrancheEducation brancheEducation;


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