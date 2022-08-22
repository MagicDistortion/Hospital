package project.servlets.admins_only;

import project.methods.DBManager;
import project.users.Doctor;
import project.users.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admins_only/patients_sortlist")
public class PatientsList extends HttpServlet {
    DBManager dbManager = DBManager.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sort = req.getParameter("sort");
        req.setAttribute("sort", sort);
        switch (sort) {
            case "name":
                sort = "ORDER BY name";
                break;
            case "date":
                sort = "ORDER BY date_of_birth";
        }
        List<Patient> patients = dbManager.findAllPatients(sort);
        req.setAttribute("patients", patients);

        int pagination;
        if (Integer.parseInt(req.getParameter("pagination")) <= 0) {
            pagination = 5;
        } else
            pagination = Integer.parseInt(req.getParameter("pagination"));
        req.setAttribute("pagination", pagination);

        int pages = patients.size() / pagination;
        if (patients.size() % pagination != 0) pages += 1;
        req.setAttribute("pages", pages);

        int page = 1;
        if (req.getParameter("page") != null)
            page = Integer.parseInt(req.getParameter("page"));

        List<Patient> patientList = dbManager.findPatientsWithLimit(sort, page, pagination);
        req.setAttribute("patientlist", patientList);
        if (patientList.size()==0)req.setAttribute("mes","empty");
        req.getRequestDispatcher("/admins_only/patients.jsp").forward(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sort = req.getParameter("sort");
        if (sort == null) sort = "name";
        switch (sort) {
            case "name":
                sort = "WHERE current_doctor_id is null ORDER BY name";
                break;
            case "date":
                sort = "WHERE current_doctor_id is null ORDER BY date_of_birth";
        }
        List<Patient> patients = dbManager.findAllPatients(sort);
        List<Doctor> doctors = dbManager.findAllDoctores("Order by users.name");
        if (patients.size()==0)req.setAttribute("mes","empty");
        req.setAttribute("doctors", doctors);
        req.setAttribute("patients", patients);
        req.getRequestDispatcher("/admins_only/appoint_a_doctor.jsp").forward(req, resp);
    }
}
