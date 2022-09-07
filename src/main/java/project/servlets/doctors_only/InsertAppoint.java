package project.servlets.doctors_only;

import project.models.appointments.AppointmentDetails;
import project.dao.AppointmentDetailsDAO;
import project.models.users.User;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/doctors_only/insert_appoint")
public class InsertAppoint extends HttpServlet {
    private final AppointmentDetailsDAO appointmentDetailsDAO = new AppointmentDetailsDAO();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate date = LocalDate.parse(req.getParameter("date"));
        if (date.isAfter(LocalDate.now()) || date.equals(LocalDate.now())) {
            AppointmentDetails appointmentDetails = new AppointmentDetails(req.getParameter("text"), date);
            appointmentDetails.setAppointmentId(Integer.parseInt(req.getParameter("appoint")));
            appointmentDetails.setNurseId(Integer.parseInt(req.getParameter("nurse")));
            appointmentDetails.setDoctorsId(((User)req.getSession().getAttribute("user")).getId());
            appointmentDetails.setHospitalCardId(Integer.parseInt(req.getParameter("id")));
            appointmentDetailsDAO.insertAppointmentDetails(appointmentDetails);
            req.setAttribute("message", "appoint added");
        } else req.setAttribute("message", "wrong date");

        req.getRequestDispatcher("/doctors_only/edit_hospital_cards.jsp").forward(req, resp);
    }
}
