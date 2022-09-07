package project.servlets.doctors_only;

import project.dao.HospitalCardDAO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/doctors_only/update_diagnosis")
public class UpdateDiagnosis extends HttpServlet {
    private final HospitalCardDAO hospitalCardDAO = new HospitalCardDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String diagnosis = req.getParameter("diagnosis");
        if (!diagnosis.isEmpty()) {
            hospitalCardDAO.updateDiagnosis(diagnosis, id);
            req.getSession().setAttribute("diagnosis", diagnosis);
        }
        resp.sendRedirect("edit_hospital_cards.jsp");

    }
}



