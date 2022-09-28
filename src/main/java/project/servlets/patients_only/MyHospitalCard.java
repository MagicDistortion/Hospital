package project.servlets.patients_only;

import project.dao.HospitalCardDAO;
import project.models.hospitalcard.HospitalCard;
import project.models.users.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/patients_only/my_hospitalcard")
public class MyHospitalCard extends HttpServlet {
    private final HospitalCardDAO hospitalCardDAO= new HospitalCardDAO();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = ((User) req.getSession().getAttribute("user")).getId();
        HospitalCard hospitalCard = hospitalCardDAO.getHospitalCard(id);
        req.getSession().setAttribute("myhospitalcard", hospitalCard);
        if (hospitalCard.getCurrentDoctorName() == null && hospitalCard.getCurrentDoctorSurname() == null) {
            hospitalCard.setCurrentDoctorSurname(((Map<?, ?>) req.getAttribute("phrases")).get("langNotAssigned").toString());
            hospitalCard.setCurrentDoctorName(((Map<?, ?>) req.getAttribute("phrases")).get("langNotAssigned").toString());
        }
        req.getRequestDispatcher("/patients_only/my_appointments.jsp").forward(req, resp);
    }
}
