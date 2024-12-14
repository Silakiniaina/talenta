package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import model.employe.CSP;
import model.utils.Database;

public class Employe {
    
    private int idEmploye;
    private Candidat candidat;
    private Poste poste;
    private Date dateEmbauche;
    int age;
    private double salaireBase;
    private CSP csp;

    // CONSTRUCTORS


    public Employe(){

    }


    // GETTERS AND SETTERS
    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }
    public void setCandidat(Connection con, int candidat) throws SQLException{
        this.candidat = Candidat.getById(candidat, con);
    }
    public void setPoste(Connection c, int poste)throws SQLException {
        Poste p = new Poste();
        this.poste = p.getById(c, poste);
    }
    public void setDateEmbauche(Date dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }
    public int getIdEmploye() {
        return idEmploye;
    }
    public Candidat getCandidat() {
        return candidat;
    }
    public Poste getPoste() {
        return poste;
    }
    public Date getDateEmbauche() {
        return dateEmbauche;
    }
    public CSP getCsp() {
        return csp;
    }


    public void setCsp(CSP csp) {
        this.csp = csp;
    }

    public String getNomComplet(){
        return this.getCandidat().getPrenomCandidat()+" "+this.getCandidat().getNomCandidat();
    }
    public static Employe getEmployeFromCandidat(Candidat candidat) throws SQLException {
        Employe employe = null;
        String query = "SELECT * FROM Employe WHERE id_candidat = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, candidat.getIdCandidat());
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                employe = new Employe();
                employe.setIdEmploye(rs.getInt("id_employe"));
                employe.setDateEmbauche(rs.getDate("date_embauche"));
                employe.setCandidat(conn,candidat.getIdCandidat());
                Poste poste = new Poste();
                poste.setIdPoste(rs.getInt("id_poste"));
                employe.setPoste(conn, poste.getIdPoste());
                employe.setCsp(new CSP().getByEmploye(conn, employe.getIdEmploye()));
            }
        }
        return employe;
    }


     public boolean checkDroitCongé(Connection connection) {
        
        String query = "SELECT anciennete_jours FROM Vue_Anciennete WHERE id_employe = ?";
        
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, this.idEmploye);
            
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    long ancienneteJours = rs.getLong("anciennete_jours");
                    
                    return ancienneteJours > 365;
                } else {
                    System.out.println("Employé non trouvé ou erreur dans la récupération de l'ancienneté.");
                    return false;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public int calculerCongesLeft(Connection connection) {
        int congéscumulésmax = 90;
        int congésPris = calculerCongesPris(connection);
        return congéscumulésmax - congésPris;
    }

    public int calculerCongesPris(Connection connection) {
        int congésPris = 0;
        String query = "SELECT date_debut, date_fin FROM Conges WHERE id_employe = ? AND EXTRACT(YEAR FROM date_debut) = EXTRACT(YEAR FROM CURRENT_DATE)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, this.idEmploye);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Date dateDebut = rs.getDate("date_debut");
                    Date dateFin = rs.getDate("date_fin");
                    long joursConges = ChronoUnit.DAYS.between(dateDebut.toLocalDate(), dateFin.toLocalDate()) + 1;
                    congésPris += joursConges;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return congésPris;
    }

    public static List<Employe> getAll() throws SQLException{
        List<Employe> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM Employe";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                Employe e = new Employe();
                e.setIdEmploye(rs.getInt(1));
                e.setCandidat(c,rs.getInt(2));
                e.setPoste(c, rs.getInt(3));
                e.setDateEmbauche(rs.getDate(4));
                e.setCsp(new CSP().getByEmploye(c, e.getIdEmploye()));
                result.add(e);
            }
        } catch (SQLException e) {
            throw e;
        }
        return result;
    }

    public static List<int[]> calculerCongesPourTousLesEmployes(Connection connection) {
        List<int[]> resultats = new ArrayList<>();
    
        try {
            List<Employe> employes = getAll();
            for (Employe employe : employes) {
                int congesRestants = employe.calculerCongesLeft(connection);
                int congesUtilises = 90 - congesRestants;
    
                int[] result = new int[3];
                result[0] = employe.getIdEmploye();
                result[1] = congesUtilises;        
                result[2] = congesRestants;         
                resultats.add(result);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return resultats;
    }
    
        

    public void setAge(int age){
        this.age= age;
    }
    public int getAge(){
        return this.age;
    }

    public static Employe getById(Connection con, int idEmploye) throws SQLException {
        Employe employe = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isNewConnection = false;

        String query = "SELECT * FROM employe WHERE id_employe = ?";
        try {
            if(con == null){
                con = Database.getConnection();
                isNewConnection = true;
            }
            ps = con.prepareStatement(query);
            ps.setInt(1, idEmploye);
            rs = ps.executeQuery();

            if (rs.next()) {
                employe = new Employe();
                employe.setIdEmploye(rs.getInt("id_employe"));
                employe.setCandidat(con, rs.getInt("id_candidat"));
                employe.setPoste(con, rs.getInt("id_poste"));
                employe.setDateEmbauche(rs.getDate("date_embauche"));
                employe.setSalaireBase(rs.getDouble("salaire_base"));
                employe.setCsp(new CSP().getByEmploye(con, employe.getIdEmploye()));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if  (con != null && isNewConnection) con.close();
        }
        return employe;
    }

    public static List<Employe> getAllReadyForRetraite(Connection con) throws SQLException {
        List<Employe> result = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        String query = "SELECT * FROM v_employes_age_retraite";
        
        try {
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Employe employe = new Employe();
                employe.setIdEmploye(rs.getInt("id_employe"));
                employe.setCandidat(con, rs.getInt("id_candidat"));
                employe.setPoste(con, rs.getInt("id_poste"));
                employe.setDateEmbauche(rs.getDate("date_embauche"));
                employe.setAge(rs.getInt("age"));
                employe.setCsp(new CSP().getByEmploye(con, employe.getIdEmploye()));

                result.add(employe);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }

        return result;
    }

    public static List<Employe> getAll(Connection con) throws SQLException {
        List<Employe> result = new ArrayList<>();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean isNewConnection = false;
        String query = "SELECT * FROM employe";
        
        try {
            if(con == null){
                con = Database.getConnection();
                isNewConnection = true;
            }
            ps = con.prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                Employe employe = new Employe();
                employe.setIdEmploye(rs.getInt("id_employe"));
                employe.setCandidat(con, rs.getInt("id_candidat"));
                employe.setPoste(con, rs.getInt("id_poste"));
                employe.setDateEmbauche(rs.getDate("date_embauche"));
                employe.setSalaireBase(rs.getDouble("salaire_base"));
                employe.setCsp(new CSP().getByEmploye(con, employe.getIdEmploye()));

                result.add(employe);
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (con != null && isNewConnection) con.close() ;
        }

        return result;
    }

    public static Employe getEmployeByIdCandidat(Connection con, int idCandidat) throws SQLException {
        Employe employe = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
    
        String query = "SELECT * FROM employe WHERE id_candidat = ?";
    
        try {
            ps = con.prepareStatement(query);
            ps.setInt(1, idCandidat); 
            rs = ps.executeQuery();
    
            if (rs.next()) {
                employe = new Employe();
                employe.setIdEmploye(rs.getInt("id_employe"));
                employe.setCandidat(con, rs.getInt("id_candidat"));
                employe.setPoste(con, rs.getInt("id_poste"));
                employe.setDateEmbauche(rs.getDate("date_embauche"));
                employe.setCsp(new CSP().getByEmploye(con, employe.getIdEmploye()));
            }
        } catch (SQLException e) {
            throw e;
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
        }
    
        return employe;
    }
    
    public double getSalaireBase() {
        return salaireBase;
    }
    
    
    public void setSalaireBase(double salaireBase) {
        this.salaireBase = salaireBase;
    }

}