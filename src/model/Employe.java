package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class Employe {
    
    private int idEmploye;
    private Candidat candidat;
    private Poste poste;
    private Date dateEmbauche;

    // CONSTRUCTORS
    public Employe(){

    }


    // GETTERS AND SETTERS
    public void setIdEmploye(int idEmploye) {
        this.idEmploye = idEmploye;
    }
    public void setCandidat(Connection con, int candidat) throws SQLException{
        Candidat c = new Candidat();
        c.setIdCandidat(candidat);
        this.candidat = c.getById(con);
    }
    public void setPoste(int poste)throws SQLException {
        this.poste = Poste.getById(poste);
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
                employe.setPoste(poste.getIdPoste());
            }
        }
        return employe;
    }



    // public Employe definirEmploye(int idCandidat, int idPoste, Connection connection) throws SQLException {
    //     Employe employe = new Employe();
    //     Candidat candidat = null;
    //     Poste poste = null;
        
    //     // Récupérer les informations du Candidat
    //     String queryCandidat = "SELECT * FROM Candidat WHERE id_candidat = ?";
    //     try (PreparedStatement stmt = connection.prepareStatement(queryCandidat)) {
    //         stmt.setInt(1, idCandidat);
    //         ResultSet rsCandidat = stmt.executeQuery();
    //         if (rsCandidat.next()) {
    //             candidat = new Candidat();
    //             candidat.setIdCandidat(rsCandidat.getInt("id_candidat"));
    //             candidat.setNom(rsCandidat.getString("nom"));
    //             candidat.setPrenom(rsCandidat.getString("prenom"));
    //             candidat.setDateNaissance(rsCandidat.getDate("date_naissance"));
    //             candidat.setAdresse(rsCandidat.getString("adresse"));
    //             candidat.setIdGenre(rsCandidat.getInt("id_genre"));
    //             candidat.setEmail(rsCandidat.getString("email"));
    //             candidat.setMdp(rsCandidat.getString("mdp"));
    //         }
    //     }
    
    //     // Récupérer le Poste de l'employé
    //     String queryPoste = "SELECT * FROM Poste WHERE id_poste = ?";
    //     try (PreparedStatement stmt = connection.prepareStatement(queryPoste)) {
    //         stmt.setInt(1, idPoste);
    //         ResultSet rsPoste = stmt.executeQuery();
    //         if (rsPoste.next()) {
    //             poste = new Poste();
    //             poste.setIdPoste(rsPoste.getInt("id_poste"));
    //             poste.setLibellePoste(rsPoste.getString("libelle_poste"));
    //         }
    //     }
    
    //     // Créer un employé en fonction du Candidat et du Poste
    //     if (candidat != null && poste != null) {
    //         employe.setCandidat(candidat);
    //         employe.setPoste(poste);
    //         employe.setDateEmbauche(new Date(System.currentTimeMillis()));  // Par exemple, date actuelle
    //     }
    
    //     return employe;
    // }
    

    // public static Employe login(String email, String password) throws SQLException{
    //     Employe result = null; 
    //     Connection c = null; 
    //     PreparedStatement prstm = null;
    //     ResultSet rs = null; 
    //     String query = "SELECT * FROM Employe WHERE email = ? AND mdp = ? ";
    //     try {
    //         c = Database.getConnection();
    //         prstm = c.prepareStatement(query);
    //         prstm.setString(1, email);
    //         prstm.setString(2, password);

    //         rs = prstm.executeQuery();

    //         if(rs.next()){
    //             result = new Employe();
    //             result.setIdCandidat(rs.getInt(1));
    //             result.setNomCandidat(rs.getString(2));
    //             result.setPrenomCandidat(rs.getString(3));
    //             result.setDateNaissance(rs.getDate(4));
    //             result.setAdresse(rs.getString(5));
    //             result.setGenre(rs.getInt(6));
    //             result.getCompetences(c);
    //             result.getExperiences(c);
    //             result.getEducations(c);
    //         }
    //         return result;
    //     } catch (SQLException e) {
    //         throw e;
    //     }finally{
    //         if(rs != null){
    //             rs.close();
    //         }
    //         if(prstm != null){
    //             prstm.close();
    //         }
    //         if(c != null){
    //             c.close();
    //         }
    //     }
    // }
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
                e.setPoste(rs.getInt(3));
                e.setDateEmbauche(rs.getDate(4));
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
        
}