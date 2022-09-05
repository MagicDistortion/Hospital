package project.servlets.doctors_only;

import project.appointments.Appointment;
import project.controller.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


@WebServlet("/doctors_only/add_appointment")
public class AddAppoinment extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        if (dbManager.findAppointmentByName(name) == null) {
            dbManager.insertAppointment(new Appointment(name));
            req.setAttribute("mes",((Map<?, ?>)req.getAttribute("phrases")).get("langSuccessfulAdd"));
        } else req.setAttribute("mes",((Map<?, ?>)req.getAttribute("phrases")).get("langAllReadyExist"));
        req.getRequestDispatcher("/doctors_only/add_appointment.jsp").forward(req, resp);
    }
}
