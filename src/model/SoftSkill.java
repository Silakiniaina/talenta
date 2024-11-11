package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SoftSkill {

    int id;
    String nom;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public static SoftSkill getById(int id) throws SQLException {
        Connection connex = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        SoftSkill skill = null;

        try {
            connex = Database.getConnection();

            String sql = "SELECT* FROM softskill WHERE id=?";
            st = connex.prepareStatement(sql);
            st.setInt(1, id); 
            rs = st.executeQuery();

            if (rs.next()) {
                skill = new SoftSkill();
                skill.setId(rs.getInt("id"));
                skill.setNom(rs.getString("nom"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (connex != null) connex.close();
        }

        return skill;
    }

    public static List<SoftSkill> getAll() throws SQLException {
        Connection connex = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<SoftSkill> result = null;

        try {
            connex = Database.getConnection();

            String sql = "SELECT* FROM softskill";
            st = connex.prepareStatement(sql); 
            rs = st.executeQuery();

            while (rs.next()) {
                SoftSkill s  = new SoftSkill();
                s.setId(rs.getInt("id"));
                s.setNom(rs.getString("nom"));
                result.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (connex != null) connex.close();
        }

        return result;
    }
}
