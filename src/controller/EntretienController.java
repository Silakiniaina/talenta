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
import model.Competence;
import model.Personne;
import model.SoftSkill;

public class EntretienController extends HttpServlet{

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
       try {
        String idPersonne = req.getParameter("idCandidat");
        String[] skills = req.getParameterValues("skills");
        Personne p = Personne.getById(Integer.parseInt(idPersonne));
        for(int i=0; i<skills.length; i++){
            SoftSkill s = SoftSkill.getById(Integer.parseInt(skills[i]));
            p.getSoftSkills().add(s);
        }
        p.insertSoftSkill();
        } catch (SQLException e) {
            e.printStackTrace(out);
       }
   }

   @Override
   protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out= resp.getWriter();
        try {
            List<Personne> pers= Personne.getAll();
            List<SoftSkill> skills= SoftSkill.getAll();

            RequestDispatcher dispat = req.getRequestDispatcher("/WEB-INF/views/entretien.jsp");
            req.setAttribute("candidat", pers);
            req.setAttribute("skills", skills);
            dispat.forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace(out);
        }
   }
}