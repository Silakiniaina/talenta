package servlet.candidat;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import model.NotificationCandidat;

@WebServlet("/notifCandidat")
public class NotificationServlet extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String notification = req.getParameter("notification");
        if(notification != null){
            req.setAttribute("notification", notification);
            doPost(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String notif = (String)req.getAttribute("notification");
        try {
            NotificationCandidat notification = NotificationCandidat.getById(Integer.parseInt(notif));
            notification.updateDateVueNotification();
            resp.sendRedirect(notification.getTargetLink());
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }
    
}
