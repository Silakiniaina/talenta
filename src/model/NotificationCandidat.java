package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class NotificationCandidat {
    
    private int idNotification; 
    private String contenuNotification;
    private Timestamp dateNotification;
    private Timestamp dateVueNotification;
    private Candidat candidat;
    private String targetLink;

    // CONSTRUCTORS
    public NotificationCandidat(){

    }

    // ACTIONS
    public static List<NotificationCandidat> getAllByCandidat(int idCandidat) throws SQLException{
        List<NotificationCandidat> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM notification_candidat WHERE id_candidat = ? ORDER BY date_notification DESC";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setInt(1, idCandidat);
            rs = prstm.executeQuery();

            while (rs.next()) {
                NotificationCandidat d = new NotificationCandidat();
                d.setIdNotification(rs.getInt(1));
                d.setCandidat(rs.getInt(2));
                d.setContenuNotification(rs.getString(3));
                d.setDateNotification(rs.getTimestamp(4));
                d.setDateVueNotification(rs.getTimestamp(5));
                d.setTargetLink(rs.getString(6));
                result.add(d);
            }
        } catch (SQLException e) {
            throw e;
        } finally{
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

    public static List<NotificationCandidat> getAllNotVueByCandidat(int idCandidat) throws SQLException{
        List<NotificationCandidat> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM notification_candidat WHERE id_candidat = ? AND date_vue_notification IS NULL ORDER BY date_notification DESC";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setInt(1, idCandidat);
            rs = prstm.executeQuery();

            while (rs.next()) {
                NotificationCandidat d = new NotificationCandidat();
                d.setIdNotification(rs.getInt(1));
                d.setCandidat(rs.getInt(2));
                d.setContenuNotification(rs.getString(3));
                d.setDateNotification(rs.getTimestamp(4));
                d.setDateVueNotification(rs.getTimestamp(5));
                d.setTargetLink(rs.getString(6));
                result.add(d);
            }
        } catch (SQLException e) {
            throw e;
        } finally{
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

    public static NotificationCandidat getById(int id_notif) throws SQLException{
        NotificationCandidat result = null;
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM notification_candidat WHERE id_notification = ?";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id_notif);
            rs = prstm.executeQuery();

            if (rs.next()) {
                result = new NotificationCandidat();
                result.setIdNotification(rs.getInt(1));
                result.setCandidat(rs.getInt(2));
                result.setContenuNotification(rs.getString(3));
                result.setDateNotification(rs.getTimestamp(4));
                result.setDateVueNotification(rs.getTimestamp(5));
                result.setTargetLink(rs.getString(6));
            }
        } catch (SQLException e) {
            throw e;
        } finally{
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

    public void insert() throws SQLException{
        Connection c = null;
        PreparedStatement prstm = null; 
        String sql = "INSERT INTO notification_candidat(id_candidat,contenu_notification,date_notification,target_link) VALUES(?, ?, ?, ?)";
        try {
            c = Database.getConnection();
            c.setAutoCommit(false);
            prstm = c.prepareStatement(sql);
            prstm.setInt(1, this.getCandidat().getIdCandidat());
            prstm.setString(2, this.getContenuNotification());
            prstm.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            prstm.setString(4, this.getTargetLink());
            prstm.executeUpdate();

            c.commit();
        } catch (Exception e) {
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

    public void updateDateVueNotification()throws SQLException{
        Connection c = null;
        PreparedStatement prstm = null; 
        String sql = "UPDATE notification_candidat SET date_vue_notification = ? WHERE id_notification = ?";
        try {
            c = Database.getConnection();
            c.setAutoCommit(false);
            prstm = c.prepareStatement(sql);
            prstm.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            prstm.setInt(2, this.getIdNotification());
            prstm.executeUpdate();

            c.commit();
        } catch (Exception e) {
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
    
    // GETTERS AND SETTERS
    public int getIdNotification() {
        return idNotification;
    }
    public String getContenuNotification() {
        return contenuNotification;
    }
    public Timestamp getDateNotification() {
        return dateNotification;
    }
    public Timestamp getDateVueNotification() {
        return dateVueNotification;
    }
    public Candidat getCandidat() {
        return candidat;
    }
    public String getTargetLink(){
        return this.targetLink;
    }

    public void setIdNotification(int idNotification) {
        this.idNotification = idNotification;
    }
    public void setContenuNotification(String contenuNotification) {
        this.contenuNotification = contenuNotification;
    }
    public void setDateNotification(Timestamp dateNotification) {
        this.dateNotification = dateNotification;
    }
    public void setDateVueNotification(Timestamp dateVueNotification) {
        this.dateVueNotification = dateVueNotification;
    }
    public void setCandidat(int candidat) throws SQLException{
        this.candidat = Candidat.getById(candidat);
    }
    public void setTargetLink(String str){
        this.targetLink = str;
    }
}
