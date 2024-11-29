package servlet.Employe.admin;

import service.EmployeService;
import model.InformationEmploye;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/admin/informations-employes")
public class InformationsEmployeServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    // Déclarer le service
    private EmployeService employeService;

    @Override
    public void init() throws ServletException {
        super.init();
        employeService = new EmployeService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            
            List<InformationEmploye> employes = employeService.getInformationsEmployeAvecConges();
            request.setAttribute("employes", employes);
            request.getRequestDispatcher("/WEB-INF/views/informationsEmployeView.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "Erreur lors de la récupération des informations des employés.");
            request.getRequestDispatcher("/WEB-INF/views/error.jsp").forward(request, response);
        }
    }
}
