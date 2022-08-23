package project.servlets.doctors_only;


import project.appointments.Appointment;
import project.hospitalcard.HospitalCard;
import project.methods.DBManager;
import project.users.Nurse;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;


@WebServlet("/doctors_only/edit_hospital_cards")
public class EditHospitalCard extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Appointment> allAppointments = dbManager.findAllAppointments();
        req.getSession().setAttribute("appoints", allAppointments);

        List<Nurse> allNurse = dbManager.findAllNurse();
        req.getSession().setAttribute("nurses", allNurse);

        req.getSession().setAttribute("today", LocalDate.now());

        int patientId = Integer.parseInt(req.getParameter("id"));
        HospitalCard hospitalCard = dbManager.getHospitalCard(patientId);

        req.getSession().setAttribute("back", req.getParameter("back"));
        req.getSession().setAttribute("patient_surname", hospitalCard.getPatientsSurname());
        req.getSession().setAttribute("patient_name", hospitalCard.getPatientsName());
        req.getSession().setAttribute("create_time", hospitalCard.getCreateTime());
        req.getSession().setAttribute("id_card", hospitalCard.getId());
        req.getSession().setAttribute("status_patient", hospitalCard.getStatus());

        if (hospitalCard.getDiagnosis() != null) {
            req.getSession().setAttribute("diagnosis", hospitalCard.getDiagnosis());
        } else req.getSession().setAttribute("diagnosis", "not exist yet");

        if (hospitalCard.getCurrentDoctorName() != null && hospitalCard.getCurrentDoctorSurname() != null) {
            req.getSession().setAttribute("current_doctorSurname", hospitalCard.getCurrentDoctorSurname());
            req.getSession().setAttribute("current_doctorName", hospitalCard.getCurrentDoctorName());
        } else {
            req.getSession().setAttribute("current_doctorSurname", "not assigned");
            req.getSession().setAttribute("current_doctorName", "not assigned");
        }
        req.getRequestDispatcher("/doctors_only/edit_hospital_cards.jsp").forward(req, resp);
    }
}



