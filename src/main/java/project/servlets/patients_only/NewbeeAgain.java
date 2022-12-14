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

@WebServlet("/patients_only/newbee_again")
public class NewbeeAgain extends HttpServlet {
    private final HospitalCardDAO hospitalCardDAO = new HospitalCardDAO();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute(("myhospitalcard")) != null) {
            HospitalCard hospitalCard = (HospitalCard) req.getSession().getAttribute(("myhospitalcard"));
            String status = hospitalCard.getStatus();
            int id = ((User) req.getSession().getAttribute("user")).getId();
            if (status.equals("discharged")) {
                hospitalCardDAO.updateHospitalCardStatus("newbee", id);
                req.setAttribute("messtatus", ((Map<?, ?>) req.getAttribute("phrases")).get("langStatusUpdated"));
                hospitalCard.setStatus("newbee");
            }
        }
        req.getRequestDispatcher("/patients_only/my_appointments.jsp").forward(req, resp);
    }
}
