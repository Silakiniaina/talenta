package model.employe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.utils.Database;

public class CSP {
    
    private int idCsp;
    private String codeCsp;
    private String descriptionCsp;

    public CSP getByEmploye(Connection c, int idEmp) throws SQLException{
        boolean isNewConnection = false;
        PreparedStatement prstm = null; 
        ResultSet rs = null;
        String query = "SELECT * FROM v_csp_employe WHERE id_employe = ?";
        try {
            if(c == null){
                isNewConnection = true;
                c = Database.getConnection();
            }
            prstm = c.prepareStatement(query);
            prstm.setInt(1, idEmp);
            rs = prstm.executeQuery();

            if (rs.next()) {
                this.setIdCsp(rs.getInt("id_csp"));
                this.setCodeCsp(rs.getString("code_csp"));
                this.setDescriptionCsp(rs.getString("description"));
            }

            return this;
        } catch (SQLException e) {
            throw e;
        }finally{
            if(rs != null){
                rs.close();
            }
            if(prstm != null){
                prstm.close();
            }
            if(c != null && isNewConnection){
                c.close();
            }
        }
    }


    // GETTERS AND SETTERS
    public int getIdCsp() {
        return idCsp;
    }
    public String getCodeCsp() {
        return codeCsp;
    }
    public String getDescriptionCsp() {
        return descriptionCsp;
    }
    public void setIdCsp(int idCsp) {
        this.idCsp = idCsp;
    }
    public void setCodeCsp(String codeCsp) {
        this.codeCsp = codeCsp;
    }
    public void setDescriptionCsp(String descriptionC) {
        this.descriptionCsp = descriptionC;
    };
}
