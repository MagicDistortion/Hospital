package project.servlets.doctors_only;

import project.appointments.Appointment;
import project.appointments.AppointmentDetails;
import project.methods.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


@WebServlet("/doctors_only/insert_appoint")
public class InsertAppoint extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDate date = LocalDate.parse(req.getParameter("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        if (date.isAfter(LocalDate.now()) || date.equals(LocalDate.now())) {
            AppointmentDetails appointmentDetails = new AppointmentDetails(req.getParameter("text"), date);
            appointmentDetails.setAppointmentId(Integer.parseInt(req.getParameter("appoint")));
            appointmentDetails.setNurseId(Integer.parseInt(req.getParameter("nurse")));
            appointmentDetails.setDoctorsId((int) req.getSession().getAttribute("id"));
            appointmentDetails.setHospitalCardId(Integer.parseInt(req.getParameter("id")));
            dbManager.insertAppointmentDetails(appointmentDetails);
            req.setAttribute("message", "appoint added");
        } else req.setAttribute("message", "wrong date");

        req.getRequestDispatcher("/doctors_only/edit_hospital_cards.jsp").forward(req, resp);


    }
}
