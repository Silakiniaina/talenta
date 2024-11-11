
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Poste;

public class SelectionServlet extends HttpServlet {

   protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out= response.getWriter();
        try {
            List<Poste> listePoste= Poste.getAll();
            RequestDispatcher dispat = request.getRequestDispatcher("/WEB-INF/views/listeSelection.jsp");
            request.setAttribute("listePoste", listePoste);
            dispat.forward(request, response);
            // out.println(liste.get(0).getEtat());
        } catch (SQLException e) {
           
            e.printStackTrace();
        }
        
    }

}
