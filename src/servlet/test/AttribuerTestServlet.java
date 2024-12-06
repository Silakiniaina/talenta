package servlet.test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import com.google.gson.Gson;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.AttributionTest;
import model.NotificationCandidat;
import model.Recrutement;
import model.Test;

@WebServlet("/attribuerTest")
public class AttribuerTestServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String idCandidatStr = req.getParameter("idCandidat");
        String idTestStr = req.getParameter("idTest");
        String idRecrutementStr = req.getParameter("recrutement");

        try {
            Connection connexion = (Connection)req.getSession().getAttribute("connexion");

            AttributionTest attribution = new AttributionTest();
            attribution.setCandidat(connexion, Integer.parseInt(idCandidatStr));
            attribution.setTest(connexion, Integer.parseInt(idTestStr));
            attribution.setStatus(connexion, 1);
            attribution.setRecrutement(connexion, Integer.parseInt(idRecrutementStr));

            attribution.insert(connexion);

            NotificationCandidat notification = new NotificationCandidat();
            notification.setCandidat(connexion, Integer.parseInt(idCandidatStr));
            notification.setDateNotification(Timestamp.valueOf(LocalDateTime.now()));
            notification.setContenuNotification("Le responsable vous invite a passer le test ");
            notification.setTargetLink("passerTest?test="+idTestStr);
            notification.insert();

            resp.sendRedirect("recrutement");
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       PrintWriter out  = resp.getWriter();
       String idCandidatStr = req.getParameter("idCandidat");
       String idRecrutementStr = req.getParameter("recrutement");

       try {
        Connection connexion = (Connection)req.getSession().getAttribute("connexion");
        Test t = new Test();
        List<Test> ls = Test.getAll(connexion);

        req.setAttribute("tests", ls);
        req.setAttribute("idCandidat", idCandidatStr);
        req.setAttribute("recrutement", idRecrutementStr);
        RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/test/attributionTest.jsp");
        disp.forward(req, resp);
       } catch (Exception e) {
        e.printStackTrace(out);
       }
    }
    
}
