package service;

import java.util.List;

import model.InformationEmploye;
import model.dao.EmployeDAO;

public class EmployeService {

    private EmployeDAO employeDAO;

    public EmployeService() {
        employeDAO = new EmployeDAO();
    }

    public List<InformationEmploye> getInformationsEmployeAvecConges() {
        return employeDAO.getInformationsEmployeAvecConges();
    }

    public InformationEmploye getEmployeById(int idEmploye) {
        return employeDAO.getEmployeById(idEmploye);
    }
}
