package project.servlets.doctors_only;


import project.hospitalcard.HospitalCard;
import project.methods.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/doctors_only/update_diagnosis")
public class UpdateDiagnosis extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String diagnosis = req.getParameter("diagnosis");
        if (!diagnosis.isEmpty()) {
            dbManager.updateDiagnos(diagnosis, id);
            req.getSession().setAttribute("diagnosis",diagnosis);
        }
        req.getRequestDispatcher("/doctors_only/edit_hospital_cards.jsp").forward(req, resp);
    }
}



