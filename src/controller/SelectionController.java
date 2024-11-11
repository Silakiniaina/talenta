
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Candidat;
import model.Personne;
import model.Poste;

public class SelectionController extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out= response.getWriter();
        List<Candidat> liste= null;
        int idPoste = Integer.parseInt(request.getParameter("idPoste")); 
        try {
                Poste b= Poste.getById(idPoste);
                liste= b.getCandidatSelection(); 
            
            Gson gson = new Gson();
            out.println(gson.toJson(liste));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
