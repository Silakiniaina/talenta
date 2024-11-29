package model;

import java.sql.*;

import model.utils.Database;

public class Contrat {
    private int idContrat;
    private Date dateDebutContrat;
    private double salaireBase;
    private Date dateFinContrat;
    private Candidat candidat;
    private TypeContrat typeContrat;
    
    // Constructeurs
    public Contrat() {}

    // ACTION
    public void insert(Recrutement r) throws SQLException {
        String sql = "INSERT INTO contrat (date_debut_contrat, salaire_base, date_fin_contrat, " +
            "id_candidat, id_type_contrat) VALUES (?, ?, ?, ?, ?)";
        Connection c = null; 
        PreparedStatement prstm = null; 
        
        try {
            c = Database.getConnection();
            c.setAutoCommit(false);

            prstm = c.prepareStatement(sql);
            prstm.setDate(1, this.getDateDebutContrat());
            prstm.setDouble(2, this.getSalaireBase());
            if (this.getDateFinContrat() != null) {
                prstm.setDate(3, this.getDateFinContrat());
            } else {
                prstm.setNull(3, Types.DATE);
            }
            prstm.setInt(4, this.getCandidat().getIdCandidat());
            prstm.setInt(5, this.getTypeContrat().getId_type_contrat());
            
            prstm.executeUpdate();
            c.commit();

            this.getCandidat().insertIntoEmploye(r);
        }catch(SQLException e){
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

    public int getIdContrat() {
        return idContrat;
    }

    public Date getDateDebutContrat() {
        return dateDebutContrat;
    }

    public double getSalaireBase() {
        return salaireBase;
    }

    public Date getDateFinContrat() {
        return dateFinContrat;
    }

    public Candidat getCandidat() {
        return candidat;
    }

    public TypeContrat getTypeContrat() {
        return typeContrat;
    }

    public void setIdContrat(int idContrat) {
        this.idContrat = idContrat;
    }

    public void setDateDebutContrat(Date dateDebutContrat) {
        this.dateDebutContrat = dateDebutContrat;
    }
    public void setDateDebutContrat(String dt){
        this.dateDebutContrat = Date.valueOf(dt);
    }

    public void setSalaireBase(double salaire_base) {
        this.salaireBase = salaire_base;
    }

    public void setDateFinContrat(Date dateFinContrat) {
        this.dateFinContrat = dateFinContrat;
    }
    public void setDateFinContrat(String dt){
        this.dateFinContrat = Date.valueOf(dt);
    }

    public void setCandidat(Connection con, int candidat)throws SQLException {
        Candidat c = new Candidat();
        this.candidat = c.getById(con,candidat);
    }

    public void setTypeContrat(int typeContrat)  throws SQLException{
        this.typeContrat = TypeContrat.getById(typeContrat);
    }
}
