package project.servlets.patients_only;

import project.hospitalcard.HospitalCard;
import project.methods.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/patients_only/newbee_again")
public class NewbeeAgain extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String status =(String) req.getSession().getAttribute("status_patient");
        int id = (int) req.getSession().getAttribute("id");

        if (status.equals("discharged")){
            dbManager.updateHospitalCardStatus("newbee", id);
            req.setAttribute("messtatus", req.getSession().getAttribute("langStatusUpdated"));
            req.getSession().setAttribute("status_patient","newbee");

        }

        req.getRequestDispatcher("/patients_only/my_appointments.jsp").forward(req, resp);
    }
}
