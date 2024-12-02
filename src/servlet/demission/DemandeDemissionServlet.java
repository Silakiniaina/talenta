package servlet.demission;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import model.Candidat;
import model.DemandeDemission;
import model.Employe;
import model.FinContrat;
import model.NotificationCandidat;
import model.TypeFinContrat;
import model.utils.Database;

@WebServlet("/demission-add")
public class DemandeDemissionServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = null;
        PrintWriter out= resp.getWriter();
        HttpSession session = req.getSession(false);
        Candidat c= null;
        try {
            String motif = req.getParameter("motif");
            String depot= req.getParameter("depot");
            conn= Database.getConnection();

            if (session!=null){
                c= (Candidat) session.getAttribute("candidat");
            }

            //c= Candidat.getById(conn, 3);
            
            if (c!=null ) {

                Date preavis = Date.valueOf(LocalDate.now()); 

                DemandeDemission dmd= new DemandeDemission(c, preavis, motif);
                dmd.insert();
                resp.sendRedirect(req.getContextPath() + "/demission-notif?idCandidat="+ c.getIdCandidat() + "&date="+preavis);
            } else {
                // req.setAttribute("errorMessage", "Tous les champs sont requis.");
                // req.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(req, resp);
                out.println("Tsa hita ilay session");
            }
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
            out.println("Erreur SQL ou argument invalide: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }



}
