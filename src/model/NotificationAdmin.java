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

public class NotificationAdmin {
    private int idNotification; 
    private String contenuNotification;
    private Timestamp dateNotification;
    private Timestamp dateVueNotification;
    private String targetLink;

    // CONSTRUCTORS
    public NotificationAdmin(){

    }

    // ACTIONS

    public static List<NotificationAdmin> getAllNotVueByAdmin() throws SQLException{
        List<NotificationAdmin> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM notification_admin WHERE date_vue_notification IS NULL ORDER BY date_notification DESC";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                NotificationAdmin d = new NotificationAdmin();
                d.setIdNotification(rs.getInt(1));
                d.setContenuNotification(rs.getString(2));
                d.setDateNotification(rs.getTimestamp(3));
                d.setDateVueNotification(rs.getTimestamp(4));
                d.setTargetLink(rs.getString(5));
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

    public static NotificationAdmin getById(int id_notif) throws SQLException{
        NotificationAdmin result = null;
        Connection c = null;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM notification_admin WHERE id_notification = ?";
        try {
            c = Database.getConnection();
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id_notif);
            rs = prstm.executeQuery();

            if (rs.next()) {
                result = new NotificationAdmin();
                result.setIdNotification(rs.getInt(1));
                result.setContenuNotification(rs.getString(2));
                result.setDateNotification(rs.getTimestamp(3));
                result.setDateVueNotification(rs.getTimestamp(4));
                result.setTargetLink(rs.getString(5));
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
        String sql = "INSERT INTO notification_admin(contenu_notification,date_notification,target_link) VALUES(?, ?, ?)";
        try {
            c = Database.getConnection();
            c.setAutoCommit(false);
            prstm = c.prepareStatement(sql);
            prstm.setString(1, this.getContenuNotification());
            prstm.setTimestamp(2, Timestamp.valueOf(LocalDateTime.now()));
            prstm.setString(3, this.getTargetLink());
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
        String sql = "UPDATE notification_admin SET date_vue_notification = ? WHERE id_notification = ?";
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
    public void setTargetLink(String str){
        this.targetLink = str;
    }
}
