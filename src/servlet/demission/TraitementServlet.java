package servlet.demission;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.DemandeDemission;


@WebServlet("/demission-traitement")
public class TraitementServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out= response.getWriter();
        Gson gson= new Gson();

        String id= request.getParameter("demandeId");
        int idDemande= Integer.parseInt(id);
        String demande= (String) request.getParameter("etat");
        out.println(demande);
        try {
            DemandeDemission.updateEtat(idDemande, demande);
            request.getRequestDispatcher("demission-liste").forward(request, response);
        } 
        catch (SQLException e) {
            out.println("Problem: "+ e.getStackTrace());
            e.printStackTrace();
        }
    }
}
