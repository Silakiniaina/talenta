package servlet.recrutement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.ResultatEvalution;

public class ResultatTestServlet extends HttpServlet{
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       PrintWriter out = resp.getWriter();
       String idRecrutement = req.getParameter("idRecrutement");
       try {
         List<ResultatEvalution> resultat = null;

         req.setAttribute("resultats", resultat);
         req.setAttribute("idRecrutement", idRecrutement);
         RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/admin/evaluation/resultatTest.jsp");
         disp.forward(req,resp);
       } catch (Exception e) {
        e.printStackTrace(out);
       }
    }
}
