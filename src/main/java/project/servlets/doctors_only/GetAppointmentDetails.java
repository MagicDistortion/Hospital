package project.servlets.doctors_only;

import project.models.appointments.AppointmentDetails;
import project.dao.AppointmentDetailsDAO;
import project.models.hospitalcard.HospitalCard;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/doctors_only/get_appointments")
public class GetAppointmentDetails extends HttpServlet {
    private final AppointmentDetailsDAO appointmentDetailsDAO = new AppointmentDetailsDAO();
    int id;
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<AppointmentDetails> appointmentsDetails = new ArrayList<>();
        String type = "actual";
        if (req.getSession().getAttribute(("hospital_card")) != null)
            id = ((HospitalCard) req.getSession().getAttribute(("hospital_card"))).getId();
        if (req.getParameter("type") != null) type = req.getParameter("type");
        req.setAttribute("type", type);
        switch (type) {
            case "actual":
                appointmentsDetails = appointmentDetailsDAO.findAllAppointmentDetailsByID(id);
                break;
            case "overdue":
                appointmentsDetails = appointmentDetailsDAO.findOverdueAppointmentsByID(id);
                break;
            case "done":
                appointmentsDetails = appointmentDetailsDAO.findDoneAppointmentDetailsByID(id);
                break;
        }
        if (appointmentsDetails.size() == 0)
            req.setAttribute("mes", ((Map<?, ?>) req.getAttribute("phrases")).get("langEmpty"));
        req.setAttribute("appointments", appointmentsDetails);
        req.getRequestDispatcher("/doctors_only/edit_hospital_cards.jsp").forward(req, resp);
    }
}




