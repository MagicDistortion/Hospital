package project.servlets.patients_only;

import project.hospitalcard.HospitalCard;
import project.controller.DBManager;
import project.users.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet("/patients_only/my_hospitalcard")
public class MyHospitalCard extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = ((User) req.getSession().getAttribute("user")).getId();
        HospitalCard hospitalCard = dbManager.getHospitalCard(id);
        req.setAttribute("myhospitalcard", hospitalCard);
        req.getSession().setAttribute("status_patient", hospitalCard.getStatus());
        if (hospitalCard.getCurrentDoctorName() == null && hospitalCard.getCurrentDoctorSurname() == null) {
            hospitalCard.setCurrentDoctorSurname(((Map<?, ?>) req.getAttribute("phrases")).get("langNotAssigned").toString());
            hospitalCard.setCurrentDoctorName(((Map<?, ?>) req.getAttribute("phrases")).get("langNotAssigned").toString());
        }
        req.getRequestDispatcher("/patients_only/my_appointments.jsp").forward(req, resp);
    }
}
