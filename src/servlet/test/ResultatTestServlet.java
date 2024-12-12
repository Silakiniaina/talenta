package servlet.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.ResultatTestCandidat;

@WebServlet("/resultatTest")
public class ResultatTestServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       PrintWriter out = resp.getWriter();
       String idRecrutementStr = req.getParameter("idRecrutement");

       try {
        Connection c = (Connection)req.getSession().getAttribute("connexion");
         
        List<ResultatTestCandidat> ls = ResultatTestCandidat.getResultatByRecrutement(c, Integer.parseInt(idRecrutementStr));

        req.setAttribute("resultats", ls);
        req.setAttribute("idRecrutement", idRecrutementStr);
        RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/test/resultatTest.jsp");
        disp.forward(req, resp);
       } catch (Exception e) {
        e.printStackTrace(out);
       }
    }
}
