package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TypeCompetence {
    
    int id;
    String type;

    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static TypeCompetence getById(int id) throws SQLException {
        Connection connex = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        TypeCompetence typeCompetence = null;

        try {
            connex = Database.getConnection();  
            String sql = "SELECT * FROM TypeCompetence WHERE id = ?";
            st = connex.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                String type = rs.getString("type");

                typeCompetence = new TypeCompetence();
                typeCompetence.setId(id);
                typeCompetence.setType(type);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (connex != null) connex.close();
        }

        return typeCompetence;
    }
}
