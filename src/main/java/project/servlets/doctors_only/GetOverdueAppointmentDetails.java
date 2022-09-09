package project.servlets.doctors_only;

import project.dao.AppointmentDetailsDAO;
import project.models.appointments.AppointmentDetails;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@WebServlet("/doctors_only/get_overdue_appointments")
public class GetOverdueAppointmentDetails extends HttpServlet {
    private final AppointmentDetailsDAO appointmentDetailsDAO = new AppointmentDetailsDAO();
    int id;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("id_card") != null) id = (int) req.getSession().getAttribute("id_card");

        List<AppointmentDetails> appointmentsDetails = appointmentDetailsDAO.findOverdueAppointmentsByID(id);
        if (appointmentsDetails.size() == 0)
            req.setAttribute("ermes", ((Map<?, ?>) req.getAttribute("phrases")).get("langEmpty"));
        req.setAttribute("overdue_appointments", appointmentsDetails);
        req.getRequestDispatcher("/doctors_only/edit_hospital_cards.jsp").forward(req, resp);
    }
}




