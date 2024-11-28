package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class TypeFinContrat {
    
    int id;
    String label;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }

    public static List<TypeFinContrat> getAll() throws SQLException {
        List<TypeFinContrat> result = new ArrayList<>();
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM type_fin_contrat";

        try {
            c = Database.getConnection();  
            prstm = c.prepareStatement(query);
            rs = prstm.executeQuery();

            while (rs.next()) {
                TypeFinContrat typeFinContrat = new TypeFinContrat();
                typeFinContrat.setId(rs.getInt("id_type_fin_contrat"));
                typeFinContrat.setLabel(rs.getString("label"));
                result.add(typeFinContrat);  
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

    public static TypeFinContrat getById(int id) throws SQLException {
        TypeFinContrat result = null;
        Connection c = null;
        PreparedStatement prstm = null;
        ResultSet rs = null;
        String query = "SELECT * FROM type_fin_contrat WHERE id_type_fin_contrat = ?";

        try {
            c = Database.getConnection();  
            prstm = c.prepareStatement(query);
            prstm.setInt(1, id);
            rs = prstm.executeQuery();

            if (rs.next()) {
                result = new TypeFinContrat();
                result.setId(rs.getInt("id_type_fin_contrat"));
                result.setLabel(rs.getString("label"));
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
