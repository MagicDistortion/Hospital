package project.servlets.patients_only;

import project.hospitalcard.HospitalCard;
import project.controller.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/patients_only/my_hospitalcard")
public class MyHospitalCard extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute("id");
        HospitalCard hospitalCard = dbManager.getHospitalCard(id);
        req.setAttribute("myhospitalcard", hospitalCard);
        if (hospitalCard.getCurrentDoctorName() == null && hospitalCard.getCurrentDoctorSurname() == null) {
            hospitalCard.setCurrentDoctorSurname(req.getSession().getAttribute("langNotAssigned").toString());
            hospitalCard.setCurrentDoctorName(req.getSession().getAttribute("langNotAssigned").toString());
        }
            req.getRequestDispatcher("/patients_only/my_appointments.jsp").forward(req, resp);
    }
}
