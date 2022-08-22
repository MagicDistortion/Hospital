package project.servlets.doctors_only;

import project.appointments.Appointment;
import project.methods.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/doctors_only/add_appointment")
public class AddAppoinment extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if (dbManager.findAppointmentByName(name) == null) {
            dbManager.insertAppointment(new Appointment(name));
            req.setAttribute("mes", "appointment successfully added");
        }else req.setAttribute("mes", "appointment is already exist");
        req.getRequestDispatcher("/doctors_only/add_appointment.jsp").forward(req, resp);
    }
}
