package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.Personne;
import model.Poste;

public class AjoutCvServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            List<Poste> ls = Poste.getAll();

            request.setAttribute("postes", ls);
            RequestDispatcher dispat = request.getRequestDispatcher("/WEB-INF/views/formulairePersonne.jsp");
            dispat.forward(request, response);
        } catch (Exception e) {

            e.printStackTrace(out);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenoms");
        String dateNaissance = request.getParameter("dtn");
        String addresse = request.getParameter("adress");
        double experience = Double.parseDouble(request.getParameter("xp"));
        String genre = request.getParameter("genre");
        String poste = request.getParameter("idPoste");

        try {
            Date dtn = Date.valueOf(dateNaissance);
            Personne p = new Personne();
            p.setNom(nom);
            p.setPrenom(prenom);
            p.setDateNaissance(dtn);
            p.setAdresse(addresse);
            p.setGenre(genre);
            p.setExperience(experience);
            p.setPoste(Integer.parseInt(poste));

            Personne inserted = p.insert();

            request.setAttribute("personne", inserted);
            request.getRequestDispatcher("/CompetenceServlet").forward(request, response);
            ;
        } catch (SQLException e) {
            out.println(e.getMessage());
        }
    }

}
