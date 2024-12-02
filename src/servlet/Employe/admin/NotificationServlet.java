package servlet.Employe.admin;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Admin;
import model.Candidat;
import model.NotificationAdmin;
import model.NotificationCandidat;

public class NotificationServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String notification = req.getParameter("notification");
        if(notification != null){
            req.setAttribute("notification", notification);
            doPost(req, resp);
        }else{
            try {
                HttpSession session = req.getSession();
                Admin admin = (Admin)session.getAttribute("admin");
                List<NotificationAdmin> notifications = NotificationAdmin.getAllNotVueByAdmin(admin.getIdAdmin());
    
                req.setAttribute("notifications", notifications);
                RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/admin/notifications.jsp");
                disp.forward(req, resp);
            } catch (Exception e) {
                e.printStackTrace(out);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String notif = (String)req.getAttribute("notification");
        try {
            NotificationAdmin notification = NotificationAdmin.getById(Integer.parseInt(notif));
            notification.updateDateVueNotification();
            resp.sendRedirect(notification.getTargetLink());
        } catch (Exception e) {
            e.printStackTrace(out);
        }
    }    
}
