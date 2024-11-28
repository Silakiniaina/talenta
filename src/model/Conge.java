package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Conge {
    
    int idConge;
    Employe employe;
    Date dateDebut;
    Date dateFin;
    TypeConge typeConge;

    public int getIdConge() {
        return idConge;
    }
    public void setIdConge(int idConge) {
        this.idConge = idConge;
    }
    public Employe getEmploye() {
        return employe;
    }
    public void setEmploye(Employe employe) {
        this.employe = employe;
    }
    public Date getDateDebut() {
        return dateDebut;
    }
    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
    public Date getDateFin() {
        return dateFin;
    }
    public void setDateFin(Date dateFin) {
        this.dateFin = dateFin;
    }
    public TypeConge getTypeConge() {
        return typeConge;
    }
    public void setTypeConge(TypeConge typeConge) {
        this.typeConge = typeConge;
    }

    public Conge(){}

    public Conge (Employe emp, Date debut, Date fin, TypeConge tp){
        this.setEmploye(emp);
        this.setDateDebut(debut);
        this.setDateFin(fin);
        this.setTypeConge(tp);
    }

    public Conge (Employe emp, Date debut, Date fin, int idTypeConge) throws SQLException{
        this.setEmploye(emp);
        this.setDateDebut(debut);
        this.setDateFin(fin);
        this.setTypeConge(TypeConge.getById(idTypeConge));
    }

    public static List<Conge> getPlanning() throws SQLException {
        List<Conge> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM conge WHERE date_debut IS NOT NULL AND date_fin IS NOT NULL";

        try {
            c = Database.getConnection(); 
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Conge conge = new Conge();
                conge.setIdConge(rs.getInt("id_conge"));
                
                Employe employe = Employe.getById(c, rs.getInt("id_employe"));
                conge.setEmploye(employe);
                
                conge.setDateDebut(rs.getDate("date_debut"));
                conge.setDateFin(rs.getDate("date_fin"));
                
                TypeConge typeConge = TypeConge.getById(rs.getInt("id_type_conge"));
                conge.setTypeConge(typeConge);

                result.add(conge);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (prstm != null) prstm.close();
            if (c != null) c.close();
        }
        return result;
    }
}
