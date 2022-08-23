package project.servlets.doctors_only;

import project.methods.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/doctors_only/update_status")
public class UpdatePatientStatus extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = (int) req.getSession().getAttribute("id_card");
        String status = req.getParameter("status");
        dbManager.updateHospitalCardStatus(status, id);
        req.getSession().setAttribute("status_patient",status);
        req.setAttribute("messtatus", "status updated");

        req.getRequestDispatcher("/doctors_only/edit_hospital_cards.jsp").forward(req, resp);
    }
}
