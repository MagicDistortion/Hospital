package project.servlets.doctors_only;

import project.appointments.AppointmentDetails;
import project.methods.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/doctors_only/get_appointments")
public class GetAppoinmentDetails extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute("id_card");
        List<AppointmentDetails> appointmentsDetails = dbManager.findAllAppointmentDetailsByID(id);
        if (appointmentsDetails.size() == 0) req.setAttribute("mes", "empty");
        req.setAttribute("appointments", appointmentsDetails);
        req.getRequestDispatcher("/doctors_only/edit_hospital_cards.jsp").forward(req, resp);
    }
}




