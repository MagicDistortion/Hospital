package project.servlets.patients_only;

import project.appointments.AppointmentDetails;
import project.hospitalcard.HospitalCard;
import project.methods.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/patients_only/my_hospitalcard")
public class MyHospitalCard extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute("id");
        HospitalCard hospitalCard = dbManager.getHospitalCard(id);

        req.getSession().setAttribute("status_patient", hospitalCard.getStatus());
        if (hospitalCard.getDiagnosis() != null) {
            req.getSession().setAttribute("diagnosis", hospitalCard.getDiagnosis());
        } else req.getSession().setAttribute("diagnosis", req.getSession().getAttribute("langNotExistYet"));

        if (hospitalCard.getCurrentDoctorName() != null && hospitalCard.getCurrentDoctorSurname() != null) {
            req.getSession().setAttribute("current_doctorSurname", hospitalCard.getCurrentDoctorSurname());
            req.getSession().setAttribute("current_doctorName", hospitalCard.getCurrentDoctorName());
        } else {
            req.getSession().setAttribute("current_doctorSurname", req.getSession().getAttribute("langNotAssigned"));
            req.getSession().setAttribute("current_doctorName", req.getSession().getAttribute("langNotAssigned"));
        }

        req.setAttribute("myhospitalcard", hospitalCard);
        req.getRequestDispatcher("/patients_only/my_appointments.jsp").forward(req, resp);
    }
}
